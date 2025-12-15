package com.ketrika.patrimony.models.assets;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Generic catch-all asset type when no specialised model applies.
 */
public final class GeneralAsset implements IAsset {

  private final String name;
  private final IValuation<GeneralAsset> valuation;
  private final Instant createdAt;

  /**
   * Constructs a new GeneralAsset.
   * 
   * @param name
   * @param valuation
   * @throws NullPointerException if any argument is null
   */
  public GeneralAsset(String name, IValuation<GeneralAsset> valuation) {
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
