package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;


@EnableWebSecurity
@RequiredArgsConstructor
@ComponentScan
@Configuration
public class SecurityConfig<AuhenticationManagerBuider> {
	
	private final UserDetailsService userDetailsService;
//	private final PasswordEncoder passwordEncoder;


	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		// for h2-console
		http
		        .authorizeRequests()
		        .requestMatchers("/login/**").permitAll()
		        .anyRequest().authenticated()
		        .and()
		        .formLogin()
		        .loginPage("/login").permitAll();
		
		return http.build();
	}
		
//	private void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService)
//				.passwordEncoder(passwordEncoder());
//	}
	@Bean
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}

	

	@Bean
	public PasswordEncoder passwordEncoder() {
		
		
		// 秘密キー、イテレーション回数、ハッシュ幅、ソルト長を指定してインスタンスを生成
        String secretKey = "secret"; // 秘密キーは安全に管理する
        int iterationCount = 185000; // イテレーション回数
        int hashWidth = 256; // ハッシュ幅
        int saltLength = 128; // ソルト長
        
        return new Pbkdf2PasswordEncoder(secretKey, iterationCount, hashWidth, saltLength);
	}
	
	


	
}
