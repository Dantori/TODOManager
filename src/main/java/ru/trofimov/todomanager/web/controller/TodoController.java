package ru.trofimov.todomanager.web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.trofimov.todomanager.domain.account.User;
import ru.trofimov.todomanager.domain.todo.Todo;
import ru.trofimov.todomanager.service.TodoService;

import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String getTodoList(Model model, @AuthenticationPrincipal User user) {
        List<Todo> todos = todoService.getAllByUserId(user.getId());
        model.addAttribute("todos", todos);
        model.addAttribute("newTodo", new Todo());
        return "todo";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute("newTodo") Todo newTodo, @AuthenticationPrincipal User user) {
        newTodo.setUser(user);
        todoService.addTodo(newTodo);
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