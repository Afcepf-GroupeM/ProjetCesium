DROP DATABASE IF EXISTS bankdb;
CREATE DATABASE bankdb;

USE bankdb;

DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS operation;


CREATE TABLE customer(
	id INT UNSIGNED AUTO_INCREMENT NOT NULL,
	civilite ENUM('Mr','Mme'),
	lastname VARCHAR(250) NOT NULL,
	firstname VARCHAR(250) NOT NULL,
	birthday DATE NOT NULL,
	email VARCHAR(64) NOT NULL,
	telephone VARCHAR(50),	
	address VARCHAR(250) NOT NULL,
	city VARCHAR(40) NOT NULL,
	country VARCHAR(40) NOT NULL,
	zipcode VARCHAR(10) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE account(
	id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
	description VARCHAR(500),
	balance BIGDECIMAL NOT NULL,
	typeCarte ENUM('CarteBleue','MasterCard','Visa','AmericanExpress'),	
	numberCard VARCHAR(50) NOT NULL,
	crytogram VARCHAR(10) NOT NULL,
	MONTANT_OPDEBIT BIGDECIMAL NOT NULL,
	dateExpiredCarte Date NOT NULL,
	dateCreationAccount Date NOT NULL,	
	PRIMARY KEY (id),
	FOREIGN KEY (customerid) REFERENCES customer(id)
);

CREATE TABLE operation(
	id TINYINT UNSIGNED AUTO_INCREMENT NOT NULL,
	label VARCHAR(100) NOT NULL,
	amount BIGDECIMAL NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (accountid) REFERENCES account(id)
);
