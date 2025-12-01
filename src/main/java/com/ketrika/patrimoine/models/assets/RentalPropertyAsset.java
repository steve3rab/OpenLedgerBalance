package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents Rental Property
 */
public final class RentalPropertyAsset implements IAsset {

  private final String name;
  private final String address;
  private final BigDecimal monthlyRent;
  private final IValuation<RentalPropertyAsset> valuation;
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
    this.name = Objects.requireNonNull(name);
    this.address = Objects.requireNonNull(address);
    this.monthlyRent = Objects.requireNonNull(monthlyRent);
    this.valuation = Objects.requireNonNull(valuation);
    this.createdAt = Instant.now();
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
