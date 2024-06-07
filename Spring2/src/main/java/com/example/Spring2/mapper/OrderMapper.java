package com.example.Spring2.mapper;

import com.example.Spring2.Cart;
import com.example.Spring2.CartProduct;
import com.example.Spring2.data.OrderDTO;
import com.example.Spring2.db.order.Order;
import com.example.Spring2.db.order.OrderProduct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static Order mapToOrder(OrderDTO orderDTO){
        return Order.builder()
                .firstName(orderDTO.getFirstName())
                .lastName(orderDTO.getLastName())
                .address(orderDTO.getAddress())
                .postCode(orderDTO.getPostCode())
                .city(orderDTO.getCity())
                .created(LocalDateTime.now())
                .build();
    }
    public static List<OrderProduct> mapToOrderProductList(Cart cart, Order order){
        List<OrderProduct> orderProductList = new ArrayList<>();
        for(CartProduct cp : cart.getCartProductList()){
            orderProductList.add(new OrderProduct(order.getOrderId(),cp.getProduct().getId(), cp.getAmount()));
        }
        return orderProductList;
    }
}
