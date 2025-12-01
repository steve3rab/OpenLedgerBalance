package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents unpaid taxes such as income tax, capital gains tax or VAT.
 */
public final class TaxLiability implements ILiability {

  private final String description;
  private final BigDecimal unpaidAmount;
  private final LocalDate dueDate;

  public TaxLiability(String description, BigDecimal unpaidAmount, LocalDate dueDate) {
    this.description = Objects.requireNonNull(description);
    this.unpaidAmount = Objects.requireNonNull(unpaidAmount);
    this.dueDate = Objects.requireNonNull(dueDate);
  }

  @Override
  public BigDecimal amount() {
    return unpaidAmount;
  }

  @Override
  public String description() {
    return description;
  }

  public LocalDate dueDate() {
    return dueDate;
  }
}
