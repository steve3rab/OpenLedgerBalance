package com.ketrika.patrimoine.models.assets;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.List;
import java.util.Objects;

/**
 * A bank account asset.
 */
public final class BankAccountAsset implements IAsset {

  private final String name;
  private final String iban;
  private final String bic;
  private final String bankName;
  private final String accountHolder;
  private final boolean jointAccount;
  private final IValuation<BankAccountAsset> valuation;
  private final Instant createdAt;
  private final Instant openedAt;
  private final Currency currency;
  private final List<String> tags;

  /**
   * Constructs a new BankAccountAsset.
   * 
   * @param name the name
   * @param iban bank account identifier
   * @param valuation that returns current balance
   * @throws NullPointerException if any argument is null
   */
  public BankAccountAsset(String name, String iban, IValuation<BankAccountAsset> valuation) {
    this(name, iban, null, null, null, false, null, valuation, null, null);
  }

  /**
   * Constructs a new BankAccountAsset.
   * 
   * @param name the name
   * @param iban bank account identifier
   * @param bankName name of the banking institution
   * @param bic SWIFT/BIC code of the bank
   * @param accountHolder name of the account holder
   * @param jointAccount whether the account is jointly owned
   * @param openedAt timestamp when the account was opened
   * @param valuation that returns current balance
   * @param currency ISO-4217 currency
   * @param tags list of tags
   * @throws NullPointerException if any argument is null
   */
  public BankAccountAsset(String name, String iban, String bankName, String bic, String accountHolder, boolean jointAccount, Instant openedAt, IValuation<BankAccountAsset> valuation,
      Currency currency, List<String> tags) {
    this.name = Objects.requireNonNull(name);
    this.iban = Objects.requireNonNull(iban);
    this.bankName = bankName;
    this.bic = bic;
    this.accountHolder = accountHolder;
    this.jointAccount = jointAccount;
    this.openedAt = openedAt;
    this.valuation = Objects.requireNonNull(valuation);
    this.currency = currency;
    this.tags = tags != null ? List.copyOf(tags) : null;
    this.createdAt = Instant.now();
  }

  public String getAccountHolder() {
    return accountHolder;
  }

  public String getBankName() {
    return bankName;
  }

  public String getBic() {
    return bic;
  }

  public Instant getOpenedAt() {
    return openedAt;
  }

  public boolean isJointAccount() {
    return jointAccount;
  }

  public String getIban() {
    return iban;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  @Override
  public Currency currency() {
    return currency != null ? currency : IAsset.super.currency();
  }

  @Override
  public List<String> tags() {
    return tags != null ? tags : IAsset.super.tags();
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
