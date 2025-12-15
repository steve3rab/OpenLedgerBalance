package com.ketrika.ledgerview.models;

/**
 * Model for holding information about the success dialog.
 */
public class SuccessDialogModel {

  private final String successMessage;

  /**
   * Constructs a {@code SuccessDialogModel} with the specified success message.
   *
   * @param successMessage the success message to display
   */
  public SuccessDialogModel(String successMessage) {
    this.successMessage = successMessage;
  }

  public String getSuccessMessage() {
    return successMessage;
  }
}
