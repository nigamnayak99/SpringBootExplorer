package com.nigam.springbootexplorer.beans;

import com.nigam.springbootexplorer.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BeanCreationAmbiguity implements CommandLineRunner {

    @Autowired
    DatabaseService databaseService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(databaseService.getDatabase());
    }
}
