package com.ketrika.patrimoine.models.participations;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a participation in an investment fund (mutual fund, hedge fund, ETF).
 */
public final class FundParticipation implements IParticipation {

  private final String entity;
  private final BigDecimal percent;
  private final String fundId;

  public FundParticipation(String entity, BigDecimal percent, String fundId) {
    this.entity = Objects.requireNonNull(entity);
    this.percent = Objects.requireNonNull(percent);
    this.fundId = Objects.requireNonNull(fundId);
  }

  @Override
  public String entity() {
    return entity;
  }

  @Override
  public BigDecimal percent() {
    return percent;
  }

  public String fundId() {
    return fundId;
  }
}
