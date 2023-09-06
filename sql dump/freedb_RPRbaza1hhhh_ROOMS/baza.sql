-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: sql.freedb.tech    Database: freedb_RPRbaza1hhhh
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `HOTELS`
--

DROP TABLE IF EXISTS `ROOMS/BUNGALOWS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ROOMS/BUNGALOWS` (
                          `idROOMS/BUNGALOWS` int NOT NULL DEFAULT '2',
                          `roomDescription` varchar(45) NOT NULL,
                          `roomAvailable` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'if role = 1 then the room is full , else it''s available.\\n',
                          PRIMARY KEY (`idROOMS/BUNGALOWS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROOMS/BUNGALOWS`
--

LOCK TABLES `ROOMS/BUNGALOWS` WRITE;
/*!40000 ALTER TABLE `ROOMS/BUNGALOWS` DISABLE KEYS */;
INSERT INTO `ROOMS/BUNGALOWS` VALUES (0,'Soba1',0),(1,'Soba2',1),(2,'Soba3',0),(3,'Soba4',1);
/*!40000 ALTER TABLE `ROOMS/BUNGALOWS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RESERVATIONS`
--

DROP TABLE IF EXISTS `RESERVATIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RESERVATIONS` (
                                `idRESERVATION` int NOT NULL,
                                `idUSERS` int NOT NULL,
                                `idROOMS/BUNGALOWS` int NOT NULL,
                                `checkIn` datetime NOT NULL,
                                `checkOut` datetime NOT NULL,
                                PRIMARY KEY (`idRESERVATION`),
                                KEY `room_id_idx` (`idROOMS/BUNGALOWS`),
                                CONSTRAINT `idROOMS/BUNGALOWS` FOREIGN KEY (`idROOMS/BUNGALOWS`) REFERENCES `ROOMS/BUNGALOWS` (`idROOMS/BUNGALOWS`)
                                /*CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `USERS` (`username`)*/
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RESERVATIONS`
--

LOCK TABLES `RESERVATIONS` WRITE;
/*!40000 ALTER TABLE `RESERVATIONS` DISABLE KEYS */;
/*!40000 ALTER TABLE `RESERVATIONS` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `USERS`
--

DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `USERS` (
                         `firstName` varchar(45) NOT NULL,
                         `lastName` varchar(45) NOT NULL,
                         `email` varchar(100) DEFAULT NULL,
                         `isAdministrator` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'if role = 1 then the user is admin of the application, else it''s a basic user.\\n',
                         `username` varchar(45) NOT NULL,
                         `password` varchar(45) DEFAULT NULL,
                         PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS`
--

LOCK TABLES `USERS` WRITE;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
INSERT INTO `USERS` VALUES ('Ilhana','Gabela','igabela1@etf.unsa.ba',1,'igabela1','ilhana123'),('Faris ','Gabela','fgabela1@etf.unsa.ba',0,'fgabela1','faris123'),('Berin ','Karahodžić','bkarahodzic1@etf.unsa.ba',0,'bkarahodzic1','berin123'),('Berina','Zejnilović','bzejnilovic1@etf.unsa.ba',0,'bzejnilovic1','berina123'),('Ehlimana','Krupalija','ekrupalija1@etf.unsa.ba',0,'ekrupalija1','ehlimana123'),('Hana','Mahmutović','hmahmutovi3@etf.unsa.ba',0,'hanamahmutovic','hana123'),('Ilhan','Hasičić','ihasicic1@etf.unsa.ba',0,'ihasicic1','ilhan123'),('Lejla','Heleg','lheleg1@etf.unsa.ba',0,'lheleg1','lejla123'),('Naila','Džananović','ndzananovic1@etf.unsa.ba',0,'ndzananovic1','naila123'),('Taner','Bajrović','tbajrovic1@etf.unsa.ba',0,'tbajrovic1','taner123'),('Tajra','Selimović','tselimovic2@etf.unsa.ba',0,'tselimovic2','tajra123'),('Zerina','Ahmetović','zahmetovic1@etf.unsa.ba',0,'zahmetovic1','zerina123');
/*!40000 ALTER TABLE `USERS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


