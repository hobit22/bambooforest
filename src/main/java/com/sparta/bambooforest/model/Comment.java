package com.sparta.bambooforest.model;

import com.sparta.bambooforest.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Board board;

    public Comment(CommentRequestDto requestDto){
        this.text = requestDto.getText();
        this.user = requestDto.getUser();
        this.board = requestDto.getBoard();
    }
}