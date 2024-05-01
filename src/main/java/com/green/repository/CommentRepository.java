package com.green.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.green.entity.Comments;
import java.util.List;


public interface CommentRepository extends JpaRepository<Comments, Long> {
	
	// @Query 에노테이션으로 findByArticleId() 함수를 실행한다.
	// @Query 애노테이션는 gjava 기능사용하지않고 Query안의 sql을 사용한다
	// Native Query Method - orcale 문법으로 작성된 쿼리를 입력하여 조회
	// nativeQuery = true 오라클 전용함수
	// nativeQuery = false JPA함수, JPQL문법사용
	// :articleId (파라미터)로 조회한다
	@Query(value = "SELECT * FROM comments where article_id=:articleId", nativeQuery = true)
	List<Comments> findByArticleId(Long articleId);
	
	// native query xml
	// src/main/resources/META-INF/orm.xml
	// orm.xml 에 sql 을 저장해서 findByNickname() 함수호출
	List<Comments> findByNickname(String nickname);
}
