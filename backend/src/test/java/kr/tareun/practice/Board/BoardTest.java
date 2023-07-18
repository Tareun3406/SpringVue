package kr.tareun.practice.Board;


import kr.tareun.practice.entity.Board;
import kr.tareun.practice.entity.BoardComment;
import kr.tareun.practice.entity.User;
import kr.tareun.practice.repository.BoardCommentRepository;
import kr.tareun.practice.repository.BoardRepository;
import kr.tareun.practice.service.BoardService;
import kr.tareun.practice.vo.BoardCommentVO;
import kr.tareun.practice.vo.BoardContentsVO;
import kr.tareun.practice.vo.BoardVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest()
public class BoardTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardCommentRepository boardCommentRepository;

//    @Rollback
    @Test
    @DisplayName("게시글의 주어진 내용과 저장된 값이 같아야 합니다.")
    public void boardPostAndRead() {
        // give
        String writer = "test";
        String title = "title14151g";
        String content = "content atwq ccgaaaqq";

        BoardVO insertedVO = new BoardVO(writer,title, content);

        //when
        BoardVO result= boardService.insertBoard(insertedVO);

        //result
        assertThat(insertedVO.getWriter()).isEqualTo(result.getWriter());
        assertThat(insertedVO.getTitle()).isEqualTo(result.getTitle());
        assertThat(insertedVO.getContent()).isEqualTo(result.getContent());
    }



    @Test
    public void insertComment(){
        // give
//        long parentBoardNo = boardService.insertBoard(new BoardVO("test", "title", "content")).getNo();
//        String writer = "test";
//        String comment = "comment test";
//
//        BoardCommentVO parentComment = new BoardCommentVO(parentBoardNo, "test", "comment");

        // when
//        boardService.insertComment()

        BoardComment boardComment = BoardComment.builder()
                .user(User.builder().username("test").build())
                .parentBoard(Board.builder().no(1L).build())
                .parentComment(BoardComment.builder().no(7L).build())
                .depth(1)
                .comment("afwwhwhwheeee")
                .build();

        boardCommentRepository.save(boardComment);
    }

    @Test
    public void getBoard() {
        Optional<Board> optional = boardRepository.findById(1L);
//        BoardContentsVO vo = BoardContentsVO.entityToVO(optional.get());
    }


    @Test
    public void getBoardList() {
        boardService.getBoardList();
    }

    @Test void getList() { boardRepository.findAll(); }

    @Transactional
    @Test void getCommentList() {
//        List<BoardComment> boardNo = boardCommentRepository.findAllByParentBoardNoAndDepth(1L,0);

//        List<BoardCommentVO> collect = boardNo.stream().map(BoardCommentVO::entityToVO).toList();
//        System.out.println("collect = " + collect);

//        List<BoardCommentVO> list = boardService.getCommentListByBoardNo(1L);
//        System.out.println("list = " + list);
    }
}
