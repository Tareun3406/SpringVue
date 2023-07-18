package kr.tareun.practice.service;

import kr.tareun.practice.vo.BoardCommentVO;
import kr.tareun.practice.vo.BoardContentsVO;
import kr.tareun.practice.vo.BoardVO;

import java.util.List;
import java.util.NoSuchElementException;

public interface BoardService {

    BoardVO insertBoard(BoardVO boardVO);

    void updateBoard();

    List<BoardVO> getBoardList();

    BoardContentsVO getBoardContents(long no) throws NoSuchElementException;

    void deleteBoardById();

    BoardCommentVO insertComment(BoardCommentVO commentVO);

//    List<BoardCommentVO> getCommentListByBoardNo(Long boardNo);
}
