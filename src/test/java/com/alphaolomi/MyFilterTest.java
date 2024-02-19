package com.alphaolomi;

import org.junit.jupiter.api.Test;

import java.util.logging.LogRecord;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MyFilterTest {

    @Test
    void testIsLoggable() {
        MyFilter filter = new MyFilter();
        LogRecord infoRecord = new LogRecord(Level.INFO, "Info message");
        LogRecord fineRecord = new LogRecord(Level.FINE, "Fine message");

        assertTrue(filter.isLoggable(infoRecord), "INFO level messages should be loggable");
        assertFalse(filter.isLoggable(fineRecord), "FINE level messages should not be loggable");
    }
}