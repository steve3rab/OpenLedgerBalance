package com.ketrika.patrimony.models.participations;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a seat on a non-profit or NGO board.
 * <p>
 * Percentage is often 0—participation is not ownership but governance.
 */
public final class NonProfitBoardMembership implements IParticipation {

  private final String entity;
  private final BigDecimal percent;
  private final String role; // e.g., Chairman, Treasurer, Member

  public NonProfitBoardMembership(String entity, BigDecimal percent, String role) {
    this.entity = Objects.requireNonNull(entity);
    this.percent = Objects.requireNonNull(percent);
    this.role = Objects.requireNonNull(role);
  }

  @Override
  public String entity() {
    return entity;
  }

  @Override
  public BigDecimal percent() {
    return percent;
  }

  public String role() {
    return role;
  }
}
