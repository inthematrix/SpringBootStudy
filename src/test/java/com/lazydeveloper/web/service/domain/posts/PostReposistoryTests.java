package com.lazydeveloper.web.service.domain.posts;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * PostRepository class를 테스트하기 위한  test class
 * 
 * @author LEE JONG HWA
 * @created 2018. 7. 18.
 * @product vEPG-SI
 * @sw_block
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostReposistoryTests {
	@Autowired
	PostsRepository postsRepository;
	
	@After
	public void cleanup() {
		/**
		 * repository를 자동으로 비운다
		 */
		postsRepository.deleteAll();
	}
	
	@Test
	public void 게시글저장_불러오기() {
		// given
		postsRepository.save(new Posts("테스트 게시글", "테스트 본문", "iaminamatrix@gmail.com"));
		
		// when
		List<Posts> list = postsRepository.findAll();
		
		// then
		Posts post = list.get(0);
		Assert.assertThat(post.getTitle(), is("테스트 게시글"));
		Assert.assertThat(post.getContent(), is("테스트 본문"));
	}
	
	@Test
	public void BaseTimeEntity_등록() {
		// given
		LocalDateTime now = LocalDateTime.now();
		postsRepository.save(new Posts("테스트 게시글", "테스트 본문", "iaminamatrix@gmail.com"));
		
		// when
		List<Posts> list = postsRepository.findAll();
		
		Posts post = list.get(0);
		//then
		assertTrue(post.getCreateDate().isAfter(now));
		assertTrue(post.getModifiedDate().isAfter(now));
	}
}
