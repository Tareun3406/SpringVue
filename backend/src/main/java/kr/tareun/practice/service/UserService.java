package kr.tareun.practice.service;

import kr.tareun.practice.vo.UserVO;

public interface UserService {

    void joinUser(UserVO joinUserInfo);

    UserVO getUserInfoById(String id);

    boolean checkIsDuplicateUser(String username);
}
