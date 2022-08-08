package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service //1. Ioc 2. 트랙젼션 관리
public class AuthService {

	private final UserRepository userrepository;
	
	public User 회원가입(User user) {
		
		User userEntity =userrepository.save(user);
		return userEntity;
	}
}
