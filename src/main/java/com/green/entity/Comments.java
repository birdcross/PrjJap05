package com.green.entity;

import com.green.dto.CommentDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든인자 생성자
@SequenceGenerator(name = "COMMENTS_SEQ_GENERATOR", sequenceName = "COMMENTS_SEQ", initialValue = 1, allocationSize = 1) 
public class Comments {
	@Id //기본키
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENTS_SEQ_GENERATOR")
	private Long id; //id
	@ManyToOne // 다대일 설정 (Comments <-> Article)
	@JoinColumn(name = "article_id")// 외래키(부모키 Article id 칼럼)
	private Article article; // 연결될 entity 객체의 이름
	//@Column(name="nick_name", nullable = false, length=255)
	@Column
	private String nickname;
	@Column
	private String body;
	public static Comments createComment(CommentDto dto, Article article ) {
		
		if(dto.getId() != null) // 입력된 댓글에 id가 존재하면 
			throw new IllegalArgumentException("댓글 생성실패! 댓글의 id가 없어야한다");
		// dto.getArticleId() :  입력받은 게시글의 id
		// article.geId() : 조회한 게시글 id
		if(dto.getArticleId() != article.getId())
			throw new IllegalArgumentException("댓글 생성실패! 게시글의 id가 잘못되었습니다");
		
		return new Comments(dto.getId(),  //입력받은 댓글아이디
				article,//조회한 부모게시글 정보
				dto.getNickname(), //입력받은 댓글 닉네임
				dto.getBody()); //입력받은 댓글내용
	}
	
	
}
