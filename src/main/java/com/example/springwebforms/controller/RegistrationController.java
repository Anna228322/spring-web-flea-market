package com.example.springwebforms.controller;

import com.example.springwebforms.models.Role;
import com.example.springwebforms.models.User;
import com.example.springwebforms.loggers.EventLogger;
import com.example.springwebforms.loggers.event.Event;
import com.example.springwebforms.loggers.event.EventType;
import com.example.springwebforms.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired private UserRepo userRepo;
    @Autowired private EventLogger eventLogger;

    @GetMapping("/registration")
    public String registration(HttpServletRequest request) {
        Event event = Event.level(EventType.INFO).that(request.getRemoteHost() + " entered the registration page").now();
        eventLogger.logEvent(event);

        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(User user, Map<String, Object> model, HttpServletRequest request) {
        Event event = Event.level(EventType.INFO).that(request.getRemoteHost() + " made request to register new user").now();
        eventLogger.logEvent(event);

        User userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null) {
            model.put("message", "User already exists");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/main";
    }
}
