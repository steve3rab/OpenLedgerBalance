package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.List;
import java.util.Objects;

/**
 * Represents revolving credit card debt with a current owed amount.
 */
public final class CreditCardLiability implements ILiability {

  private final String description;
  private final BigDecimal balance;
  private final Currency currency;
  private final List<String> tags;
  private final Instant createdAt;

  private CreditCardLiability(Builder builder) {
    this.description = Objects.requireNonNull(builder.description);
    this.balance = Objects.requireNonNull(builder.balance);
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
    return balance;
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
    private BigDecimal balance;
    private Currency currency;
    private List<String> tags;

    private Builder() {}

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder balance(BigDecimal balance) {
      this.balance = balance;
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

    public CreditCardLiability build() {
      return new CreditCardLiability(this);
    }
  }
}
