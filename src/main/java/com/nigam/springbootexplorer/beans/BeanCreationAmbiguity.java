package com.nigam.springbootexplorer.beans;

import com.nigam.springbootexplorer.services.DatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BeanCreationAmbiguity implements CommandLineRunner {

    private final DatabaseService databaseService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(databaseService.getDatabase());
    }
}
