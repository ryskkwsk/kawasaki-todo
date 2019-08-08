#Todoアプリケーション

###使用技術
-Spring Boot2.1.7

-Java8

-BootStrap3.3.7

-MySQL5.7

###全体の設計・構成についての説明

####ルーティング

 | HTTPメソッド | URL | Conrtollerメソッド | 概要 |
 |:-----------|:------------|:------------|:--- |
 | GET       |        / |     top     | 一覧画面の表示 |
 | POST    |      / |    create    | データの保存 |
 | GET       |        {id}/edit |     edit     | 編集画面の表示 |
 | PUT         |   {id}/update |      update      | データの更新 |
 | PUT       |       {id}/modify |    modify    | データの状態変更 |
 | GET    |     search |   search    | 検索画面の表示 |
 | POST | search | searchResult | データの検索機能　|
 | POST | /{id}/delete | delete | データの削除機能 |
 | POST | /delete | deleteAll | データの全て削除機能 |


####DB設計

 | カラム名 | 型 | null | key |
 |:-----------|:------------|:------------|:--- |
 | id       |INT|     no     | primary key |
 | name    |VARCHAR|    no    |  |
 | create_date       |DATETIME|     no     |  |
 | dead_line         |   DATE |      no      |  |
 | done       |       boolean |    yes    |  |


####ディレクトリ構成

    ├src/
        ├main/   			     
             ├java/
		          ├com/
			          ├example/
				              ├kawasakitodo
					        	           ├controller/
									                  ├TodoController	
									       ├entity/
									              ├TodoList
									       ├exception/
									                 ├TodoExceptionHandler
									                 ├TodoNotFoundException
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
				                 ├error/
				                       ├404.html
				                       ├500.html
				                 ├layout/
							            ├header.html
						         ─index.html
							     ─edit.html
							     ─search.html
    build.gradle
    
####開発環境のセットアップ
・Java8 インストール
```
$ brew cask install java8 
$ export JAVA_HOME=`/usr/libexec/java_home -v 1.8`
```

・MySQLインストール
```
$ brew install mysql@5.7
```
・MySQL起動
```
$ mysql.server start
```
・DBの作成
```
 $ mysql -u root #MySQLにログイン
 mysql> CREATE DATABASE kawasaki_todo; #データベース作成
```
・application.propertiesの作成
```
spring.datasource.url=jdbc:mysql://localhost:3306/kawasaki_todo
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.jpa.database=MYSQL
spring.jpa.hibernate.ddl-auto=update
```
・アプリ起動
```
./gradlew bootRun
```
・ブラウザで起動
```
http://localhost:8080
```
