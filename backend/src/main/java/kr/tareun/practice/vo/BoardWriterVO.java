package kr.tareun.practice.vo;

import kr.tareun.practice.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardWriterVO {
    private Long no;
    private String writer;

    public static BoardWriterVO entityToVO(Board entity) {
        BoardWriterVO vo = new BoardWriterVO();
        vo.no = entity.getNo();
        vo.writer = entity.getUser().getUsername();
        return vo;
    }
}
