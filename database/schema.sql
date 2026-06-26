CREATE DATABASE IF NOT EXISTS game_project;

USE game_project;

CREATE TABLE players (
                         id INT NOT NULL AUTO_INCREMENT,
                         username VARCHAR(50) NOT NULL,
                         password VARCHAR(100) NOT NULL,
                         wins INT DEFAULT 0,
                         losses INT DEFAULT 0,
                         draws INT DEFAULT 0,
                         score INT DEFAULT 0,
                         PRIMARY KEY (id),
                         UNIQUE KEY username (username)
);
