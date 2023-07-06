package kr.tareun.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @GetMapping("/isLoggedIn")
    public boolean getIsLoggedIn(Principal principal) {
        return principal != null;
    }

}
