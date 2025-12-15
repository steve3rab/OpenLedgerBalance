package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Immutable loan liability.
 */
public final class LoanLiability implements ILiability {

  private final String description;
  private final BigDecimal outstanding;
  private final Instant createdAt;

  private LoanLiability(Builder builder) {
    this.description = Objects.requireNonNull(builder.description);
    this.outstanding = Objects.requireNonNull(builder.outstanding);
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
    return outstanding;
  }

  @Override
  public String description() {
    return description;
  }

  public static final class Builder {
    private String description;
    private BigDecimal outstanding;

    private Builder() {}

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder outstanding(BigDecimal outstanding) {
      this.outstanding = outstanding;
      return this;
    }

    public LoanLiability build() {
      return new LoanLiability(this);
    }
  }
}
