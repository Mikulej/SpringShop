package com.example.Spring2.service;

import com.example.Spring2.Cart;
import com.example.Spring2.ProductOperation;
import com.example.Spring2.db.Product;
import com.example.Spring2.db.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final ProductRepository productRepository;
    private final Cart cart;

    @Autowired
    public CartService(ProductRepository productRepository, Cart cart) {
        this.productRepository = productRepository;
        this.cart = cart;
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public List<Product> getAllProducts(Sort sort){
        return productRepository.findAll(sort);
    }
    public List<Product> findByPriceLessThan(Double maxPrice,Sort sort){
        return productRepository.findByPriceLessThan(maxPrice,sort);
    }
    public List<Product> findByPriceGreaterThan(Double minPrice,Sort sort){
        return productRepository.findByPriceGreaterThan(minPrice,sort);
    }
    public List<Product> findByPriceBetween(Double minPrice,Double maxPrice,Sort sort){
        return productRepository.findByPriceBetween(minPrice,maxPrice,sort);
    }
//    public void addProductToCart(Long productId){
//        Optional<Product> oProduct = productRepository.findById(productId);
//        if(oProduct.isPresent()){
//            Product product =  oProduct.get();
//            cart.addProduct(product);
//        }
//    }
//    public void decreaseProductAmount(Long productId){
//        Optional<Product> oProduct = productRepository.findById(productId);
//        if(oProduct.isPresent()){
//            Product product =  oProduct.get();
//            cart.decreaseProduct(product);
//        }
//    }
//    public void removeProduct(Long productId){
//        Optional<Product> oProduct = productRepository.findById(productId);
//        if(oProduct.isPresent()){
//            Product product =  oProduct.get();
//            cart.removeAllProducts(product);
//        }
//    }
    public void productOperation(Long productId, ProductOperation productOperation){
        Optional<Product> oProduct = productRepository.findById(productId);
        if(oProduct.isPresent()){
            Product product =  oProduct.get();
            switch (productOperation){
                case INCREASE -> cart.addProduct(product);
                case DECREASE -> cart.decreaseProduct(product);
                case REMOVE -> cart.removeAllProducts(product);
                default -> throw new IllegalArgumentException();
            }
        }
    }
}
