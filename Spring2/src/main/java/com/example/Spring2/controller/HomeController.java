package com.example.Spring2.controller;

import com.example.Spring2.data.ProductDTO;
import com.example.Spring2.db.Product;
import com.example.Spring2.db.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private final ProductRepository productRepository;

    @Autowired
    public HomeController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String index(@RequestParam(value = "sortBy", required = false) String sortBy,
                        @RequestParam(value = "minPrice", required = false) Double minPrice,
                        @RequestParam(value = "maxPrice", required = false) Double maxPrice,
                        Model model) {
        List<Product> products;

        Sort sort = Sort.by(sortBy != null ? sortBy : "name");

        if (minPrice != null && maxPrice != null) {
            products = productRepository.findByPriceBetween(minPrice, maxPrice, sort);
        } else if (minPrice != null) {
            products = productRepository.findByPriceGreaterThan(minPrice, sort);
        } else if (maxPrice != null) {
            products = productRepository.findByPriceLessThan(maxPrice, sort);
        } else {
            products = productRepository.findAll(sort);
        }

        model.addAttribute("products", products);
        return "index";
    }
}