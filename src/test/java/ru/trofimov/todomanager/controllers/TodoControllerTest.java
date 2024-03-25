package ru.trofimov.todomanager.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import ru.trofimov.todomanager.domain.account.User;
import ru.trofimov.todomanager.domain.todo.Todo;
import ru.trofimov.todomanager.service.TodoService;
import ru.trofimov.todomanager.web.controller.TodoController;
import ru.trofimov.todomanager.web.dto.TodoDto;
import ru.trofimov.todomanager.web.mapper.TodoMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoControllerTest {

    private TodoController todoController;

    @Mock
    private TodoService todoService;

    @Mock
    private TodoMapper todoMapper;

    @Mock
    private Model model;

    @BeforeEach
    public void setup() {
        todoController = new TodoController(todoService, todoMapper);
    }

    @Test
    void testGetTodoList() {
        User user = new User();
        user.setId(1L);
        List<Todo> todos = Collections.singletonList(new Todo());
        when(todoMapper.toDto(todos)).thenReturn(Collections.singletonList(new TodoDto()));

        when(todoService.getAllByUserId(user.getId())).thenReturn(todos);
        String viewName = todoController.getTodoList(model, user);

        verify(todoService).getAllByUserId(user.getId());
        verify(todoMapper).toDto(todos);
        verify(model).addAttribute(eq("todos"), anyList());
        verify(model).addAttribute(eq("newTodo"), any(TodoDto.class));
        assertEquals("todo", viewName);
    }

    @Test
    public void testAddTodo() {
        User user = new User();
        user.setId(1L);
        TodoDto todoDto = new TodoDto();
        todoDto.setUser(user);

        Todo todo = new Todo();
        when(todoMapper.toEntity(todoDto)).thenReturn(todo);
        doNothing().when(todoService).addTodo(todo);
        String viewName = todoController.addTodo(todoDto, user);

        verify(todoMapper).toEntity(todoDto);
        verify(todoService).addTodo(todo);
        assertEquals("redirect:/todos", viewName);
    }

    @Test
    public void testToggleTodoStatus() {
        long todoId = 1L;

        doNothing().when(todoService).setStatus(todoId);

        String viewName = todoController.toggleTodoStatus(todoId);

        verify(todoService).setStatus(todoId);
        verifyNoMoreInteractions(todoService);
        assertEquals("redirect:/todos", viewName);
    }

    @Test
    public void testDeleteTodoById() {
        long todoId = 1L;

        doNothing().when(todoService).deleteTodoById(todoId);

        String viewName = todoController.deleteTodoById(todoId);

        verify(todoService).deleteTodoById(todoId);
        verify(todoService).deleteTodoById(todoId);
        verifyNoMoreInteractions(todoService);
        assertEquals("redirect:/todos", viewName);
    }
}