package com.example.Spring2.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DbInit implements CommandLineRunner {
    private final ProductRepository ProductRepository;

    @Autowired
    public DbInit(ProductRepository ProductRepository){
        this.ProductRepository = ProductRepository;
    }
    @Override
    public void run(String... args) throws Exception{
        ProductRepository.saveAll(List.of(
            new Product("Ciastko",1.2,17),
                new Product("Slodycze",5.2,21),
                new Product("Mleko",4.4,30),
            new Product("Jajka",4.49,44),
                new Product("Mąka",7.81,62),
                new Product("Cukier",2.97,54)
        ));
    }
}
