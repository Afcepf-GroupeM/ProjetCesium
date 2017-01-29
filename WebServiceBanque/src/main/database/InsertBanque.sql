INSERT INTO customer (id,civilite,lastname,firstname,birthday,email,telephone,address,city,country,zipcode) VALUES (1,'Mr','Lagaffe','Gaston','1956-10-02','toto@franquin.com','0163458950','40 rue des champs','paris','france','75000');

INSERT INTO account (id,description,balance,typeCarte,numberCard,cryptogram,MONTANT_OPDEBIT,dateExpiredCarte,dateCreationAccount,customerid) VALUES (1,'compte ou il y a de argent','10000','CarteBleue','123456789','123','7','2017-12-01','2014-10-01',1);

INSERT INTO operation (id,label,amount,dateOp,accountid) VALUES (1,'achat d un ordinateur','500','2016-11-11 12:55:55',1);

