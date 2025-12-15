package com.ketrika.patrimony.parser;

public interface ITypedJsonParser<T> extends IJsonParser<T> {
  String type(); // e.g., "bank", "loan", "stock"
}
