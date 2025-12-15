package com.ketrika.patrimony.models.assets;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.ToDoubleFunction;

/**
 * Generic valuation strategy for assets whose value is calculated as quantity × unit price.
 *
 * @param <T> type of asset
 */
public final class MultiplicativeValuation<T extends IAsset> implements IValuation<T> {

  private final BigDecimal unitPrice;
  private final ToDoubleFunction<T> quantityExtractor;

  /**
   * @param unitPrice price per unit
   * @param quantityExtractor function to extract quantity from the asset
   */
  public MultiplicativeValuation(BigDecimal unitPrice, ToDoubleFunction<T> quantityExtractor) {
    this.unitPrice = Objects.requireNonNull(unitPrice, "unitPrice cannot be null");
    this.quantityExtractor = Objects.requireNonNull(quantityExtractor, "quantityExtractor cannot be null");
  }

  @Override
  public BigDecimal calculate(T asset) {
    double quantity = quantityExtractor.applyAsDouble(asset);
    return unitPrice.multiply(BigDecimal.valueOf(quantity));
  }
}
