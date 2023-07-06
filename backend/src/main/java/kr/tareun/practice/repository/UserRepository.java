package kr.tareun.practice.repository;

import kr.tareun.practice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
}
