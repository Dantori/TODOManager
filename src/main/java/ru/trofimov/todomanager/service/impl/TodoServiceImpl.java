package ru.trofimov.todomanager.service.impl;

import org.springframework.stereotype.Service;
import ru.trofimov.todomanager.domain.exception.TodoNotFoundException;
import ru.trofimov.todomanager.domain.todo.Todo;
import ru.trofimov.todomanager.repository.TodoRepository;
import ru.trofimov.todomanager.service.EmailService;
import ru.trofimov.todomanager.service.TodoService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final EmailService emailService;

    public TodoServiceImpl(TodoRepository todoRepository, EmailService emailService) {
        this.todoRepository = todoRepository;
        this.emailService = emailService;
    }

    @Override
    public void addTodo(Todo todo) {
        todo.setDate(new Date());
        todoRepository.save(todo);
        String subj = "TODO Manager. Событие";
        String msg = "Здравствуйте, " + todo.getUser().getUsername() + "!" +
                "\nВы добавили новую задачу: " + todo.getDescription() +
                "\nВремя создания: " + todo.getDate();

        emailService.sendEmailToUser(todo.getUser().getUsername(), subj, msg);
    }

    @Override
    public void setStatus(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) {
            todo.get().setCompleted(!todo.get().isCompleted());
            todoRepository.save(todo.get());
        } else {
            throw new TodoNotFoundException("Todo with id " + id + " not found!");
        }

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

