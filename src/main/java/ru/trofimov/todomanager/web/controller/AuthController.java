package ru.trofimov.todomanager.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.trofimov.todomanager.service.UserService;
import ru.trofimov.todomanager.web.dto.UserDto;
import ru.trofimov.todomanager.web.mapper.UserMapper;

@Controller
public class AuthController {

    private final UserService userService;
    private final UserMapper userMapper;

    public AuthController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") UserDto userDto, Model model) {
        if (!userDto.getPassword().equals(userDto.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Passwords not equals!");
            return "registration";
        }
        if(!userService.addUser(userMapper.toEntity(userDto))) {
            model.addAttribute("usernameError", "A user with this username already exists!");
            return "registration";
        }
        return "/login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "/login";
    }
}
