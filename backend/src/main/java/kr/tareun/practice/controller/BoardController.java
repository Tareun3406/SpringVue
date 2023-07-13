package kr.tareun.practice.controller;

import kr.tareun.practice.service.BoardService;
import kr.tareun.practice.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boardList")
    public List<BoardVO> getBoardList() {
        return boardService.getBoardList();
    }

    @PutMapping("/board")
    public void putBoard(@RequestBody BoardVO boardVO) {
        boardService.insertBoard(boardVO);
    }
}
