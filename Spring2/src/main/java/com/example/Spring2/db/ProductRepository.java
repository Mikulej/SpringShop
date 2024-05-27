package com.example.Spring2.db;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    // Dodatkowe metody zapytań mogą być dodane tutaj
    List<Product> findAll();

    List<Product> findAll(Sort sort);

    // Find products with price greater than a specified value
    List<Product> findByPriceGreaterThan(double price, Sort sort);

    // Find products with price less than a specified value
    List<Product> findByPriceLessThan(double price, Sort sort);

    // Find products with price between than specified values
    List<Product> findByPriceBetween(double minPrice, double maxPrice, Sort sort);
}