package kr.tareun.practice.login;

import kr.tareun.practice.repository.UserRepository;
import kr.tareun.practice.service.UserService;
import kr.tareun.practice.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class loginTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Test
    @Rollback
    public void checkJoinLogin() {
        // give
        String username = "test";
        String password = "test";   // * 매번 인코딩 값이 달라지므로 테스트에서 비밀번호를 비교하지 않음
        String email = "test";

        //
        UserVO user = new UserVO(username,password,email);
        UserVO encodedUser = new UserVO(username, encoder.encode(password), email);

        userService.joinUser(user);
        UserVO foundUser = userService.getUserInfoById(username);


        // result
        assertThat(foundUser).isEqualTo(encodedUser);
    }
}
