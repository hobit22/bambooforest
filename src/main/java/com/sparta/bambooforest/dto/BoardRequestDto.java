package com.sparta.bambooforest.dto;

import com.sparta.bambooforest.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BoardRequestDto {
    private String title;
    private String content;
    private User user;
}
