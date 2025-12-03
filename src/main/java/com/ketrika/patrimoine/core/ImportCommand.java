package com.ketrika.patrimoine.core;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.StreamSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ketrika.patrimoine.models.assets.BankAccountAsset;
import com.ketrika.patrimoine.models.assets.FixedValuation;
import com.ketrika.patrimoine.models.assets.IAsset;
import com.ketrika.patrimoine.models.generals.Address;
import com.ketrika.patrimoine.models.generals.Birth;
import com.ketrika.patrimoine.models.generals.Contact;
import com.ketrika.patrimoine.models.generals.EGender;
import com.ketrika.patrimoine.models.generals.Person;
import com.ketrika.patrimoine.models.liabilities.ILiability;
import com.ketrika.patrimoine.models.participations.IParticipation;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

/**
 * Command to import assets from a JSON file.
 */
@Command(name = "import", description = "Import assets from a JSON file")
public class ImportCommand implements Callable<Integer> {
  private static final Logger LOGGER = LogManager.getLogger(ImportCommand.class);

  @Option(names = {"-f", "--file"}, description = "Path to CSV file to import", required = true)
  private File jsonFile;

  @Override
  public Integer call() throws Exception {
    if (!jsonFile.exists()) {
      LOGGER.error("File not found: {}", jsonFile.getAbsolutePath());
      return 1;
    }

    try {
      JsonNode root = new ObjectMapper().readTree(jsonFile);
      if (!root.isArray()) {
        LOGGER.error("JSON must be an array of Person objects.");
        return 1;
      }

      List<Person> persons = new CopyOnWriteArrayList<>();
      try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
        // Submit parsing tasks concurrently
        List<Future<Optional<Person>>> futures = StreamSupport
            .stream(root.spliterator(), false)
            .map(node -> executor.submit(() -> parsePerson(node)))
            .toList();
        // Collect results
        for (Future<Optional<Person>> future : futures) {
          try {
            future.get().ifPresent(persons::add);
          } catch (ExecutionException ee) {
            LOGGER.error("Failed to parse a person entry: {}", ee.getCause().getMessage());
          }
        }
      }
      LOGGER.trace("Successfully imported: {}", persons.size());
    } catch (JacksonException e) {
      LOGGER.error("File could not be read: {}", jsonFile.getAbsolutePath());
      return 1;
    }

