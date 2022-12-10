package com.masai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

	 @Bean
	 public SecurityFilterChain masaiSecurityConfig(HttpSecurity http) throws Exception {
	
			http.authorizeHttpRequests( (auth)->auth
				.requestMatchers("/masaimail/welcome","/masaimail/register").permitAll()
				.requestMatchers("/masaimail/**").authenticated()
//				.requestMatchers("/admin/**").hasAuthority("ADMIN")
					
			).csrf().disable()
			.httpBasic();
	
			return http.build();
	  }
	 
	 @Bean
	 public PasswordEncoder PasswordEncoder() {
		// TODO Auto-generated method stub
		return new BCryptPasswordEncoder();
	 }
	
	
}
