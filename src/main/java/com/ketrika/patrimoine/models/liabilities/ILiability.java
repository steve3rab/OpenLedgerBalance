package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;

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
}
