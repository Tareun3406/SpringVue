package kr.tareun.practice.service;

import kr.tareun.practice.vo.BoardVO;

import java.util.List;

public interface BoardService {

    void insertBoard(BoardVO boardVO);

    void updateBoard();

    List<BoardVO> getBoardList();

    void getBoardContents();

    void deleteBoardById();
}
