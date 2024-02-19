package com.alphaolomi;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

class MyFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        // Custom formatting - Option 1
        // return record.getLevel() + ": " + record.getMessage() + "\n";

        // Custom formatting - Option 2
        return record.getLongThreadID() + "::" + record.getSourceClassName() + "::"
                + record.getSourceMethodName() + "::"
                + new Date(record.getMillis()) + "::"
                + record.getMessage() + "\n";
    }
}