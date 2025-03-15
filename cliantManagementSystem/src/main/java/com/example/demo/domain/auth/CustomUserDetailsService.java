package com.example.demo.domain.auth;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


// @Serviceを付けておくことでCustomeDetailsServiceをSecurityConfigでインジェクションできる。
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	
	private final UserRepository userRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return userRepository.findByUsername(username)
				.map(
						user -> new CustomUserDetails(
								user.getUsername(),
								user.getPassword(),
								toGrantedAuthorityList(user.getAuthority())
						)
				)
				.orElseThrow(
						() -> new UsernameNotFoundException(
								"Given username is not found. (username = '" + username + "')"
						)
				);
		
	}

	// DBはADMIN、USERで登録しているのでSpringSecurityが正しく処理できるようにROLE_を付与。
	private List<GrantedAuthority> toGrantedAuthorityList(User.Authority authority) {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + authority.name()));
	}
		
	
	
}
