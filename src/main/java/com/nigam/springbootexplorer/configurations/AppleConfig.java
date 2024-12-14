package com.nigam.springbootexplorer.configurations;


import com.nigam.springbootexplorer.classes.Apple;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppleConfig {


    @Bean
//    @Scope("singleton")
    @Scope("prototype")
    Apple getApple() {
        return new Apple("green", 500.00d, "Super Sweet");}

}
