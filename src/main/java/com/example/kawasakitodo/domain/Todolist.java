package com.example.kawasakitodo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Todolist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String todoname;
    private Boolean done;

    public Long getId() {
        return id;
    }

    public String getTodoname(String todoname) {
        return todoname;
    }

    public Boolean getDone() {
        return done;
    }

}