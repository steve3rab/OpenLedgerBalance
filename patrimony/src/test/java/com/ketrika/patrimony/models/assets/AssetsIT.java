package com.ketrika.patrimony.models.assets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.ketrika.patrimony.models.generals.Address;
import com.ketrika.patrimony.models.generals.AssetsCalculation;
import com.ketrika.patrimony.models.generals.Birth;
import com.ketrika.patrimony.models.generals.Contact;
import com.ketrika.patrimony.models.generals.EGender;
import com.ketrika.patrimony.models.generals.Person;

class AssetsIT {

  private Person person;
  private BankAccountAsset bank;
  private CollectibleAsset collectible;
  private CryptoAsset crypto;
  private TrustBeneficiaryAsset trust;
  private RealEstateAsset realEstate;
  private VehicleAsset vehicle;

  @BeforeEach
  void setUp() {

    // Address
    var address = new Address(
        "123H",
        "Antanetibe",
        "Antehiroka",
        "105",
        "Madagascar");

    // Birth
    var birth = new Birth(
        LocalDate.of(1990, 5, 15),
        address);

    // Contact
    var contact = new Contact(
        "fenitra.fita@example.com",
        "+2615512344");

    // Person
    person = new Person("Fenitra", "Fita", EGender.MASCULINE);
    person.setAddress(address);
    person.setContact(contact);
    person.setBirth(birth);

    // BankAccountAsset with FixedValuation
    bank = new BankAccountAsset.Builder()
        .name("Checking Account")
        .iban("MG89370400440532013000")
        .valuation(new FixedValuation<>(BigDecimal.valueOf(1000)))
        .build();

    // CollectibleAsset with FixedValuation
    collectible = new CollectibleAsset.Builder()
        .name("Vintage Watch")
        .category("Luxury")
        .valuation(new FixedValuation<>(BigDecimal.valueOf(5000)))
        .build();

    // CryptoAsset using MultiplicativeValuation
    crypto = new CryptoAsset.Builder()
        .name("Bitcoin")
        .symbol("BTC")
        .quantity(BigDecimal.valueOf(2))
        .valuation(new MultiplicativeValuation<>(
            BigDecimal.valueOf(30000),
            a -> a.getQuantity().doubleValue()))
        .build();

    // TrustBeneficiaryAsset using TrustBeneficiaryValuation
    trust = new TrustBeneficiaryAsset.Builder()
        .name("Family Trust")
        .expectedPayout(BigDecimal.valueOf(50000))
        .yearsUntilDistribution(10)
        .valuation(new TrustBeneficiaryValuation(BigDecimal.valueOf(0.05)))
        .build();

    // RealEstateAsset
    realEstate = new RealEstateAsset.Builder()
        .name("Primary Residence")
        .address("Lot II M 123 - Antehiroka")
        .propertyType("Residential")
        .landAreaSqM(BigDecimal.valueOf(500))
        .buildingAreaSqM(BigDecimal.valueOf(220))
        .yearBuilt(2010)
        .currency(Currency.getInstance("USD"))
        .tags(List.of("house", "main-home"))
        .valuation(new FixedValuation<>(BigDecimal.valueOf(150_000)))
        .build();

    // VehicleAsset
    vehicle = new VehicleAsset.Builder()
        .name("Toyota Hilux")
        .registration("ABC-1234")
        .manufacturer("Toyota")
        .model("Hilux")
        .year(2018)
        .fuelType("Diesel")
        .transmission("Automatic")
        .horsepower(150)
        .seatingCapacity(5)
        .purchasePrice(BigDecimal.valueOf(35000))
        .purchaseDate(Instant.parse("2019-01-01T00:00:00Z"))
        .odometerKm(85000)
        .usageType("Personal")
        .currency(Currency.getInstance("USD"))
        .tags(List.of("4x4", "pickup"))
        .valuation(new FixedValuation<>(BigDecimal.valueOf(25000)))
        .build();

    person.addAssets(List.of(bank, collectible, crypto, trust, realEstate, vehicle));
  }

  // ---------------------------------------------------------
  // PERSON DATA TESTS
  // ---------------------------------------------------------

  @Test
  void testPersonBasicInfo() {
    assertEquals("Fenitra", person.getFirstName());
    assertEquals("Fita", person.getLastName());
    assertEquals(EGender.MASCULINE, person.getGender());
  }

  @Test
  void testPersonContactAddressBirth() {
    assertEquals("fenitra.fita@example.com", person.getContact().getEmail());
    assertEquals("123H", person.getAddress().getStreet());
    assertEquals(LocalDate.of(1990, 5, 15), person.getBirth().getDate());
  }

  // ---------------------------------------------------------
  // INDIVIDUAL ASSET TESTS
  // ---------------------------------------------------------

  @Test
  void testBankAccountValue() {
    assertTrue(bank.value().compareTo(BigDecimal.valueOf(1000)) == 0);
  }

  @Test
  void testCollectibleValue() {
    assertEquals(BigDecimal.valueOf(5000), collectible.value());
  }

  @Test
  void testCryptoValue() {
    // 2 BTC × 30000 = 60000
    assertTrue(crypto.value().compareTo(BigDecimal.valueOf(60000)) == 0);
  }

  @Test
  void testTrustValue() {
    // PV = 50000 / (1.05^10)
    BigDecimal expected = BigDecimal.valueOf(50000)
        .divide(BigDecimal.valueOf(1.05).pow(10), 2, java.math.RoundingMode.HALF_UP);

    assertTrue(trust.value().compareTo(expected) == 0);
  }

  @Test
  void testRealEstateValue() {
    assertEquals(BigDecimal.valueOf(150_000), realEstate.value());
  }

  @Test
  void testVehicleValue() {
    assertEquals(BigDecimal.valueOf(25000), vehicle.value());
  }

  // ---------------------------------------------------------
  // TOTAL ASSETS CALCULATION
  // ---------------------------------------------------------

  @Test
  void testTotalAssets() {
    BigDecimal total = person.calculate(new AssetsCalculation());

    BigDecimal expected = bank.value()
        .add(collectible.value())
        .add(crypto.value())
        .add(trust.value())
        .add(realEstate.value())
        .add(vehicle.value());

    assertEquals(expected, total);
  }
}
