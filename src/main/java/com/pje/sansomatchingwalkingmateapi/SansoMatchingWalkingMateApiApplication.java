package com.pje.sansomatchingwalkingmateapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SansoMatchingWalkingMateApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SansoMatchingWalkingMateApiApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder(); //패스워드 전달받아서(배달) 인코딩시켜주는애를 만들어주겠다.
	}

}
