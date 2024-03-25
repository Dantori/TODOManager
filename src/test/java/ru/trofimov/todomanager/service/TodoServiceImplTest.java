package ru.trofimov.todomanager.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.trofimov.todomanager.domain.account.User;
import ru.trofimov.todomanager.domain.exception.TodoNotFoundException;
import ru.trofimov.todomanager.domain.todo.Todo;
import ru.trofimov.todomanager.repository.TodoRepository;
import ru.trofimov.todomanager.service.impl.TodoServiceImpl;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceImplTest {

    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private EmailService emailService;

    @Before
    public void setup() {
        todoService = new TodoServiceImpl(todoRepository, emailService);
    }

    @Test
    public void testAddTodo() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        Todo todo = new Todo();
        todo.setDescription("Your Todo Description");
        todo.setUser(user);

        todoService.addTodo(todo);

        assertNotNull(todo.getDate());
        verify(todoRepository).save(todo);
        verify(emailService).sendEmailToUser(todo.getUser().getUsername(), "TODO Manager. Событие",
                "Здравствуйте, " + todo.getUser().getUsername() + "!" +
                        "\nВы добавили новую задачу: " + todo.getDescription() +
                        "\nВремя создания: " + todo.getDate());
    }

    @Test
    public void testSetStatusTodoFound() {
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setCompleted(false);

        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));

        todoService.setStatus(1L);

        assertTrue(todo.isCompleted());
        verify(todoRepository).save(todo);
    }

    @Test(expected = TodoNotFoundException.class)
    public void testSetStatusTodoNotFound() {
        Long id = 2L;

        when(todoRepository.findById(id)).thenReturn(Optional.empty());

        todoService.setStatus(id);
    }
}

