package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Valuation strategy for TrustBeneficiaryAsset. Calculates present value of expected payout.
 */
public final class TrustBeneficiaryValuation implements IValuation<TrustBeneficiaryAsset> {

  private final BigDecimal discountRate; // e.g., 0.05 for 5% per year

  /**
   * @param discountRate annual discount rate (can be 0 if no discounting is needed)
   */
  public TrustBeneficiaryValuation(BigDecimal discountRate) {
    this.discountRate = Objects.requireNonNull(discountRate, "discountRate cannot be null");
  }

  @Override
  public BigDecimal calculate(TrustBeneficiaryAsset asset) {
    BigDecimal expectedPayout = asset.getExpectedPayout();
    int years = asset.getYearsUntilDistribution();

    // Present Value = ExpectedPayout / (1 + r)^years
    BigDecimal factor = BigDecimal.ONE.add(discountRate).pow(years);
    return expectedPayout.divide(factor, 2, RoundingMode.HALF_UP);
  }
}
