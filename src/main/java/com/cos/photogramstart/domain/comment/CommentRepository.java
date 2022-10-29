package com.cos.photogramstart.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

// 객체를 만들지 못한다
//	@Modifying
//	@Query(value = "INSERT INTO comment(content, imageId,userId,createDate) VALUES(:content, :imageId, :principalId, now())",nativeQuery = true)
//	Comment mSave(String content, int imageId,int principalId);
}
