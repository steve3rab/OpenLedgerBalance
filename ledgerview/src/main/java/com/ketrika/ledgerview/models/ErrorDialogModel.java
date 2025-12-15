package com.ketrika.ledgerview.models;

/**
 * Model for holding information about the error dialog.
 */
public class ErrorDialogModel {

  private final String errorMessage;

  /**
   * Constructs an {@code ErrorDialogModel} with the specified error message.
   *
   * @param errorMessage the error message to display
   */
  public ErrorDialogModel(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
