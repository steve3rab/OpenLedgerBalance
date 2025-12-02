package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Immutable loan liability.
 */
public final class LoanLiability implements ILiability {

  private final String description;
  private final BigDecimal outstanding;
  private final Instant createdAt;

  /**
   * Constructs a new LoanLiability.
   * 
   * @param description
   * @param outstanding
   * @throws NullPointerException if any argument is null
   */
  public LoanLiability(String description, BigDecimal outstanding) {
    this.description = Objects.requireNonNull(description);
    this.outstanding = Objects.requireNonNull(outstanding);
    this.createdAt = Instant.now();
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  @Override
  public BigDecimal amount() {
    return outstanding;
  }

  @Override
  public String description() {
    return description;
  }
}
