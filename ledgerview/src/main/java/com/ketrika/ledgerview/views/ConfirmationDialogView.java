package com.ketrika.ledgerview.views;

import org.eclipse.jdt.annotation.NonNull;
import com.ketrika.ledgerview.presenters.ConfirmationDialogPresenter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * A dialog view for displaying a confirmation message.
 */
public class ConfirmationDialogView extends ADialogView {

  private static final String CONFIRM = "Confirm";
  private final ConfirmationDialogPresenter presenter;

  public ConfirmationDialogView(@NonNull Stage owner, @NonNull String title, ConfirmationDialogPresenter presenter) {
    super(owner, title);
    this.presenter = presenter;
    this.presenter.setView(this); // Set this view in the presenter
    initializeComponents(); // Initialize components when view is constructed
  }

  /**
   * Initializes the UI components of the confirmation dialog.
   */
  public void initializeComponents() {
    FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.INFO_CIRCLE);
    icon.getStyleClass().add("custom-icon");

    Label messageLabel = new Label(presenter.getMessage());

    var contentBox = new HBox(10, icon, messageLabel);
    contentBox.setPadding(new Insets(10));

    setContent(contentBox);

    // Configure the OK buttons via the presenter
    getOkButton().setText(CONFIRM);
    getOkButton().setOnAction(event -> presenter.onConfirmButtonClicked());
  }

  /**
   * Returns whether the user confirmed the action.
   *
   * @return {@code true} if the user clicked "Confirm"; {@code false} otherwise
   */
  public boolean isConfirmed() {
    return presenter.isConfirmed();
  }

}
