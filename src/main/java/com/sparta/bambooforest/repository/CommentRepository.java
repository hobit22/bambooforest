package com.sparta.bambooforest.repository;

import com.sparta.bambooforest.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoardIdOrderByModifiedAtDesc(Long id);

}
