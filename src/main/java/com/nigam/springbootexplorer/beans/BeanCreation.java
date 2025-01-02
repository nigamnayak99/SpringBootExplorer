package com.nigam.springbootexplorer.beans;

import com.nigam.springbootexplorer.classes.Apple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BeanCreation  implements CommandLineRunner {

    @Autowired
    Apple apple1;

    @Autowired
    Apple apple2;

    @Override
    public void run(String... args) throws Exception {
        if (showHashcode()) {
            System.out.println("Apple Bean created");
            System.out.println(apple1.hashCode());
            System.out.println(apple2.hashCode());
        }
    }

    private boolean showHashcode() {
        return Boolean.parseBoolean(System.getProperty("bean.show.hashcode", "false"));
    }
}
