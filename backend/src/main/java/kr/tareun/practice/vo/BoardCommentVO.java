package kr.tareun.practice.vo;

import kr.tareun.practice.entity.BoardComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardCommentVO {

    private long no;
    private long parentBoardNo;
    private Long parentCommentNo;
    private int depth;

    private String writer;
    private String comment;

    private LocalDateTime regDate;

    private List<BoardCommentVO> childComments;

    public BoardCommentVO(long parentBoardNo, Long parentCommentNo, int depth, String writer, String comment) {
        this.parentBoardNo = parentBoardNo;
        this.parentCommentNo = parentCommentNo;
        this.depth = depth;
        this.writer = writer;
        this.comment = comment;
    }

    public BoardCommentVO(long parentBoardNo, String writer, String comment) {
        this.parentBoardNo = parentBoardNo;
        this.writer = writer;
        this.comment = comment;
    }

    public static BoardCommentVO entityToVO(BoardComment entity) {
        BoardCommentVO vo = new BoardCommentVO();
        vo.no = entity.getNo();
        vo.parentBoardNo = entity.getParentBoard().getNo();
        vo.parentCommentNo = (entity.getParentComment() == null)
                ? null : entity.getParentComment().getNo();
        vo.depth = entity.getDepth();
        vo.writer = entity.getUser().getUsername();
        vo.comment = entity.getComment();
        vo.regDate = entity.getRegDate();

        vo.childComments = entity.getChildComments().stream()
                .map(BoardCommentVO::entityToVO).collect(Collectors.toList());

        return vo;
    }
}
