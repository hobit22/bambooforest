package com.sparta.bambooforest.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
public class SignupRequestDto {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Pattern(regexp = "[0-9a-zA-Z]{3,10}", message = "3자 ~ 10자, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)")
    private String username;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "[0-9a-zA-Z]{4,}", message = "비밀번호는 4자이상 숫자.")
    private String password;
    private String password2;
    @NotBlank(message = "이메일는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}