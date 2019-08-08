package com.example.kawasakitodo.service;


import com.example.kawasakitodo.entity.TodoList;
import com.example.kawasakitodo.exception.TodoNotFoundException;
import com.example.kawasakitodo.repository.TodoListRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.Sort.Direction.DESC;


import java.util.List;
import java.util.Objects;

@Service
public class TodoService {

    private final TodoListRepository todoListRepository;

    public TodoService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    /**
     * findAllメソッド
     * データベースから指定された述語によって定義された条件と一致するすべての要素を取得
     *
     * @return リポジトリのfindAllメソッドを返す
     */
    public List<TodoList> findAll() {
        return todoListRepository.findAll(new Sort(DESC, "createDate"));
    }

    /**
     * searchInDoneメソッド
     * 元のデータに名前が含まれているか
     *
     * @param name データカラム
     * @return リポジトリのfindByNameContainingAndDoneIsFalseOrderByCreateDateDescを返す
     */
    public List<TodoList> searchInDone(String name) {
        return todoListRepository.findByNameContainingAndDoneIsFalseOrderByCreateDateDesc(name);
    }


    /**
     * findByIdメソッド
     * 特定のidの要素を取得する
     *
     * @param id 　データカラム
     * @return リポジトリのfindByIdを返す
     */
    public TodoList findById(Long id) {
        return todoListRepository.findById(id).orElseThrow(() -> new TodoNotFoundException(String.format(id + "対象のtodoリストは見つかりませんでした")));
    }

    /**
     * searchExistSameNameCaseメソッド
     * 重複するかどうかを判定するメソッド
     *
     * @param todoList
     * @return trueかfalseを返す
     */
    public boolean searchExistSameNameCase(TodoList todoList) {
        Integer result = todoListRepository.countByNameEquals(todoList.getName());
        return result != 0;
    }

    /**
     * createメソッド
     * todoリストを作成する
     *
     * @param todoList
     */
    public void create(TodoList todoList) {
        todoListRepository.save(todoList);
    }

    /**
     * updateメソッド
     *
     * @param id データベースのカラム
     * @param receivedTodoList
     */
    public void update(Long id, TodoList receivedTodoList) {
        TodoList todoList = findById(id);
        todoList.setName(receivedTodoList.getName());
        todoList.setDeadLine(receivedTodoList.getDeadLine());
        todoListRepository.save(todoList);
    }

    /**
     * changeDone メソッド
     * 状態を変更する
     * @param todoList
     */
    public void changeDone(TodoList todoList) {
        todoList.setDone(!todoList.isDone());
    }

    /**
     * changeメソッド
     *
     * @param id データベースのカラム
     */
    public void change(Long id) {
        TodoList todoList = findById(id);
        changeDone(todoList);
        todoListRepository.save(todoList);
    }

    /**
     * deleteメソッド
     * 指定したtodoリストを削除する
     *
     * @param id データベースのカラム
     */
    public void delete(Long id) {
        todoListRepository.deleteById(id);
    }

    /**
     * allDeleteメソッド
     * 全てのtodoリストを削除する
     */
    public void allDelete() {
        todoListRepository.deleteAll();
    }

}
