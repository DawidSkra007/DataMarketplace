-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: tldrdb
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `timestamp_ac` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (0,'admin','admin@tldr','admin',NULL),(19,'alice','alice@test','123',NULL),(20,'bob','bob@test','123',NULL),(21,'clive','clive@test','123',NULL),(22,'viktor','viktor@test','234',NULL),(23,'jack','jack@test','654',NULL),(26,'poppy','poppy@test','Password123','2022-03-19 19:02:23');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dataset`
--

DROP TABLE IF EXISTS `dataset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dataset` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(140) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `tot_available` int DEFAULT NULL,
  `min_quantity` int DEFAULT NULL,
  `max_quantity` int DEFAULT NULL,
  `timestamp_ds` datetime DEFAULT NULL,
  `hidden` tinyint DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dataset`
--

LOCK TABLES `dataset` WRITE;
/*!40000 ALTER TABLE `dataset` DISABLE KEYS */;
INSERT INTO `dataset` VALUES (8,'Sports','Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.',1,10000,10,10000,'2022-03-19 18:03:18',0),(9,'Movies','Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.',2,5000,10,3000,'2022-03-19 18:04:06',0),(10,'Food','Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.',3,3000,10,3000,'2022-03-19 18:04:35',0),(11,'Furniture','Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.',2,30000,10,15000,'2022-03-19 18:04:57',0),(12,'Clothes','Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.',2,2000,5,2000,'2022-03-19 18:16:57',0),(13,'Cars','Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.',4,1000,5,500,'2022-03-19 18:17:49',0),(14,'Houses','Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.',6,1000,5,700,'2022-03-19 18:18:18',0),(15,'Phones','Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.',2,500,30,500,'2022-03-19 18:18:44',0),(16,'Games','Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.',1,350,25,350,'2022-03-19 18:19:15',0),(17,'Plants','Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.',1,6000,300,5000,'2022-03-19 18:19:51',0),(18,'Animals','Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.',2,7000,500,7000,'2022-03-19 18:30:24',0),(19,'Boats','Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.',2,700,5,700,'2022-03-19 18:30:59',0),(20,'Crime','Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.',1,500,5,500,'2022-03-19 18:31:52',0),(21,'TV Shows','Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.',3,600,50,600,'2022-03-19 18:32:35',0),(22,'Bikes','Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.',1,260,20,260,'2022-03-19 18:38:30',0),(23,'Universities','Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.',2,3000,100,3000,'2022-03-19 18:39:08',0),(25,'Planes','Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.',3,2000,100,2000,'2022-03-19 18:42:30',0),(26,'Languages','Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.',2,300,12,300,'2022-03-19 18:44:13',0),(27,'Countries','Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.',2,195,10,195,'2022-03-19 18:44:51',0),(28,'Olympics','Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.',3,600,10,600,'2022-03-19 18:47:38',0);
/*!40000 ALTER TABLE `dataset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase` (
  `cartID` int NOT NULL,
  `accID` int DEFAULT NULL,
  `timestamp_purchase` datetime DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `datasetID` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `approved` tinyint DEFAULT '0',
  KEY `ID_idx` (`accID`),
  KEY `ID_idx1` (`datasetID`),
  CONSTRAINT `accountID` FOREIGN KEY (`accID`) REFERENCES `account` (`ID`),
  CONSTRAINT `datasetID` FOREIGN KEY (`datasetID`) REFERENCES `dataset` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (0,26,'2022-03-19 19:13:27',10,8,10,1),(1,20,'2022-03-19 19:22:39',250,16,250,1),(1,20,'2022-03-19 19:22:39',400,20,400,1),(1,20,'2022-03-19 19:22:39',20,11,40,1),(2,20,'2022-03-19 19:23:26',100,23,200,1),(2,20,'2022-03-19 19:23:26',48,27,96,1),(2,20,'2022-03-19 19:23:26',110,22,110,1),(3,21,'2022-03-19 19:24:42',72,21,216,1),(3,21,'2022-03-19 19:24:42',45,13,180,1),(3,21,'2022-03-19 19:24:42',62,27,124,1),(4,21,'2022-03-19 19:25:10',3000,17,3000,0),(5,22,'2022-03-19 19:25:58',195,27,390,0),(5,22,'2022-03-19 19:25:58',700,19,1400,0),(6,22,'2022-03-19 19:26:31',7000,18,14000,0),(7,23,'2022-03-19 19:28:10',3000,23,6000,0),(7,23,'2022-03-19 19:28:10',350,16,350,0),(7,23,'2022-03-19 19:28:10',10000,8,10000,0),(7,23,'2022-03-19 19:28:10',195,27,390,0),(8,23,'2022-03-19 19:28:54',100,23,200,0),(8,23,'2022-03-19 19:28:54',1500,12,3000,0),(9,19,'2022-03-19 19:30:11',100,11,200,0),(10,19,'2022-03-19 19:36:13',400,25,1200,0),(10,19,'2022-03-19 19:36:13',260,22,260,0),(10,19,'2022-03-19 19:36:13',500,28,1500,0);
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-19 22:44:54
