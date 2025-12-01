package com.ketrika.patrimoine.models.participations;

import java.math.BigDecimal;

/**
 * Represents a participation (e.g. ownership percentage of a company).
 */
public interface IParticipation {
  /**
   * The name of the entity in which the person participates.
   *
   * @return entity name
   */
  String entity();

  /**
   * Percentage of participation, as a decimal between 0 and 1.
   *
   * @return participation percent (e.g. 0.25 = 25%)
   */
  BigDecimal percent();
}
