package com.example.Spring2;

import com.example.Spring2.db.Product;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value="session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private List<CartProduct> cartProductList = new ArrayList<>();
    private int amount = 0;
    private double sum = 0;

    public void addProduct(Product product){
        boolean notFound = true;
        for(CartProduct cp: cartProductList) {
            if(cp.getProduct().getId().equals(product.getId())){
                notFound= false;
                cp.increaseAmount();
                recalculatePriceAndAmount();
                break;
            }
        }
        if(notFound){
            cartProductList.add(new CartProduct(product));
            recalculatePriceAndAmount();
        }

    }
    public void removeProduct(Product product){
        for(CartProduct cp : cartProductList){
            if(cp.getProduct().getId().equals(product.getId())){
                cp.decreaseAmount();
                if(cp.getAmount() == 0){
                    cartProductList.remove(cp);
                    recalculatePriceAndAmount();
                }
                break;
            }
        }

    }
    private void recalculatePriceAndAmount(){
        int tempAmount =0;
        double tempPrice = 0;

        for(CartProduct cp : cartProductList){
            tempAmount += cp.getAmount();
            tempPrice += cp.getPrice();
        }
        this.amount = tempAmount;
        this.sum = tempPrice;

    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getSum() {
        return sum;
    }
}
