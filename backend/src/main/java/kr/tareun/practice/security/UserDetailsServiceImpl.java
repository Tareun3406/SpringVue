package kr.tareun.practice.security;

import kr.tareun.practice.entity.User;
import kr.tareun.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optional = userRepository.findById(username);

        User userInfo;
        if (optional.isPresent()){
            userInfo = optional.get();
        }else {
            throw new UsernameNotFoundException("회원 정보를 찾을 수 없습니다.");
        }
        // 로그인 테스트용
        return new UserDetailsImpl(userInfo);
    }

}
