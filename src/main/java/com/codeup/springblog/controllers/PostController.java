package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
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

    @GetMapping
    public String onePost(@PathVariable long id, Model model) {
        Post post = postDao.findById(id);
        model.addAttribute("post", post);
        return "/posts/show";
    }

    @GetMapping("/create")
    public String createPost() {
        return "posts/create";
    }

    @PostMapping("/create")
    public String submitPost(@RequestParam (name="title") String title, @RequestParam (name="body") String body) {
        // we create an object that maps to the relational database table
        User user = userDao.findById(1);
        Post post = new Post(title, body, user);
        postDao.save(post);
        return "redirect:/post/create/all-post";
    }

    @GetMapping("/show/{id}")
    public String showUser(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.findById(id));
        return "/posts/show";
    }

//    @GetMapping("/user")
//    public String showUserInfo(Model model){
//        List<User> users = userDao.findAll();
//        model.addAttribute("user", users);
//        return "/posts/user";
//    }
//
//    @PostMapping("/user")
//    public String addUser(@RequestParam (name="username") String userName, @RequestParam (name="email") String email, @RequestParam (name="password") String password) {
//        User user = new User(userName, email, password);
//        userDao.save(user);
//        return "redirect:/post/user";
//    }

}
