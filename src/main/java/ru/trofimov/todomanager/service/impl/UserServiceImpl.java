package ru.trofimov.todomanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.trofimov.todomanager.domain.account.Role;
import ru.trofimov.todomanager.domain.account.User;
import ru.trofimov.todomanager.repository.UserRepository;
import ru.trofimov.todomanager.service.EmailService;
import ru.trofimov.todomanager.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }


    @Override
    public boolean addUser(User user) {
        Optional<User> userFromDB = userRepository.findByUsername(user.getUsername());
        if(userFromDB.isPresent()) {
            return false;
        } else {
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            String subj = "TODO Manager. Регистрация";
            String msg = "Здравствуйте, " + user.getUsername() + "!" +
                    "\nВаш аккаунт успешно зарегистрирован" +
                    "\nСпасибо за то, что пользуйтесь нашим приложением :)";

            emailService.sendEmailToUser(user.getUsername(), subj, msg);

            return true;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}