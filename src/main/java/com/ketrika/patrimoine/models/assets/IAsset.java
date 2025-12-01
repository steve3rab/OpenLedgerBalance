package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;

/**
 * Represents an asset with a valuation.
 */
public interface IAsset {
  /**
   * Returns the monetary value of the asset.
   *
   * @return asset valuation
   */
  BigDecimal value();

  /**
   * Human-readable asset name.
   *
   * @return asset name
   */
  String name();
}
