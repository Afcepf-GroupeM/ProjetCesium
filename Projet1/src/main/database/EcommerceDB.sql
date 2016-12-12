DROP DATABASE ecommercedb;
CREATE DATABASE ecommercedb;

DROP TABLE user;
DROP TABLE adress;
DROP TABLE metacategory;
DROP TABLE category;
DROP TABLE review;
DROP TABLE item;
DROP TABLE cartline;
DROP TABLE cart;
DROP TABLE orderline;
DROP TABLE order;
DROP TABLE coupon;
DROP TABLE carrier;
DROP TABLE administrator;

CREATE TABLE user(
	id INT UNSIGNED,
	civility ENUM('Mr','Mme'),
	lastname VARCHAR2(40) NOT NULL,
	firstname VARCHAR2(40) NOT NULL,
	birthdate DATE NOT NULL,
	email VARCHAR2(64) NOT NULL,
	phone VARCHAR2(10),
	hashpassword VARCHAR2(65535) NOT NULL,	#modifier comme il convient
	PRIMARY KEY(id) AUTO_INCREMENT NOT NULL
);

CREATE TABLE adress(
	id BIGINT UNSIGNED,
	name VARCHAR2(40),
	roadnumber TINYINT UNSIGNED NOT NULL,
	roadtype ENUM('Avenue','Boulevard','Chemin','Impasse','Rue'),	#A compléter si on voit autre chose
	roadname VARCHAR2(255) NOT NULL,
	city VARCHAR2(40) NOT NULL,
	zipcode VARCHAR2(5) NOT NULL,
	country VARCHAR2(40) NOT NULL,
	isbilling BOOLEAN,
	isvalid BOOLEAN,
	userid INT UNSIGNED NOT NULL,
	PRIMARY KEY(id) AUTO_INCREMENT NOT NULL,
	FOREIGN KEY (userid) REFERENCES user(id)
);

CREATE TABLE metacategory(
	id TINYINT UNSIGNED,
	name VARCHAR(40) NOT NULL,
	PRIMARY KEY(id) AUTO_INCREMENT NOT NULL
);

CREATE TABLE category(
	id TINYINT UNSIGNED,
	name VARCHAR2(40) NOT NULL,
	metacategoryid TINYINT UNSIGNED NOT NULL,
	PRIMARY KEY(id) AUTO_INCREMENT NOT NULL,
	FOREIGN KEY (metacategoryid) REFERENCES metacategory(id)
);

CREATE TABLE review(
	id BIGINT UNSIGNED,
	rating FLOAT,
	comment VARCHAR2(65535) NOT NULL,
	creationdate DATE,
	userid INT UNSIGNED NOT NULL,
	itemid BIGINT UNSIGNED NOT NULL,
	PRIMARY KEY(id) AUTO_INCREMENT NOT NULL,
	FOREIGN KEY (userid) REFERENCES user(id),
	FOREIGN KEY (itemid) REFERENCES item(id)
);

CREATE TABLE item(
	id BIGINT UNSIGNED,
	imagepath VARCHAR2(255) NOT NULL,
	reference VARCHAR2(40) NOT NULL,
	name VARCHAR2(40) NOT NULL,
	description VARCHAR2(65535) NOT NULL,
	price FLOAT,
	stock SMALLINT UNSIGNED,
	categoryid TINYINT UNSIGNED NOT NULL,
	PRIMARY KEY(id) AUTO_INCREMENT NOT NULL,
	FOREIGN KEY (categoryid) REFERENCES category(id),
);

CREATE TABLE cartline(
	id BIGINT UNSIGNED,
	quantity TINYINT UNSIGNED,
	unitprice FLOAT,
	cartid BIGINT UNSIGNED NOT NULL,
	itemid BIGINT UNSIGNED NOT NULL,
	PRIMARY KEY(id) AUTO_INCREMENT NOT NULL,
	FOREIGN KEY (cartid) REFERENCES cart(id),
	FOREIGN KEY (itemid) REFERENCES item(id)
);

CREATE TABLE cart(
	id BIGINT UNSIGNED,
	creationdate DATE,
	userid INT UNSIGNED NOT NULL,
	PRIMARY KEY(id) AUTO_INCREMENT NOT NULL,
);

CREATE TABLE orderline(
	id BIGINT UNSIGNED,
	unitprice FLOAT,
	quantity TINYINT UNSIGNED,
	orderid BIGINT UNSIGNED NOT NULL,
	itemid BIGINT UNSIGNED NOT NULL,
	PRIMARY KEY(id) AUTO_INCREMENT NOT NULL,
	FOREIGN KEY (orderid) REFERENCES order(id),
	FOREIGN KEY (itemid) REFERENCES item(id)
);

CREATE TABLE order(
	id BIGINT UNSIGNED,
	creationdate DATE,
	amount FLOAT,
	typepayment ENUM('CarteBleu','MasterCard','Visa','AmericanExpress'),	#A compléter si on voit autre chose
	orderstate ENUM('En préparation','Commande prête','Acheminement chez le transporteur','En attente de paiement','Expédiée','Livrée'),	#A compléter si on voit autre chose
	trackingnumber VARCHAR(40),
	userid INT UNSIGNED NOT NULL,
	billingadressid BIGINT UNSIGNED NOT NULL,
	shippingadressid BIGINT UNSIGNED NOT NULL,
	carrierid TINYINT UNSIGNED NOT NULL,
	couponid INT UNSIGNED,
	PRIMARY KEY(id) AUTO_INCREMENT NOT NULL,
	FOREIGN KEY (userid) REFERENCES user(id),
	FOREIGN KEY (billingadressid) REFERENCES adress(id)
	FOREIGN KEY (shippingadressid) REFERENCES adress(id)
	FOREIGN KEY (carrierid) REFERENCES carrier(id)
	FOREIGN KEY (couponid) REFERENCES coupon(id)
);

CREATE TABLE coupon(
	id INT UNSIGNED,
	code VARCHAR2(20) NOT NULL,
	startdate DATE,
	enddate DATE,
	rebate FLOAT,
	description VARCHAR2(65535) NOT NULL,
	imagepath VARCHAR(255) NOT NULL,
	categoryid TINYINT UNSIGNED NOT NULL,
	PRIMARY KEY(id) AUTO_INCREMENT NOT NULL,
	FOREIGN KEY (categoryid) REFERENCES category(id)
);

CREATE TABLE carrier(
	id TINYINT UNSIGNED,
	name VARCHAR2(40) NOT NULL,
	trackingUrl VARCHAR2(255) NOT NULL,
	PRIMARY KEY(id) AUTO_INCREMENT NOT NULL
);

CREATE TABLE administrator(
	id SMALLINT UNSIGNED,
	lastname VARCHAR2(40) NOT NULL,
	firstname VARCHAR2(40) NOT NULL,
	creationdate DATE,
	phone VARCHAR2(10) NOT NULL,
	email VARCHAR2(64) NOT NULL,
	hashpassword VARCHAR2(64),	#modifier comme il convient
	PRIMARY KEY(id) AUTO_INCREMENT NOT NULL
);