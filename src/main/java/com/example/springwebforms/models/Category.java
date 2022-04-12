package com.example.springwebforms.models;

import javax.persistence.*;

@Entity
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String username) {
        this.name = username;
    }
}
