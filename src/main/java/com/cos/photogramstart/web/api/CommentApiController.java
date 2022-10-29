package com.cos.photogramstart.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.service.CommentService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.comment.commentDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

	public final CommentService commentService;
	
	@PostMapping("/api/comment")
	public ResponseEntity<?> commentSave(@RequestBody commentDto commentDto,@AuthenticationPrincipal PrincipalDetails principalDetails){
		//System.out.println(commentDto);
		Comment comment=commentService.댓글쓰기(commentDto.getContent(),commentDto.getImageId(),principalDetails.getUser().getId());
		
		return new ResponseEntity<>(new CMRespDto<>(1,"댓글쓰기성공",comment),HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> commentDelete(@PathVariable int id){
		
		return null;
	}
}
