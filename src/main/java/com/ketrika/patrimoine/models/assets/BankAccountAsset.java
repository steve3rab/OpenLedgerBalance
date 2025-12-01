package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A bank account asset.
 */
public final class BankAccountAsset implements IAsset {

  private final String name;
  private final String iban;
  private final IValuation<BankAccountAsset> valuation;
  private final Instant createdAt;

  /**
   * Constructs a new BankAccountAsset.
   * 
   * @param name the name
   * @param iban bank account identifier
   * @param valuation that returns current balance
   * @throws NullPointerException if any argument is null
   */
  public BankAccountAsset(String name, String iban, IValuation<BankAccountAsset> valuation) {
    this.name = Objects.requireNonNull(name);
    this.iban = Objects.requireNonNull(iban);
    this.valuation = Objects.requireNonNull(valuation);
    this.createdAt = Instant.now();
  }

  public String getIban() {
    return iban;
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
