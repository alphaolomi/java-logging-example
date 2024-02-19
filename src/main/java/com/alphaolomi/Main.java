package com.alphaolomi;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.*;

/**
 * Main class to demonstrate logging in Java
 * The class sets up a custom logger with a custom handler and formatter
 *
 * @author Alpha
 * @version 1.0
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * Constructor for the Main class
     */
    public Main() {
        // This is a constructor
    }

    /*
      Static block to load the logging configuration
     */
    static {
        // Config file in the resources folder
        System.setProperty("java.util.logging.config.file", "src/main/resources/logging.properties");
        try (FileInputStream ins = new FileInputStream(System.getProperty("java.util.logging.config.file"))) {
            LogManager.getLogManager().readConfiguration(ins);
            logger.info("Loaded logging.properties configuration");
        } catch (SecurityException | IOException e) {
            logger.log(Level.SEVERE, "Error in loading configuration", e);
        }
    }

    /**
     * Method to set up the logger with custom handler and formatter
     */
    private static void setupLogger() {
        // Check if the ConsoleHandler is already added to avoid duplicate logs
        if (logger.getHandlers().length == 0) {
            logger.addHandler(new ConsoleHandler());
            // Custom handler setup
            Handler myHandler = new MyStreamHandler();
            myHandler.setFormatter(new MyFormatter());
            myHandler.setFilter(new MyFilter());
            logger.addHandler(myHandler);

            // FileHandler setup
            try {
                Handler fileHandler = new FileHandler("app.log");
                fileHandler.setFormatter(new MyFormatter());
                fileHandler.setFilter(new MyFilter());
                logger.addHandler(fileHandler);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error in setting up file handler", e);
            }

            // Avoid duplicate messages in console output
            for (Handler handler : logger.getParent().getHandlers()) {
                if (handler instanceof ConsoleHandler) {
                    handler.setLevel(Level.OFF);
                }
            }
        }
    }

    /**
     * Main method to demonstrate logging
     *
     * @param args command line arguments
     *             Not used in this example
     */
    public static void main(String[] args) {
        setupLogger();
        logger.fine("Starting application...");

        try {
            // Simulate application logic
            for (int i = 0; i < 10; i++) {
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine("Processing data " + i);
                }
            }
            logger.info("Application run completed.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error occurred", e);
        }
    }
}




