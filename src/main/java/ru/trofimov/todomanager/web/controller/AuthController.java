package ru.trofimov.todomanager.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.trofimov.todomanager.domain.account.User;
import ru.trofimov.todomanager.service.UserService;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Passwords not equals!");
            return "registration";
        }
        if(!userService.addUser(user)) {
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
