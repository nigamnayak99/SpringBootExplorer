package com.nigam.springbootexplorer.classes;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@AllArgsConstructor
@NoArgsConstructor
@ConditionalOnProperty(name = "bean.creation.stereotype")
public class Apple {

    private String color = "Red";
    private double price = 180.00d;
    private String taste = "Moderate Sweet";


    @PostConstruct
    public void afterCreation() {
        if(isPostConstructEnabled()) System.out.println("Apple: Post Construct");
    }

    @PreDestroy
    public void beforeDestroy() {
        if (isPreDestroyEnabled()) System.out.println("Apple: Pre Destroy");
    }

    private boolean isPostConstructEnabled() {
        // Add logic to read the "bean.show.message.postConstruct" property dynamically
        return Boolean.parseBoolean(System.getProperty("bean.show.message.postConstruct", "false"));
    }

    private boolean isPreDestroyEnabled() {
        // Add logic to read the "bean.show.message.preDestroy" property dynamically
        return Boolean.parseBoolean(System.getProperty("bean.show.message.preDestroy", "false"));
    }

}
