use ecommercedb;

INSERT INTO metacategory (name) VALUES ("Trésor");

INSERT INTO category (name,metacategoryid) VALUE ("Affaire",1);

INSERT INTO item (imagepath,reference,name,description,price,stock,categoryid) VALUES ("path/image","MonItem1","Pièces d'or","Une jolie pièce d'or",12.5,1,1);

INSERT INTO user (civilite,lastname,firstname,email,phone,birthdate,hashPassword) VALUES ('Mr','Duck','Picsou','banque@distributeur.fr',"0987654321","1958-03-15","$2a$10$PiRFHk6krX6GUV9wOObx.OI7tPr3zMxeV6WSygCoeOA/d69OsXi7O");
INSERT INTO user (civilite,lastname,firstname,email,phone,birthdate,hashPassword) VALUES ('Mme','Duck','Daisy','daisy@la-tete-sur-les-epaules.fr',"0987654321","1971-05-23","$2a$10$AWVvcjYrr4wJomHr4.mVBuCnPLJCXmjwVKUxLsGUrPH.NeXCplOMC");
INSERT INTO user (civilite,lastname,firstname,email,phone,birthdate,hashPassword) VALUES ('Mr','Duck','Donald','normal@canard.fr',"0987654321","1970-11-09","$2a$10$05qx87KPj2bt.0dUHGFUOO8qBk7wcPenINDA8DGxd8GTFy.zgG.kO");

INSERT INTO address(name,roadNumber,complement,roadType,roadName,city,zipcode,country,isBilling,isValid,userid) VALUES ('Coffre',13,null,'Rue','du fric','PicsouVille','77777','Pays du flouze',0,1,1);
INSERT INTO address(name,roadNumber,complement,roadType,roadName,city,zipcode,country,isBilling,isValid,userid) VALUES ('',5,'bis','Rue','des canards normaux','PicsouVille','77777','Pays du flouze',0,0,2);
INSERT INTO address(name,roadNumber,complement,roadType,roadName,city,zipcode,country,isBilling,isValid,userid) VALUES ('Maison',5,'bis','Rue','des canards normaux','PicsouVille','77777','Pays du flouze',0,0,3);
INSERT INTO address(name,roadNumber,complement,roadType,roadName,city,zipcode,country,isBilling,isValid,userid) VALUES ('Travail',13,'bis','Rue','du fric','PicsouVille','77777','Pays du flouze',0,1,3);
INSERT INTO address(name,roadNumber,complement,roadType,roadName,city,zipcode,country,isBilling,isValid,userid) VALUES ('Maison',8,null,'Chemin','des canetons','PicsouVille','77777','Pays du flouze',1,1,3);
INSERT INTO address(name,roadNumber,complement,roadType,roadName,city,zipcode,country,isBilling,isValid,userid) VALUES ('',8,null,'Chemin','des canetons','PicsouVille','77777','Pays du flouze',1,1,2);