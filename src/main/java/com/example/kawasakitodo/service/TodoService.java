package com.example.kawasakitodo.service;


import com.example.kawasakitodo.repojitory.TodoRepository;
import com.sun.tools.javac.comp.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> findAll() {
        return TodoRepository.findAll();
    }

    public Todo findOne(Long id) {
        return TodoRepository.findOne(id);
    }

    public Todo save (Todo todo) {
        return todoRepojitory.save(todo);
    }

    public void delete(Long id) {
        todoRepojitory.delete(id);
    }
}
