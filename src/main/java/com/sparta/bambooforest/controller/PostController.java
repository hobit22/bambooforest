package com.sparta.bambooforest.controller;


import com.sparta.bambooforest.dto.PostRequestDto;
import com.sparta.bambooforest.model.Post;
import com.sparta.bambooforest.repository.PostRepository;
import com.sparta.bambooforest.security.UserDetailsImpl;
import com.sparta.bambooforest.service.PostService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping("/post")
    @ResponseBody
    public List<Post> getPosts() {
        System.out.println("getTask" + postRepository.findAllByOrderByModifiedAtDesc().get(0).getTitle());
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    @PostMapping("/post/register")
    @ResponseBody
    public Post createTasks(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Post post = new Post(requestDto);
        post.setUsername(userDetails.getUser().getUsername());
        post.setAccount_id(userDetails.getUser().getId());
        System.out.println(post.getContent());
        System.out.println(post.getUsername());

        return postRepository.save(post);
    }
//    @GetMapping("/api/tasks/{id}")
//    public List<Task> getTasksById(@PathVariable Long id) {
//        System.out.println("getTasksById" + postsRepository.findAllById(id).get(0).getTitle());
//        return postsRepository.findAllById(id);
//    }
//
//    @PutMapping("/api/tasks/{id}")
//    public Long updateTasks(@PathVariable Long id, @RequestBody TaskRequestDto requestDto) {
//        return taskService.update(id, requestDto);
//    }
//
//    @DeleteMapping("/api/tasks/{id}")
//    public Long deleteTasks(@PathVariable Long id) {
//        postsRepository.deleteById(id);
//        return id;
//    }

}

