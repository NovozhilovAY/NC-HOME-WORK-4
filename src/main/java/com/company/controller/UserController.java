package com.company.controller;

import com.company.model.User;
import com.company.model.UsersStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController {
    UsersStorage storage = new UsersStorage();

    @GetMapping("/user-form")
    public String userForm(Model model){
        model.addAttribute("user", new User());
        return "user-form";
    }

    @GetMapping("/result")
    public String userSubmit(@ModelAttribute User user) {
        storage.addUser(user);
        return "result";
    }
}
