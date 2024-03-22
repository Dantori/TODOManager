package ru.trofimov.todomanager.web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.trofimov.todomanager.domain.account.User;
import ru.trofimov.todomanager.service.TodoService;
import ru.trofimov.todomanager.web.dto.TodoDto;
import ru.trofimov.todomanager.web.mapper.TodoMapper;

import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;
    private final TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @GetMapping
    public String getTodoList(Model model, @AuthenticationPrincipal User user) {
        List<TodoDto> todos = todoMapper.toDto(todoService.getAllByUserId(user.getId()));
        model.addAttribute("todos", todos);
        model.addAttribute("newTodo", new TodoDto());
        return "todo";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute("newTodo") TodoDto todoDto, @AuthenticationPrincipal User user) {
        todoDto.setUser(user);
        todoService.addTodo(todoMapper.toEntity(todoDto));
        return "redirect:/todos";
    }

    @PostMapping("/{id}/toggle")
    public String toggleTodoStatus(@PathVariable("id") Long id) {
        todoService.setStatus(id);
        return "redirect:/todos";
    }

    @PostMapping("/{id}/delete")
    public String deleteTodoById(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return "redirect:/todos";
    }
}