package com.example.springwebforms.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String name;
    private String description;
    private String phone;
    private double price;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Category> categories;

    public Product(User author, String name, String description, double price, String phone, Set<Category> categories) {
        this.author = author;
        this.name = name;
        this.price = price;
        this.description = description;
        this.phone = phone;
        this.categories = categories;
    }

    protected Product() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Category> getCategories() {
        return categories;
    }
    public void setCategories(Set<Category> questions) {
        this.categories = questions;
    }
}
