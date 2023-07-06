package kr.tareun.practice.login;

import kr.tareun.practice.entity.User;
import kr.tareun.practice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@SpringBootTest
public class loginTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    public void insertUser() {
        User user = User.builder().username("test").password(encoder.encode("test")).build();
        userRepository.save(user);
    }
}
