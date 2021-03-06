package com.lazydeveloper.web.service.domain.posts;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lazydeveloper.web.controller.PostsSaveRequestDto;

/**
 * Tip) 
Controller에서 Dto.toEntity를 통해서 바로 전달해도 되는데 굳이 Service에서 Dto를 받는 이유는 간단합니다. 
Controller와 Service 의 역할을 분리하기 위함입니다. 
비지니스 로직 & 트랜잭션 관리는 모두 Service에서 관리하고, View 와 연동되는 부분은 Controller에서 담당하도록 구성합니다.
Tip) 트랜잭션? 
일반적으로 DB 데이터를 등록/수정/삭제 하는 Service 메소드는 @Transactional를 필수적으로 가져갑니다. 
이 어노테이션이 하는 일은 간단합니다. 
메소드 내에서 Exception이 발생하면 해당 메소드에서 이루어진 모든 DB작업을 초기화 시킵니다. 
즉, save 메소드를 통해서 10개를 등록해야하는데 5번째에서 Exception이 발생하면 앞에 저장된 4개까지를 전부 롤백시켜버립니다. 
(정확히 얘기하면, 이미 넣은걸 롤백시키는건 아니며, 모든 처리가 정상적으로 됐을때만 DB에 커밋하며 그렇지 않은 경우엔 커밋하지 않는것입니다.) 
 * @author LEE JONG HWA
 * @created 2018. 7. 18.
 * @product vEPG-SI
 * @sw_block
 */
@Service
public class PostsService {

	@Autowired
	private PostsRepository postsRepository;
	
	public PostsService() {
		// TODO Auto-generated constructor stub
	}
	
	@Transactional
	public Long save(PostsSaveRequestDto dto) {
		return postsRepository.save(dto.toEntity()).getId();
	}
	
	@Transactional
	public List<PostsMainResponseDto> finaAllDesc() {
		return postsRepository.finadAllDesc().map(PostsMainResponseDto::new).collect(Collectors.toList());
	}
}
