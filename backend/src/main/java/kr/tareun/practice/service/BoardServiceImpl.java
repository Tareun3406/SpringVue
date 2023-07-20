package kr.tareun.practice.service;

import kr.tareun.practice.entity.Board;
import kr.tareun.practice.entity.BoardComment;
import kr.tareun.practice.repository.BoardCommentRepository;
import kr.tareun.practice.repository.BoardRepository;
import kr.tareun.practice.vo.BoardCommentVO;
import kr.tareun.practice.vo.BoardContentsVO;
import kr.tareun.practice.vo.BoardVO;
import kr.tareun.practice.vo.BoardWriterVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final BoardCommentRepository boardCommentRepository;

    @Override
    public BoardVO insertBoard(BoardVO boardVO) {
        Board saved = boardRepository.save(Board.toEntity(boardVO));
        return BoardVO.entityToVO(saved);
    }



    @Override
    public List<BoardVO> getBoardList() {
        List<Board> all = boardRepository.findAll();
        return all.stream().map(BoardVO::entityToVO).toList();
    }

    @Override
    public BoardContentsVO getBoardContents(long no) {

//         DB 에서 필터링 하지 않고 가져오기
        Optional<Board> optional = boardRepository.findById(no);
        Board board = optional.orElseThrow(NoSuchElementException :: new);

        List<BoardCommentVO> filteredCommentsVO = filterRootComments(board);

        return new BoardContentsVO(
                board.getNo(),
                board.getUser().getUsername(),
                board.getTitle(),
                board.getContent(),
                board.getRegDate(),
                filteredCommentsVO
        );
    }

    @Override
    public BoardWriterVO getBoardWriter(long no) {
        Optional<Board> optional = boardRepository.findById(no);
        Board board = optional.orElseThrow(NoSuchElementException::new);

        return BoardWriterVO.entityToVO(board);
    }

    @Override
    public void deleteBoardById(long no) {
        boardRepository.deleteById(no);
    }

    @Override
    public BoardCommentVO insertComment(BoardCommentVO commentVO) {
        BoardComment saved = boardCommentRepository.save(BoardComment.toEntity(commentVO));
        return BoardCommentVO.entityToVO(saved);
    }

    private List<BoardCommentVO> filterRootComments(Board board) {
        List<BoardComment> comments = board.getComments();
        return comments.stream()
                .filter(comment -> comment.getParentComment() == null)
                .map(BoardCommentVO::entityToVO).toList();
    }
}
