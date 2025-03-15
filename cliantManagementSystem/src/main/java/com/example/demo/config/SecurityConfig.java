package com.example.demo.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;


// SpringSecurity5.3以降ではWebSecurityConfigurerAdapterを継承して configure(WebSecurity) をオーバーライドしなくなった。
// WebSecurityConfiguraAdapterは削除された。
// 
@Configuration
@EnableWebSecurity
@ComponentScan
@RequiredArgsConstructor
public class SecurityConfig {
	
	// @Serviceを付けたCustomeDetailsServiceがインジェクションされる
	private final UserDetailsService userDetailsService;

	// requestMatchersでURLの認可処理を導入できる。
	@Bean
	protected SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {

		http.formLogin(login -> login
                .loginProcessingUrl("/login")
                .loginPage("/login").defaultSuccessUrl("/")
                .failureUrl("/login?error").permitAll()
        ).logout(logout -> logout
                .logoutSuccessUrl("/login")
        ).authorizeHttpRequests(authz -> authz
        		.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/login").permitAll()
//                .requestMatchers("/general").hasRole("USER")
                .requestMatchers("/cliants/**").hasRole("ADMIN")
                .requestMatchers("/contacts/**").hasRole("ADMIN")
                .requestMatchers("/users/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        );
		return http.build();
	}
		
//	private void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService)
//				.passwordEncoder(passwordEncoder());
//	}

	private void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

    //Spring Security 5.0.0以降では、PasswordEncoderの設定が必須です。
	//エンコードする際はBCryptPasswordEncoder()	等私用しましょう。
	//
	@Bean
	public PasswordEncoder passwordEncoder() {
		// NoOpPasswordEncoderを使用すると渡された文字列を一切ハッシュ化・暗号化せず、認証時にも平文のまま比較します。
		// Spring Security 5.0以降も使用は可能。ただし非推奨。
		return NoOpPasswordEncoder.getInstance();
	}

}
