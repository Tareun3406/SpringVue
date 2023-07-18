package kr.tareun.practice.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentPostVO {
    private long parentBoardNo;
    private Long parentCommentNo;
    private String comment;
}
