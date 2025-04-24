package com.upeu.wom_matricula;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WomMatriculaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WomMatriculaApplication.class, args);
	}

}
