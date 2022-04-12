package com.example.springwebforms.repos;

import com.example.springwebforms.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {}
