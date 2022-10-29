package com.cos.photogramstart.web.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.service.CommentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

	public final CommentService commentService;
	
	@PostMapping("/api/comment")
	public ResponseEntity<?> commentSave(){
		return null;
	}
	
	public ResponseEntity<?> commentDelete(@PathVariable int id){
		
		return null;
	}
}
