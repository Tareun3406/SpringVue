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

    private String writer;
    private String comment;

    private LocalDateTime regDate;

    private List<BoardCommentVO> childComments;


    public BoardCommentVO(BoardCommentPostVO postVO, String writer) {
        this.parentBoardNo = postVO.getParentBoardNo();
        this.parentCommentNo =
                (postVO.getParentCommentNo() == null || postVO.getParentCommentNo() == 0)
                        ? null : postVO.getParentCommentNo();
        this.comment = postVO.getComment();
        this.writer = writer;
    }


    public static BoardCommentVO entityToVO(BoardComment entity) {
        BoardCommentVO vo = new BoardCommentVO();
        vo.no = entity.getNo();
        vo.parentBoardNo = entity.getParentBoard().getNo();
        vo.parentCommentNo = (entity.getParentComment() == null)
                ? null : entity.getParentComment().getNo();
        vo.writer = entity.getUser().getUsername();
        vo.comment = entity.getComment();
        vo.regDate = entity.getRegDate();

        if (entity.getChildComments() != null){
            vo.childComments = entity.getChildComments().stream()
                    .map(BoardCommentVO::entityToVO).collect(Collectors.toList());
        }

        return vo;
    }
}
