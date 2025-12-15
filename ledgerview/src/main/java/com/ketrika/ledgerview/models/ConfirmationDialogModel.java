package com.ketrika.ledgerview.models;

/**
 * Model for holding information about the confirmation dialog.
 */
public class ConfirmationDialogModel {

  private final String confirmationQuestion;
  private boolean confirmed;

  /**
   * Constructs a {@code ConfirmationDialogModel} with the specified confirmation question.
   *
   * @param confirmationQuestion the question to display in the dialog
   */
  public ConfirmationDialogModel(String confirmationQuestion) {
    this.confirmationQuestion = confirmationQuestion;
  }

  public String getConfirmationQuestion() {
    return confirmationQuestion;
  }

  public boolean isConfirmed() {
    return confirmed;
  }

  public void setConfirmed(boolean confirmed) {
    this.confirmed = confirmed;
  }
}
