package com.sparta.bambooforest.model;


import com.sparta.bambooforest.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Board extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(nullable=false)
    private User user;

//    @OneToMany(mappedBy = "board",cascade = CascadeType.REMOVE)
    //List<Comment> comment = new ArrayList<>();


    public Board(BoardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.user = requestDto.getUser();
    }

    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.user = requestDto.getUser();
    }
}