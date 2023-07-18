package kr.tareun.practice.controller;

import jakarta.servlet.http.HttpServletResponse;
import kr.tareun.practice.service.BoardService;
import kr.tareun.practice.vo.*;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boardList")
    public List<BoardVO> getBoardList() {
        return boardService.getBoardList();
    }

    @PostMapping("/board")
    public ResponseEntity<String> postBoard(@RequestBody BoardPostVO post, Principal principal) {

        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        } else if (post.getTitle() == null || post.getContent() == null) { // not null 컬럼 확인, try - catch ?
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("제목과 내용을 모두 입력해야 합니다.");
        }

        boardService.insertBoard(new BoardVO(principal.getName(), post.getTitle(), post.getContent()));
        return ResponseEntity.ok("게시물이 등록 되었습니다.");
    }

    @GetMapping("/board")
    public BoardContentsVO getBoard(long no, HttpServletResponse response) {
        // try - catch 가 좋은 방법인가?
        try {
            return boardService.getBoardContents(no);
        }catch (NoSuchElementException e){
            response.setStatus(Response.SC_NOT_FOUND);
            return null;
        }
    }

    @PostMapping("/boardComment")
    public ResponseEntity<String> postBoardComment(@RequestBody BoardCommentPostVO comment, Principal principal) {

        System.out.println("comment = " + comment);
        if (principal == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }else if (comment.getParentBoardNo() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("parentBoardNo는 0 또는 null 이 될 수 없습니다.");
        } else if (comment.getComment() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("댓글 내용은 필수입니다.");
        }


        boardService.insertComment(
                new BoardCommentVO(comment, principal.getName())
        );
        return ResponseEntity.status(HttpStatus.OK).body("댓글이 등록 되었습니다.");
    }
}
