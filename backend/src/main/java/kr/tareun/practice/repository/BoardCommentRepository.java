package kr.tareun.practice.repository;

import kr.tareun.practice.entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {

    public List<BoardComment> findAllByParentBoardNo(long boardNo);
}
