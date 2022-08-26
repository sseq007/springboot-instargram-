package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {

	private int Id;
	private String username;
	private String profileImageUrl;
	private Integer subscribeState; //mariaDB는 Integer로 받아야하낟 True FALSE
	private Integer equalUserState;
}
