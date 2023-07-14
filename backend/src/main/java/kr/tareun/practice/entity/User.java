package kr.tareun.practice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import kr.tareun.practice.vo.UserVO;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

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


    // DB에 전송전 email 정보 인코딩
    public User toEntity(UserVO vo) {
        String encodedEmail = getEncoder().encodeToString(vo.getEmail().getBytes());
        return User.builder()
                .username(vo.getUsername())
                .password(vo.getPassword())
                .email(encodedEmail)
                .build();
    }

    // 회원 가입시 비밀번호 인코딩 추가, email 인코딩
    public static User toEntityWithPwEncode(UserVO vo, PasswordEncoder encoder) {
        String encodedEmail = getEncoder().encodeToString(vo.getEmail().getBytes());
        return User.builder()
                .username(vo.getUsername())
                .password(encoder.encode(vo.getPassword()))
                .email(encodedEmail)
                .build();
    }
}
