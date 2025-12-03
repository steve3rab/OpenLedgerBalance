package com.ketrika.patrimoine.core;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.StreamSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ketrika.patrimoine.models.assets.BankAccountAsset;
import com.ketrika.patrimoine.models.assets.FixedValuation;
import com.ketrika.patrimoine.models.assets.IAsset;
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
      for (JsonNode node : root) {
        parsePerson(node);
      }
      LOGGER.trace("Successfully imported: {}", persons.size());
    } catch (JacksonException e) {
      LOGGER.error("File could not be read: {}", jsonFile.getAbsolutePath());
    }

    return 0;
  }

  private Person parsePerson(JsonNode node) {
    String firstName = safeText(node, "firstname").orElse("Unknown");
    String lastName = safeText(node, "lastname").orElse("Unknown");
    EGender gender = switch (safeText(node, "gender").orElse("Unknown")) {
      case "F" -> EGender.FEMININE;
      case "M" -> EGender.MASCULINE;
      default -> EGender.OTHER;
    };

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

    var person = new Person(firstName, lastName, gender);
    person.addAssets(assets);
    person.addLiabilities(liabilities);
    person.addParticipations(participations);

    return person;
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

  private static Optional<ILiability> parseLiability(JsonNode node) {
    return Optional.empty();
  }

  private static Optional<IParticipation> parseParticipation(JsonNode node) {
    return Optional.empty();
  }

  private static Optional<String> safeText(JsonNode node, String field) {
    JsonNode n = node.get(field);
    return n == null || n.isNull() ? Optional.empty() : Optional.of(n.asString());
  }
}
