package com.example.demo.domain.auth;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserRepository {
	
	@Select("SELECT * from users WHERE username = #{username}")
	Optional<User> findByUsername(String username);
	
	@Select("select * from users")
	List<User> findAll();
	
	@Insert("insert into users (username, password, authority) values (#{username}, #{password}, #{authority})")
	void create(String username, String password, String authority);
}
