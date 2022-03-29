package com.sparta.bambooforest.repository;

import com.sparta.bambooforest.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByModifiedAtDesc();

    List<Board> findAllById(Long id);
}
