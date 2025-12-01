package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents collectible items such as art, wine, jewelry or rare objects.
 */
public final class CollectibleAsset implements IAsset {

  private final String name;
  private final String category;
  private final IValuation<CollectibleAsset> valuation;
  private final Instant createdAt;

  /**
   * Constructs a new CollectibleAsset.
   * 
   * @param name the name
   * @param category the category
   * @param valuation that returns current balance
   * @throws NullPointerException if any argument is null
   */
  public CollectibleAsset(String name, String category, IValuation<CollectibleAsset> valuation) {
    this.name = Objects.requireNonNull(name);
    this.category = Objects.requireNonNull(category);
    this.valuation = Objects.requireNonNull(valuation);
    this.createdAt = Instant.now();
  }

  public String getCategory() {
    return category;
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
