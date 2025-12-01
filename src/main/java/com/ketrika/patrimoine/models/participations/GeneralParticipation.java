package com.ketrika.patrimoine.models.participations;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * General participation.
 */
public final class GeneralParticipation implements IParticipation {

  private final String entity;
  private final BigDecimal percent;

  public GeneralParticipation(String entity, BigDecimal percent) {
    this.entity = Objects.requireNonNull(entity);
    this.percent = Objects.requireNonNull(percent);
  }

  @Override
  public String entity() {
    return entity;
  }

  @Override
  public BigDecimal percent() {
    return percent;
  }
}
