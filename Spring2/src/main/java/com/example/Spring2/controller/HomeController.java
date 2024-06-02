package com.example.Spring2.controller;

import com.example.Spring2.data.ProductDTO;
import com.example.Spring2.db.Product;
import com.example.Spring2.db.ProductRepository;
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

    @GetMapping("/add/{productId}")
    public String addItemToCart(@PathVariable("productId") Long productId,Model model, HttpSession session){
        @SuppressWarnings("unchecked")

        List<Product>  cart = (List<Product>)session.getAttribute("cart");
        if(cart==null){
            cart = new ArrayList<>();
        }
        Optional<Product> oProduct = productRepository.findById(productId);
        if(oProduct.isPresent()){
            Product product =  oProduct.get();
            //check if product was in cart already, if yes increment amount, if no just add new product
            cart.add(product);
            session.setAttribute("cart",cart);
        }
        model.addAttribute("products",productRepository.findAll());
        return "index";
    }
    private ProductDTO convertToDto(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(),product.getAmount());
    }
}