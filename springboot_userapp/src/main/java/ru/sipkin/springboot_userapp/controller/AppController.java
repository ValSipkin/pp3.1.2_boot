package ru.sipkin.springboot_userapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sipkin.springboot_userapp.model.User;
import ru.sipkin.springboot_userapp.service.UserService;
import java.util.List;

@Controller
public class AppController {
    private UserService userService;

    @Autowired
    public AppController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String showUsers(Model model) {
        List<User> usersList = userService.getUserList();
        model.addAttribute("usersList", usersList);
        return "showUsersView";
    }
    @RequestMapping("/userInfo")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "userInfoView";
    }
    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        if(user.getId() == 0) {
            userService.saveUser(user);
        } else {
            userService.updateUser(user);
        }

        return "redirect:/";
    }
    @RequestMapping("/updateUser")
    public String updateUser(@RequestParam("id") int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "userInfoView";
    }
    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
