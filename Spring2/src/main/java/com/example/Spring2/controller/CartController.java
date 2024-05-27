package com.example.Spring2.controller;

import com.example.Spring2.db.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    private final ProductRepository cart;

    @Autowired
    public CartController(ProductRepository cart) {
        this.cart = cart;
    }

    @GetMapping("/cart")
    public String index(){
        return "cart";
    }

//    @GetMapping("/addToCart/{id}")
//    public String addToCart(@PathVariable("id") Long id) {
//        cart.save(id);
//        return "redirect:/admin/";
//    }
}
