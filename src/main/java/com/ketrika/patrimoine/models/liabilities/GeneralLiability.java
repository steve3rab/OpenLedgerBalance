package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.List;
import java.util.Objects;

/**
 * A general-purpose liability type for obligations not fitting any specific category.
 */
public final class GeneralLiability implements ILiability {

  private final String description;
  private final BigDecimal amount;
  private final Currency currency;
  private final List<String> tags;
  private final Instant createdAt;

  private GeneralLiability(Builder builder) {
    this.description = Objects.requireNonNull(builder.description);
    this.amount = Objects.requireNonNull(builder.amount);
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

  @Override
  public BigDecimal amount() {
    return amount;
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
    private BigDecimal amount;
    private Currency currency;
    private List<String> tags;

    private Builder() {}

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder amount(BigDecimal amount) {
      this.amount = amount;
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

    public GeneralLiability build() {
      return new GeneralLiability(this);
    }
  }
}
