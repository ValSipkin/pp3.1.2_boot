package ru.sipkin.springboot_userapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sipkin.springboot_userapp.model.User;
import ru.sipkin.springboot_userapp.service.UserService;
import java.util.List;

@Controller
@RequestMapping("/")
public class AppController {
    private UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUsers(Model model) {
        List<User> usersList = userService.getUserList();
        model.addAttribute("usersList", usersList);
        return "showUsersView";
    }
    @GetMapping("/userInfo")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "userInfoView";
    }
    @PostMapping
    public String saveUser(@ModelAttribute("user") User user) {
        if(user.getId() == 0) {
            userService.saveUser(user);
        } else {
            userService.updateUser(user);
        }

        return "redirect:/";
    }
    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "userInfoView";
    }
    @PostMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
