package com.sparta.bambooforest.controller;


import com.sparta.bambooforest.dto.CommentRequestDto;
import com.sparta.bambooforest.model.Board;
import com.sparta.bambooforest.model.Comment;
import com.sparta.bambooforest.repository.BoardRepository;
import com.sparta.bambooforest.repository.CommentRepository;
import com.sparta.bambooforest.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    BoardRepository boardRepository;


    @GetMapping("/api/board/{id}/comment")
    @ResponseBody
    public List<Comment> getComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id){
        System.out.println("GET /api/board/{id}/comment 들어옴");
        Board board = boardRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        List<Comment> commentList = commentRepository.findByBoardIdOrderByModifiedAtDesc(id);

        return commentList;
    }

    @PostMapping("/api/board/{id}/comment")
    public String createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @RequestBody CommentRequestDto requestDto){
        System.out.println("POST /api/board/{id}/comment 들어옴");
        Comment comment = new Comment(requestDto);
        Board board = boardRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        comment.setUser(userDetails.getUser());
        System.out.println("comment.getText " + comment.getText());
        comment.setBoard(board);
        commentRepository.save(comment);
        return "redirect:/api/board/{id}";
    }

    @PutMapping("/api/board/{id}/comment/{commentId}")
    public String editComment(@PathVariable Long commentId, @ModelAttribute CommentRequestDto requestDto){
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );

        comment.setText(requestDto.getText());

        commentRepository.save(comment);
        return "redirect:/api/board/{id}";
    }

    @DeleteMapping("/api/board/{id}/comment/{commentId}")
    public String deleteComment(@PathVariable Long commentId){
        commentRepository.deleteById(commentId);
        return "redirect:/api/board/{id}";
    }
}