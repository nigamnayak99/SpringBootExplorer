package com.nigam.springbootexplorer;

import com.nigam.springbootexplorer.classes.Animal;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootExplorerApplication implements CommandLineRunner {

	@Autowired
	Animal animal;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExplorerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(animal.toString());
	}
}
