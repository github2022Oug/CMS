package com.example.demo.domain.auth;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	
	private final UserRepository userRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		if ("tom".equals(username)) {
//			return new CustomUserDetails("tom", "password", Collections.emptyList());
//		}
//		throw new UsernameNotFoundException(
//				"Given username is not found. (username = '" + username + "')"
//		);
		return userRepository.findByUsername(username)
				.map(
						user -> new CustomUserDetails(
								user.getUsername(),
								user.getPassword(),
								Collections.emptyList()
//								toGrantedAuthorityList(user.getAuthority())
						)
				)
				.orElseThrow(
						() -> new UsernameNotFoundException(
								"Given username is not found. (username = '" + username + "')"
						)
				);
		
	}
		
//	private List<GrantedAuthority> toGrantedAuthorityList(User.Authority authority) {
//		return Collections.singletonList(new SimpleGrantedAuthority(authority.name()));
//	}
		
	
	
}
