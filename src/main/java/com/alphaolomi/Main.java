package com.alphaolomi;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.*;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

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




