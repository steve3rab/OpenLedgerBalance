package com.ketrika.patrimoine.models.generals;

import java.math.BigDecimal;

/**
 * Represents a financial calculation strategy.
 * 
 * <p>
 * This follows the Strategy Pattern and allows dynamic switching between calculation methods such
 * as net worth, total assets, total liabilities, etc.
 * </p>
 */
@FunctionalInterface
public interface IFinancialCalculation {

  /**
   * Executes a financial computation on the given person.
   *
   * @param person the person whose financial data is being evaluated
   * @return resulting monetary value
   */
  BigDecimal calculate(Person person);
}
