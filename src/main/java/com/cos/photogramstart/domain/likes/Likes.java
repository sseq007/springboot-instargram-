package com.cos.photogramstart.domain.likes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(
		uniqueConstraints = {
				@UniqueConstraint(
						name = "likes_uk",
						columnNames= {"imageId", "userId"})
		}
	)
public class Likes {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다
	private int Id;
	
	//무한 참조됨
	@JoinColumn(name = "imageId")
	@ManyToOne //Many to One은 기본전략이 EAGER
	private Image image;
	
	@JsonIgnoreProperties({"images"})
	@JoinColumn(name = "userId")
	@ManyToOne
	private User user;
	
	private LocalDateTime createDate;
	@PrePersist // DB에 INSERT 되기 직전에 실행
	private void createDate() {
		this.createDate=LocalDateTime.now();
	}
}
