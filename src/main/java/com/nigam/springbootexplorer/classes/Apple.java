package com.nigam.springbootexplorer.classes;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Component
public class Apple {

    private String color = "red";
    private double price = 180.00d;
    private String taste = "Moderate Sweet";

    @PostConstruct
    public void afterCreation() {
        System.out.println("Apple: Post Construct");
    }

    @PreDestroy
    public void beforeDestroy() {
        System.out.println("Apple: Pre Destroy");
    }

}
