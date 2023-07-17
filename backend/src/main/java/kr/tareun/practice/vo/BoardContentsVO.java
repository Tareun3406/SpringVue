package kr.tareun.practice.vo;

import kr.tareun.practice.entity.Board;
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
public class BoardContentsVO {

    private long no;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime regDate;

    private List<BoardCommentVO> commentList;

    public static BoardContentsVO entityToVO(Board entity) {
        BoardContentsVO vo = new BoardContentsVO();
        vo.no = entity.getNo();
        vo.writer = entity.getUser().getUsername();
        vo.title = entity.getTitle();
        vo.content = entity.getContent();
        vo.regDate = entity.getRegDate();

        vo.commentList = entity.getCommentList().stream().map(BoardCommentVO::entityToVO).collect(Collectors.toList());

        System.out.println("vo = " + vo);
        return vo;
    }
}
