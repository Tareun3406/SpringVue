package kr.tareun.practice.service;

import kr.tareun.practice.vo.BoardVO;

import java.util.List;

public interface BoardService {

    BoardVO insertBoard(BoardVO boardVO);

    void updateBoard();

    List<BoardVO> getBoardList();

    BoardVO getBoardContents(long no);

    void deleteBoardById();
}
