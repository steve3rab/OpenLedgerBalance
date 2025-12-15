package com.ketrika.ledgerview.views;

import java.util.concurrent.atomic.AtomicReference;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * A modal dialog displaying a progress bar, typically used to indicate ongoing background tasks.
 * The dialog is a singleton and is automatically closed when progress reaches 100%.
 */
public class WaitFrameDialogView extends Stage {

  private final ProgressBar progressBar = new ProgressBar();
  private final DoubleProperty progressProperty = new SimpleDoubleProperty(0);

  private static final AtomicReference<WaitFrameDialogView> INSTANCE = new AtomicReference<>();
  private static final double MINIMUM_PROGRESS_TIME = 2.0;

  /**
   * Constructs a {@code WaitFrameDialogView} with the specified owner and title. The dialog is modal
   * and non-resizable.
   *
   * @param owner the owner stage of the dialog
   * @param title the title of the dialog
   */
  private WaitFrameDialogView(Stage owner, String title) {
    super(StageStyle.UNDECORATED);
    initOwner(owner);
    initModality(Modality.APPLICATION_MODAL);
    setTitle(title);
    setResizable(false);

    // Bind progress bar to progress property
    progressBar.progressProperty().bind(progressProperty);
    progressBar.getStyleClass().add("progress-bar");

    // Set up the dialog content
    var contentBox = new VBox(10, progressBar);
    contentBox.setPadding(new Insets(10));
    contentBox.getStyleClass().add("wait-frame-dialog");

    var scene = new Scene(contentBox);
    setScene(scene);

    // Reset the progress when the dialog is hidden
    setOnHidden(event -> resetProgress());

    // Auto-close when progress is full
    progressProperty.addListener((observable, oldValue, newValue) -> {
      if (newValue.doubleValue() >= 1.0) {
        closeDialog();
      }
    });
  }

  /**
   * Returns the singleton instance of the {@code WaitFrameDialogView}. If the instance does not
   * exist, it creates one with the provided owner and title.
   *
   * @param owner the owner stage of the dialog
   * @param title the title of the dialog
   * @return the singleton instance
   */
  public static WaitFrameDialogView getInstance(Stage owner, String title) {
    WaitFrameDialogView currentInstance = INSTANCE.get();
    if (currentInstance == null) {
      currentInstance = new WaitFrameDialogView(owner, title);
      if (!INSTANCE.compareAndSet(null, currentInstance)) {
        currentInstance = INSTANCE.get();
      }
    }
    return currentInstance;
  }

  /**
   * Displays the dialog.
   */
  public void showDialog() {
    Platform.runLater(this::show);
  }

  /**
   * Closes the dialog. If the progress is less than the minimum required time, the method waits for
   * one second before closing.
   */
  public void closeDialog() {
    Platform.runLater(() -> {
      if (progressProperty.get() < MINIMUM_PROGRESS_TIME) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
      close();
    });
  }

  /**
   * Increments the progress bar by the specified amount.
   *
   * @param amount the amount to increment the progress bar
   */
  public void incrementProgress(double amount) {
    setProgress(progressProperty.get() + amount);
  }

  /**
   * Sets the progress value.
   *
   * @param progress the progress value to set
   */
  public void setProgress(double progress) {
    Platform.runLater(() -> progressProperty.set(progress));
  }

  /**
   * Gets the progress property.
   *
   * @return the progress property
   */
  public DoubleProperty progressProperty() {
    return progressProperty;
  }

  /**
   * Resets the progress bar to 0.
   */
  private void resetProgress() {
    progressProperty.set(0);
  }
}
