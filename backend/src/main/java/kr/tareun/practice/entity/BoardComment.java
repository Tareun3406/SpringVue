package kr.tareun.practice.entity;

import jakarta.persistence.*;
import kr.tareun.practice.vo.BoardCommentVO;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "board_comment")
@EntityListeners(AuditingEntityListener.class)
public class BoardComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    @ManyToOne
    @JoinColumn(name = "board_no", nullable = false)
    private Board parentBoard;

    @ManyToOne
    @JoinColumn(name = "comment_no")
    private BoardComment parentComment;

    @OneToMany(mappedBy = "parentComment")
    private List<BoardComment> childComments;

    @ManyToOne
    @JoinColumn(name = "user_username", nullable = false)
    private User user;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime regDate;

    public static BoardComment toEntity(BoardCommentVO vo) {

        BoardCommentBuilder builder = BoardComment.builder()
                .parentBoard(Board.builder().no(vo.getParentBoardNo()).build())
                .user(User.builder().username(vo.getWriter()).build())
                .comment(vo.getComment());

        if (vo.getParentCommentNo() != null) {
            builder.parentComment(BoardComment.builder().no(vo.getParentCommentNo()).build());
        }

        return builder.build();
    }
}
