package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents a lease obligation such as car leasing or house rental arrears.
 */
public final class LeaseLiability implements ILiability {

  private final String description;
  private final BigDecimal remainingPayments;
  private final Instant createdAt;

  private LeaseLiability(Builder builder) {
    this.description = Objects.requireNonNull(builder.description);
    this.remainingPayments = Objects.requireNonNull(builder.remainingPayments);
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
    return remainingPayments;
  }

  @Override
  public String description() {
    return description;
  }

  public static final class Builder {
    private String description;
    private BigDecimal remainingPayments;

    private Builder() {}

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder remainingPayments(BigDecimal remainingPayments) {
      this.remainingPayments = remainingPayments;
      return this;
    }

    public LeaseLiability build() {
      return new LeaseLiability(this);
    }
  }
}
