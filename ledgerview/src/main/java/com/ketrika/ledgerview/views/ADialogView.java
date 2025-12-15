package com.ketrika.ledgerview.views;

import org.eclipse.jdt.annotation.NonNull;
import org.tbee.javafx.scene.layout.MigPane;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class ADialogView extends Stage {
  /**
   * The main layout pane for the dialog.
   */
  private final MigPane migPane = new MigPane("", "[grow]", "[][grow][]");

  /**
   * The OK button for the dialog.
   */
  protected final Button okButton = new Button("OK");

  /**
   * Constructs an {@code ADialogView} with the specified owner and title.
   *
   * @param owner the owner stage of the dialog
   * @param title the title of the dialog
   * @throws NullPointerException if owner or title is null
   */
  public ADialogView(@NonNull Stage owner, @NonNull String title) {
    super(StageStyle.UTILITY);
    initOwner(owner);
    initModality(Modality.APPLICATION_MODAL);
    setTitle(title);

    okButton.setOnAction(event -> okAction());

    var scene = createScene();
    setScene(scene);
    sizeToScene();
    setResizable(false);
  }

  /**
   * Sets the content of the dialog.
   *
   * @param content the content node to set
   */
  protected void setContent(@NonNull Node content) {
    content.getStyleClass().add("content-pane");
    migPane.add(content, "cell 0 1, align center");
  }

  /**
   * The default action for the OK button. This method can be overridden by subclasses to provide
   * custom behavior.
   */
  protected void okAction() {
    close();
  }

  private Scene createScene() {
    var scene = new Scene(migPane);
    return scene;
  }

  public Button getOkButton() {
    return okButton;
  }
}
