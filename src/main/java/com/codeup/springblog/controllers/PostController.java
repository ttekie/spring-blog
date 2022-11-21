package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostRepository postDao;
    // dependency injection
    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/create/all-post")
    public String allPosts(Model model) {
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/create")
    public String createNewPost() {
        return "posts/create";
    }

    @PostMapping("/create")
    public String addNewPost(@RequestParam (name="title") String title, @RequestParam (name="body") String body) {
        Post post = new Post(title, body);
        postDao.save(post);
        return "redirect:/post/create/all-post";
    }

}
