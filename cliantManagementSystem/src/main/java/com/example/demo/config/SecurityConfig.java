package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@ComponentScan
@RequiredArgsConstructor
public class SecurityConfig<AuhenticationManagerBuider> {
	
	private final UserDetailsService userDetailsService;

	@Bean
	protected SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
		// for h2-console
//		http.authorizeHttpRequests(
//			.requestMatchers("/login/**").permitAll()
//			.requestMatchers("/users/**").hasAuthority("ADMIN")
//			.anyRequest().authenticated()
//			.and()
//			.formLogin()
//			.loginPage("login");

		http.formLogin(login -> login
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error").permitAll()
        ).logout(logout -> logout
                .logoutSuccessUrl("/login")
        ).authorizeHttpRequests(authz -> authz
                .requestMatchers("/login").permitAll()
                .anyRequest().permitAll()  //.authenticated()
        );
		return http.build();
	}
		
//	private void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService)
//				.passwordEncoder(passwordEncoder());
//	}
	
	
	private void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}

	

	@Bean
	public PasswordEncoder passwordEncoder() {        
        return new BCryptPasswordEncoder();
	}
	
	
	


	
}
