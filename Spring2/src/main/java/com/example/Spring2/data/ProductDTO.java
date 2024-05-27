package com.example.Spring2.data;

public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private  int amount;

    public ProductDTO(Long id, String name, double price,int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String id) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
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
}