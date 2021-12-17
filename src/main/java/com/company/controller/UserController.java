package com.company.controller;

import com.company.model.User;
import com.company.model.UsersStorage;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

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

    @GetMapping("/upload-user-file")
    public String uploadUserFile(){
        return "upload-user-file";
    }

    @PostMapping("/upload-file-submit")
    public String uploadFileSubmit(@RequestParam("file") MultipartFile file, Model model) {
        User newUser = storage.addUser(file);
        if(newUser == null){
            return "bad-file";
        }
        model.addAttribute("user", newUser);
        return "result";
    }
}
