package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;

/**
 * Interface representing a valuation approach.
 */
@FunctionalInterface
public interface IValuation<T extends IAsset> {
  /**
   * Calculates a monetary value for an asset.
   *
   * @param asset the asset being valued
   * @return calculated value
   */
  BigDecimal calculate(T asset);
}
