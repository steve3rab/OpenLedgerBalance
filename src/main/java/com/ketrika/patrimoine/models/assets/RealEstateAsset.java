package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Real estate asset.
 */
public final class RealEstateAsset implements IAsset {

  private final String name;
  private final IValuation<RealEstateAsset> valuation;
  private final Instant createdAt;

  /**
   * Creates real estate asset.
   *
   * @param name asset name
   * @param valuation to compute value
   * @throws NullPointerException if any argument is null
   */
  public RealEstateAsset(String name, IValuation<RealEstateAsset> valuation) {
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
