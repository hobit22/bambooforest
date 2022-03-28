package com.sparta.bambooforest.model;


import com.sparta.bambooforest.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Post extends Timestamped {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(length = 1000, nullable = false)
    private String content;

    @Column(nullable = false)
    private Long account_id;

    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
    public Post(String title, String content, Long account_id, String username ){
        this.title = title;
        this.content = content;
        this.account_id = account_id;
        this.username = username;
    }
}
