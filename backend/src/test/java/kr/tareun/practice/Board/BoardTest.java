package kr.tareun.practice.Board;


import kr.tareun.practice.entity.Board;
import kr.tareun.practice.entity.User;
import kr.tareun.practice.repository.BoardRepository;
import kr.tareun.practice.service.BoardService;
import kr.tareun.practice.vo.BoardVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;


@SpringBootTest()
public class BoardTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Test
    public void BoardInsertTest() {
        // give
        BoardVO boardVO = new BoardVO("test", "title", "content");

        System.out.println("boardVO = " + boardVO);

        //when
        boardService.insertBoard(boardVO);


        //result

    }

    @Test
    public void getBoardById() {
        Optional<Board> optional = boardRepository.findById(1L);
        System.out.println("optional = " + optional);
    }

    @Test
    public void getBoardList() {
        boardService.getBoardList();
    }
}
