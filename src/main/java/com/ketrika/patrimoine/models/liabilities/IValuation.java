package com.ketrika.patrimoine.models.liabilities;

import java.math.BigDecimal;

/**
 * Interface representing a valuation approach.
 */
@FunctionalInterface
public interface IValuation<T extends ILiability> {
  /**
   * Calculates a monetary value for a liability.
   *
   * @param liability the liability being valued
   * @return calculated value
   */
  BigDecimal calculate(T liability);
}
