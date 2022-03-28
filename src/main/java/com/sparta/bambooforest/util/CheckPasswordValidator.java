package com.sparta.bambooforest.util;

import com.sparta.bambooforest.dto.SignupRequestDto;
import com.sparta.bambooforest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckPasswordValidator extends AbstractValidator<SignupRequestDto> {
    private final UserRepository userRepository;

    @Override
    protected void doValidate(SignupRequestDto dto, Errors errors) {
        String username = dto.getUsername();
        String pw = dto.getPassword();
        String pw2 = dto.getPassword2();

        if(!pw.equals(pw2)){
            errors.rejectValue("password", "비밀번호 오류", "비밀번호와 비밀번호 확인이 같이 않습니다.");
        }

        if(pw.contains(username)){
            errors.rejectValue("password", "비밀번호 오류", "닉네임과 같은 값이 포함되어 있습니다.");
        }
    }
}