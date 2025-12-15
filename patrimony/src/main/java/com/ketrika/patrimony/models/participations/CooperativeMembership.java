package com.ketrika.patrimony.models.participations;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a membership in a cooperative (credit union, food co-op, etc.).
 */
public final class CooperativeMembership implements IParticipation {

  private final String entity;
  private final BigDecimal percent; // often 0, but included for uniformity
  private final String membershipId;

  public CooperativeMembership(String entity, BigDecimal percent, String membershipId) {
    this.entity = Objects.requireNonNull(entity);
    this.percent = Objects.requireNonNull(percent);
    this.membershipId = Objects.requireNonNull(membershipId);
  }

  @Override
  public String entity() {
    return entity;
  }

  @Override
  public BigDecimal percent() {
    return percent;
  }

  public String membershipId() {
    return membershipId;
  }
}
