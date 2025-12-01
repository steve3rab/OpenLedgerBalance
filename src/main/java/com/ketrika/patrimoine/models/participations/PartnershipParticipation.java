package com.ketrika.patrimoine.models.participations;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a partnership interest in a business partnership.
 */
public final class PartnershipParticipation implements IParticipation {

  private final String entity;
  private final BigDecimal percent;
  private final String partnerRole; // e.g., General Partner, Limited Partner

  public PartnershipParticipation(String entity, BigDecimal percent, String partnerRole) {
    this.entity = Objects.requireNonNull(entity);
    this.percent = Objects.requireNonNull(percent);
    this.partnerRole = Objects.requireNonNull(partnerRole);
  }

  @Override
  public String entity() {
    return entity;
  }

  @Override
  public BigDecimal percent() {
    return percent;
  }

  public String partnerRole() {
    return partnerRole;
  }
}
