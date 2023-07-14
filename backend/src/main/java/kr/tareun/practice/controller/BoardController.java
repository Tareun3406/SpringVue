package kr.tareun.practice.controller;

import jakarta.servlet.http.HttpServletResponse;
import kr.tareun.practice.service.BoardService;
import kr.tareun.practice.vo.BoardPostVO;
import kr.tareun.practice.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
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
    public void postBoard(@RequestBody BoardPostVO post, Principal principal, HttpServletResponse response) {

        if (principal == null) {
            response.setStatus(Response.SC_UNAUTHORIZED);
            return;
        }
        boardService.insertBoard(new BoardVO(principal.getName(), post.getTitle(), post.getContent()));
    }

    @GetMapping("/board")
    public BoardVO getBoard(long no, HttpServletResponse response) {
        try {
            return boardService.getBoardContents(no);
        }catch (NoSuchElementException e){
            response.setStatus(Response.SC_NOT_FOUND);
            return null;
        }
    }
}
