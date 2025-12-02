package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

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

  /**
   * Currency of the asset value (Malagasy Ariary).
   *
   * @return ISO-4217 currency code
   */
  default Currency currency() {
    return Currency.getInstance("MGA");
  }

  /**
   * Arbitrary tags for filtering or grouping (e.g. ["liquid", "risky"]).
   *
   * @return list of tags, empty list by default
   */
  default List<String> tags() {
    return List.of();
  }
}
