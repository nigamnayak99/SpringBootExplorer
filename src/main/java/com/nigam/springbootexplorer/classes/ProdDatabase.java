package com.nigam.springbootexplorer.classes;

import com.nigam.springbootexplorer.interfaces.Database;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@ConditionalOnProperty(name ="spring.profile", havingValue = "prod")
public class ProdDatabase implements Database {
    String databaseName = "Prod";
    String userName = "Prod_Nigam";
    String password = "prod-pass";
}
