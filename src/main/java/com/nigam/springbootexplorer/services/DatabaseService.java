package com.nigam.springbootexplorer.services;

import com.nigam.springbootexplorer.interfaces.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    @Autowired
    Database database;

    public Database getDatabase(){
        return database;
    }
}
