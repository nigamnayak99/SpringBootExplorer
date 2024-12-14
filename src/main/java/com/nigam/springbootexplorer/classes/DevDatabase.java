package com.nigam.springbootexplorer.classes;


import com.nigam.springbootexplorer.interfaces.Database;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Getter
@Primary
@ToString
@ConditionalOnProperty(name ="spring.profile", havingValue = "dev")
public class DevDatabase implements Database {

    String databaseName = "Dev";
    String userName = "Dev_Nigam";
    String password = "dev-pass";

}
