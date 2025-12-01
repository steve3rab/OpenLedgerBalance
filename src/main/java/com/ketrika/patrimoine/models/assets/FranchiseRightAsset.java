package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents Franchise Right
 */
public final class FranchiseRightAsset implements IAsset {

  private final String name;
  private final int yearsRemaining;
  private final BigDecimal annualProfit;
  private final IValuation<FranchiseRightAsset> valuation;
  private final Instant createdAt;

  /**
   * Constructs a new FranchiseRightAsset.
   * 
   * @param name
   * @param yearsRemaining
   * @param annualProfit
   * @param valuation
   * @throws NullPointerException if any argument is null
   */
  public FranchiseRightAsset(String name, int yearsRemaining, BigDecimal annualProfit, IValuation<FranchiseRightAsset> valuation) {
    this.name = Objects.requireNonNull(name);
    this.yearsRemaining = yearsRemaining;
    this.annualProfit = Objects.requireNonNull(annualProfit);
    this.valuation = Objects.requireNonNull(valuation);
    this.createdAt = Instant.now();
  }

  public int getYearsRemaining() {
    return yearsRemaining;
  }

  public BigDecimal getAnnualProfit() {
    return annualProfit;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  @Override
  public BigDecimal value() {
    // Value = AnnualProfit × YearsRemaining
    return valuation.calculate(this);
  }

  @Override
  public String name() {
    return name;
  }
}
