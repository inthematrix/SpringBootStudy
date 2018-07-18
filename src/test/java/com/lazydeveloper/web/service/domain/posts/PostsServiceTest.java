package com.lazydeveloper.web.service.domain.posts;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lazydeveloper.web.controller.PostsSaveRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsServiceTest {

	@Autowired
	private PostsService postsService;
	
	@Autowired
	private PostsRepository postsRepository;
	
	@After
	public void cleanUp() {
		postsRepository.deleteAll();
	}
	
	@Test
	public void Dto데이터가_posts테이블에_저장된다() {
		PostsSaveRequestDto dto = new PostsSaveRequestDto();
		dto.setAuthor("iaminamatrix@gmail.com");
		dto.setContent("Test Contents");
		dto.setTitle("Test Title");
		
		postsService.save(dto);
		
		Posts post = postsRepository.findAll().get(0);
		assertThat(post.getAuthor()).isEqualTo(dto.getAuthor());
		assertThat(post.getTitle()).isEqualTo(dto.getTitle());
		assertThat(post.getContent()).isEqualTo(dto.getContent());
	}
}
