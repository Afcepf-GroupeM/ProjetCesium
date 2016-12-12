DROP DATABASE IF EXISTS ecommercedb;
CREATE DATABASE ecommercedb;

USE ecommercedb;

DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS adress;
DROP TABLE IF EXISTS metacategory;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS cartline;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS orderline;
DROP TABLE IF EXISTS userorder;
DROP TABLE IF EXISTS coupon;
DROP TABLE IF EXISTS carrier;
DROP TABLE IF EXISTS administrator;

CREATE TABLE user(
	id INT UNSIGNED AUTO_INCREMENT NOT NULL,
	civilite ENUM('Mr','Mme'),
	lastname VARCHAR(40) NOT NULL,
	firstname VARCHAR(40) NOT NULL,
	birthdate DATE NOT NULL,
	email VARCHAR(64) NOT NULL,
	phone VARCHAR(10),
	hashpassword TEXT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE adress(
	id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
	name VARCHAR(40),
	roadnumber TINYINT UNSIGNED NOT NULL,
	roadtype ENUM('Avenue','Boulevard','Chemin','Impasse','Rue'),
	roadname VARCHAR(255) NOT NULL,
	city VARCHAR(40) NOT NULL,
	zipcode VARCHAR(5) NOT NULL,
	country VARCHAR(40) NOT NULL,
	isbilling BOOLEAN,
	isvalid BOOLEAN,
	userid INT UNSIGNED NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (userid) REFERENCES user(id)
);

CREATE TABLE metacategory(
	id TINYINT UNSIGNED AUTO_INCREMENT NOT NULL,
	name VARCHAR(40) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE category(
	id TINYINT UNSIGNED AUTO_INCREMENT NOT NULL,
	name VARCHAR(40) NOT NULL,
	metacategoryid TINYINT UNSIGNED NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (metacategoryid) REFERENCES metacategory(id)
);

CREATE TABLE item(
	id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
	imagepath VARCHAR(255) NOT NULL,
	reference VARCHAR(40) NOT NULL,
	name VARCHAR(40) NOT NULL,
	description TEXT NOT NULL,
	price FLOAT,
	stock SMALLINT UNSIGNED,
	categoryid TINYINT UNSIGNED NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (categoryid) REFERENCES category(id)
);

CREATE TABLE review(
	id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
	rating FLOAT,
	comment TEXT NOT NULL,
	creationdate DATE,
	userid INT UNSIGNED NOT NULL,
	itemid BIGINT UNSIGNED NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (userid) REFERENCES user(id),
	FOREIGN KEY (itemid) REFERENCES item(id)
);

CREATE TABLE cart(
	id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
	creationdate DATE,
	userid INT UNSIGNED NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE cartline(
	id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
	quantity TINYINT UNSIGNED,
	unitprice FLOAT,
	cartid BIGINT UNSIGNED NOT NULL,
	itemid BIGINT UNSIGNED NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (cartid) REFERENCES cart(id),
	FOREIGN KEY (itemid) REFERENCES item(id)
);

CREATE TABLE carrier(
	id TINYINT UNSIGNED AUTO_INCREMENT NOT NULL,
	name VARCHAR(40) NOT NULL,
	trackingUrl VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE coupon(
	id INT UNSIGNED AUTO_INCREMENT NOT NULL,
	code VARCHAR(20) NOT NULL,
	startdate DATE,
	enddate DATE,
	rebate FLOAT,
	description TEXT(65535) NOT NULL,
	imagepath VARCHAR(255) NOT NULL,
	categoryid TINYINT UNSIGNED NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (categoryid) REFERENCES category(id)
);

CREATE TABLE userorder(
	id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
	creationdate DATE,
	amount FLOAT,
	typepayment ENUM('CarteBleu','MasterCard','Visa','AmericanExpress'),
	orderstate ENUM('En préparation','Commande prête','Acheminement chez le transporteur','En attente de paiement','Expédiée','Livrée'),
	trackingnumber VARCHAR(40),
	userid INT UNSIGNED NOT NULL,
	billingadressid BIGINT UNSIGNED NOT NULL,
	shippingadressid BIGINT UNSIGNED NOT NULL,
	carrierid TINYINT UNSIGNED NOT NULL,
	couponid INT UNSIGNED NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (userid) REFERENCES user(id),
	FOREIGN KEY (billingadressid) REFERENCES adress(id),
	FOREIGN KEY (shippingadressid) REFERENCES adress(id),
	FOREIGN KEY (carrierid) REFERENCES carrier(id),
	FOREIGN KEY (couponid) REFERENCES coupon(id)
);

CREATE TABLE orderline(
	id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
	unitprice FLOAT,
	quantity TINYINT UNSIGNED NOT NULL,
	userorderid BIGINT UNSIGNED NOT NULL,
	itemid BIGINT UNSIGNED NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (userorderid) REFERENCES userorder(id),
	FOREIGN KEY (itemid) REFERENCES item(id)
);

CREATE TABLE administrator(
	id SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL,
	lastname VARCHAR(40) NOT NULL,
	firstname VARCHAR(40) NOT NULL,
	creationdate DATE,
	phone VARCHAR(10) NOT NULL,
	email VARCHAR(64) NOT NULL,
	hashpassword VARCHAR(64),
	PRIMARY KEY (id)
);