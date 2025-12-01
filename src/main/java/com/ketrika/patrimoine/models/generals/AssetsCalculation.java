package com.ketrika.patrimoine.models.generals;

import java.math.BigDecimal;
import com.ketrika.patrimoine.models.assets.IAsset;

/**
 * Calculates assets for a person.
 */
public final class AssetsCalculation implements IFinancialCalculation {

  @Override
  public BigDecimal calculate(Person person) {
    return person.getAssets().stream()
        .map(IAsset::value)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
