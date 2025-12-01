package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents a vehicle such as a car, motorcycle, or boat.
 */
public final class VehicleAsset implements IAsset {

  private final String name;
  private final String registration;
  private final IValuation<VehicleAsset> valuation;
  private final Instant createdAt;

  /**
   * Constructs a VehicleAsset
   * 
   * @param name descriptive name
   * @param registration license plate or identifier
   * @param valuation the valuation(e.g depreciation)
   * @throws NullPointerException if any argument is null
   */
  public VehicleAsset(String name, String registration, IValuation<VehicleAsset> valuation) {
    this.name = Objects.requireNonNull(name);
    this.registration = Objects.requireNonNull(registration);
    this.valuation = Objects.requireNonNull(valuation);
    this.createdAt = Instant.now();
  }

  public String getRegistration() {
    return registration;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  @Override
  public BigDecimal value() {
    return valuation.calculate(this);
  }

  @Override
  public String name() {
    return name;
  }
}
