package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Immutable loan liability.
 */
public final class LoanLiability implements ILiability {

  private final String description;
  private final BigDecimal outstanding;

  public LoanLiability(String description, BigDecimal outstanding) {
    this.description = Objects.requireNonNull(description);
    this.outstanding = Objects.requireNonNull(outstanding);
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
