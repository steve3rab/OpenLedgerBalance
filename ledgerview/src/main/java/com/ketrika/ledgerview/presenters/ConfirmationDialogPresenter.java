package com.ketrika.ledgerview.presenters;

import com.ketrika.ledgerview.models.ConfirmationDialogModel;
import com.ketrika.ledgerview.views.ConfirmationDialogView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Label;

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
   * Initializes the view with data from the model.
   */
  public void initViewContent(FontAwesomeIconView icon, Label messageLabel) {
    messageLabel.setText(model.getConfirmationQuestion());
  }

  /**
   * Handles the confirm button click action.
   */
  public void onConfirmButtonClicked() {
    model.setConfirmed(true);
    view.close();
  }

  /**
   * Handles the cancel button click action.
   */
  public void onCancelButtonClicked() {
    model.setConfirmed(false);
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
}
