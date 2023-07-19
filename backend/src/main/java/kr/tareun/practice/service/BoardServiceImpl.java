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
//        DB 에서 필터링된 값 가져오기
//        Optional<Board> optional = boardRepository.findBoardWithFilteredComments(no);
//        return optional.map(BoardContentsVO::entityToVO).orElseThrow(NoSuchElementException::new);


//         DB 에서 값을 가져온뒤 필터링
        Optional<Board> optional = boardRepository.findById(no);
        optional.orElseThrow(NoSuchElementException :: new);

        // 댓글 트리구조에서 최상단만 필터링
        Board board = optional.get();
        List<BoardComment> comments = board.getComments();
        List<BoardCommentVO> filteredCommentsVO = comments.stream()
                .filter(comment -> comment.getParentComment() == null)
                .map(BoardCommentVO::entityToVO).toList();

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
    public void deleteBoardById() {
    }

    @Override
    public BoardCommentVO insertComment(BoardCommentVO commentVO) {
        BoardComment saved = boardCommentRepository.save(BoardComment.voToEntity(commentVO));
        return BoardCommentVO.entityToVO(saved);
    }
}
