package com.example.Spring2.controller;

import com.example.Spring2.data.ProductDTO;
import com.example.Spring2.db.Product;
import com.example.Spring2.db.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductRepository productRepository;

    @Autowired
    public AdminController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Wyświetlanie listy produktów
    @GetMapping("/")
    public String productList(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("newProduct", new ProductDTO(4L,"Pizza",20));
        return "admin";
    }
    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        productRepository.save(product);
        return "redirect:/admin/";
    }

    // Metoda pomocnicza do mapowania DTO na encję
    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        return product;
    }
    // Wyświetlanie formularza edycji produktu
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            ProductDTO productDTO = convertToDto(product);
            model.addAttribute("product", productDTO);
            return "edit-product";
        } else {
            return "redirect:/admin/";
        }
    }

    // Obsługa edycji produktu
    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, @ModelAttribute("product") ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        product.setId(id);
        productRepository.save(product);
        return "redirect:/admin/";
    }

    // Usuwanie produktu
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/admin/";
    }

    // ...metody pomocnicze

    private ProductDTO convertToDto(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }
}