package com.example.springwebforms.loggers;

import com.example.springwebforms.loggers.event.Event;

public interface EventLogger {

    void logEvent(Event event);

    String getName();

}
