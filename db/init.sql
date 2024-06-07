CREATE DATABASE db1;

USE db1;

CREATE TABLE user
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age  INT          NOT NULL
);

INSERT INTO user (name, age)
VALUES ('小一', 25);
INSERT INTO user (name, age)
VALUES ('小二', 30);
INSERT INTO user (name, age)
VALUES ('小三', 35);


CREATE DATABASE db2;

USE db2;

CREATE TABLE user
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age  INT          NOT NULL
);

INSERT INTO user (name, age)
VALUES ('大一', 15);
INSERT INTO user (name, age)
VALUES ('大二', 20);
INSERT INTO user (name, age)
VALUES ('大三', 25);