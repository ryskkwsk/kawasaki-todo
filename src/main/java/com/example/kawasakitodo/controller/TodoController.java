package com.example.kawasakitodo.controller;


import com.example.kawasakitodo.entity.TodoList;
import com.example.kawasakitodo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    /**
     * topメソッド
     * データベースから一覧を取得して表示する
     * @param model Modelクラスのインスタンス
     * @return index.htmlを返す
     */
    @GetMapping("/")
    public String top( Model model) {
        TodoList todoList = new TodoList();
        List<TodoList> todoLists = todoService.findAll();
        model.addAttribute("todoList", todoList).addAttribute("todoLists", todoLists);
        return "index";
    }

    /**
     * createメソッド
     * 新しいTodoを作成する
     * @param receivedTodoList
     * @param result
     * @param model Modelクラスのインスタンス
     * @return index.htmlにリダイレクトする
     */
    @PostMapping("/")
    public String create(@ModelAttribute @Validated TodoList receivedTodoList, BindingResult result, Model model) {

        if (todoService.createExistSameNameCase(receivedTodoList)) {
            result.rejectValue("name", null, "このTodoは存在します。");
        }
        if (result.hasErrors()) {
            model.addAttribute("todoList", receivedTodoList).addAttribute("todoLists", todoService.findAll());
            return "index";
        }
        todoService.create(receivedTodoList);
        return "redirect:/";
    }

    /**
     * editメソッド
     * todoリストを編集する
     * @param id データベースのカラム
     * @param model Modelクラスのインスタンス
     * @return edit.htmlを返す
     */
    @GetMapping("{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        TodoList todoList = todoService.findOneById(id);
        model.addAttribute("todoList", todoList);
        return "edit";
    }

    /**
     * updateメソッド
     * @param id
     * @param receivedTodoList
     * @return index.htmlにリダイレクトする
     */
    @PutMapping("/{id}/update")
    public String update(@PathVariable long id, @ModelAttribute TodoList receivedTodoList) {
        todoService.update(id, receivedTodoList);
        return "redirect:/";
    }


    /**
     * changeメソッド
     * todoリストの状態を変更する
     * @param id
     * @return index.htmlにリダイレクトする
     */
    @RequestMapping(value = "{id}/change", method = RequestMethod.PUT)
    public String change(@PathVariable Long id) {
        todoService.change(id);
        return "redirect:/";
    }


    /**
     * searchメソッド
     * 検索画面を表示する
     * @param model Modelクラスのインスタンス
     * @return search.htmlを返す
     */
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(Model model) {
        TodoList todoList = new TodoList();
        model.addAttribute("todoList", todoList);
        return "search";
    }

    /**
     * searchAfterメソッド
     * リストから検索する
     * @param searchword　入力された検索キーワード
     * @param model　　Modelクラスのインスタンス
     * @return search.htmlを返す
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String searchAfter(@RequestParam String searchword, Model model) {

        List<TodoList> todoList = todoService.findAll();

        if(searchword.isEmpty()){
            model.addAttribute("err_msg01", "文字を入力してください");
            return "search";
        }

        if(!todoList.contains(searchword)){
            model.addAttribute("err_msg02","対象のtodoリストはありません");
            return "search";
        }

        List<TodoList> todoLists = todoService.searchInDone(searchword);
        model.addAttribute("todoLists", todoLists).addAttribute("searchword", searchword);
        return "search";
    }


    /**
     * destroyメソッド
     * 指定したtodoリストを削除する
     * @param id
     * @param model Modelクラスのインスタンス
     * @return index.htmlにリダイレクトする
     */
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String destroy(@PathVariable Long id, Model model) {
        //該当リストの削除
        todoService.delete(id);
        //更新されたリスト一覧を取得
        List<TodoList> todoLists = todoService.findAll();
        //リスト一覧を表示
        model.addAttribute("todoLists",todoLists);
        return "redirect:/";
    }

    /**
     * allDestroyメソッド
     * 全てのtodoリストを削除する
     * @return index.htmlにリダイレクトする
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String allDestroy() {
        todoService.allDelete();
        return "redirect:/";
    }


}
