package com.sparta.bambooforest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.bambooforest.dto.SignupRequestDto;
import com.sparta.bambooforest.security.UserDetailsImpl;
import com.sparta.bambooforest.service.KakaoUserService;
import com.sparta.bambooforest.service.UserService;
import com.sparta.bambooforest.util.CheckEmailValidator;
import com.sparta.bambooforest.util.CheckPasswordValidator;
import com.sparta.bambooforest.util.CheckUsernameValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@Controller
public class UserController {
    private final UserService userService;
    private final KakaoUserService kakaoUserService;
    private final CheckUsernameValidator checkUsernameValidator;
    private final CheckPasswordValidator checkPasswordValidator;
    private final CheckEmailValidator checkEmailValidator;

    /* 커스텀 유효성 검증을 위해 추가 */ @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkUsernameValidator);
        binder.addValidators(checkPasswordValidator);
        binder.addValidators(checkEmailValidator);
    }

    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService, CheckUsernameValidator checkUsernameValidator, CheckEmailValidator checkEmailValidator, CheckPasswordValidator checkPasswordValidator) {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
        this.checkEmailValidator = checkEmailValidator;
        this.checkPasswordValidator = checkPasswordValidator;
        this.checkUsernameValidator = checkUsernameValidator;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@Valid SignupRequestDto requestDto, Errors errors, Model model) {
        if (errors.hasErrors()) { /* 회원가입 실패시 입력 데이터 값을 유지 */
            model.addAttribute("requestDto", requestDto); /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                System.out.println("key = " + key);
                System.out.println("message = " + validatorResult.get(key));
                model.addAttribute(key, validatorResult.get(key));
            } /* 회원가입 페이지로 다시 리턴 */
            return "signup";
        }
        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        // authorizedCode: 카카오 서버로부터 받은 인가 코드
        kakaoUserService.kakaoLogin(code);

        return "redirect:/";
    }
}