package com.green.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.green.dto.CommentDto;
import com.green.entity.Comments;
import com.green.service.CommentService;

@RestController
public class CommentApiController {
	
	@Autowired
	private CommentService commentService;
	//1. 댓글조회 (GET)
	@GetMapping("/api/articles/{articleId}/comments")
	public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
		
		// 정보 조회를 서비스에게 위임
		List<CommentDto> dtos = commentService.comments(articleId);
		
		// ResponseEntity : staus.ok + dtos(arraylist)
		// -> json 으로 출력 (이유 : @RestController 라서 )) 를 리턴
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}

	//2. 댓글생성 (POST)
	// Post http://localhost9090:9090/api/articles/4/comments
	// 입력 data : {"article_id" : 4, "nickname" : "Hoon", "body" :"이프 온리"}
	// 결과{"id": null, "article_id": 4, "nickname": "Hoon", "body": "이프 온리"}
	// 에러입력 : {"id": 4, "article_id" : 4, "nickname" : "Hoon", "body" :"이프 온리"}
	// 결과
	@PostMapping("/api/articles/{articleId}/comments")
	public ResponseEntity<CommentDto> create(
			@PathVariable Long articleId, //{articleId} : 게시글번호
			@RequestBody CommentDto dto){ //입력된 자료들 input, select
		CommentDto creatDto = commentService.create(articleId, dto);
		//결과를 응답
		return ResponseEntity.status(HttpStatus.OK).body(creatDto);
		
	}
	
	//3. 댓글수정 (PATCH)
	@PatchMapping("/api/comments/{id}")
	public ResponseEntity<CommentDto> update(@PathVariable Long id,
			@RequestBody CommentDto dto){ // 수정할 데이터를 가지고 있다
		CommentDto updateDto = commentService.update(id, dto);
		return ResponseEntity.status(HttpStatus.OK).body(updateDto);
	}
	//4. 댓글삭제 (DELETE)
	@DeleteMapping("/api/comments/{id}")
	public ResponseEntity<CommentDto> delete(@PathVariable Long id ) {
		CommentDto deletedDto = commentService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
	}
	
		
}

