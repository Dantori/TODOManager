package ru.trofimov.todomanager.service;

import ru.trofimov.todomanager.domain.account.User;

import java.util.List;

public interface UserService {

    boolean addUser(User user);
    List<User> getAllUsers();
    void deleteUserById(Long id);
}
