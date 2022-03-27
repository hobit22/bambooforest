package com.sparta.bambooforest.controller;


import com.sparta.bambooforest.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
public class HomeController {
//
//    @GetMapping("/")
//    public String homeNoLogin() {
//        return "index";
//    }

    @GetMapping("/")
    public String homeLogin(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Optional<UserDetailsImpl> userDetails1 = Optional.ofNullable(userDetails);
        if(userDetails1.isPresent()) {
            model.addAttribute("username", userDetails.getUsername());
            return "index";
        }
        return "index";
    }
}