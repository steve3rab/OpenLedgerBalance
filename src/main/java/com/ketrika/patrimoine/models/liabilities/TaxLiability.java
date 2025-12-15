package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.Objects;

/**
 * Represents unpaid taxes such as income tax, capital gains tax or VAT.
 */
public final class TaxLiability implements ILiability {

  private final String description;
  private final LocalDate dueDate;
  private final Currency currency;
  private final List<String> tags;
  private final IValuation<TaxLiability> valuation;
  private final Instant createdAt;

  private TaxLiability(Builder builder) {
    this.description = Objects.requireNonNull(builder.description);
    this.dueDate = Objects.requireNonNull(builder.dueDate);
    this.valuation = Objects.requireNonNull(builder.valuation);
    this.currency = builder.currency;
    this.tags = builder.tags != null ? List.copyOf(builder.tags) : null;
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
    return valuation.calculate(this);
  }

  @Override
  public String description() {
    return description;
  }

  @Override
  public Currency currency() {
    return currency != null ? currency : ILiability.super.currency();
  }

  @Override
  public List<String> tags() {
    return tags != null ? tags : ILiability.super.tags();
  }

  public static final class Builder {
    private String description;
    private LocalDate dueDate;
    private Currency currency;
    private List<String> tags;
    private IValuation<TaxLiability> valuation;

    private Builder() {}

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder dueDate(LocalDate dueDate) {
      this.dueDate = dueDate;
      return this;
    }

    public Builder currency(Currency currency) {
      this.currency = currency;
      return this;
    }

    public Builder tags(List<String> tags) {
      this.tags = tags;
      return this;
    }

    public Builder valuation(IValuation<TaxLiability> valuation) {
      this.valuation = valuation;
      return this;
    }

    public TaxLiability build() {
      return new TaxLiability(this);
    }
  }
}
