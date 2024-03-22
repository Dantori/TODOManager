package ru.trofimov.todomanager.service;

public interface EmailService {

    void sendEmailToUser(String userEmail, String subject, String body);
}
