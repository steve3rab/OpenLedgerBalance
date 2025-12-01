package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A general-purpose liability type for obligations not fitting any specific category.
 */
public final class GeneralLiability implements ILiability {

  private final String description;
  private final BigDecimal amount;

  public GeneralLiability(String description, BigDecimal amount) {
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
