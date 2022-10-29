package com.cos.photogramstart.domain.comment;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // DB에 테이블을 생성
public class Comment {
	
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다
	private int id;
	
	@Column(length = 100,nullable = false)
	private String content;
	
	@JsonIgnoreProperties({"images"})
	@JoinColumn(name = "userId")
	@ManyToOne(fetch = FetchType.EAGER) // defalt 값이 EAGER 딸려오는것이 하나이면 EAGER 딸려오는것이 많으면 LAZY
	private User user;
	
	@JoinColumn(name = "imageId")
	@ManyToOne(fetch = FetchType.EAGER)
	private Image image;
	
	
	private LocalDateTime createDate;

	@PrePersist // DB에 INSERT 되기 직전에 실행
	private void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
