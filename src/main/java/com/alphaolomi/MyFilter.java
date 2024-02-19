package com.alphaolomi;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

class MyFilter implements Filter {
    @Override
    public boolean isLoggable(LogRecord record) {
        // Example filter: only log INFO and above
        // Don't log CONFIG logs in file
        return (record.getLevel().intValue() >= Level.INFO.intValue())
                && (record.getLevel() != Level.CONFIG);


    }
}
