package com.nigam.springbootexplorer.configurations;


import com.nigam.springbootexplorer.classes.Apple;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppleConfig {


    @Bean
    @ConditionalOnProperty(name = "bean.creation.configuration")
    Apple getApple() {
        return new Apple("green", 500.00d, "Super Sweet");
    }

}
