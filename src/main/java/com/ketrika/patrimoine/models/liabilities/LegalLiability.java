package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a legal obligation to pay a settlement, fine, or damages.
 */
public final class LegalLiability implements ILiability {

  private final String description;
  private final BigDecimal amount;

  public LegalLiability(String description, BigDecimal amount) {
    this.description = Objects.requireNonNull(description);
    this.amount = Objects.requireNonNull(amount);
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
