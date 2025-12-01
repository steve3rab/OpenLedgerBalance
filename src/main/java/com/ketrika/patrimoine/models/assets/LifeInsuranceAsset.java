package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents the cash-out value (or surrender value) of a life insurance policy.
 */
public final class LifeInsuranceAsset implements IAsset {

  private final String name;
  private final String contractNumber;
  private final IValuation<LifeInsuranceAsset> valuation;
  private final Instant createdAt;

  /**
   * Constructs a new LifeInsuranceAsset.
   * 
   * @param name policy name
   * @param contractNumber insurer contract identifier
   * @param valuation that returns policy cash value
   * @throws NullPointerException if any argument is null
   */
  public LifeInsuranceAsset(String name, String contractNumber, IValuation<LifeInsuranceAsset> valuation) {
    this.name = Objects.requireNonNull(name);
    this.contractNumber = Objects.requireNonNull(contractNumber);
    this.valuation = Objects.requireNonNull(valuation);
    this.createdAt = Instant.now();
  }

  public String getContractNumber() {
    return contractNumber;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  @Override
  public BigDecimal value() {
    return valuation.calculate(this);
  }

  @Override
  public String name() {
    return name;
  }
}
