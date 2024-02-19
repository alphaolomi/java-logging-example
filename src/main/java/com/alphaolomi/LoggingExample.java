package com.alphaolomi;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggingExample {

    static Logger logger = Logger.getLogger(LoggingExample.class.getName());

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("logging.properties"));
        } catch (SecurityException | IOException e1) {
            // e1.printStackTrace();
            logger.log(Level.SEVERE, "Error in loading configuration", e1);
        }
        logger.setLevel(Level.FINE);
        logger.addHandler(new ConsoleHandler());
        //adding custom handler
        logger.addHandler(new MyHandler());
        try {
            // FileHandler file name with max size and number of log files limit
            // String pattern = "%h/java%u.log";
            String pattern = "%h/java%u.log";
            // Handler fileHandler = new FileHandler("/Users/pankaj/temp/logger.log", 2000, 5);
            Handler fileHandler = new FileHandler(pattern, 2000, 5);
            fileHandler.setFormatter(new MyFormatter());
            // setting custom filter for FileHandler
            fileHandler.setFilter(new MyFilter());
            logger.addHandler(fileHandler);

            for (int i = 0; i < 1000; i++) {
                //logging messages
                logger.log(Level.INFO, "Msg" + i);
            }
            logger.log(Level.CONFIG, "Config data");
        } catch (SecurityException | IOException e) {
            // e.printStackTrace();
            logger.log(Level.SEVERE, "Error occur in FileHandler.", e);
        }
    }

}