package kr.tareun.practice.Board;


import kr.tareun.practice.entity.Board;
import kr.tareun.practice.entity.User;
import kr.tareun.practice.repository.BoardRepository;
import kr.tareun.practice.service.BoardService;
import kr.tareun.practice.vo.BoardVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@SpringBootTest()
public class BoardTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Rollback
    @Test
    @DisplayName("게시글의 주어진 내용과 저장된 값이 같아야 합니다.")
    public void BoardPostAndRead() {
        // give
        String writer = "test";
        String title = "title14151g";
        String content = "content atwq ccgaaaqq";

        BoardVO insertedVO = new BoardVO(writer,title, content);

        //when
        BoardVO result= boardService.insertBoard(insertedVO);

        System.out.println("insertedVO = " + insertedVO);
        System.out.println("result = " + result);

        //result
        assertThat(insertedVO.getWriter()).isEqualTo(result.getWriter());
        assertThat(insertedVO.getTitle()).isEqualTo(result.getTitle());
        assertThat(insertedVO.getContent()).isEqualTo(result.getContent());
    }

    @Test
    public void getBoardById() {
        //give get board
        BoardVO boardContents = boardService.getBoardContents(2L);

        System.out.println("boardContents = " + boardContents);
    }

    @Test
    public void getBoardList() {
        boardService.getBoardList();
    }
}
