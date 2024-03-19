package ru.trofimov.todomanager.service;

import ru.trofimov.todomanager.domain.todo.Todo;

import java.util.List;

public interface TodoService {

    void addTodo(Todo todo);
    Todo getById(Long id);
    List<Todo> getAllByUserId(Long userId);
    void deleteTodoById(Long id);
}
