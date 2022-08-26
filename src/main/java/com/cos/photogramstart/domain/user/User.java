package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다
	private int Id;
	
	
	 
	@Column( length = 20, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	private String website; // 웹사이트
	private String bio; // 자기소개
	@Column(nullable = false)
	private String email;
	private String phone;
	private String gender;
	
	private String profileImageUrl; //사진
	private String role; //권한
	//나는 연관관계의 주인이 아니다 테이블에 컬럼을 만들지마
	//user를 Select 할때 해당 User id로 등록된 image들을 가져와
	//Lazy = User를 Select할 때 해당 User Id로 등록된 images들을 가져오지마 - 대신 getimages() 함수가 호출될 때 가져와
	//EAGER = User를 Selec할 때 해당 User id로 등록된 images들을 전부 Join해서 가져와
	 @OneToMany(mappedBy = "user", fetch= FetchType.EAGER) 
	private List<Image> images; // 양방향 메핑
	
	private LocalDateTime createDate;
	
	@PrePersist // DB에 INSERT 되기 직전에 실행
	private void createDate() {
		this.createDate=LocalDateTime.now();
				}
}

