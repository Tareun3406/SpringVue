package kr.tareun.practice.vo;

import kr.tareun.practice.entity.User;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

import static java.util.Base64.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

    private String username;
    private String password;
    private String email;

    // DB에서 가져온 email 정보 사용전 디코딩
    public static UserVO entityToVo(User entity){

        UserVO vo = new UserVO();
        vo.username = entity.getUsername();
        vo.password = entity.getPassword();
        vo.email = new String(getDecoder().decode(entity.getEmail()));

        return vo;
    }



    // equals, hashCode 에 비밀번호는 포함 되지 않음
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVO userVO = (UserVO) o;
        return Objects.equals(username, userVO.username) && Objects.equals(email, userVO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }
}
