package com.ketrika.patrimony.models.generals;

import java.math.BigDecimal;
import com.ketrika.patrimony.models.assets.IAsset;

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
