package com.sparta.bambooforest.controller;


import com.sparta.bambooforest.dto.BoardRequestDto;
import com.sparta.bambooforest.model.Board;
import com.sparta.bambooforest.repository.BoardRepository;
import com.sparta.bambooforest.security.UserDetailsImpl;
import com.sparta.bambooforest.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;
//
//    @GetMapping("/")
//    public String getIndex(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        List<Board> boardList = boardRepository.findAllByOrderByModifiedAtDesc();
//        model.addAttribute("boardList",boardList);
//        model.addAttribute("username",userDetails.getUsername());
//        return "index";
//    }

    @GetMapping("/api/index")
    @ResponseBody //template 안쓰고 data 만 넘길때 필요
    public List<Board> getBoard(){
        List<Board> boardList = boardRepository.findAllByOrderByModifiedAtDesc();
//        model.addAttribute("boardList",boardList);
        return boardList;
    }

    // json 일때 postMapping @RequestBody 필요함
    @PostMapping("/api/board")
    public String createBoard(@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody BoardRequestDto requestDto){
        requestDto.setUser(userDetails.getUser());
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return "index";
    }

    @GetMapping("/api/board/{id}")
    public String getPostById(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("/api/board/{id} 들어옴");
        Board board = boardRepository.findById(id).orElseThrow(() -> new NullPointerException("없는 게시물 입니다."));
        boolean boardflag = false;
        if(userDetails == null){
            model.addAttribute("user","null");
        }else{
            model.addAttribute("user",userDetails.getUser().getUsername());
            boardflag = board.getUser().getUsername().equals(userDetails.getUser().getUsername());
        }
        model.addAttribute("board", board);
        model.addAttribute("boardflag", boardflag);
        return "detail";
    }

    @DeleteMapping("/api/board/{id}")
    public String deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("/api/board/{id} " + id );
        Board board = boardRepository.findById(id).orElseThrow(
                ()->new NullPointerException("해당 게시물이 없습니다.")
        );

        if(!Objects.equals(board.getUser().getId(), userDetails.getUser().getId())){
            return "index";
        }
        boardRepository.deleteById(id);
        return "redirect:/";
    }

    /* 수정
    @GetMapping("/api/board/{id}/edit")
    public String getEditBoard(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Board board = boardRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        if(userDetails == null){
            model.addAttribute("user","null");
        }else{

            model.addAttribute("user",userDetails.getUser().getUsername());
        }
        model.addAttribute("board",board);
        return "editboard";
    }



    @PutMapping("/api/board/{id}")
    public String updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        requestDto.setUser(userDetails.getUser());
        if(!Objects.equals(board.getUser().getId(), userDetails.getUser().getId())){
            return "redirect:/";
        }
        boardService.update(id, requestDto);
        return "redirect:/";
    }
    */

}

