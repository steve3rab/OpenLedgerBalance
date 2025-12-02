package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents a legal obligation to pay a settlement, fine, or damages.
 */
public final class LegalLiability implements ILiability {

  private final String description;
  private final BigDecimal amount;
  private final Instant createdAt;

  /**
   * Constructs a new LegalLiability.
   * 
   * @param description
   * @param amount
   * @throws NullPointerException if any argument is null
   */
  public LegalLiability(String description, BigDecimal amount) {
    this.description = Objects.requireNonNull(description);
    this.amount = Objects.requireNonNull(amount);
    this.createdAt = Instant.now();
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  @Override
  public BigDecimal amount() {
    return amount;
  }

  @Override
  public String description() {
    return description;
  }
}
