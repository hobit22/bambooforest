package com.sparta.bambooforest.controller;


import com.sparta.bambooforest.model.Board;
import com.sparta.bambooforest.repository.BoardRepository;
import com.sparta.bambooforest.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {
//
//    @GetMapping("/")
//    public String homeNoLogin() {
//        return "index";
//    }
    private final BoardRepository boardRepository;
    @GetMapping("/")
    public String homeLogin(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Optional<UserDetailsImpl> userDetails1 = Optional.ofNullable(userDetails);
        if(userDetails1.isPresent()) {
            model.addAttribute("username", userDetails.getUsername());
            return "index";
        }
        //List<Board> boardList = boardRepository.findAllByOrderByModifiedAtDesc();
//        List<Board> boardList = boardRepository.findAll();
//        System.out.println(boardList);
//        for(Board board: boardList){
//            System.out.println(board.getTitle());
//        }
//        model.addAttribute("boardList", boardList);
        return "index";
    }
}