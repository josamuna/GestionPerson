CREATE DATABASE IF NOT EXISTS gestion_person;
USE gestion_person;
CREATE TABLE person(
	id INT AUTO_INCREMENT,
	firstname VARCHAR(50) NOT NULL,
	lastname VARCHAR(50) NOT NULL,
	age INT,
	email VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	CONSTRAINT pk_person PRIMARY KEY(id)
);