package com.example.Spring2.service;

import com.example.Spring2.Cart;
import com.example.Spring2.CartProduct;
import com.example.Spring2.data.OrderDTO;
import com.example.Spring2.db.order.Order;
import com.example.Spring2.db.order.OrderProduct;
import com.example.Spring2.db.order.OrderProductRepository;
import com.example.Spring2.db.order.OrderRepository;
import com.example.Spring2.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final Cart cart;
    private final OrderRepository orderRepository;

    private final OrderProductRepository orderProductRepository;

    @Autowired
    public OrderService(Cart cart, OrderRepository orderRepository, OrderProductRepository orderProductRepository) {
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
    }

    public void saveOrder(OrderDTO orderDTO){
        Order order = OrderMapper.mapToOrder(orderDTO);
        orderRepository.save(order);
        orderProductRepository.saveAll(OrderMapper.mapToOrderProductList(cart,order));
        cart.clearCart();
    }
}
