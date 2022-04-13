package com.Vassah.MyBank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyBankApplication {

	private static final Logger log = LoggerFactory.getLogger(MyBankApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(MyBankApplication.class, args);
	}

}