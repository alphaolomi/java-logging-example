package com.alphaolomi;


import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

class MyStreamHandler extends StreamHandler {
    @Override
    public void publish(LogRecord record) {
        // Custom publishing logic
        if (getFilter() == null || getFilter().isLoggable(record)) {
            System.out.println(getFormatter().format(record));
        }
        super.publish(record);
    }

    @Override
    public void flush() {
        // Flush any buffered output
        super.flush();
    }

    @Override
    public void close() throws SecurityException {
        // Clean up resources
        super.close();
    }
}