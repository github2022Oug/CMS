package com.example.demo.domain.auth;



import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
// 暗号化用
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	// 暗号化用
//	private final PasswordEncoder passwordEncoder;
	
	// メソッド呼び出しの認可処理
	// PreAuthorizeはMethodSecurityConfigクラスで有効化している。
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<User> findAll() {
		return userRepository.findAll();
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	public void create(String username, String password, String authority) {
		userRepository.create(username, password, authority);
		
		// 暗号化用
//		var encodedPassword = passwordEncoder.encode(password);
//		userRepository.create(username, encodedPassword, authority);
	}
}
