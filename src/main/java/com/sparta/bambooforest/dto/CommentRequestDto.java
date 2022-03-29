package com.sparta.bambooforest.dto;

import com.sparta.bambooforest.model.Board;
import com.sparta.bambooforest.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDto {
    private String text;
    private User user;
    private Board board;
}