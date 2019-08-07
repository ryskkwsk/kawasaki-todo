#Todoアプリケーション

###使用技術
-Spring Boot2.1.7
-Java8
-BootStrap3.3.7
-MySQL5.7

###全体の設計・構成についての説明

####ルーティング
|HTTPメソッド|URL|Conrtollerメソッド|概要|
|:--|:--|:--|:--|
|GET|/|top|一覧画面の表示|
|POST|/|create|データの保存|
|GET|{id}/edit|編集画面の表示|
|PUT|/{id}/update|データの更新|
|PUT|{id}/change|change|データの状態変更|
|GET|search|search|検索画面の表示|
|POST|search|searchAfter|データ検索機能|
|POST|/{id}/delete|destroy|データの削除機能|
|POST|/delete|allDestroy|データの全て削除機能|

####DB設計
|カラム名|型|null|key|
|:--|:--|:--|:--|
|id|INT|no|primary key|
|name|VARCHAR|no||
|create_date|DATETIME|no||
|dead_line|DATETIME|no||
|done|BIT|yes||

####ディレクトリ構成

src/
   ├main/   
        ├java/
		
		     ├com/
			     ├example/
				         ├kawasakitodo
						             ├controller/
									            ├TodoController	
									 ├entity/
									        ├TodoList
									 ├repository/
									            ├TodoListRepository	
									 ├service/
									         ├TodoService
								     ─ KawasakiTodoApplication	 
        ├resources/
		          ├static/
				         ├bootstrap.js
						 ├jquery.js
				  ├templates/
				            ├layout/
							       ├header.html
						    ─index.html
							─edit.html
							─search.html
build.gradle