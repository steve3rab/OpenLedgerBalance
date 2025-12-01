package com.ketrika.patrimoine.models.generals;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;
import com.ketrika.patrimoine.models.assets.IAsset;
import com.ketrika.patrimoine.models.liabilities.ILiability;
import com.ketrika.patrimoine.models.participations.IParticipation;

/**
 * Represents a person with financial data including assets, liabilities, and participations.
 */
public class Person {

  private final String id;
  private final String firstName;
  private final String lastName;
  private final EGender gender;

  /** Timestamp when this entity was created. */
  private final Instant createdAt;

  private final AtomicReference<Contact> contact = new AtomicReference<>();
  private final AtomicReference<Address> address = new AtomicReference<>();
  private final AtomicReference<Birth> birth = new AtomicReference<>();

  private final List<IAsset> assets = new CopyOnWriteArrayList<>();
  private final List<ILiability> liabilities = new CopyOnWriteArrayList<>();
  private final List<IParticipation> participations = new CopyOnWriteArrayList<>();

  /**
   * Constructs a new Person.
   *
   * @param firstName first name
   * @param lastName last name
   * @param lastName the gender
   * @throws NullPointerException if any argument is null
   */
  public Person(String firstName, String lastName, EGender gender) {
    this.id = UUID.randomUUID().toString();
    this.firstName = Objects.requireNonNull(firstName, "firstName cannot be null");
    this.lastName = Objects.requireNonNull(lastName, "lastName cannot be null");
    this.gender = Objects.requireNonNull(gender, "gender cannot be null");
    this.createdAt = Instant.now();
  }

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void addAsset(IAsset asset) {
    assets.add(Objects.requireNonNull(asset, "asset cannot be null"));
  }

  public void addAssets(Collection<? extends IAsset> assets) {
    Objects.requireNonNull(assets, "assets cannot be null");
    for (IAsset a : assets) {
      addAsset(a);
    }
  }

  public List<IAsset> getAssets() {
    return Collections.unmodifiableList(new ArrayList<>(assets));
  }

  public void addLiability(ILiability liability) {
    liabilities.add(Objects.requireNonNull(liability, "liability cannot be null"));
  }

  public List<ILiability> getLiabilities() {
    return Collections.unmodifiableList(new ArrayList<>(liabilities));
  }

  public void addParticipation(IParticipation participation) {
    participations.add(Objects.requireNonNull(participation, "participation cannot be null"));
  }

  public List<IParticipation> getParticipations() {
    return Collections.unmodifiableList(new ArrayList<>(participations));
  }

  public Contact getContact() {
    return contact.get();
  }

  public void setContact(Contact newContact) {
    contact.set(newContact);
  }

  public Address getAddress() {
    return address.get();
  }

  public void setAddress(Address newAddress) {
    address.set(newAddress);
  }

  public Birth getBirth() {
    return birth.get();
  }

  public void setBirth(Birth newBirth) {
    birth.set(newBirth);
  }

  public EGender getGender() {
    return gender;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  /**
   * Executes a financial calculation using a pluggable strategy.
   *
   * @param financialCalculation performs the calculation
   * @return calculated financial value
   */
  public BigDecimal calculate(IFinancialCalculation financialCalculation) {
    Objects.requireNonNull(financialCalculation, "calculate cannot be null");
    return financialCalculation.calculate(this);
  }
}
