package com.ketrika.ledgerview.views;

import com.ketrika.ledgerview.models.ConfirmationDialogModel;
import com.ketrika.ledgerview.presenters.ConfirmationDialogPresenter;
import com.ketrika.ledgerview.utils.TaskExecutor;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * Main application view. This class sets up the main window, including the menu bar, content pane,
 * and footer.
 */
public class MainView extends Application {

  private static final String TITLE = "Open Ledger Balance";
  private static final String CONFIRM_ACTION = "Confirm Action";
  private static final String HOME_BTN = "Home";
  private static final String PATRIMONY_BTN = "Patrimony";
  private static final String EXPENSE_INCOME_BTN = "Expense - Income";

  private static final double WINDOW_WIDTH = 900d;
  private static final double WINDOW_HEIGHT = 700d;

  private ConfirmationDialogView confirmationDialog;

  private Stage primaryStage;
  private TaskExecutor taskExecutor;

  private final BorderPane contentPane = new BorderPane();

  @Override
  public void init() {
    taskExecutor = new TaskExecutor();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.primaryStage = primaryStage;
    initializeStage();
    createDialogs();
  }

  private void initializeStage() {
    primaryStage.setTitle(TITLE);

    contentPane.setTop(createTopPane());

    var scene = new Scene(contentPane, WINDOW_WIDTH, WINDOW_HEIGHT);
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);

    primaryStage.setOnCloseRequest(event -> {
      event.consume();
      if (showConfirmationDialog()) {
        primaryStage.close();
      }
    });

    primaryStage.show();
  }

  private void createDialogs() {
    confirmationDialog = new ConfirmationDialogView(primaryStage, CONFIRM_ACTION,
        new ConfirmationDialogPresenter(new ConfirmationDialogModel("Are you sure you want to proceed?")));
  }

  /**
   * Creates the top pane containing the menu bar.
   *
   * @return the top pane
   */
  private HBox createTopPane() {
    var topPane = new HBox();
    topPane.setAlignment(Pos.CENTER_LEFT);
    topPane.setSpacing(10);

    ToolBar toolBar = createToolBar();
    HBox.setHgrow(toolBar, Priority.ALWAYS);

    topPane.getChildren().addAll(toolBar);

    return topPane;
  }

  /**
   * Creates the tool bar for the application.
   *
   * @return the tool bar
   */
  private ToolBar createToolBar() {
    var toolBar = new ToolBar();
    toolBar.getStyleClass().add("tool-bar");

    Button homeBtn = createToolbarButton(
        HOME_BTN,
        FontAwesomeIcon.HOME);
    Button patrimonyBtn = createToolbarButton(
        PATRIMONY_BTN,
        FontAwesomeIcon.BANK);
    Button expenseIncomeBtn = createToolbarButton(
        EXPENSE_INCOME_BTN,
        FontAwesomeIcon.MONEY);
    toolBar.getItems().addAll(homeBtn, new Separator(), patrimonyBtn, expenseIncomeBtn);

    return toolBar;
  }

  private Button createToolbarButton(String text, FontAwesomeIcon iconType) {
    FontAwesomeIconView icon = new FontAwesomeIconView(iconType);
    icon.setGlyphSize(16);

    var button = new Button(text, icon);
    button.getStyleClass().add("toolbar-button");
    button.setContentDisplay(ContentDisplay.LEFT);
    button.setGraphicTextGap(6);
    button.setFocusTraversable(false);

    return button;
  }

  @Override
  public void stop() {
    Platform.exit();
  }

  /**
   * Shows the confirmation dialog to the user.
   *
   * @return true if the user confirmed, false otherwise
   */
  private boolean showConfirmationDialog() {
    confirmationDialog.showAndWait(); // Show the dialog and wait for it to close
    return confirmationDialog.isConfirmed();
  }

}
