package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents a legal obligation to pay a settlement, fine, or damages.
 */
public final class LegalLiability implements ILiability {

  private final String description;
  private final BigDecimal amount;
  private final Instant createdAt;

  private LegalLiability(Builder builder) {
    this.description = Objects.requireNonNull(builder.description);
    this.amount = Objects.requireNonNull(builder.amount);
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

  public static final class Builder {
    private String description;
    private BigDecimal amount;

    private Builder() {}

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder amount(BigDecimal amount) {
      this.amount = amount;
      return this;
    }

    public LegalLiability build() {
      return new LegalLiability(this);
    }
  }
}
