-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: beaphilosopher
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `article_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `branch` varchar(100) DEFAULT NULL,
  `appearance` text,
  `likes` int DEFAULT '0',
  `dislikes` int DEFAULT '0',
  `author_id` int NOT NULL,
  PRIMARY KEY (`article_id`),
  KEY `author_id` (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (2,'Test','Test','Test',0,0,9),(3,'Modern Society\'s propaganda (Democracy)','Poli','',0,0,9),(4,'Modern Society\'s propaganda (Democracy)','Political Philosophy','OOh modern society. You guys always think you are the best yet you are soo heavily attached to materialism. Attached to technology and how do you make all those things? Just by money and how to get as much money as possible? By showing the lower authority that in order to be succes, you must follow traditional school system, produce rather than using your own creativity. How dissappointing!\r\n\r\nAfter all we aren\'t to do your dirty work! We are born because our parents love us and want us to see us grow. Unfortunately they are also manipulated by your powers.',0,0,9),(5,'Test Title','Science','Some article text',10,2,1);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `quote_id` int NOT NULL,
  `author_id` int NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `quote_id` (`quote_id`),
  KEY `author_id` (`author_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`quote_id`) REFERENCES `quote` (`quote_id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`author_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `philosopher`
--

DROP TABLE IF EXISTS `philosopher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `philosopher` (
  `philosopher_id` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(100) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  `yearOfBirth` int DEFAULT NULL,
  `yearOfDeath` int DEFAULT NULL,
  `bio` text,
  PRIMARY KEY (`philosopher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `philosopher`
--

LOCK TABLES `philosopher` WRITE;
/*!40000 ALTER TABLE `philosopher` DISABLE KEYS */;
INSERT INTO `philosopher` VALUES (1,'Friedrich','Nietzsche',1844,1900,'Germany'),(2,'Bruce','Lee',1940,1973,'United States'),(3,'David ','Hume',1711,1776,'United Kingdom');
/*!40000 ALTER TABLE `philosopher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quote`
--

DROP TABLE IF EXISTS `quote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quote` (
  `quote_id` int NOT NULL AUTO_INCREMENT,
  `text` text NOT NULL,
  `likes` int DEFAULT '0',
  `dislikes` int DEFAULT '0',
  `author_id` int NOT NULL,
  PRIMARY KEY (`quote_id`),
  KEY `author_id` (`author_id`),
  CONSTRAINT `quote_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quote`
--

LOCK TABLES `quote` WRITE;
/*!40000 ALTER TABLE `quote` DISABLE KEYS */;
INSERT INTO `quote` VALUES (1,'\"The beauty of life is becoming something that modern society don\'t want you to be.\"',0,0,9),(2,'\"The modern society is really powerful authority, maybe too powerful. Yet it has a weakness which is \'blindness\'! Which also prevents you become addicted to their products!\"',0,0,10),(3,'\"In order to find true unique passion, you must realized your \'self awareness\', only then you are able to find what you are truly passionate about!\"',0,0,9),(4,'\"If someone is skibidi, flush him like a toilet!\"',0,0,9),(5,'\"God is Death\"',0,0,1),(6,'\"It\'s me, Mario!!!\"',0,0,9);
/*!40000 ALTER TABLE `quote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `report_id` int NOT NULL AUTO_INCREMENT,
  `description` text,
  `reported_user_id` int NOT NULL,
  `reported_by_id` int NOT NULL,
  PRIMARY KEY (`report_id`),
  KEY `reported_user_id` (`reported_user_id`),
  KEY `reported_by_id` (`reported_by_id`),
  CONSTRAINT `report_ibfk_1` FOREIGN KEY (`reported_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (2,'I like him!',10,0),(3,'He is doing drugs',7,0),(4,'I really like him!',10,9),(5,'Test',8,9);
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(100) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `telephone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Danny','','','','$2a$10$LzpPMs9BLjqmgAuCpBVD7OlMjwDvEbw0Y4Z.1vnGb2OBvYxIsOyoW',''),(3,'Danny','Nguyen','DannyNguyen2004','','$2a$10$CydkAuE7REWYfYFAnn1VUe1yKlrBfmkTndz.6Wx0D7jjb9.NMEwfm',''),(7,'Toad','Paddo','PaddoUser1234','paddoisthebest@gmail.com','$2a$10$ZNNFErMr99w05QQ8W9eLxerQlKeInf3D1Zb1up/SD7jgXvJuhb22y','113'),(8,'Test','Test','Test','test@gmail.com','$2a$10$kknNdATawCFJpvlUW8MOEeeXg.iaPeY7C/Kl.2IgCHt.PeiP1TKqq','112'),(9,'Hanni','Pham','HanniPham','hannipham@gmail.com','$2a$10$i1RXzTKTFLiSv33bnj3Z4O5ci4siowQ3ubtZRFn0NqwmxpDDW1X.K','112'),(10,'Danny','Nguyen','DannyNguyen','dannyduyanhnguyen@gmail.com','$2a$10$5yLC.Wa2wm/y6f4PY8lIWu1VR1ZpUyskXvu5m6uFq49ig6XVqQhGC','112');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-23 16:53:28
