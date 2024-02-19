package com.alphaolomi;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.logging.LogRecord;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyFormatterTest {

    @Test
    void testFormat() {
        MyFormatter formatter = new MyFormatter();
        LogRecord record = new LogRecord(Level.INFO, "Test message");
        // String expected = "INFO: Test message\n";
        String expected = record.getLongThreadID() + "::" + record.getSourceClassName() + "::"
                + record.getSourceMethodName() + "::"
                + new Date(record.getMillis()) + "::"
                + record.getMessage() + "\n";
        assertEquals(expected, formatter.format(record));
    }
}