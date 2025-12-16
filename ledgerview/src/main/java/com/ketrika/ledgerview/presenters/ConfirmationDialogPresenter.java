package com.ketrika.ledgerview.presenters;

import com.ketrika.ledgerview.models.ConfirmationDialogModel;
import com.ketrika.ledgerview.views.ConfirmationDialogView;

/**
 * Presenter for the ConfirmationDialog, handling the business logic and updating the view.
 */
public class ConfirmationDialogPresenter {

  private final ConfirmationDialogModel model;
  private ConfirmationDialogView view;

  /**
   * Constructs a {@code ConfirmationDialogPresenter} with the specified model.
   *
   * @param model the model containing the data
   */
  public ConfirmationDialogPresenter(ConfirmationDialogModel model) {
    this.model = model;
  }

  /**
   * Sets the view that this presenter controls.
   *
   * @param view the ConfirmationDialogView instance
   */
  public void setView(ConfirmationDialogView view) {
    this.view = view;
  }

  /**
   * Handles the confirm button click action.
   */
  public void onConfirmButtonClicked() {
    model.setConfirmed(true);
    view.close();
  }

  /**
   * Returns whether the user confirmed the action.
   *
   * @return {@code true} if the user clicked "Confirm"; {@code false} otherwise
   */
  public boolean isConfirmed() {
    return model.isConfirmed();
  }

  /**
   * Get message
   * 
   * @return the dialog message
   */
  public String getMessage() {
    return model.getConfirmationQuestion();
  }
}
