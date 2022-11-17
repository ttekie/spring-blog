package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roll-dice")
public class RollDiceController {

    @GetMapping("/roll-dice/num")
    public int guessANumber(@PathVariable int num, Model model) {
        return 0;
    }
}
