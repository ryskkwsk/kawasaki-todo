package com.example.kawasakitodo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todo_list")
public class TodoList {

    /**
     * プライマリーキー
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    /**
     * todoリスト名
     */
    @NotEmpty(message = "Todo名を入力してください")
    @Length(max = 30, message = "ToDo名は{max}文字以内にしてください。")
    @Column(length = 30, nullable = false)
    private String name;


    /**
     * todoリストの作成日
     */
    @Column
    private LocalDateTime createDate;

    /**
     * todoリストの期限
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "期限を入力してください")
    @Column
    private LocalDate deadLine;

    /**
     * todoリストの状態(未完 or 完了)
     */
    @Column
    private boolean done;

    @PrePersist
    public void setCreateDate() {
        createDate = LocalDateTime.now();
    }

    public boolean isDone() {
        return done;
    }

    public void changeDone() {
        done = !done;
    }

}
