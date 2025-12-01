package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents revolving credit card debt with a current owed amount.
 */
public final class CreditCardLiability implements ILiability {

  private final String description;
  private final BigDecimal balance;

  public CreditCardLiability(String description, BigDecimal balance) {
    this.description = Objects.requireNonNull(description);
    this.balance = Objects.requireNonNull(balance);
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
