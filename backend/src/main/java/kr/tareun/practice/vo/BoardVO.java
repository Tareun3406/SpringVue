package kr.tareun.practice.vo;

import kr.tareun.practice.entity.Board;
import kr.tareun.practice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
    private long id;
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
        boardVO.id = entity.getNumber();
        boardVO.writer = entity.getUser().getUsername();
        boardVO.title = entity.getTitle();
        boardVO.content = entity.getContent();
        boardVO.regDate = entity.getRegDate();

        return boardVO;
    }

    public Board ToEntity() {
        return Board.builder()
                .user(User.builder().username(this.writer).build())
                .title(this.title)
                .content(this.content)
                .build();
    }
}
