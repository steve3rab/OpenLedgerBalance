package com.ketrika.patrimony.models.liabilities;

import java.math.BigDecimal;
import java.util.Objects;

public class FixedValuation<T extends ILiability> implements IValuation<T> {

  private final BigDecimal value;

  public FixedValuation(BigDecimal value) {
    this.value = Objects.requireNonNull(value);
  }

  @Override
  public BigDecimal calculate(T liability) {
    return value;
  }

}
