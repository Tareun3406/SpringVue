package kr.tareun.practice.service;

import kr.tareun.practice.entity.Board;
import kr.tareun.practice.entity.BoardComment;
import kr.tareun.practice.repository.BoardCommentRepository;
import kr.tareun.practice.repository.BoardRepository;
import kr.tareun.practice.vo.BoardCommentVO;
import kr.tareun.practice.vo.BoardContentsVO;
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
    private final BoardCommentRepository boardCommentRepository;

    @Override
    public BoardVO insertBoard(BoardVO boardVO) {
        Board saved = boardRepository.save(Board.voToEntity(boardVO));
        return BoardVO.entityToVO(saved);
    }

    @Override
    public void updateBoard() {
    }

    @Override
    public List<BoardVO> getBoardList() {
        List<Board> all = boardRepository.findAll();
        return all.stream().map(BoardVO::entityToVO).collect(Collectors.toList());
    }

    @Override
    public BoardContentsVO getBoardContents(long no) throws NoSuchElementException{
        Optional<Board> optional = boardRepository.findBoardWithFilteredComments(no);
//        Optional<Board> optional = boardRepository.findById(no);
        return optional.map(BoardContentsVO::entityToVO).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void deleteBoardById() {
    }

    @Override
    public BoardCommentVO insertComment(BoardCommentVO commentVO) {
        BoardComment saved = boardCommentRepository.save(BoardComment.voToEntity(commentVO));
        return BoardCommentVO.entityToVO(saved);
    }
}
