package com.example.Spring2.data;

public class ProductDTO {
    private Long id;
    private String name;
    private double price;

    public ProductDTO(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    // Wygeneruj konstruktor, gettery i settery
}