package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents revolving credit card debt with a current owed amount.
 */
public final class CreditCardLiability implements ILiability {

  private final String description;
  private final BigDecimal balance;
  private final Instant createdAt;

  /**
   * Constructs a new CreditCardLiability.
   * 
   * @param description
   * @param balance
   * @throws NullPointerException if any argument is null
   */
  public CreditCardLiability(String description, BigDecimal balance) {
    this.description = Objects.requireNonNull(description);
    this.balance = Objects.requireNonNull(balance);
    this.createdAt = Instant.now();
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  @Override
  public BigDecimal amount() {
    return balance;
  }

  @Override
  public String description() {
    return description;
  }
}
