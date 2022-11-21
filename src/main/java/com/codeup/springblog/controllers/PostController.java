package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postDao;
    // dependency injection
    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping
    public String allPosts(Model model) {
        Post post1 = new Post(1, "First", "This is my first post!");
        Post post2 = new Post(2, "Second", "Hey everyone, I'm back");
        List<Post> allPosts = new ArrayList<>(List.of(post1, post2));
        model.addAttribute("allPosts", allPosts);
        return "/posts/index";
    }

    @GetMapping("/{id}")
    public String onePost(@PathVariable long id, Model model) {
        Post post1 = new Post(1, "First", "This is my first post!");
        Post post2 = new Post(2, "Second", "Hey everyone, I'm back");
        Post post3 = new Post(3, "Yo", "heye heye heyeee");
        List<Post> allPosts = new ArrayList<>(List.of(post1, post2, post3));
        Post post = null;
        for(Post userPost : allPosts) {
            if(userPost.getId() == id) {
                post = userPost;
            }
        }
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/create")
    public String createPost() {
        return "/posts/create";
    }

    @GetMapping("/posts")
    @ResponseBody
    public String viewPost() {
        return "view the form for creating a post!";
    }

    @PostMapping("/create")
    @ResponseBody
    public String fourPost() {
        return "view the form for creating a post!";
    }

}
