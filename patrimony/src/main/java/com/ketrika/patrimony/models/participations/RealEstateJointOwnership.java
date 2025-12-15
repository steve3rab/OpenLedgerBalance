package com.ketrika.patrimony.models.participations;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents partial ownership of a real estate property.
 */
public final class RealEstateJointOwnership implements IParticipation {

  private final String entity;
  private final BigDecimal percent;
  private final String propertyId;

  public RealEstateJointOwnership(String entity, BigDecimal percent, String propertyId) {
    this.entity = Objects.requireNonNull(entity);
    this.percent = Objects.requireNonNull(percent);
    this.propertyId = Objects.requireNonNull(propertyId);
  }

  @Override
  public String entity() {
    return entity;
  }

  @Override
  public BigDecimal percent() {
    return percent;
  }

  public String propertyId() {
    return propertyId;
  }
}