    return 0;
  }

  private Optional<Person> parsePerson(JsonNode node) {
    String firstName = safeText(node, "firstname").orElse(null);
    String lastName = safeText(node, "lastname").orElse(null);
    EGender gender = switch (safeText(node, "gender").orElse("Unknown")) {
      case "F" -> EGender.FEMININE;
      case "M" -> EGender.MASCULINE;
      default -> EGender.OTHER;
    };

    if (firstName == null || lastName == null) {
      LOGGER.error("Skipping person: firstName and lastName are required");
      return Optional.empty();
    }

    var person = new Person(firstName, lastName, gender);

    Optional<Contact> contactOpt = parseContact(node.path("contact"));
    contactOpt.ifPresent(person::setContact);

    Optional<Address> addressOpt = parseAddress(node.path("address"));
    addressOpt.ifPresent(person::setAddress);

    Optional<Birth> birthOpt = parseBirth(node.path("birth"));
    birthOpt.ifPresent(person::setBirth);

    List<IAsset> assets =
        StreamSupport.stream(node.withArray("assets").spliterator(), false)
            .map(ImportCommand::parseAsset).flatMap(Optional::stream)
            .toList();

    List<ILiability> liabilities =
        StreamSupport.stream(node.withArray("liabilities").spliterator(), false)
            .map(ImportCommand::parseLiability).flatMap(Optional::stream)
            .toList();

    List<IParticipation> participations =
        StreamSupport.stream(node.withArray("participations").spliterator(), false)
            .map(ImportCommand::parseParticipation).flatMap(Optional::stream)
            .toList();

    person.addAssets(assets);
    person.addLiabilities(liabilities);
    person.addParticipations(participations);

    return Optional.of(person);
  }

  private static Optional<IAsset> parseAsset(JsonNode node) {
    return safeText(node, "type").flatMap(type -> {
      try {
        return switch (type) {
          case "bank" -> Optional.of(new BankAccountAsset(
              safeText(node, "name").orElse("Unknown bank asset"),
              safeText(node, "iban").orElse("UNKNOWN_IBAN"),
              new FixedValuation<>(new BigDecimal(safeText(node, "value").orElse("0")))));
          default -> {
            LOGGER.error("Skipping unknown asset type: {}", type);
            yield Optional.empty();
          }
        };
      } catch (Exception e) {
        LOGGER.error("Skipping malformed asset: {}", e.getMessage());
        return Optional.empty();
      }
    });
  }

  private static Optional<Birth> parseBirth(JsonNode node) {
    if (node == null || node.isMissingNode() || node.isNull()) {
      return Optional.empty();
    }

    // Parse date
    Optional<String> dateStr = safeText(node, "date");
    if (dateStr.isEmpty()) {
      LOGGER.error("Skipping birth: missing date");
      return Optional.empty();
    }

    LocalDate date;
    try {
      date = LocalDate.parse(dateStr.get());
    } catch (Exception e) {
      LOGGER.error("Skipping birth: invalid date format => {}", e.getMessage());
      return Optional.empty();
    }

    // Parse place (must be a full Address)
    Optional<Address> placeOpt = parseAddress(node.path("place"));
    if (placeOpt.isEmpty()) {
      LOGGER.error("Skipping birth: invalid or incomplete place address");
      return Optional.empty();
    }

    try {
      return Optional.of(new Birth(date, placeOpt.get()));
    } catch (Exception e) {
      LOGGER.error("Invalid birth entry: {}", e.getMessage());
      return Optional.empty();
    }
  }

  private static Optional<Address> parseAddress(JsonNode node) {
    if (node == null || node.isMissingNode() || node.isNull()) {
      return Optional.empty();
    }

    String street = safeText(node, "street").orElse(null);
    String city = safeText(node, "city").orElse(null);
    String state = safeText(node, "state").orElse(null);
    String zipCode = safeText(node, "zipCode").orElse(null);
    String country = safeText(node, "country").orElse(null);

    // All required by constructor
    if (street == null || city == null || state == null || zipCode == null || country == null) {
      LOGGER.error("Skipping address: missing required fields");
      return Optional.empty();
    }

    try {
      return Optional.of(new Address(street, city, state, zipCode, country));
    } catch (Exception e) {
      LOGGER.error("Invalid address: {}", e.getMessage());
      return Optional.empty();
    }
  }

  private Optional<Contact> parseContact(JsonNode node) {
    if (node == null || node.isMissingNode() || node.isNull()) {
      return Optional.empty();
    }

    String email = safeText(node, "email").orElse(null);
    String phone = safeText(node, "phone").orElse(null);
    String secondary = safeText(node, "secondaryPhone").orElse(null);

    // All required by constructor
    if (email == null || phone == null) {
      LOGGER.error("Skipping contact: email and phone are required");
      return Optional.empty();
    }

    try {
      return Optional.of(new Contact(email, phone, secondary));
    } catch (Exception e) {
      LOGGER.error("Invalid contact data: {}", e.getMessage());
      return Optional.empty();
    }
  }

  private static Optional<ILiability> parseLiability(JsonNode node) {
    return Optional.empty();
  }

  private static Optional<IParticipation> parseParticipation(JsonNode node) {
    return Optional.empty();
  }

  private static Optional<String> safeText(JsonNode node, String fieldName) {
    JsonNode child = node.get(fieldName);
    if (child == null || child.isMissingNode() || child.isNull()) {
      return Optional.empty();
    }
    String text = child.asString().trim();
    return text.isEmpty() ? Optional.empty() : Optional.of(text);
  }
}
