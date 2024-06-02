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
            new Product("Ciastko",1.2,1),
                new Product("Slodycze",5.2,2),
                new Product("Mleko",4.4,3)
        ));
    }
}
