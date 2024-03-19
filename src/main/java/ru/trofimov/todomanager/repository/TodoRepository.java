package ru.trofimov.todomanager.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.trofimov.todomanager.domain.todo.Todo;

import java.util.List;

@Repository
public interface TodoRepository extends PagingAndSortingRepository<Todo, Long> {

    List<Todo> findAllByUserId(Long id);

}