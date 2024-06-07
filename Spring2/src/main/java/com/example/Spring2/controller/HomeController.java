package com.example.Spring2.controller;

import com.example.Spring2.Cart;
import com.example.Spring2.CartProduct;
import com.example.Spring2.ProductOperation;
import com.example.Spring2.data.ProductDTO;
import com.example.Spring2.db.Product;
import com.example.Spring2.db.ProductRepository;
import com.example.Spring2.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private final CartService cartService;
    @Autowired
    public HomeController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String index(@RequestParam(value = "sortBy", required = false) String sortBy,
                        @RequestParam(value = "minPrice", required = false) Double minPrice,
                        @RequestParam(value = "maxPrice", required = false) Double maxPrice,
                        Model model) {
        List<Product> products = new ArrayList<>();

        Sort sort = Sort.by(sortBy != null ? sortBy : "name");

        if (minPrice != null && maxPrice != null) {
            products = cartService.findByPriceBetween(minPrice,maxPrice,sort);
        } else if (minPrice != null) {
            products = cartService.findByPriceGreaterThan(minPrice,sort);
        } else if (maxPrice != null) {
            products = cartService.findByPriceLessThan(maxPrice,sort);
        } else {
            products = cartService.getAllProducts(sort);
        }
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/add/{productId}")
    public String addItemToCart(@PathVariable("productId") Long productId,Model model){
        cartService.productOperation(productId, ProductOperation.INCREASE);
        model.addAttribute("products",cartService.getAllProducts());
        return "index";
    }

    private ProductDTO convertToDto(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(),product.getAmount());
    }
}