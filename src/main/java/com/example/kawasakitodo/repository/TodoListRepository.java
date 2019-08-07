package com.example.kawasakitodo.repository;


import com.example.kawasakitodo.entity.TodoList;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    /**
     * todoリスト名が含まれているかどうか
     *
     * @param name
     * @return findByNameContainingAndDoneIsFalseOrderByCreateDateDescの結果を返却
     */
    List<TodoList> findByNameContainingAndDoneIsFalseOrderByCreateDateDesc(String name);


    /**
     * todoリストの名前と重複しているかどうか
     *
     * @param name
     * @return findByNameEqualsの結果を返却
     */
    TodoList findByNameEquals(String name);
}
