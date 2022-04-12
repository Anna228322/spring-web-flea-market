package com.example.springwebforms.controller;

import com.example.springwebforms.models.Role;
import com.example.springwebforms.models.User;
import com.example.springwebforms.loggers.EventLogger;
import com.example.springwebforms.loggers.event.Event;
import com.example.springwebforms.loggers.event.EventType;
import com.example.springwebforms.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EventLogger eventLogger;

    @GetMapping
    public String userList(Model model, HttpServletRequest request) {
        eventLogger.logEvent(Event.level(EventType.INFO).that(request.getRemoteHost() + " saw users list").now());

        var users = userRepo.findAll();
        model.addAttribute("users", users);

        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model, HttpServletRequest request) {
        eventLogger.logEvent(Event.level(EventType.INFO).that(request.getRemoteHost() + " editing user " + user.getId() + " " + user.getUsername()).now());

        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            Model model,
            @RequestParam("userId") User user,
            HttpServletRequest request) {
        eventLogger.logEvent(Event.level(EventType.INFO).that(request.getRemoteHost() + " edited user " + user.getId() + " " + user.getUsername()).now());

        user.setUsername(username);
        var roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.setRoles(new HashSet<>());
        for (var key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        if (user.getRoles().isEmpty()) {
            model.addAttribute("message", "У пользователя должна быть роль!");
            model.addAttribute("user", user);
            model.addAttribute("roles", Role.values());
            return "userEdit";
        }

        userRepo.save(user);

        return "redirect:/user";
    }
}
