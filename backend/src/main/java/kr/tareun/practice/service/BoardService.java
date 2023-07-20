package kr.tareun.practice.service;

import kr.tareun.practice.vo.BoardCommentVO;
import kr.tareun.practice.vo.BoardContentsVO;
import kr.tareun.practice.vo.BoardVO;
import kr.tareun.practice.vo.BoardWriterVO;

import java.util.List;

public interface BoardService {

    BoardVO insertBoard(BoardVO boardVO);

    List<BoardVO> getBoardList();

    BoardContentsVO getBoardContents(long no);

    BoardWriterVO getBoardWriter(long no);

    void deleteBoardById(long no);

    BoardCommentVO insertComment(BoardCommentVO commentVO);

}
