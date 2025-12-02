package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents unpaid taxes such as income tax, capital gains tax or VAT.
 */
public final class TaxLiability implements ILiability {

  private final String description;
  private final BigDecimal unpaidAmount;
  private final LocalDate dueDate;
  private final Instant createdAt;

  /**
   * Constructs a new TaxLiability.
   * 
   * @param description
   * @param unpaidAmount
   * @param dueDate
   * @throws NullPointerException if any argument is null
   */
  public TaxLiability(String description, BigDecimal unpaidAmount, LocalDate dueDate) {
    this.description = Objects.requireNonNull(description);
    this.unpaidAmount = Objects.requireNonNull(unpaidAmount);
    this.dueDate = Objects.requireNonNull(dueDate);
    this.createdAt = Instant.now();
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  @Override
  public BigDecimal amount() {
    return unpaidAmount;
  }

  @Override
  public String description() {
    return description;
  }
}
