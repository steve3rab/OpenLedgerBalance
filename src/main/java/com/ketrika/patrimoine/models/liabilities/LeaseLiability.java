package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents a lease obligation such as car leasing or house rental arrears.
 */
public final class LeaseLiability implements ILiability {

  private final String description;
  private final BigDecimal remainingPayments;
  private final Instant createdAt;

  /**
   * Constructs a new LeaseLiability.
   * 
   * @param description
   * @param remainingPayments
   * @throws NullPointerException if any argument is null
   */
  public LeaseLiability(String description, BigDecimal remainingPayments) {
    this.description = Objects.requireNonNull(description);
    this.remainingPayments = Objects.requireNonNull(remainingPayments);
    this.createdAt = Instant.now();
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  @Override
  public BigDecimal amount() {
    return remainingPayments;
  }

  @Override
  public String description() {
    return description;
  }
}
