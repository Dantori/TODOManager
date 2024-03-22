package ru.trofimov.todomanager.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.trofimov.todomanager.service.UserService;
import ru.trofimov.todomanager.web.mapper.UserMapper;

@Controller
public class AdminController {

    private final UserService userService;
    private final UserMapper userMapper;

    public AdminController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/admin")
    public String userList( Model model) {
        model.addAttribute("allUsers", userMapper.toDto(userService.getAllUsers()));
        return "admin";
    }

    @PostMapping("/admin")
    public String deleteUser(@RequestParam(defaultValue = "") Long userId,
                             @RequestParam(defaultValue = "") String action) {
        if (action.equals("delete")) {
            userService.deleteUserById(userId);
        }
        return "redirect:/admin";
    }
}
