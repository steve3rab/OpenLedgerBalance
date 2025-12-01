package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents Land Parcel
 */
public final class LandParcelAsset implements IAsset {

  private final String name;
  private final double acreage;
  private final String zoningType;
  private final IValuation<LandParcelAsset> valuation;
  private final Instant createdAt;

  /**
   * Constructs a new LandParcelAsset.
   * 
   * @param name
   * @param acreage
   * @param zoningType
   * @param valuation
   * @throws NullPointerException if any argument is null
   */
  public LandParcelAsset(String name, double acreage, String zoningType, IValuation<LandParcelAsset> valuation) {
    this.name = Objects.requireNonNull(name);
    this.acreage = acreage;
    this.zoningType = Objects.requireNonNull(zoningType);
    this.valuation = Objects.requireNonNull(valuation);
    this.createdAt = Instant.now();
  }

  public double getAcreage() {
    return acreage;
  }

  public String getZoningType() {
    return zoningType;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  @Override
  public BigDecimal value() {
    // Value = PricePerAcre × Acreage
    return valuation.calculate(this);
  }

  @Override
  public String name() {
    return name;
  }
}
