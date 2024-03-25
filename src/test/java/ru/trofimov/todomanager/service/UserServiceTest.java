package ru.trofimov.todomanager.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.trofimov.todomanager.domain.account.User;
import ru.trofimov.todomanager.repository.UserRepository;
import ru.trofimov.todomanager.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailService emailService;

    private UserService userService;

    @Before
    public void setup() {
        userService = new UserServiceImpl(userRepository, new BCryptPasswordEncoder(), emailService);
    }

    @Test
    public void testAddNewUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        Mockito.when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(user)).thenReturn(user);

        boolean result = userService.addUser(user);

        assertTrue(result);

        Mockito.verify(userRepository, Mockito.times(1)).findByUsername("testuser");
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void testAddUserWithExistingUsername() {
        User user = new User();
        user.setUsername("existinguser");
        user.setPassword("testpassword");

        Mockito.when(userRepository.findByUsername("existinguser")).thenReturn(Optional.of(user));

        boolean result = userService.addUser(user);

        assertFalse(result);

        Mockito.verify(userRepository, Mockito.times(1)).findByUsername("existinguser");
        Mockito.verify(userRepository, Mockito.never()).save(user);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("user1", "password1"));
        users.add(new User("user2", "password2"));

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(users, result);

        Mockito.verify(userRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testDeleteUserById() {
        Long userId = 1L;

        userService.deleteUserById(userId);

        Mockito.verify(userRepository, Mockito.times(1)).deleteById(userId);
    }
}
