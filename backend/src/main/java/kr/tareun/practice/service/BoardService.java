package kr.tareun.practice.service;

import kr.tareun.practice.vo.BoardCommentVO;
import kr.tareun.practice.vo.BoardContentsVO;
import kr.tareun.practice.vo.BoardVO;
import kr.tareun.practice.vo.BoardWriterVO;

import java.util.List;
import java.util.NoSuchElementException;

public interface BoardService {

    BoardVO insertBoard(BoardVO boardVO);

    void updateBoard();

    List<BoardVO> getBoardList();

    BoardContentsVO getBoardContents(long no) throws NoSuchElementException;

    BoardWriterVO getBoardWriter(long no);

    void deleteBoardById(long no);

    BoardCommentVO insertComment(BoardCommentVO commentVO);

//    List<BoardCommentVO> getCommentListByBoardNo(Long boardNo);
}
