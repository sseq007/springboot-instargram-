package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//JPA - Java Persistence API 

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // DB에 테이블을 생성
public class User {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	private String username;
	private String password;
	
	private String name;
	private String website; // 웹사이트
	private String bio; // 자기소개
	private String email;
	private String phone;
	private String gender;
	
	private String profileImageUrl; //사진
	private String role; //권한
	private LocalDateTime createDate;
	
	@PrePersist // DB에 INSERT 되기 직전에 실행
	private void createDate() {
		this.createDate=LocalDateTime.now();
				}
}

