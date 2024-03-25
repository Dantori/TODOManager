package ru.trofimov.todomanager.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import ru.trofimov.todomanager.service.UserService;
import ru.trofimov.todomanager.web.controller.AdminController;
import ru.trofimov.todomanager.web.mapper.UserMapper;

import java.util.Collections;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private Model model;

    private AdminController adminController;

    @Before
    public void setup() {
        adminController = new AdminController(userService, userMapper);
    }

    @Test
    public void testUserList() {
        when(userService.getAllUsers()).thenReturn(Collections.emptyList());

        adminController.userList(model);

        verify(userService, times(1)).getAllUsers();
        verify(userMapper, times(1)).toDto(Collections.emptyList());
        verify(model, times(1)).addAttribute(eq("allUsers"), any());
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;
        String action = "delete";

        adminController.deleteUser(userId, action);

        verify(userService, times(1)).deleteUserById(userId);
    }
}
