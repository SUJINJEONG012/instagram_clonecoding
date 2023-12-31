package com.instagram.service;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.instagram.config.auth.PrincipalDetails;
import com.instagram.dto.ImageUploadDto;
import com.instagram.entity.Image;
import com.instagram.repository.ImageRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {

	private final ImageRepository imageRepository;
	
	@Transactional(readOnly = true) 
	public List<Image> 인기사진(){
		return imageRepository.mPopular();
	}
	
	@Transactional(readOnly = true) // 영속성 컨텍스트 변경 감지를 해석, 더티체킹, flush(반영) X
	public Page<Image> 이미지스토리(int principalId, Pageable pageable){
		
		Page<Image> images= imageRepository.mStory(principalId, pageable);
		
		images.forEach((image ->{
			image.setLikeCount(image.getLikes().size());
			image.getLikes().forEach(like->{
				if(like.getUser().getId() == principalId) { 
					//해당 이미지에 좋아요한 사람을 찾아서 현재 로그인한사람이 좋아한것인지 비교
					image.setLikeState(true);
				}
			});
			
		}));
		return images;
	}
	
	
	@Value("${file.path}")
	private String uploadFolder;
	
	
	@Transactional
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		UUID uuid = UUID.randomUUID(); // uuid
		String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename(); // 1.jpg
		System.out.println("이미지 파일이름 : "+imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		
		// 통신, I/O -> 예외가 발생할 수 있다.
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// image 테이블에 저장
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser()); // 5cf6237d-c404-43e5-836b-e55413ed0e49_bag.jpeg
		imageRepository.save(image);
	}
	
}
