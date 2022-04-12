package com.example.springwebforms.controller;

import com.example.springwebforms.loggers.EventLogger;
import com.example.springwebforms.loggers.event.Event;
import com.example.springwebforms.loggers.event.EventType;
import com.example.springwebforms.models.Category;
import com.example.springwebforms.models.Product;
import com.example.springwebforms.models.User;
import com.example.springwebforms.repos.CategoryRepo;
import com.example.springwebforms.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired private EventLogger eventLogger;
    @Autowired private ProductRepo productRepo;
    @Autowired private CategoryRepo categoryRepo;

    @GetMapping
    public String getProducts(
        Model model,
        HttpServletRequest request
    ) {
        var event = Event.level(EventType.INFO).that(request.getRemoteHost() + " viewing products").now();
        eventLogger.logEvent(event);

        model.addAttribute("products", productRepo.findAll());
        return "productsList";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(
        @PathVariable Long id,
        Model model,
        HttpServletRequest request
    ) {
        var event = Event.level(EventType.INFO)
                .that(request.getRemoteHost() + " editing product with id: " + id)
                .now();
        eventLogger.logEvent(event);

        return "redirect:/product/add/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(
        @PathVariable Long id,
        Model model,
        HttpServletRequest request
    ) {
        var event = Event.level(EventType.INFO)
                .that(request.getRemoteHost() + " editing product with id: " + id)
                .now();
        eventLogger.logEvent(event);

        productRepo.deleteById(id);

        return "redirect:/product/own";
    }

    @GetMapping("/own")
    public String myProducts(
        @AuthenticationPrincipal User user,
        Model model,
        HttpServletRequest request
    ) {
        var event = Event.level(EventType.INFO)
                .that(request.getRemoteHost() + " viewing his own products")
                .now();
        eventLogger.logEvent(event);

        var products = Arrays.asList(productRepo.findAll().stream()
            .filter( p ->
                Objects.equals(p.getAuthor().getId(), user.getId())
            ).toArray());
        model.addAttribute("products", products);
        model.addAttribute("own", true);
        return "productsList";
    }

    @GetMapping("/search")
    public String searchProducts(
        @RequestParam String query,
        Model model,
        HttpServletRequest request
    ) {
        var event = Event.level(EventType.INFO)
                .that(request.getRemoteHost() + " searching products with query: " + query)
                .now();
        eventLogger.logEvent(event);

        var products = Arrays.asList(productRepo.findAll().stream()
                .filter(p ->
                    p.getName().toLowerCase().contains(query.toLowerCase()) ||
                    p.getDescription().toLowerCase().contains(query.toLowerCase())
                ).toArray());
        model.addAttribute("products", products);
        model.addAttribute("query", query);
        return "productsList";
    }

    @GetMapping("/filter/{categoryId}")
    public String getProductsByCategory(
        @PathVariable Long categoryId,
        Model model,
        HttpServletRequest request
    ) {
        var event = Event.level(EventType.INFO)
                .that(request.getRemoteHost() + " viewing products with category with id: " + categoryId)
                .now();
        eventLogger.logEvent(event);

        var category = categoryRepo.findById(categoryId);
        var products = category.isPresent() ?
                Arrays.asList(productRepo.findAll().stream()
                .filter(p -> p.getCategories().contains(category.get()))
                .toArray())
                : new ArrayList<Product>();
        model.addAttribute("products", products);
        model.addAttribute("category", category.orElse(null));
        return "productsList";
    }

    @GetMapping("/filter")
    public String filterProductsButCategories(
        Model model,
        HttpServletRequest request
    ) {
        var event = Event.level(EventType.INFO).that(request.getRemoteHost() + " filtering products").now();
        eventLogger.logEvent(event);

        model.addAttribute("categories", categoryRepo.findAll());
        return "categories";
    }

    @GetMapping("/add")
    public String getAddProductPage(
        @AuthenticationPrincipal User user,
        HttpServletRequest request
    ) {
        var event = Event.level(EventType.INFO).that(request.getRemoteHost() + " creating product").now();
        eventLogger.logEvent(event);

        var categories = new HashSet<Category>(categoryRepo.findAll());
        var product = new Product(user, "", "", 0, "", categories);
        productRepo.save(product);

        return "redirect:/product/add/" + product.getId();
    }

    @GetMapping("/add/{product}")
    public String formAddPage(
        @PathVariable Product product,
        Model model,
        HttpServletRequest request
    ) {
        var event = Event.level(EventType.INFO).that(request.getRemoteHost() + " modifying product").now();
        eventLogger.logEvent(event);

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepo.findAll());

        productRepo.save(product);

        return "newProduct";
    }

    @PostMapping("/add/{product}")
    public String saveProduct(
        @PathVariable Product product,
        @RequestParam String productName,
        @RequestParam Double productPrice,
        @RequestParam String productDescription,
        @RequestParam String productPhone,
        @RequestParam Map<String, String> categories,
        HttpServletRequest request
    ) {
        var event = Event.level(EventType.INFO).that(request.getRemoteHost() + " created product: " + productName).now();
        eventLogger.logEvent(event);

        product.setName(productName);
        product.setPrice(productPrice);
        product.setPhone(productPhone);
        product.setDescription(productDescription);

        var categoryEntries = new HashSet<Category>();
        for(var categoryKey : categories.keySet()) {
            if (categoryKey.contains("checkbox")) {
                var name = categoryKey.split("-")[1];
                var category = categoryRepo.findByName(name);
                var isChecked = Objects.equals(categories.get(categoryKey), "on");
                if (isChecked) {
                    categoryEntries.add(category);
                }
            }
        }

        product.setCategories(categoryEntries);
        productRepo.save(product);

        return "redirect:/product/own";
    }
}
