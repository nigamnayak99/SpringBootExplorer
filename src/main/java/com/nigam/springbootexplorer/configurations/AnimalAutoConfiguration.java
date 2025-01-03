package com.nigam.springbootexplorer.configurations;

import com.nigam.springbootexplorer.classes.Animal;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class AnimalAutoConfiguration {

    @Bean
    Animal animal() {
        return new Animal();
    }
}
