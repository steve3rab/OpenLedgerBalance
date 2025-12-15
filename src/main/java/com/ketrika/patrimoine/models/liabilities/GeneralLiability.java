package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A general-purpose liability type for obligations not fitting any specific category.
 */
public final class GeneralLiability implements ILiability {

  private final String description;
  private final BigDecimal amount;
  private final Instant createdAt;

  private GeneralLiability(Builder builder) {
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

    public GeneralLiability build() {
      return new GeneralLiability(this);
    }
  }
}
