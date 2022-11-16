package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {

    @GetMapping
    @ResponseBody
    public String allPosts() {
        return "Here are all the posts:...";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String onePost(@PathVariable String id) {
        return "Here is a post number!" + id;
    }

    @GetMapping("/create")
    @ResponseBody
    public String createPost() {
        return "posts index page!";
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
