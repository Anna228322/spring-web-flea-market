package com.example.springwebforms.controller;

import com.example.springwebforms.loggers.EventLogger;
import com.example.springwebforms.loggers.event.Event;
import com.example.springwebforms.loggers.event.EventType;
import com.example.springwebforms.models.Category;
import com.example.springwebforms.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired private EventLogger eventLogger;
    @Autowired private CategoryRepo categoryRepo;

    @GetMapping
    public String getCategories(Model model, HttpServletRequest request) {
        var event = Event.level(EventType.INFO).that(request.getRemoteHost() + " requested all categories").now();
        eventLogger.logEvent(event);

        var categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);

        return "categories";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getNewCategoryForm(Model model, HttpServletRequest request) {
        var event = Event.level(EventType.INFO).that(request.getRemoteHost() + " opened new category form").now();
        eventLogger.logEvent(event);

        return "newCategory";
    }

    @PostMapping("/add")
    public String addCategory(
        @RequestParam String categoryName,
        HttpServletRequest request
    ) {
        var event = Event.level(EventType.INFO).that(request.getRemoteHost() + " added category: " + categoryName).now();
        eventLogger.logEvent(event);

        var category = new Category();
        category.setName(categoryName);
        categoryRepo.save(category);

        return "redirect:/category";
    }
}
