package com.ketrika.ledgerview.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ketrika.ledgerview.views.MainView;
import javafx.application.Application;
import javafx.application.Platform;

/**
 * UserInterface class to launch the MainView application. This class contains the main method to
 * run the application.
 *
 * <p>
 * JavaFX Application initialization is handled in this class.
 * </p>
 */
public class LedgerView {

  private static final Logger LOGGER = LogManager.getLogger(LedgerView.class);

  /**
   * The main method to launch the JavaFX application.
   *
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    try {
      LOGGER.info("Running the application...");
      Application.launch(MainView.class, args);
    } catch (Exception e) {
      LOGGER.error("Failed to launch the application.", e);
      Platform.exit();
    }
  }
}
