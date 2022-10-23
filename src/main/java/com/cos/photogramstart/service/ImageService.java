package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.Image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {

	private final ImageRepository imageRepository;
	   
	@Transactional(readOnly = true) // 영속성 컨텍스트 변경 감지를 해서, 더티체킹, flush(반영x)
	public Page<Image> 이미지스토리(int principalId, Pageable pageable){
		Page<Image> images = imageRepository.mStroy(principalId,pageable);
		
		//images에 좋아요 상태 담기
		
		images.forEach((image)->{
			image.getLikes().forEach((like)->{
				if (like.getUser().getId()==principalId) { //해당 이미지에 종아요한 사람들을 찾아서 현재 로그인한 사람이 좋아요 한 것인지 비교
					image.setLikeState(true);
					
				}
			});
		});
		
		
		
		return images;
	}
	@Value("${file.path}") // C:/workspace/springbootwork/upload/
	private String uploadFolder;
	
	@Transactional
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		
		UUID uuid = UUID.randomUUID(); //uuid
		String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename(); //1.jpg
		
		Path ImageFilePath = Paths.get(uploadFolder+imageFileName);
		
		
		try {
			Files.write(ImageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// image 테이블에 저장
		
		Image image = imageUploadDto.toEntity(imageFileName,principalDetails.getUser());
		imageRepository.save(image);
		
		//System.out.println(imageEntity);
	}
}
