package kr.tareun.practice.entity;

import jakarta.persistence.*;
import kr.tareun.practice.vo.BoardVO;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    @ManyToOne
    @JoinColumn(name = "user_username", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime regDate;

    @OneToMany(mappedBy = "parentBoard", fetch = FetchType.EAGER)
    private List<BoardComment> comments;

    public static Board voToEntity(BoardVO boardVO) {
        return Board.builder()
                .user(User.builder().username(boardVO.getWriter()).build())
                .title(boardVO.getTitle())
                .content(boardVO.getContent())
                .build();
    }

}


