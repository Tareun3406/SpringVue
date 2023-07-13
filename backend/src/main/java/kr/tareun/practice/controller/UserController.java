package kr.tareun.practice.controller;

import jakarta.servlet.http.HttpServletResponse;
import kr.tareun.practice.service.UserService;
import kr.tareun.practice.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/isLoggedIn")
    public boolean getIsLoggedIn(Principal principal) {
        return principal != null;
    }

    @PostMapping("/joinUser")
    public void joinUser(@RequestBody UserVO joinUserInfo) {
        userService.joinUser(joinUserInfo);
    }

    @GetMapping("/checkIsDuplicateUser")
    public boolean checkIsDuplicateUser(String username){
        return userService.checkIsDuplicateUser(username);
    }

    @GetMapping("/userInfo")
    public UserVO getUserInfo(Principal principal, HttpServletResponse response) {

        if (principal == null ) {
            response.setStatus(Response.SC_UNAUTHORIZED);
            return null;
        }
        return userService.getUserInfoById(principal.getName());
    }

    @PatchMapping("/updateUser")
    public void updateUser(@RequestBody UserVO userInfo) {
        userService.updateUser(userInfo);
    }
}
