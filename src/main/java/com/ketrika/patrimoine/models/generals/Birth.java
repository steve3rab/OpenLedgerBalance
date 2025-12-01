package com.ketrika.patrimoine.models.generals;

import java.time.LocalDate;
import java.util.Objects;

public final class Birth {

  private final LocalDate date;
  private final String place; // e.g., city/country

  public Birth(LocalDate date, String place) {
    this.date = Objects.requireNonNull(date, "date cannot be null");
    this.place = Objects.requireNonNull(place, "place cannot be null");
  }

  public LocalDate getDate() {
    return date;
  }

  public String getPlace() {
    return place;
  }
}
