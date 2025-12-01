package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents cryptocurrency holdings.
 */
public final class CryptoAsset implements IAsset {

  private final String name;
  private final String symbol;
  private final BigDecimal quantity;
  private final IValuation<CryptoAsset> valuation;
  private final Instant createdAt;

  /**
   * Constructs a new CryptoAsset.
   * 
   * @param name
   * @param symbol
   * @param quantity
   * @param valuation
   * @throws NullPointerException if any argument is null
   */
  public CryptoAsset(String name, String symbol, BigDecimal quantity, IValuation<CryptoAsset> valuation) {
    this.name = Objects.requireNonNull(name);
    this.symbol = Objects.requireNonNull(symbol);
    this.quantity = Objects.requireNonNull(quantity);
    this.valuation = Objects.requireNonNull(valuation);
    this.createdAt = Instant.now();
  }

  public String getSymbol() {
    return symbol;
  }

  public BigDecimal getQuantity() {
    return quantity;
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
