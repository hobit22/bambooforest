package com.sparta.bambooforest.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class PostRequestDto {

    private final String title;
    private final String content;
    private final String username;
    private final Long account_id;

}
