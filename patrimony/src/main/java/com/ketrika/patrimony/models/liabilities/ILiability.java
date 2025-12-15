package com.ketrika.patrimony.models.liabilities;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

/**
 * Represents a liability.
 */
public interface ILiability {
  /**
   * Returns the outstanding amount of the liability.
   *
   * @return outstanding amount (never null)
   */
  BigDecimal amount();

  /**
   * A human-readable description of the liability.
   *
   * @return liability description
   */
  String description();

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
