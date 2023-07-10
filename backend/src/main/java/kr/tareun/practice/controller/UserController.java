package kr.tareun.practice.controller;

import kr.tareun.practice.service.UserService;
import kr.tareun.practice.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/isLoggedIn")
    public boolean getIsLoggedIn(Principal principal) {
        return principal != null;
    }

    @PutMapping("/joinUser")
    public void joinUser(@RequestBody UserVO joinUserInfo) {
        userService.joinUser(joinUserInfo);
    }

    @GetMapping("/checkIsDuplicateUser")
    public boolean checkIsDuplicateUser(String username){
        return userService.checkIsDuplicateUser(username);
    }
}
