package com.cos.photogramstart.domain.image;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.subscribe.Subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // DB에 테이블을 생성
public class Image {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다
	private int Id;
	private String caption;
	private String postImageUrl; // 사진을 전송받아서 사진을 서버에 특정 폴더에 저장 - DB에 그 저장된 경로를 insert

	@JoinColumn(name="userId")
	@ManyToOne
	private User user;

	private LocalDateTime createDate;

	@PrePersist // DB에 INSERT 되기 직전에 실행
	private void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
}
