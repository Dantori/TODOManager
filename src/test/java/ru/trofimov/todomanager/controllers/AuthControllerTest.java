package ru.trofimov.todomanager.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import ru.trofimov.todomanager.service.UserService;
import ru.trofimov.todomanager.web.controller.AuthController;
import ru.trofimov.todomanager.web.dto.UserDto;
import ru.trofimov.todomanager.web.mapper.UserMapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    private AuthController authController;

    @Before
    public void setup() {
        authController = new AuthController(userService, userMapper);
    }

    @Test
    public void testAddUser_WhenPasswordsNotEqual_ExpectPasswordError() {
        UserDto userDto = new UserDto("username", "password", "differentPassword");
        Model model = new ExtendedModelMap();

        String result = authController.addUser(userDto, model);

        assertEquals("registration", result);
        assertEquals("Passwords not equals!", model.getAttribute("passwordError"));
    }

    @Test
    public void testAddUser_WhenUserExists_ExpectUsernameError() {
        UserDto userDto = new UserDto("existingUsername", "password", "password");
        Model model = new ExtendedModelMap();
        when(userService.addUser(any())).thenReturn(false);

        String result = authController.addUser(userDto, model);

        assertEquals("registration", result);
        assertEquals("A user with this username already exists!", model.getAttribute("usernameError"));
    }
}