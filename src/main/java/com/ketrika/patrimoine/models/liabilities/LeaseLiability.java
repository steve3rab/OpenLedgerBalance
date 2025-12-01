package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a lease obligation such as car leasing or house rental arrears.
 */
public final class LeaseLiability implements ILiability {

  private final String description;
  private final BigDecimal remainingPayments;

  public LeaseLiability(String description, BigDecimal remainingPayments) {
    this.description = Objects.requireNonNull(description);
    this.remainingPayments = Objects.requireNonNull(remainingPayments);
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
