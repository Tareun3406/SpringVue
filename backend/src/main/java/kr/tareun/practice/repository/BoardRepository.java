package kr.tareun.practice.repository;

import kr.tareun.practice.entity.Board;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

//    @EntityGraph(attributePaths = {"comments"})
//    @Query("SELECT b FROM Board b LEFT JOIN FETCH b.comments c WHERE c.parentComment = null AND b.no = :no")
//    Optional<Board> findBoardWithFilteredComments(@Param("no") Long no);
}