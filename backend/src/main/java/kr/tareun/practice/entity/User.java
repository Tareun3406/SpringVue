package kr.tareun.practice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import kr.tareun.practice.vo.UserVO;
import lombok.*;

import static java.util.Base64.getEncoder;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @Column(length = 100)
    private String username;

    @Column(length = 200, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String email;

    public User toEntity(UserVO vo) {
        String encodedEmail = getEncoder().encodeToString(vo.getEmail().getBytes());
        return User.builder()
                .username(vo.getUsername())
                .password(vo.getPassword())
                .email(encodedEmail)
                .build();
    }
}
