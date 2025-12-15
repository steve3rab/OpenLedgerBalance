package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents revolving credit card debt with a current owed amount.
 */
public final class CreditCardLiability implements ILiability {

  private final String description;
  private final BigDecimal balance;
  private final Instant createdAt;

  private CreditCardLiability(Builder builder) {
    this.description = Objects.requireNonNull(builder.description);
    this.balance = Objects.requireNonNull(builder.balance);
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

  public static final class Builder {
    private String description;
    private BigDecimal balance;

    private Builder() {}

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder balance(BigDecimal balance) {
      this.balance = balance;
      return this;
    }

    public CreditCardLiability build() {
      return new CreditCardLiability(this);
    }
  }
}
