package kr.tareun.practice.service;

import kr.tareun.practice.entity.Board;
import kr.tareun.practice.repository.BoardRepository;
import kr.tareun.practice.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public BoardVO insertBoard(BoardVO boardVO) {
        Board saved = boardRepository.save(Board.voToEntity(boardVO));
        return BoardVO.EntityToVo(saved);
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
    public BoardVO getBoardContents(long no) throws NoSuchElementException{
        Optional<Board> optional = boardRepository.findById(no);

        return optional.map(BoardVO::EntityToVo).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void deleteBoardById() {

    }
}
