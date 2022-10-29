package com.cos.photogramstart.web.dto.comment;

import lombok.Data;

@Data
public class commentDto {

	private String content;
	private int imageId;
	
	//toEntity가 필요 없다
}
