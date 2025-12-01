package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;
import java.util.Objects;

public class FixedValuation<T extends IAsset> implements IValuation<T> {

  private final BigDecimal value;

  public FixedValuation(BigDecimal value) {
    this.value = Objects.requireNonNull(value);
  }

  @Override
  public BigDecimal calculate(T asset) {
    return value;
  }

}
