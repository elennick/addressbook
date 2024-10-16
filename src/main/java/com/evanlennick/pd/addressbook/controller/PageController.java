package com.evanlennick.pd.addressbook.controller;

import com.evanlennick.pd.addressbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    private final UserService userService;

    @Autowired
    public PageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.getUsers().users());
        return "index";
    }

    @GetMapping("/user-profile/{userId}")
    public String userProfile(Model model, @PathVariable("userId") String userId) {
        model.addAttribute("user", userService.getUser(userId));
        return "user-profile";
    }

}
