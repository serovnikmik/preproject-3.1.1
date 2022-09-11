package com.example.demo.user.controller;

import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model){
        return "home";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("list", userService.getAllUsers());
        return "list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id){
        System.out.println("trying to delete: " + userService.getUserById(id));
        userService.delete(id);
        return "redirect:/user/list";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute User user){
        System.out.println("trying to create user: " + user);
        userService.save(user);
        return "redirect:/user/list";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute User user){
        userService.update(id, user.getName(), user.getAge(), user.getEmail());
        return "redirect:/user/{id}";
    }

    @GetMapping("/addExamples")
    public String addExamples(){
        userService.addExamples();
        return "redirect:/user/list";
    }

    @GetMapping("/deleteAll")
    public String deleteAll(){
        userService.deleteAll();
        return "redirect:/user/list";
    }

}
