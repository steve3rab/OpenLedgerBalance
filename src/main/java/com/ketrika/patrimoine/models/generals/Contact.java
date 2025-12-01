package com.ketrika.patrimoine.models.generals;

import java.util.Objects;

public final class Contact {

  private final String email;
  private final String phone;

  public Contact(String email, String phone) {
    this.email = Objects.requireNonNull(email, "email cannot be null");
    this.phone = Objects.requireNonNull(phone, "phone cannot be null");
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }
}
