package com.ketrika.ledgerview.views;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Main application view. This class sets up the main window, including the menu bar, content pane,
 * and footer.
 */
public class MainView extends Application {

  private static final String TITLE = "Open Ledger Balance";

  private static final double WINDOW_WIDTH = 900;
  private static final double WINDOW_HEIGHT = 700;

  private Stage primaryStage;

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.primaryStage = primaryStage;
    initializeStage();
  }

  private void initializeStage() {
    primaryStage.setTitle(TITLE);

    var root = new BorderPane();

    var scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);

    primaryStage.show();
  }

  @Override
  public void stop() {
    Platform.exit();
  }

}
