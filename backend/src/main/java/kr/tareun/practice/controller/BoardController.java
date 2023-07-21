package kr.tareun.practice.controller;

import jakarta.servlet.http.HttpServletResponse;
import kr.tareun.practice.exception.errorcode.AuthErrorCode;
import kr.tareun.practice.exception.exceptions.RestApiException;
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
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
            throw new RestApiException(AuthErrorCode.NOT_LOGGED_IN);
        } else if (post.getTitle() == null || post.getContent() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("제목과 내용을 모두 입력해야 합니다.");
        }

        boardService.insertBoard(
                new BoardVO(principal.getName(), post.getTitle(), post.getContent())
        );
        return ResponseEntity.ok("게시물이 등록 되었습니다.");
    }

    @GetMapping("/board")
    public BoardContentsVO getBoard(long no, HttpServletResponse response) {
//        try {
//        } catch (NoSuchElementException e) {
//            response.setStatus(Response.SC_NOT_FOUND);
//            return null;
//        }
        return boardService.getBoardContents(no);

    }

    @DeleteMapping("/board")
    public ResponseEntity<String> deleteBoard(Long no,Principal principal) {

//        try {
//            if (principal == null) {
////                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
//
//            } else if (!boardService.getBoardWriter(no).getWriter().equals(principal.getName())) { // 글 작성자와 요청자가 다를때
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("작성자가 다릅니다.");
//            }
//        }catch (NoSuchElementException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }

        if (principal == null) {
            throw new RestApiException(AuthErrorCode.NOT_LOGGED_IN);
        } else if (!boardService.getBoardWriter(no).getWriter().equals(principal.getName())) { // 글 작성자와 요청자가 다를때
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("작성자가 다릅니다.");
        }


        boardService.deleteBoardById(no);
        return ResponseEntity.ok("게시글 삭제 완료");
    }

    @PostMapping("/boardComment")
    public ResponseEntity<String> postBoardComment(@RequestBody BoardCommentPostVO comment, Principal principal) {

        if (principal == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
            throw new RestApiException(AuthErrorCode.NOT_LOGGED_IN);
        } else if (comment.getParentBoardNo() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("parentBoardNo는 0 또는 null 이 될 수 없습니다.");
        } else if (comment.getComment() == null || comment.getComment().equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("댓글 내용은 필수입니다.");
        }


        boardService.insertComment(
                new BoardCommentVO(comment, principal.getName())
        );
        return ResponseEntity.status(HttpStatus.OK).body("댓글이 등록 되었습니다.");
    }
}
