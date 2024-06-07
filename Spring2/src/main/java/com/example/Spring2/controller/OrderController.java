package com.example.Spring2.controller;

import com.example.Spring2.ProductOperation;
import com.example.Spring2.data.OrderDTO;
import com.example.Spring2.db.order.OrderRepository;
import com.example.Spring2.service.CartService;
import com.example.Spring2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final CartService cartService;
    private final OrderService orderService;
    @Autowired
    public OrderController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping("/cart")
    public String showCart(){

        return "cartView";
    }
    @GetMapping("/increase/{productId}")
    public String increaseProductAmount(@PathVariable("productId") Long productId){
        cartService.productOperation(productId,ProductOperation.INCREASE);
        return "cartView";
    }

    @GetMapping("/decrease/{productId}")
    public String decreaseProductAmount(@PathVariable("productId") Long productId){
        cartService.productOperation(productId,ProductOperation.DECREASE);
        return "cartView";
    }
    @GetMapping("/remove/{productId}")
    public String removeProductsFromCart(@PathVariable("productId") Long productId){
        cartService.productOperation(productId, ProductOperation.REMOVE);
        return "cartView";
    }
    @GetMapping("/summary")
    public String showSummary(){
        return "summary";
    }
    @PostMapping("/saveorder")
    public String saveOrder(OrderDTO orderDTO){
        orderService.saveOrder(orderDTO);
        return "redirect:/";
    }

}
