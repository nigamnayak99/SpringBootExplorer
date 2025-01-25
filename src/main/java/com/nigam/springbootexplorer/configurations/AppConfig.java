package com.nigam.springbootexplorer.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper getModelMapperBean() {
        return new ModelMapper();
    }

    @Bean
    PasswordEncoder encode() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
