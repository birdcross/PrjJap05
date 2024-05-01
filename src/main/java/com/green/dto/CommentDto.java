package com.green.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.green.entity.Comments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
	private Long id;
	@JsonProperty("article_id")
	private Long  articleId;
	private String nickname; 
	private String body;
	// CommentDto <- Comments (db에 조회한)
	public static CommentDto createCommentDto(Comments comments) {
		return new CommentDto(
				comments.getId(),
				comments.getArticle().getId(),
				comments.getNickname(),
				comments.getBody()
				);
	}
}
