package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents Royalty Stream
 */
public final class RoyaltyStreamAsset implements IAsset {

  private final String name;
  private final BigDecimal annualRoyaltyIncome;
  private final int yearsRemaining;
  private final IValuation<RoyaltyStreamAsset> valuation;
  private final Instant createdAt;

  /**
   * Constructs a new RoyaltyStreamAsset.
   * 
   * @param name
   * @param annualRoyaltyIncome
   * @param yearsRemaining
   * @param valuation
   * @throws NullPointerException if any argument is null
   */
  public RoyaltyStreamAsset(String name, BigDecimal annualRoyaltyIncome, int yearsRemaining, IValuation<RoyaltyStreamAsset> valuation) {
    this.name = Objects.requireNonNull(name);
    this.annualRoyaltyIncome = Objects.requireNonNull(annualRoyaltyIncome);
    this.yearsRemaining = yearsRemaining;
    this.valuation = Objects.requireNonNull(valuation);
    this.createdAt = Instant.now();
  }

  public BigDecimal getAnnualRoyaltyIncome() {
    return annualRoyaltyIncome;
  }

  public int getYearsRemaining() {
    return yearsRemaining;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  @Override
  public BigDecimal value() {
    // Value = AnnualRoyaltyIncome × YearsRemaining
    return valuation.calculate(this);
  }

  @Override
  public String name() {
    return name;
  }
}
