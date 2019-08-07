package com.example.kawasakitodo.service;


import com.example.kawasakitodo.entity.TodoList;
import com.example.kawasakitodo.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import static org.springframework.data.domain.Sort.Direction.DESC;


import java.util.List;
import java.util.Objects;

@Service
@ComponentScan("service")
public class TodoService {

    @Autowired
    private TodoListRepository todoListRepository;

    /**
     * findAllメソッド
     * データベースから指定された述語によって定義された条件と一致するすべての要素を取得
     * @return リポジトリのfindAllメソッドを返す
     */
    public List<TodoList> findAll() {
        return todoListRepository.findAll(new Sort(DESC, "createDate"));
    }

    /**
     * searchInDoneメソッド
     *元のデータに名前が含まれているか
     * @param name データカラム
     * @return リポジトリのfindByNameContainingAndDoneIsFalseOrderByCreateDateDescを返す
     */
    public List<TodoList> searchInDone(String name) {
        return todoListRepository.findByNameContainingAndDoneIsFalseOrderByCreateDateDesc(name);
    }


    /**
     * findOneByIdメソッド
     * 特定のidの要素を取得する
     * @param id　データカラム
     * @return リポジトリのfindByIdを返す
     */
    public TodoList findOneById(Long id) {
        return todoListRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    /**
     * createExistSameNameCaseメソッド
     * 重複するかどうかを判定するメソッド
     * @param todoList
     * @return trueかfalseを返す
     */
    public boolean createExistSameNameCase(TodoList todoList) {
        TodoList sameNameTodoList = todoListRepository.findByNameEquals(todoList.getName());
        return Objects.nonNull(sameNameTodoList);
    }

    /**
     * createメソッド
     * todoリストを作成する
     * @param todoList
     */
    public void create(TodoList todoList) {
        todoList.setCreateDate();
        todoListRepository.save(todoList);
    }

    /**
     * updateメソッド
     * @param id データベースのカラム
     * @param receivedTodoList
     */
    public void update(Long id, TodoList receivedTodoList) {
        TodoList todoList = findOneById(id);
        todoList.setName(receivedTodoList.getName());
        todoList.setDeadLine(receivedTodoList.getDeadLine());
        todoListRepository.save(todoList);
    }

    /**
     * changeメソッド
     * @param id データベースのカラム
     */
    public void change(Long id) {
        TodoList todoList = findOneById(id);
        todoList.changeDone();
        todoListRepository.save(todoList);
    }

    /**
     * deleteメソッド
     * 指定したtodoリストを削除する
     * @param id データベースのカラム
     */
    public void delete(Long id) {
        todoListRepository.deleteById(id);
    }

    /**
     * allDeleteメソッド
     * 全てのtodoリストを削除する
     */
    public void allDelete(){
        todoListRepository.deleteAll();
    }

}
