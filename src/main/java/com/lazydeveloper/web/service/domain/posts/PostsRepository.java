package com.lazydeveloper.web.service.domain.posts;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 * 보통 ibatis/MyBatis 등에서 Dao라고 불리는 DB Layer 접근자입니다. 
JPA에선 Repository라고 부르며 인터페이스로 생성합니다. 
단순히 인터페이스를 생성후, JpaRepository<Entity클래스, PK타입>를 상속하면 기본적인 CRUD 메소드가 자동생성 됩니다. 
특별히 <code>@Repository</code>를 추가할 필요도 없습니다.

 * @author LEE JONG HWA
 * @created 2018. 7. 18.
 * @product vEPG-SI
 * @sw_block
 */
public interface PostsRepository extends JpaRepository<Posts, Long>{

//	굳이 @Query를 쓴 이유는, SpringDataJpa에서 제공하지 않는 메소드는 위처럼 쿼리로 작성해도 되는것을 보여주기 위함입니다.
//	Tip) 
//	규모가 있는 프로젝트에서의 데이터 조회는 FK의 조인, 복잡한 조건등으로 인해 이런 Entity 클래스만으로 처리하기 어려워 조회용 프레임워크를 추가로 사용합니다. 
//	대표적 예로 querydsl, jooq, MyBatis 등이 있습니다. 
//	조회는 위 3가지 프레임워크중 하나를 통해 조회하고, 등록/수정/삭제 등은 SpringDataJpa를 통해 진행합니다. 
//	(개인적으로는 querydsl를 강추합니다.) 
//	JPA, querydsl에 대한 더 자세한 내용은 김영한님의 자바 ORM 표준 JPA 프로그래밍 을 참고하시면 아주 좋습니다.
	
	@Query("SELECT p FROM Posts p ORDER BY p.id DESC")
	Stream<Posts> finadAllDesc();
}
