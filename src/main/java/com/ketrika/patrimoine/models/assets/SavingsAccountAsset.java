package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents a savings account, often interest-bearing.
 */
public final class SavingsAccountAsset implements IAsset {

  private final String name;
  private final IValuation<SavingsAccountAsset> valuation;
  private final Instant createdAt;

  /**
   * Constructs a new SavingsAccountAsset.
   * 
   * @param name
   * @param valuation
   * @throws NullPointerException if any argument is null
   */
  public SavingsAccountAsset(String name, IValuation<SavingsAccountAsset> valuation) {
    this.name = Objects.requireNonNull(name);
    this.valuation = Objects.requireNonNull(valuation);
    this.createdAt = Instant.now();
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
