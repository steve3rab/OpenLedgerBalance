package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.List;
import java.util.Objects;

/**
 * Real estate asset.
 */
public final class RealEstateAsset implements IAsset {

  private final String name;
  private final String address;
  private final String propertyType; // e.g., Residential, Commercial, Land
  private final BigDecimal landAreaSqM;
  private final BigDecimal buildingAreaSqM;
  private final int yearBuilt;
  private final Currency currency;
  private final List<String> tags;
  private final IValuation<RealEstateAsset> valuation;
  private final Instant createdAt;

  /**
   * Creates real estate asset.
   *
   * @param name asset name
   * @param valuation to compute value
   * @throws NullPointerException if any argument is null
   */
  public RealEstateAsset(String name, IValuation<RealEstateAsset> valuation) {
    this(name, null, null, null, null, 0, null, null, valuation);
  }

  /**
   * Full constructor including optional metadata.
   */
  public RealEstateAsset(
      String name,
      String address,
      String propertyType,
      BigDecimal landAreaSqM,
      BigDecimal buildingAreaSqM,
      int yearBuilt,
      Currency currency,
      List<String> tags,
      IValuation<RealEstateAsset> valuation) {
    this.name = Objects.requireNonNull(name);
    this.valuation = Objects.requireNonNull(valuation);
    this.address = address;
    this.propertyType = propertyType;
    this.landAreaSqM = landAreaSqM;
    this.buildingAreaSqM = buildingAreaSqM;
    this.yearBuilt = yearBuilt;
    this.currency = currency;
    this.tags = tags != null ? List.copyOf(tags) : null;
    this.createdAt = Instant.now();
  }

  @Override
  public Currency currency() {
    return currency != null ? currency : IAsset.super.currency();
  }

  @Override
  public List<String> tags() {
    return tags != null ? tags : IAsset.super.tags();
  }

  public String getAddress() {
    return address;
  }

  public String getPropertyType() {
    return propertyType;
  }

  public BigDecimal getLandAreaSqM() {
    return landAreaSqM;
  }

  public BigDecimal getBuildingAreaSqM() {
    return buildingAreaSqM;
  }

  public int getYearBuilt() {
    return yearBuilt;
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
