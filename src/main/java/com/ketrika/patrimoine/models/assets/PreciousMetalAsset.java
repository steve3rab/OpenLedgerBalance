package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents precious metal holdings such as gold, silver, or platinum.
 */
public final class PreciousMetalAsset implements IAsset {

  private final String name;
  private final BigDecimal weightInGrams;
  private final IValuation<PreciousMetalAsset> valuation;
  private final Instant createdAt;

  /**
   * Constructs a new PreciousMetalAsset.
   * 
   * @param name
   * @param weightInGrams
   * @param valuation
   * @throws NullPointerException if any argument is null
   */
  public PreciousMetalAsset(String name, BigDecimal weightInGrams, IValuation<PreciousMetalAsset> valuation) {
    this.name = Objects.requireNonNull(name);
    this.weightInGrams = Objects.requireNonNull(weightInGrams);
    this.valuation = Objects.requireNonNull(valuation);
    this.createdAt = Instant.now();
  }

  public BigDecimal getWeightInGrams() {
    return weightInGrams;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  @Override
  public BigDecimal value() {
    // Value = PricePerGram × WeightInGrams
    return valuation.calculate(this);
  }

  @Override
  public String name() {
    return name;
  }
}
