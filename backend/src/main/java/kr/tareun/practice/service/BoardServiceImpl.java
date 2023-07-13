package kr.tareun.practice.service;

import kr.tareun.practice.entity.Board;
import kr.tareun.practice.repository.BoardRepository;
import kr.tareun.practice.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public void insertBoard(BoardVO boardVO) {
        boardRepository.save(boardVO.ToEntity());
    }

    @Override
    public void updateBoard() {

    }

    @Override
    public List<BoardVO> getBoardList() {
        List<Board> all = boardRepository.findAll();
        return all.stream().map(BoardVO::EntityToVo).collect(Collectors.toList());
    }

    @Override
    public void getBoardContents() {

    }

    @Override
    public void deleteBoardById() {

    }
}
