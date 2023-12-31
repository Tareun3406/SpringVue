package kr.tareun.practice.service;

import kr.tareun.practice.entity.User;
import kr.tareun.practice.repository.UserRepository;
import kr.tareun.practice.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService{

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;


    @Override
    public void joinUser(UserVO joinUserInfo) {
        userRepository.save(User.toEntityWithPwEncode(joinUserInfo, encoder));
    }

    @Override
    public UserVO getUserInfoById(String id) {
        Optional<User> optional = userRepository.findById(id);

        return optional.map(UserVO::entityToVo).orElse(null);
    }

    @Override
    public boolean checkIsDuplicateUser(String username) {
        Optional<User> optional = userRepository.findById(username);
        return optional.isPresent();
    }

    @Override
    public void updateUser(UserVO userInfo) {
        userRepository.save(User.toEntityWithPwEncode(userInfo, encoder));
    }


}
