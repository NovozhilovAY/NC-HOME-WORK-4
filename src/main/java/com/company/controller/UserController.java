package com.company.controller;

import com.company.model.User;
import com.company.model.UsersStorage;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class UserController {
    UsersStorage storage = new UsersStorage();

    @GetMapping("/user-form")
    public String userForm(Model model){
        model.addAttribute("user", new User());
        return "user-form";
    }

    @GetMapping("/result")
    public String userSubmit(@ModelAttribute @Valid User user ,BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "user-form";
        storage.addUser(user);
        return "result";
    }

    @GetMapping("/find-user")
    public String findUser(Model model) {
        model.addAttribute("firstName", "");
        model.addAttribute("lastName", "");
        return "find-user";
    }

    @GetMapping("/find-result")
    public String findResult(@RequestParam String firstName, @RequestParam String lastName,@RequestHeader("User-agent") String browser, Model model) {
        User user = storage.findUser(firstName, lastName);
        if(user != null){
            model.addAttribute("user", user);
            model.addAttribute("browser", browser);
            model.addAttribute("time", new Date().toString());
            return "find-result";
        } else {
            return "user-not-found";
        }
    }
}
