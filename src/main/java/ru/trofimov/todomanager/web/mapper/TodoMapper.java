package ru.trofimov.todomanager.web.mapper;

import org.mapstruct.Mapper;
import ru.trofimov.todomanager.domain.todo.Todo;
import ru.trofimov.todomanager.web.dto.TodoDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    TodoDto toDto(Todo todo);
    List<TodoDto> toDto(List<Todo> todo);
    Todo toEntity(TodoDto todoDto);
}
