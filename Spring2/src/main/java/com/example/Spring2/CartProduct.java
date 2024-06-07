package com.example.Spring2;

import com.example.Spring2.db.Product;
public class CartProduct {
    private Product product;
    private double price;
    private int amount;

    public CartProduct(Product product){
        this.product = product;
        this.price =product.getPrice();
        this.amount= 1;
    }

    public void increaseAmount(){
        amount++;
        price = product.getPrice() * amount;
    }
    public void decreaseAmount(){
        if(amount > 0){
            amount--;
            price = product.getPrice() * amount;
        }

    }
    public boolean idEquals(Product product){
        return this.product.getId().equals((product.getId()));
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
