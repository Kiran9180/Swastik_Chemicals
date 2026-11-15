-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: acid_order_management
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
-- Table structure for table `additional_cost`
--

DROP TABLE IF EXISTS `additional_cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `additional_cost` (
  `additional_cost_id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `cost_name` varchar(100) NOT NULL,
  `amount` decimal(12,2) NOT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`additional_cost_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `additional_cost_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `customer_order` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `additional_cost`
--

LOCK TABLES `additional_cost` WRITE;
/*!40000 ALTER TABLE `additional_cost` DISABLE KEYS */;
INSERT INTO `additional_cost` VALUES (2,1,'Loading Charges',800.00,'Warehouse loading'),(3,1,'Special Handling',500.00,'Extra safety precautions'),(4,5,'Transporation ',5500.00,'Transporation at customer end'),(5,7,'Transporation for Nitric acid',500.00,'nitric acid additional cost transport at customer end'),(7,12,'transposrt',12.00,''),(8,14,'add',100.00,'');
/*!40000 ALTER TABLE `additional_cost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `bill_id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `product_total` decimal(12,2) NOT NULL,
  `additional_total` decimal(12,2) NOT NULL,
  `grand_total` decimal(12,2) NOT NULL,
  `bill_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`bill_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `customer_order` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,1,12700.00,3800.00,16500.00,'2026-11-01 00:25:24'),(2,1,67500.00,3800.00,71300.00,'2026-11-01 19:04:10'),(3,5,21120.00,5500.00,26620.00,'2026-11-01 21:38:16'),(4,6,8540.00,0.00,8540.00,'2026-11-01 21:41:20'),(5,7,40450.00,500.00,40950.00,'2026-11-01 21:49:30'),(6,8,22575.00,100.00,22675.00,'2026-11-01 21:57:36'),(7,17,0.00,0.00,0.00,'2026-11-14 21:07:10'),(8,18,200.00,0.00,200.00,'2026-11-14 21:11:00'),(9,18,200.00,0.00,200.00,'2026-11-14 21:12:11'),(10,6,8540.00,0.00,8540.00,'2026-11-14 21:31:15'),(11,5,21120.00,5500.00,26620.00,'2026-11-14 21:31:40');
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_order`
--

DROP TABLE IF EXISTS `customer_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_order` (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `taluka` varchar(100) DEFAULT NULL,
  `village` varchar(100) DEFAULT NULL,
  `district` varchar(100) DEFAULT NULL,
  `full_address` text,
  `status` varchar(30) NOT NULL DEFAULT 'PENDING_APPROVAL',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` bigint DEFAULT NULL,
  `customer_name` varchar(150) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `shop_name` varchar(200) DEFAULT NULL,
  `pincode` varchar(20) DEFAULT NULL,
  `remarks` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_customer_order_user` (`user_id`),
  CONSTRAINT `fk_customer_order_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_order`
--

LOCK TABLES `customer_order` WRITE;
/*!40000 ALTER TABLE `customer_order` DISABLE KEYS */;
INSERT INTO `customer_order` VALUES (1,'Miraj','Arewadi','Sangli','Near Gram Panchayat Office, Arewadi, Miraj, Sangli','APPROVED','2026-10-31 18:53:28',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'Jath','Umrani','SAngli','umarani 23324','APPROVED','2026-11-01 09:27:10',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'pune','pune','pune','pune','APPROVED','2026-11-01 13:35:50',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'mirj','sngli','sngli','kirans shop','APPROVED','2026-11-01 16:01:29',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'Atapadi','V1','Sangli','New Checms','APPROVED','2026-11-01 16:05:56',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'aa','vv','ss','dd','APPROVED','2026-11-01 16:10:28',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'vita','village1','sangli','vitasjd','APPROVED','2026-11-01 16:17:10',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,'aa','bb','vv','sangli moraj kupwad','APPROVED','2026-11-01 16:25:07',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,'jath','umarni','sngli','kirn shop','APPROVED','2026-11-07 05:43:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,'miraj','savali','sangli','savalsk','REJECTED','2026-11-14 05:33:25',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,'dads','sad','sd','sd','REJECTED','2026-11-14 05:37:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,'s','sf','fs','s','APPROVED','2026-11-14 07:21:58',NULL,'Kiran shinde','3243484','shindekiran9180@gmail.com','ss','s',''),(13,'se','est','etg','es','APPROVED','2026-11-14 07:44:18',3,'Kiran shinde 2','3243484','shindekiran9180@gmail.com2','u','se',''),(14,'miraj','sghjs','ssd','ss','APPROVED','2026-11-14 07:47:27',4,'ramesh','9765213687','rameshshinde@gmail.com','Rmeahs cechs','416410',''),(15,'Miraj','Savali','Sangli','Savali','REJECTED','2026-11-14 13:18:18',6,'Kiran shinde','7499520070','shindekiran9180@gmail.com','kirans shop','465456',''),(16,'we','we','we','we','APPROVED','2026-11-14 14:41:27',1,'Kiran Shinde','7499520070','shindekiran9180@gmail.com','wew','wewe',''),(17,'miraj','kupwad','sangli','swami galli','APPROVED','2026-11-14 14:46:55',7,'akash hire','9823037269','aksghire@gamil.com','Swastik ','1234',''),(18,'Miraj','new','Sangli','Near MIDC Area','APPROVED','2026-11-14 15:40:29',1,'Kiran Shinde','7499520070','shindekiran9180@gmail.com','Shinde Chemicals','416410','');
/*!40000 ALTER TABLE `customer_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_cost_breakup`
--

DROP TABLE IF EXISTS `order_cost_breakup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_cost_breakup` (
  `order_breakup_id` bigint NOT NULL AUTO_INCREMENT,
  `order_item_id` bigint NOT NULL,
  `cost_name` varchar(100) NOT NULL,
  `amount` decimal(12,2) NOT NULL,
  PRIMARY KEY (`order_breakup_id`),
  KEY `order_item_id` (`order_item_id`),
  CONSTRAINT `order_cost_breakup_ibfk_1` FOREIGN KEY (`order_item_id`) REFERENCES `order_item` (`order_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_cost_breakup`
--

LOCK TABLES `order_cost_breakup` WRITE;
/*!40000 ALTER TABLE `order_cost_breakup` DISABLE KEYS */;
INSERT INTO `order_cost_breakup` VALUES (1,1,'Raw Material',3000.00),(2,1,'Manufacturing',800.00),(3,1,'Packing',500.00),(4,1,'Lab Testing',700.00),(5,2,'Raw Material',2000.00),(6,2,'Manufacturing',600.00),(7,2,'Packing',400.00),(8,2,'Lab Testing',500.00),(9,7,'Raw Material',2005.00),(10,7,'Manufacturing',600.00),(11,7,'Packing',400.00),(12,7,'Lab Testing',500.00),(13,7,'Miscellaneous ',15.00),(16,9,'Raw material',500.00),(17,9,'labour',250.00),(19,10,'Raw Material',2005.00),(20,10,'Manufacturing',600.00),(21,10,'Packing',400.00),(22,10,'Lab Testing',500.00),(23,10,'Miscellaneous ',15.00),(26,11,'Raw Material',2005.00),(27,11,'Manufacturing',600.00),(28,11,'Packing',400.00),(29,11,'Lab Testing',500.00),(30,11,'Miscellaneous ',15.00),(33,12,'Raw material',500.00),(34,12,'labour',250.00),(37,14,'Manufacturing',600.00),(38,14,'Packing',400.00),(39,14,'Lab Testing',500.00),(40,14,'Miscellaneous ',15.00),(43,15,'Raw Material',3000.00),(44,15,'Manufacturing',800.00),(45,15,'Packing',500.00),(46,15,'Lab Testing',700.00),(47,25,'Cost',200.00);
/*!40000 ALTER TABLE `order_cost_breakup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `order_item_id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `quantity` decimal(12,2) NOT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `order_id` (`order_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `customer_order` (`order_id`),
  CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (1,1,1,10.00),(2,1,2,5.00),(3,1,3,3.00),(4,2,5,10.00),(5,3,5,10.00),(6,4,5,3.00),(7,5,2,6.00),(8,5,3,5.00),(9,6,3,2.00),(10,6,2,2.00),(11,7,2,10.00),(12,7,3,7.00),(13,8,6,15.00),(14,8,2,5.00),(15,9,1,2.00),(16,10,5,2.00),(17,10,7,12.00),(18,11,6,12.00),(19,12,6,1.00),(20,13,6,1.00),(21,14,5,2.00),(22,15,6,2.00),(23,16,6,2.00),(24,17,6,2.00),(25,18,6,1.00);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` bigint NOT NULL AUTO_INCREMENT,
  `product_name` varchar(200) NOT NULL,
  `final_cost` decimal(12,2) NOT NULL,
  `active` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Sulphuric Acid',5000.00,1,'2026-10-31 18:50:11'),(2,'Hydrochloric Acid',3160.00,1,'2026-10-31 18:50:11'),(3,'Nitric Aci',4200.00,1,'2026-10-31 18:50:11'),(5,'1 ltr HCL Box of 12 Nos',1201.00,1,'2026-11-01 08:36:36'),(6,'Finale 200 ml',150.00,1,'2026-11-01 16:19:52'),(7,'Floor Wash 100ml',100.00,1,'2026-11-01 16:25:56');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_cost_breakup`
--

DROP TABLE IF EXISTS `product_cost_breakup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_cost_breakup` (
  `breakup_id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint NOT NULL,
  `cost_name` varchar(100) NOT NULL,
  `amount` decimal(12,2) NOT NULL,
  PRIMARY KEY (`breakup_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `product_cost_breakup_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_cost_breakup`
--

LOCK TABLES `product_cost_breakup` WRITE;
/*!40000 ALTER TABLE `product_cost_breakup` DISABLE KEYS */;
INSERT INTO `product_cost_breakup` VALUES (1,1,'Raw Material',3000.00),(2,1,'Manufacturing',800.00),(3,1,'Packing',500.00),(4,1,'Lab Testing',700.00),(5,2,'Raw Material',3000.00),(6,2,'Manufacturing',600.00),(7,2,'Packing',400.00),(8,2,'Lab Testing',500.00),(9,2,'Miscellaneous ',15.00),(10,3,'Raw material',500.00),(11,3,'labour',250.00),(12,7,'Raw matrial ',20.00),(13,7,'labour',40.00),(14,7,'transport',20.00),(15,6,'Cost',200.00);
/*!40000 ALTER TABLE `product_cost_breakup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `mobile` varchar(15) NOT NULL,
  `shop_name` varchar(150) DEFAULT NULL,
  `address` text,
  `taluka` varchar(100) DEFAULT NULL,
  `district` varchar(100) DEFAULT NULL,
  `pincode` varchar(10) DEFAULT NULL,
  `access` enum('ADMIN','REGULAR') NOT NULL DEFAULT 'REGULAR',
  `password_hash` varchar(255) NOT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `gst_no` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_mobile` (`mobile`),
  KEY `idx_user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Kiran Shinde','shindekiran9180@gmail.com','7499520070','Shinde Chemicals','Near MIDC Area','Miraj','Sangli','416410','ADMIN','Kiran@123',1,'2026-11-01 14:46:55','2026-11-01 14:46:55',NULL),(3,'Kiran shinde 2','shindekiran9180@gmail.com2','3243484','u','es','se','etg','se','REGULAR','newUser1',1,'2026-11-14 07:44:18','2026-11-14 07:44:18',NULL),(4,'ramesh','rameshshinde@gmail.com','9765213687','Rmeahs cechs','ss','miraj','ssd','416410','REGULAR','newUser1',1,'2026-11-14 07:47:27','2026-11-14 07:47:27',NULL),(6,'Kiran shinde','shindekiran9180@gmail.com','7499520070','kirans shop','Savali','Miraj','Sangli','465456','REGULAR','newUser1',1,'2026-11-14 13:18:18','2026-11-14 13:18:18',NULL),(7,'akash hire','aksghire@gamil.com','9823037269','Swastik ','swami galli','miraj','sangli','1234','REGULAR','newUser1',1,'2026-11-14 14:46:55','2026-11-14 14:46:55',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-11-15 12:14:17
