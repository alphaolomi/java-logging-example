package com.alphaolomi;

import org.junit.jupiter.api.Test;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
// import org.mockito.Mockito.*;
import static org.mockito.Mockito.*;
public class MyHandlerTest {

    @Test
    public void testPublish() {
        Handler handler = spy(new MyStreamHandler());
        MyFormatter formatter = mock(MyFormatter.class);
        MyFilter filter = mock(MyFilter.class);

        handler.setFormatter(formatter);
        handler.setFilter(filter);

        LogRecord record = new LogRecord(Level.INFO, "Test publish");

        when(filter.isLoggable(any(LogRecord.class))).thenReturn(true);
        handler.publish(record);

        verify(handler, times(1)).publish(record);
        verify(filter, times(1)).isLoggable(record);
    }
}
