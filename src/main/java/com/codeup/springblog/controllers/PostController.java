package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.Util;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    // dependency injection
    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/create/all-post")
    public String allPosts(Model model) {
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/single-post/{id}")
    public String onePost(@PathVariable long id, Model model) {
        Post post = postDao.findById(id);
        model.addAttribute("post", post);
        return "/posts/show";
    }

    @GetMapping("/create")
    public String createPost(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/create")
    public String submitPost(@ModelAttribute Post post) {
        User user = Util.login();
        post.setUser(user);
        postDao.save(post);
        return "redirect:";
    }

    @GetMapping("/show/{id}")
    public String showUserInfo(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.findById(id));
        return "/posts/show";
    }

    @GetMapping("/{id}/edit")
    public String postForm(@PathVariable long id, Model model) {
        Post post = postDao.findById(id);
        model.addAttribute("post", post);
        return "posts/edit-post";
    }

    @PostMapping("/{id}/edit")
    public String editPost(@PathVariable long id, @ModelAttribute Post post) {
        User user = Util.login();
        post.setUser(user);
        postDao.save(post);
        return "redirect:/post/create/all-post";
    }

}
