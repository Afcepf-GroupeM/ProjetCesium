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

CREATE TABLE `bankdb`.`account` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(500) NOT NULL,
  `balance` DECIMAL NOT NULL,
  `typeCarte` ENUM('CarteBleue','MasterCard','Visa','AmericanExpress') NOT NULL,
  `numberCard` VARCHAR(50) NOT NULL,
  `crytogram` VARCHAR(10) NOT NULL,
  `MONTANT_OPDEBIT` DECIMAL NOT NULL,
  `dateExpiredCarte` DATE NOT NULL,
  `dateCreationAccount` DATE NOT NULL,
  `customerid` INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_account_1` FOREIGN KEY `FK_account_1` (`customerid`)
    REFERENCES `customer` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)
ENGINE = InnoDB;


CREATE TABLE `bankdb`.`operation` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `label` VARCHAR(100) NOT NULL,
  `amount` DECIMAL NOT NULL,
  `accountid` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_operation_1` FOREIGN KEY `FK_operation_1` (`accountid`)
    REFERENCES `account` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)
ENGINE = InnoDB;
