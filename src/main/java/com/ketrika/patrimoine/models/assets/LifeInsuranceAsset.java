package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.List;
import java.util.Objects;

/**
 * Represents the cash-out value (or surrender value) of a life insurance policy.
 */
public final class LifeInsuranceAsset implements IAsset {

  private final String name;
  private final String contractNumber;
  private final String ownerName;
  private final String insuredPersonName;
  private final String beneficiaryName;
  private final String issuer;
  private final Currency currency;
  private final List<String> tags;
  private final IValuation<LifeInsuranceAsset> valuation;
  private final Instant createdAt;

  /**
   * Constructs a new LifeInsuranceAsset.
   * 
   * @param name policy name
   * @param contractNumber insurer contract identifier
   * @param valuation that returns policy cash value
   * @throws NullPointerException if any argument is null
   */
  public LifeInsuranceAsset(String name, String contractNumber, IValuation<LifeInsuranceAsset> valuation) {
    this(name, contractNumber, null, null, null, null, null, null, valuation);
  }

  /**
   * Full constructor including optional metadata.
   */
  public LifeInsuranceAsset(
      String name,
      String contractNumber,
      String ownerName,
      String insuredPersonName,
      String beneficiaryName,
      String issuer,
      Currency currency,
      List<String> tags,
      IValuation<LifeInsuranceAsset> valuation) {
    this.name = Objects.requireNonNull(name);
    this.contractNumber = Objects.requireNonNull(contractNumber);
    this.ownerName = ownerName;
    this.insuredPersonName = insuredPersonName;
    this.beneficiaryName = beneficiaryName;
    this.issuer = issuer;
    this.currency = currency;
    this.valuation = Objects.requireNonNull(valuation);
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

  public String getOwnerName() {
    return ownerName;
  }

  public String getInsuredPersonName() {
    return insuredPersonName;
  }

  public String getBeneficiaryName() {
    return beneficiaryName;
  }

  public String getIssuer() {
    return issuer;
  }

  public String getContractNumber() {
    return contractNumber;
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
