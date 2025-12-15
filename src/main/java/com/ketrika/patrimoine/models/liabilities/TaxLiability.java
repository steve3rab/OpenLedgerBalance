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

  private TaxLiability(Builder builder) {
    this.description = Objects.requireNonNull(builder.description);
    this.unpaidAmount = Objects.requireNonNull(builder.unpaidAmount);
    this.dueDate = Objects.requireNonNull(builder.dueDate);
    this.createdAt = Instant.now();
  }

  public static Builder builder() {
    return new Builder();
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

  public static final class Builder {
    private String description;
    private BigDecimal unpaidAmount;
    private LocalDate dueDate;

    private Builder() {}

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder unpaidAmount(BigDecimal unpaidAmount) {
      this.unpaidAmount = unpaidAmount;
      return this;
    }

    public Builder dueDate(LocalDate dueDate) {
      this.dueDate = dueDate;
      return this;
    }

    public TaxLiability build() {
      return new TaxLiability(this);
    }
  }
}
