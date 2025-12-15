package com.ketrika.patrimony.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Map;
import java.util.Objects;

/**
 * Utility class for currencies.
 * <p>
 * This utility does not fetch live exchange rates. The user must provide the exchange rate or a map
 * of rates.
 */
public final class UtilsCurrency {

  private UtilsCurrency() {
    // Utility class; no instances allowed
  }

  /**
   * Converts a monetary value from one currency to another using the supplied rate.
   *
   * @param amount non-null monetary amount
   * @param from source currency
   * @param to target currency
   * @param rate exchange rate defined as: 1 {@code from} = {@code rate} {@code to}
   * @return converted amount in the target currency
   * @throws NullPointerException if any argument is null
   * @throws IllegalArgumentException if rate is <= 0
   */
  public static BigDecimal convert(BigDecimal amount,
      Currency from,
      Currency to,
      BigDecimal rate) {

    Objects.requireNonNull(amount, "amount must not be null");
    Objects.requireNonNull(from, "source currency must not be null");
    Objects.requireNonNull(to, "target currency must not be null");
    Objects.requireNonNull(rate, "rate must not be null");

    if (rate.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Exchange rate must be greater than zero.");
    }

    if (from.equals(to)) {
      return amount;
    }

    return amount.multiply(rate)
        .setScale(2, RoundingMode.HALF_UP);
  }

  /**
   * Converts a monetary value using a table of exchange rates.
   *
   * @param amount non-null amount
   * @param from source currency
   * @param to target currency
   * @param rates map of exchange rates (keyed by "FROM->TO", e.g. "USD->EUR")
   * @return converted amount
   * @throws IllegalArgumentException if no rate is found in the map
   */
  public static BigDecimal convert(BigDecimal amount,
      Currency from,
      Currency to,
      Map<String, BigDecimal> rates) {

    Objects.requireNonNull(rates, "rates map must not be null");

    String key = from.getCurrencyCode() + "->" + to.getCurrencyCode();
    BigDecimal rate = rates.get(key);

    if (rate == null) {
      throw new IllegalArgumentException("Missing exchange rate for " + key);
    }

    return convert(amount, from, to, rate);
  }

  /**
   * Creates the map key for an exchange rate.
   *
   * @param from the source currency
   * @param to the target currency
   * @return key usable in the rate table
   */
  public static String key(Currency from, Currency to) {
    return from.getCurrencyCode() + "->" + to.getCurrencyCode();
  }
}
