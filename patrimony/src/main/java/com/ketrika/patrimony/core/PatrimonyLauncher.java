package com.ketrika.patrimony.core;

import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * Main launcher for the Patrimony CLI application.
 */
@Command(name = "patrimony-cli", mixinStandardHelpOptions = true,
    version = "Patrimony CLI 1.0",
    description = "Manage patrimony via Json import/export.",
    subcommands = {ImportCommand.class})
public class PatrimonyLauncher implements Callable<Integer> {
  private static final Logger LOGGER = LogManager.getLogger(PatrimonyLauncher.class);

  public static void main(String[] args) {
    int exitCode = new CommandLine(new PatrimonyLauncher()).execute(args);
    System.exit(exitCode);
  }

  @Override
  public Integer call() {
    LOGGER.warn("Use a subcommand: import or export. See --help for details.");
    return 0;
  }
}
