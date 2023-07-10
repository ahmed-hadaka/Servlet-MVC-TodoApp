DROP DATABASE IF EXISTS todos;

CREATE DATABASE IF NOT EXISTS todos;

USE todos;

CREATE TABLE users (
user_name VARCHAR(50) NOT NULL PRIMARY KEY,
first_name VARCHAR(50),
last_name VARCHAR(50) ,
password VARCHAR(50)
);

CREATE TABLE todo (
id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(50),
description VARCHAR(300),
status BOOLEAN,
target_date VARCHAR(20),
user_name VARCHAR(50),
FOREIGN KEY(user_name) REFERENCES users (user_name)
);

INSERT INTO users VALUES('ahmed_hadaka', 'ahmed', 'hadaka', '123#Ahmed');
INSERT INTO users VALUES('mohamed', 'ahmed', 'hadaka', '123#Ahmed');
INSERT INTO todo (title, description, status, target_date, user_name) VALUES(
'do shopping', 'get some vegetables, apples, tomatoes', true, '2023-07-04', 'ahmed_hadaka');
INSERT INTO todo (title, description, status, target_date, user_name) VALUES(
't1', ' ' , true, '2023-07-04', 'ahmed_hadaka');
INSERT INTO todo (title, description, status, target_date, user_name) VALUES(
't2', ' ' , true, '2023-04-04', 'mohamed');


SELECT * FROM users;
SELECT * FROM todo;
