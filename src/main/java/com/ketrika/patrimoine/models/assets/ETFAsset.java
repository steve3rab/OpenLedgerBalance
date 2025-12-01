package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents ETF.
 */
public final class ETFAsset implements IAsset {

  private final String name;
  private final String ticker;
  private final int shares;
  private final IValuation<ETFAsset> valuation;
  private final Instant createdAt;

  /**
   * Constructs a new ETFAsset.
   * 
   * @param name
   * @param ticker
   * @param shares
   * @param valuation
   * @throws NullPointerException if any argument is null
   */
  public ETFAsset(String name, String ticker, int shares, IValuation<ETFAsset> valuation) {
    this.name = Objects.requireNonNull(name);
    this.ticker = Objects.requireNonNull(ticker);
    this.shares = shares;
    this.valuation = Objects.requireNonNull(valuation);
    this.createdAt = Instant.now();
  }

  public String getTicker() {
    return ticker;
  }

  public int getShares() {
    return shares;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  @Override
  public BigDecimal value() {
    // Value = PricePerShare × NumberOfShares
    return valuation.calculate(this);
  }

  @Override
  public String name() {
    return name;
  }
}
