DROP DATABASE IF EXISTS ecommercedb;
CREATE DATABASE ecommercedb;

USE ecommercedb;

DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS address;
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
	hashpassword VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE address(
	id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
	name VARCHAR(40),
	roadnumber TINYINT UNSIGNED NOT NULL,
	complement ENUM('bis','a','b'),
	roadtype ENUM('Avenue','Boulevard','Chemin','Impasse','Rue','Voie','Place','Allee'),
	roadname VARCHAR(255) NOT NULL,
	city VARCHAR(40) NOT NULL,
	zipcode VARCHAR(5) NOT NULL,
	country VARCHAR(40) NOT NULL,
	isbilling BOOLEAN NOT NULL,
	isvalid BOOLEAN NOT NULL,
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
	price FLOAT NOT NULL,
	stock SMALLINT UNSIGNED NOT NULL,
	categoryid TINYINT UNSIGNED NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (categoryid) REFERENCES category(id)
);

CREATE TABLE review(
	id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
	rating FLOAT NOT NULL,
	comment TEXT NOT NULL,
	creationdate DATE NOT NULL,
	userid INT UNSIGNED NOT NULL,
	itemid BIGINT UNSIGNED NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (userid) REFERENCES user(id),
	FOREIGN KEY (itemid) REFERENCES item(id)
);

CREATE TABLE cart(
	id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
	creationdate DATE NOT NULL,
	userid INT UNSIGNED,
	sessionid VARCHAR(255),
	PRIMARY KEY (id)
);

CREATE TABLE cartline(
	id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
	quantity TINYINT UNSIGNED NOT NULL,
	unitprice FLOAT NOT NULL,
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
	startdate DATE NOT NULL,
	enddate DATE NOT NULL,
	rebate FLOAT NOT NULL,
	description TEXT NOT NULL,
	imagepath VARCHAR(255) NOT NULL,
	categoryid TINYINT UNSIGNED NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (categoryid) REFERENCES category(id)
);

CREATE TABLE userorder(
	id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
	creationdate DATE NOT NULL,
	amount FLOAT NOT NULL,
	typepayment ENUM('CarteBleue','MasterCard','Visa','AmericanExpress'),
	orderstate ENUM('EnPreparation','Expediee','Livree','EnAttenteDePaiement','RetourClient','RemboursementClient','EchangeProduit'),
	trackingnumber VARCHAR(40),
	userid INT UNSIGNED NOT NULL,
	billingaddressid BIGINT UNSIGNED NOT NULL,
	shippingaddressid BIGINT UNSIGNED NOT NULL,
	carrierid TINYINT UNSIGNED NOT NULL,
	couponid INT UNSIGNED,
	PRIMARY KEY (id),
	FOREIGN KEY (userid) REFERENCES user(id),
	FOREIGN KEY (billingaddressid) REFERENCES address(id),
	FOREIGN KEY (shippingaddressid) REFERENCES address(id),
	FOREIGN KEY (carrierid) REFERENCES carrier(id),
	FOREIGN KEY (couponid) REFERENCES coupon(id)
);

CREATE TABLE orderline(
	id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
	unitprice FLOAT NOT NULL,
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
	creationdate DATE NOT NULL,
	phone VARCHAR(10) NOT NULL,
	email VARCHAR(64) NOT NULL,
	hashpassword VARCHAR(64) NOT NULL,
	PRIMARY KEY (id)
);