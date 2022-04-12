package com.example.springwebforms.controller;

import com.example.springwebforms.loggers.EventLogger;
import com.example.springwebforms.loggers.event.Event;
import com.example.springwebforms.loggers.event.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired private EventLogger eventLogger;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Event event = Event.level(EventType.INFO).that(request.getRemoteHost() + " called index page").now();
        eventLogger.logEvent(event);

        return "index";
    }
}
