-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommercedb
-- ------------------------------------------------------
-- Server version	5.1.31-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `adress`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'AFCEPF','6','a','Rue','Danton','Paris','75000','France','1','1','8'),(2,'AFCEPF1','6','a','Rue','Danton','Paris','75000','France','1','1','9'),(3,'AFCEPF2','6','a','Rue','Danton','Paris','75000','France','1','1','8');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Dumping data for table `carrier`
--

LOCK TABLES `carrier` WRITE;
/*!40000 ALTER TABLE `carrier` DISABLE KEYS */;
INSERT INTO `carrier` VALUES (1,'Post','http://lapost.com'),(2,'Post','http://lapost.com/post123456'),(3,'Post','http://lapost.com/post123456');
/*!40000 ALTER TABLE `carrier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `cartline`
--

LOCK TABLES `cartline` WRITE;
/*!40000 ALTER TABLE `cartline` DISABLE KEYS */;
/*!40000 ALTER TABLE `cartline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Cat 1',1),(2,'Cat 2',1),(3,'Cat 3',2),(4,'Cat4',2),(5,'Cat5',3),(6,'Cat6',3),(7,'Cat7',3),(8,'Cat8',1);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;

/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (7,'/images/items/7','REFProd1','Produit1','Integer gravida lorem id sodales accumsan. Interdum et malesuada fames ac ante ipsum primis in faucibus. Morbi aliquet feugiat interdum. Curabitur arcu augue, aliquam sed lectus ut, hendrerit dignissim tortor.',20,12,1),(8,'/images/items/8','REFProd2','Produit2','Duis porta risus sit amet libero pulvinar, ut tincidunt diam feugiat. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.',39.9,5,1),(9,'/images/items/9','REFProd3','Produit3','Aenean vehicula viverra varius. Curabitur mauris augue, bibendum a cursus nec, sodales non felis. Proin vehicula sem augue, eu rutrum dui maximus non. Phasellus non tempor ipsum, quis vehicula augue.',9.9,4,1),(10,'/images/items/10','REFProd4','Produit4','Aliquam faucibus arcu ut velit interdum, sit amet congue quam sodales. Morbi non risus egestas, pharetra dui in, ullamcorper risus. Aenean congue turpis mattis sapien cursus scelerisque.',109.2,2,2),(11,'/images/items/11','RefProd5','Produit5','Vestibulum fringilla cursus sem eget eleifend. Nam gravida risus et ex mattis, ut egestas lorem congue. Phasellus aliquam varius tellus in finibus.',42.7,50,2),(12,'/images/items/12','RefProduit6','Produit6','Mauris porttitor hendrerit mollis. Pellentesque mauris magna, sollicitudin vitae accumsan quis, varius quis odio. Phasellus ac libero in velit convallis ultricies. In vitae interdum purus. In ac placerat lacus.',12.5,23,1),(13,'/images/items/13','REFProd7','Produit7','Curabitur quis euismod odio, sed commodo nisi. Etiam vel tincidunt augue, non rhoncus augue. ',89.7,5,1),(14,'/images/items/14','REFProd8','Produit8','Cras vel erat eget nisl rutrum euismod. Etiam convallis bibendum efficitur. Nulla facilisi.',23.52,74,2),(15,'/images/items/15','REFProd9','Produit9','Fusce eros mauris, finibus vitae lobortis ut, luctus eu nisi. Ut pellentesque pharetra risus, eget efficitur augue congue eget. Aenean sed porttitor lectus.',14.99,3,3),(16,'/images/items/16','REFProd10','Produit10','Aenean pharetra sapien id risus imperdiet, in consectetur nunc pharetra. Cras sit amet tortor ligula. Morbi vel augue a dolor aliquam tincidunt at vel augue. Curabitur vitae dignissim arcu.',20,65,3),(17,'/images/items/17','REFProd11','Produit11','Phasellus tincidunt ultrices lacus, non varius quam viverra sit amet. Phasellus eros tortor, ultricies at tellus ac, malesuada bibendum lacus.',100,12,4),(18,'/images/items/18','REFProd12','Produit12','Phasellus quam tortor, ultricies a scelerisque non, condimentum vitae nulla. Donec vestibulum dui in condimentum lobortis. ',5.99,10,5),(19,'/images/items/19','REFProd13','Produit13',' Nunc eget nunc eu ligula dignissim facilisis et sit amet arcu. Nunc enim dui, volutpat vitae efficitur vitae, mollis eu lectus. Duis leo eros, tincidunt ac gravida tempus, porttitor ac ante.',45.15,8,5),(20,'/images/items/20','REFProd14','Produit14','Aliquam efficitur, ante nec laoreet dignissim, ante justo sagittis nunc, a tempor augue turpis et nisi.',78.9,15,6);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `metacategory`
--

LOCK TABLES `metacategory` WRITE;
/*!40000 ALTER TABLE `metacategory` DISABLE KEYS */;
INSERT INTO `metacategory` VALUES (1,'MetaCat1'),(2,'MetaCat2'),(3,'MetaCat3'),(4,'MetaCat4'),(5,'MetaCat5');
/*!40000 ALTER TABLE `metacategory` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (8,'Mr','Lagaffe','Gaston','1956-10-02','toto@franquin.com','0163458950','$2a$10$ql6IkE4MblaPsc5FTwzulOyYD49HKv65AIbMrdAru.MBU4uhHx18q'),(9,'Mme','Placard','Monique','1945-10-05','lol@lol.com','0687956498','$2a$10$FNeFz03VbUz.AKn92CJQWeIHBxoT5kxNn5pMGHSTvzGmIwSa3c3FK');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `userorder`
--

LOCK TABLES `userorder` WRITE;
/*!40000 ALTER TABLE `userorder` DISABLE KEYS */;
INSERT INTO `userorder` VALUES (1,'2016-12-14','200.0','CarteBleu','Livree','Post123456','8','1','1','1',null),(2,'2015-12-14','200.0','CarteBleu','Livree','Post123456','8','1','1','1',null);
/*!40000 ALTER TABLE `userorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orderline`
--

LOCK TABLES `orderline` WRITE;
/*!40000 ALTER TABLE `orderline` DISABLE KEYS */;
INSERT INTO `orderline` VALUES (1,'20','5','1','7'),(2,'100','1','1','17'),(3,'20','5','1','7'),(4,'100','1','1','17');
/*!40000 ALTER TABLE `orderline` ENABLE KEYS */;
UNLOCK TABLES;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-14 16:04:59