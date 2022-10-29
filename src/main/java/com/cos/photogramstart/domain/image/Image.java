package com.cos.photogramstart.domain.image;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.likes.Likes;
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
public class Image {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다
	private int Id;
	private String caption;
	private String postImageUrl; // 사진을 전송받아서 사진을 서버에 특정 폴더에 저장 - DB에 그 저장된 경로를 insert

	@JsonIgnoreProperties({"images"})
	@JoinColumn(name="userId")
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	
	//이미지 좋아요
	@JsonIgnoreProperties({"image"}) //image 무한 참조 방지
	@OneToMany(mappedBy="image")
	 private List<Likes> likes;
	
	@Transient // DB에 컬럼이 만들어지지 않는다
	 private boolean likeState;
	
	 //댓글
	@OrderBy("id DESC")
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy="image") // foregin 키 연관관계의 주인이 아니다
	private List<Comment> comments;
	
	@Transient
	private int likeCount;

	private LocalDateTime createDate;

	@PrePersist // DB에 INSERT 되기 직전에 실행
	private void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
}
