package ru.trofimov.todomanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trofimov.todomanager.domain.exception.TodoNotFoundException;
import ru.trofimov.todomanager.domain.todo.Todo;
import ru.trofimov.todomanager.repository.TodoRepository;
import ru.trofimov.todomanager.service.TodoService;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void addTodo(Todo todo) {
        todoRepository.save(todo);
    }

    @Override
    public Todo getById(Long id) {
        Optional<Todo> todoFromDB = todoRepository.findById(id);
        return todoFromDB.orElseThrow(() -> new TodoNotFoundException("Todo with id " + id + " not found!"));
    }

    @Override
    public List<Todo> getAllByUserId(Long userId) {
        return todoRepository.findAllByUserId(userId);
    }

    @Override
    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }
}

