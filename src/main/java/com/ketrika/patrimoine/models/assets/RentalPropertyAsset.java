package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.List;
import java.util.Objects;

/**
 * Represents Rental Property
 */
public final class RentalPropertyAsset implements IAsset {

  private final String name;
  private final String address;
  private final BigDecimal monthlyRent;
  private final IValuation<RentalPropertyAsset> valuation;
  private final BigDecimal areaSqM;
  private final int yearBuilt;
  private final Currency currency;
  private final List<String> tags;
  private final Instant createdAt;

  /**
   * Constructs a new RentalPropertyAsset.
   * 
   * @param name
   * @param address
   * @param monthlyRent
   * @param valuation
   * @throws NullPointerException if any argument is null
   */
  public RentalPropertyAsset(String name, String address, BigDecimal monthlyRent, IValuation<RentalPropertyAsset> valuation) {
    this(name, address, monthlyRent, null, 0, null, null, valuation);
  }

  /**
   * Full constructor including optional metadata.
   */
  public RentalPropertyAsset(
      String name,
      String address,
      BigDecimal monthlyRent,
      BigDecimal areaSqM,
      int yearBuilt,
      Currency currency,
      List<String> tags,
      IValuation<RentalPropertyAsset> valuation) {
    this.name = Objects.requireNonNull(name);
    this.address = Objects.requireNonNull(address);
    this.monthlyRent = Objects.requireNonNull(monthlyRent);
    this.valuation = Objects.requireNonNull(valuation);
    this.areaSqM = areaSqM;
    this.yearBuilt = yearBuilt;
    this.currency = currency;
    this.tags = tags != null ? List.copyOf(tags) : null;
    this.createdAt = Instant.now();
  }

  @Override
  public Currency currency() {
    return currency != null ? currency : IAsset.super.currency();
  }

  @Override
  public List<String> tags() {
    return tags != null ? tags : IAsset.super.tags();
  }

  public BigDecimal getAreaSqM() {
    return areaSqM;
  }

  public int getYearBuilt() {
    return yearBuilt;
  }

  public String getAddress() {
    return address;
  }

  public BigDecimal getMonthlyRent() {
    return monthlyRent;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  @Override
  public BigDecimal value() {
    // Value= MonthlyRent × 12 (per year)
    return valuation.calculate(this);
  }

  @Override
  public String name() {
    return name;
  }
}
