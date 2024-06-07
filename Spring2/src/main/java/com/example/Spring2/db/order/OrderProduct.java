package com.example.Spring2.db.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class OrderProduct {
    @Id
    @GeneratedValue
    private Long orderProductId;
    private Long orderId;
    private Long productId;
    private int amount;

    public OrderProduct(Long orderId, Long productId, int amount) {
        this.orderId = orderId;
        this.productId = productId;
        this.amount = amount;
    }
}
