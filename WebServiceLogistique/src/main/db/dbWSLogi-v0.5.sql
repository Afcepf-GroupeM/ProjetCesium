CREATE DATABASE  IF NOT EXISTS `wslogistique` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `wslogistique`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: wslogistique
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
-- Table structure for table `adresse`
--

DROP TABLE IF EXISTS `adresse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adresse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lastname` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `complement` varchar(45) DEFAULT NULL,
  `typevoie` varchar(45) DEFAULT NULL,
  `nomvoie` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adresse`
--

LOCK TABLES `adresse` WRITE;
/*!40000 ALTER TABLE `adresse` DISABLE KEYS */;
INSERT INTO `adresse` VALUES (1,'Laporte','Bernard',42,'','rue','des papillons','Draveil','91210','France');
/*!40000 ALTER TABLE `adresse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transporteur`
--

DROP TABLE IF EXISTS `transporteur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transporteur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `trackingPrefix` varchar(255) DEFAULT NULL,
  `delailivraisongaranti` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transporteur`
--

LOCK TABLES `transporteur` WRITE;
/*!40000 ALTER TABLE `transporteur` DISABLE KEYS */;
INSERT INTO `transporteur` VALUES (1,'UPS','http://www.ups.com','UPS',3),(2,'La Poste','http://www.laposte.fr','POST',5),(3,'TNT','http://www.chronopost.com','CHRONO',2);
/*!40000 ALTER TABLE `transporteur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livraison`
--

DROP TABLE IF EXISTS `livraison`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `livraison` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trackingcode` varchar(255) NOT NULL,
  `nbitems` int(11) DEFAULT NULL,
  `datedemande` date DEFAULT NULL,
  `datepriseencharge` date DEFAULT NULL,
  `datelivraison` date DEFAULT NULL,
  `idadresse` int(11) DEFAULT NULL,
  `idtransporteur` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_adr` (`idadresse`),
  KEY `id_tra` (`idtransporteur`),
  CONSTRAINT `idadresse` FOREIGN KEY (`idadresse`) REFERENCES `adresse` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idtransporteur` FOREIGN KEY (`idtransporteur`) REFERENCES `transporteur` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livraison`
--

LOCK TABLES `livraison` WRITE;
/*!40000 ALTER TABLE `livraison` DISABLE KEYS */;
INSERT INTO `livraison` VALUES (3,'UPS-FR91-004CXF-109',50,'2017-01-25','2017-01-25','2017-01-30',1,1);
/*!40000 ALTER TABLE `livraison` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statut`
--

DROP TABLE IF EXISTS `statut`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statut` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idlivraison` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_liv` (`idlivraison`),
  CONSTRAINT `idlivraison` FOREIGN KEY (`idlivraison`) REFERENCES `livraison` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statut`
--

LOCK TABLES `statut` WRITE;
/*!40000 ALTER TABLE `statut` DISABLE KEYS */;
INSERT INTO `statut` VALUES (3,3);
/*!40000 ALTER TABLE `statut` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statutline`
--

DROP TABLE IF EXISTS `statutline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statutline` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dateupdate` date DEFAULT NULL,
  `locationupdate` varchar(255) DEFAULT NULL,
  `detailsupdate` varchar(255) DEFAULT NULL,
  `idstatut` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_sta` (`idstatut`),
  CONSTRAINT `idstatut` FOREIGN KEY (`idstatut`) REFERENCES `statut` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statutline`
--

LOCK TABLES `statutline` WRITE;
/*!40000 ALTER TABLE `statutline` DISABLE KEYS */;
INSERT INTO `statutline` VALUES (3,'2017-01-25','PARIS','Demande de prise en charge par le marchand.',3);
/*!40000 ALTER TABLE `statutline` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-25 19:20:26
