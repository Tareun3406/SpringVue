package kr.tareun.practice.vo;

import kr.tareun.practice.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
    private long no;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime regDate;

    public BoardVO(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    public static BoardVO EntityToVo(Board entity) {
        BoardVO boardVO = new BoardVO();
        boardVO.no = entity.getNo();
        boardVO.writer = entity.getUser().getUsername();
        boardVO.title = entity.getTitle();
        boardVO.content = entity.getContent();
        boardVO.regDate = entity.getRegDate();

        return boardVO;
    }
}
