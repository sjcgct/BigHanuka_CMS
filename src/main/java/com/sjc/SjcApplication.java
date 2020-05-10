package com.sjc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
public class SjcApplication{

	public static void main(String[] args) {
		SpringApplication.run(SjcApplication.class, args);
	}

}
