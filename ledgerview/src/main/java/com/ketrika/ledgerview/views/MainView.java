package com.ketrika.ledgerview.views;

import com.ketrika.ledgerview.models.ConfirmationDialogModel;
import com.ketrika.ledgerview.presenters.ConfirmationDialogPresenter;
import com.ketrika.ledgerview.utils.TaskExecutor;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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

  private static final double WINDOW_WIDTH = 900d;
  private static final double WINDOW_HEIGHT = 700d;

  private ConfirmationDialogView confirmationDialog;

  private Stage primaryStage;
  private TaskExecutor taskExecutor;

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

    var root = new BorderPane();
    root.setTop(createTopPane());

    var scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
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
    topPane.setPadding(new Insets(10));
    topPane.setAlignment(Pos.CENTER_LEFT);
    topPane.setSpacing(10);

    MenuBar menuBar = createMenuBar();
    HBox.setHgrow(menuBar, Priority.ALWAYS);

    return topPane;
  }

  /**
   * Creates the menu bar for the application.
   *
   * @return the menu bar
   */
  private MenuBar createMenuBar() {
    var menuBar = new MenuBar();
    menuBar.getStyleClass().add("menu-bar");

    var menu = new Menu("Menu");
    menu.getItems().add(createMenuItem("menuitem", FontAwesomeIcon.COG, null));

    menuBar.getMenus().addAll(menu);

    return menuBar;
  }

  private MenuItem createMenuItem(String text, FontAwesomeIcon icon, EventHandler<ActionEvent> eventHandler) {
    var menuItem = new MenuItem(text);
    menuItem.setGraphic(new FontAwesomeIconView(icon));
    if (eventHandler != null) {
      menuItem.setOnAction(eventHandler);
    }
    return menuItem;
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
