-- MySQL dump 10.13  Distrib 5.7.31, for Win64 (x86_64)
--
-- Host: localhost    Database: weos
-- ------------------------------------------------------
-- Server version	5.7.31

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
-- Table structure for table `tblayuda`
--

DROP TABLE IF EXISTS `tblayuda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblayuda` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `preguntas` varchar(500) NOT NULL,
  `respuestas` varchar(500) NOT NULL,
  `docIdentidad_usuario` varchar(10) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_tblayuda_tblusuarios1_idx` (`docIdentidad_usuario`),
  CONSTRAINT `fk_tblayuda_tblusuarios1` FOREIGN KEY (`docIdentidad_usuario`) REFERENCES `tblusuarios` (`docIdentidad`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblayuda`
--

LOCK TABLES `tblayuda` WRITE;
/*!40000 ALTER TABLE `tblayuda` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblayuda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcalificacion`
--

DROP TABLE IF EXISTS `tblcalificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblcalificacion` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `calificacion` tinyint(1) NOT NULL,
  `codigo_cotizacion` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_tblcalificacion_tblcotizacion1_idx` (`codigo_cotizacion`),
  CONSTRAINT `fk_tblcalificacion_tblcotizacion1` FOREIGN KEY (`codigo_cotizacion`) REFERENCES `tblcotizacion` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcalificacion`
--

LOCK TABLES `tblcalificacion` WRITE;
/*!40000 ALTER TABLE `tblcalificacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblcalificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblchat`
--

DROP TABLE IF EXISTS `tblchat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblchat` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `mensajesEmpresas` varchar(5000) NOT NULL,
  `mensajesUsuarios` varchar(5000) NOT NULL,
  `docIdentidad_usuario` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_tblchat_tblusuarios1_idx` (`docIdentidad_usuario`),
  CONSTRAINT `fk_tblchat_tblusuarios1` FOREIGN KEY (`docIdentidad_usuario`) REFERENCES `tblusuarios` (`docIdentidad`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblchat`
--

LOCK TABLES `tblchat` WRITE;
/*!40000 ALTER TABLE `tblchat` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblchat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblciudad`
--

DROP TABLE IF EXISTS `tblciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblciudad` (
  `codigo` varchar(7) NOT NULL,
  `nombre` varchar(85) NOT NULL,
  `departamento` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_tblCiudad_tbldepartamentos1_idx` (`departamento`),
  CONSTRAINT `fk_tblCiudad_tbldepartamentos1` FOREIGN KEY (`departamento`) REFERENCES `tbldepartamentos` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblciudad`
--

LOCK TABLES `tblciudad` WRITE;
/*!40000 ALTER TABLE `tblciudad` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcontacto`
--

DROP TABLE IF EXISTS `tblcontacto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblcontacto` (
  `docidentidad` varchar(10) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `correo` varchar(76) NOT NULL,
  `NITempresa` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`docidentidad`),
  KEY `fk_tblcontacto_tblorganizadoraEventos_idx` (`NITempresa`),
  CONSTRAINT `fk_tblcontacto_tblorganizadoraEventos` FOREIGN KEY (`NITempresa`) REFERENCES `tblempresa` (`NITempresa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcontacto`
--

LOCK TABLES `tblcontacto` WRITE;
/*!40000 ALTER TABLE `tblcontacto` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblcontacto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcotizacion`
--

DROP TABLE IF EXISTS `tblcotizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblcotizacion` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `NITempresa` varchar(10) DEFAULT NULL,
  `docIdentidad_usuario` varchar(10) DEFAULT NULL,
  `fecha` date NOT NULL,
  `calificacion` tinyint(1) DEFAULT NULL,
  `comentario` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_tblcotizacion_tbEmpresa1_idx` (`NITempresa`),
  KEY `fk_tblcotizacion_tblusuarios1_idx` (`docIdentidad_usuario`),
  CONSTRAINT `fk_tblcotizacion_tbEmpresa1` FOREIGN KEY (`NITempresa`) REFERENCES `tblempresa` (`NITempresa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblcotizacion_tblusuarios1` FOREIGN KEY (`docIdentidad_usuario`) REFERENCES `tblusuarios` (`docIdentidad`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=223 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcotizacion`
--

LOCK TABLES `tblcotizacion` WRITE;
/*!40000 ALTER TABLE `tblcotizacion` DISABLE KEYS */;
INSERT INTO `tblcotizacion` VALUES (19,'745457','785691','2021-11-07',NULL,NULL),(20,'745457','785691','2021-11-07',NULL,NULL),(21,'745457','785691','2021-11-07',NULL,NULL),(22,'745457','785691','2021-11-07',NULL,NULL),(23,'47896','785691','2021-11-07',NULL,NULL),(24,'745457','785691','2021-11-07',NULL,NULL),(25,'745457','148614','2021-11-07',NULL,NULL),(26,'47896','412357','2021-11-07',NULL,NULL),(27,'745457','148614','2021-11-07',NULL,NULL),(28,'47896','148614','2021-11-07',NULL,NULL),(29,'47896','148614','2021-11-07',NULL,NULL),(30,'4785995','148614','2021-11-07',NULL,NULL),(31,'745457','785691','2021-11-09',NULL,NULL),(32,'47896','785691','2021-11-09',NULL,NULL),(33,'745457','785691','2021-11-09',NULL,NULL),(34,'4785995','785691','2021-11-09',NULL,NULL),(35,'745457','785691','2021-11-09',NULL,NULL),(36,'745457','785691','2021-11-11',NULL,NULL),(37,'745457','785691','2021-11-11',NULL,NULL),(38,'745457','785691','2021-11-11',NULL,NULL),(39,'745457','785691','2021-11-11',NULL,NULL),(40,'745457','785691','2021-11-11',NULL,NULL),(41,'745457','785691','2021-11-11',NULL,NULL),(42,'745457','785691','2021-11-11',NULL,NULL),(43,'745457','785691','2021-11-11',NULL,NULL),(44,'745457','785691','2021-11-11',NULL,NULL),(45,'745457','785691','2021-11-11',NULL,NULL),(46,'745457','785691','2021-11-11',NULL,NULL),(47,'745457','785691','2021-11-11',NULL,NULL),(48,'745457','785691','2021-11-11',NULL,NULL),(49,'745457','785691','2021-11-11',NULL,NULL),(50,'47896','785691','2021-11-11',NULL,NULL),(51,'745457','412357','2021-11-11',NULL,NULL),(52,'745457','7654986','2021-11-15',NULL,NULL),(55,'745457','7654986','2021-11-15',NULL,NULL),(56,'745457','7654986','2021-11-15',NULL,NULL),(57,'745457','7654986','2021-11-15',NULL,NULL),(58,'745457','7654986','2021-11-15',NULL,NULL),(59,'745457','7654986','2021-11-15',NULL,NULL),(61,'745457','7654986','2021-11-15',NULL,NULL),(62,'745457','7654986','2021-11-15',NULL,NULL),(63,'745457','7654986','2021-11-15',NULL,NULL),(64,'745457','7654986','2021-11-15',NULL,NULL),(65,'745457','7654986','2021-11-15',NULL,NULL),(66,'745457','7654986','2021-11-15',NULL,NULL),(67,'745457','7654986','2021-11-15',NULL,NULL),(68,'745457','7654986','2021-11-15',NULL,NULL),(69,'745457','785691','2021-11-15',NULL,NULL),(70,'745457','785691','2021-11-15',NULL,NULL),(71,'745457','785691','2021-11-15',NULL,NULL),(72,'745457','785691','2021-11-15',NULL,NULL),(73,'745457','785691','2021-11-15',NULL,NULL),(74,'745457','785691','2021-11-15',NULL,NULL),(75,'745457','785691','2021-11-15',NULL,NULL),(76,'745457','785691','2021-11-15',NULL,NULL),(77,'745457','785691','2021-11-15',NULL,NULL),(78,'745457','785691','2021-11-15',NULL,NULL),(79,'745457','785691','2021-11-15',NULL,NULL),(81,'745457','785691','2021-11-15',NULL,NULL),(82,'745457','785691','2021-11-15',NULL,NULL),(83,'745457','785691','2021-11-15',NULL,NULL),(84,'745457','785691','2021-11-16',NULL,NULL),(85,'745457','785691','2021-11-16',NULL,NULL),(86,'745457','785691','2021-11-16',NULL,NULL),(87,'745457','785691','2021-11-16',NULL,NULL),(88,'745457','785691','2021-11-16',NULL,NULL),(89,'745457','785691','2021-11-16',NULL,NULL),(90,'745457','785691','2021-11-16',NULL,NULL),(91,'745457','785691','2021-11-16',NULL,NULL),(92,'745457','785691','2021-11-16',NULL,NULL),(93,'745457','785691','2021-11-17',NULL,NULL),(94,'745457','785691','2021-11-17',NULL,NULL),(95,'745457','785691','2021-11-17',NULL,NULL),(96,'745457','785691','2021-11-17',NULL,NULL),(97,'745457','785691','2021-11-17',NULL,NULL),(98,'745457','785691','2021-11-17',NULL,NULL),(99,'745457','785691','2021-11-17',NULL,NULL),(100,'745457','785691','2021-11-17',NULL,NULL),(101,'745457','785691','2021-11-17',NULL,NULL),(103,'745457','785691','2021-11-17',NULL,NULL),(104,'745457','785691','2021-11-17',NULL,NULL),(105,'745457','785691','2021-11-17',NULL,NULL),(106,'745457','785691','2021-11-17',NULL,NULL),(107,'745457','785691','2021-11-17',NULL,NULL),(108,'745457','785691','2021-11-17',NULL,NULL),(109,'745457','785691','2021-11-17',NULL,NULL),(110,'745457','785691','2021-11-17',NULL,NULL),(111,'745457','785691','2021-11-17',NULL,NULL),(112,'745457','785691','2021-11-17',NULL,NULL),(113,'745457','785691','2021-11-17',NULL,NULL),(114,'745457','785691','2021-11-17',NULL,NULL),(115,'745457','785691','2021-11-17',NULL,NULL),(116,'745457','785691','2021-11-18',NULL,NULL),(117,'745457','785691','2021-11-18',NULL,NULL),(118,'745457','785691','2021-11-19',NULL,NULL),(120,'745457','785691','2021-11-19',NULL,NULL),(121,'745457','785691','2021-11-19',NULL,NULL),(122,'745457','785691','2021-11-19',NULL,NULL),(123,'745457','785691','2021-11-19',NULL,NULL),(124,'745457','785691','2021-11-19',NULL,NULL),(125,'745457','785691','2021-11-19',NULL,NULL),(126,'745457','785691','2021-11-19',NULL,NULL),(127,'745457','785691','2021-11-19',NULL,NULL),(128,'745457','785691','2021-11-19',NULL,NULL),(129,'745457','785691','2021-11-19',NULL,NULL),(130,'745457','785691','2021-11-19',NULL,NULL),(131,'745457','785691','2021-11-19',NULL,NULL),(132,'745457','785691','2021-11-19',NULL,NULL),(133,'745457','785691','2021-11-19',NULL,NULL),(134,'745457','785691','2021-11-19',NULL,NULL),(135,'745457','785691','2021-11-19',NULL,NULL),(136,'745457','785691','2021-11-19',NULL,NULL),(137,'745457','785691','2021-11-19',NULL,NULL),(138,'745457','785691','2021-11-19',NULL,NULL),(139,'745457','785691','2021-11-19',NULL,NULL),(140,'745457','785691','2021-11-19',NULL,NULL),(141,'745457','785691','2021-11-19',NULL,NULL),(142,'745457','785691','2021-11-19',NULL,NULL),(143,'745457','785691','2021-11-19',NULL,NULL),(144,'745457','785691','2021-11-19',NULL,NULL),(145,'745457','785691','2021-11-19',NULL,NULL),(146,'745457','785691','2021-11-19',NULL,NULL),(147,'745457','785691','2021-11-19',NULL,NULL),(148,'745457','785691','2021-11-19',NULL,NULL),(149,'745457','785691','2021-11-19',NULL,NULL),(150,'745457','785691','2021-11-19',NULL,NULL),(151,'745457','785691','2021-11-19',NULL,NULL),(152,'745457','785691','2021-11-19',NULL,NULL),(153,'745457','785691','2021-11-19',NULL,NULL),(154,'745457','785691','2021-11-19',NULL,NULL),(155,'745457','785691','2021-11-20',NULL,NULL),(156,'745457','785691','2021-11-20',NULL,NULL),(157,'745457','785691','2021-11-20',NULL,NULL),(158,'745457','785691','2021-11-20',NULL,NULL),(159,'745457','785691','2021-11-20',NULL,NULL),(160,'745457','785691','2021-11-20',NULL,NULL),(161,'745457','785691','2021-11-20',NULL,NULL),(162,'745457','785691','2021-11-20',NULL,NULL),(163,'745457','785691','2021-11-20',NULL,NULL),(164,'745457','785691','2021-11-20',NULL,NULL),(165,'745457','785691','2021-11-20',NULL,NULL),(166,'745457','785691','2021-11-20',NULL,NULL),(167,'745457','785691','2021-11-20',NULL,NULL),(168,'745457','785691','2021-11-20',NULL,NULL),(169,'745457','785691','2021-11-20',NULL,NULL),(170,'745457','785691','2021-11-20',NULL,NULL),(171,'745457','785691','2021-11-20',NULL,NULL),(172,'745457','785691','2021-11-22',NULL,NULL),(173,'745457','785691','2021-11-22',NULL,NULL),(174,'745457','785691','2021-11-22',NULL,NULL),(175,'745457','785691','2021-11-22',NULL,NULL),(176,'76543','98675433','2021-11-22',NULL,NULL),(177,'76543','98675433','2021-11-22',NULL,NULL),(178,'76543','98675433','2021-11-22',NULL,NULL),(179,'76543','98675433','2021-11-22',NULL,NULL),(180,'76543','98675433','2021-11-22',NULL,NULL),(181,'76543','98675433','2021-11-22',NULL,NULL),(182,'76543','98675433','2021-11-22',NULL,NULL),(183,'76543','98675433','2021-11-22',NULL,NULL),(184,'76543','98675433','2021-11-22',NULL,NULL),(185,'76543','98675433','2021-11-22',NULL,NULL),(186,'76543','98675433','2021-11-22',NULL,NULL),(187,'76543','98675433','2021-11-22',NULL,NULL),(188,'76543','98675433','2021-11-22',NULL,NULL),(189,'76543','98675433','2021-11-22',NULL,NULL),(190,'76543','98675433','2021-11-22',NULL,NULL),(191,'76543','98675433','2021-11-22',NULL,NULL),(192,'76543','98675433','2021-11-22',NULL,NULL),(193,'76543','98675433','2021-11-22',NULL,NULL),(194,'76543','98675433','2021-11-22',NULL,NULL),(195,'76543','98675433','2021-11-22',NULL,NULL),(196,'76543','98675433','2021-11-22',NULL,NULL),(197,'76543','98675433','2021-11-22',NULL,NULL),(198,'76543','98675433','2021-11-22',NULL,NULL),(199,'76543','98675433','2021-11-22',NULL,NULL),(200,'76543','98675433','2021-11-22',NULL,NULL),(201,'76543','98675433','2021-11-22',NULL,NULL),(202,'76543','98675433','2021-11-22',NULL,NULL),(203,'76543','98675433','2021-11-22',NULL,NULL),(204,'76543','98675433','2021-11-22',NULL,NULL),(205,'76543','98675433','2021-11-22',NULL,NULL),(206,'76543','98675433','2021-11-22',NULL,NULL),(207,'76543','98675433','2021-11-22',NULL,NULL),(208,'76543','98675433','2021-11-22',NULL,NULL),(209,'76543','98675433','2021-11-22',NULL,NULL),(210,'76543','98675433','2021-11-22',NULL,NULL),(211,'76543','98675433','2021-11-22',NULL,NULL),(212,'76543','98675433','2021-11-22',NULL,NULL),(213,'76543','98675433','2021-11-22',NULL,NULL),(214,'76543','98675433','2021-11-22',NULL,NULL),(215,'76543','98675433','2021-11-22',NULL,NULL),(216,'76543','98675433','2021-11-22',NULL,NULL),(217,'76543','98675433','2021-11-22',NULL,NULL),(218,'745457','785691','2021-11-22',NULL,NULL),(219,'876543455','3455673','2021-11-23',NULL,NULL),(220,'876543455','3455673','2021-11-23',NULL,NULL),(221,'876543455','785691','2021-11-23',NULL,NULL),(222,'876543455','785691','2021-11-23',NULL,NULL);
/*!40000 ALTER TABLE `tblcotizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbldepartamentos`
--

DROP TABLE IF EXISTS `tbldepartamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbldepartamentos` (
  `codigo` varchar(2) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `pais` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_tbldepartamentos_tblPais1_idx` (`pais`),
  CONSTRAINT `fk_tbldepartamentos_tblPais1` FOREIGN KEY (`pais`) REFERENCES `tblpais` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbldepartamentos`
--

LOCK TABLES `tbldepartamentos` WRITE;
/*!40000 ALTER TABLE `tbldepartamentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbldepartamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblempresa`
--

DROP TABLE IF EXISTS `tblempresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblempresa` (
  `NITempresa` varchar(10) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `ciudad` varchar(7) DEFAULT NULL,
  `correo` varchar(76) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `descripcion` varchar(5000) NOT NULL,
  `avatar` varchar(260) DEFAULT NULL,
  `usuario` varchar(10) NOT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `link_facebook` varchar(56) DEFAULT NULL,
  `link_instagram` varchar(70) DEFAULT NULL,
  `link_youtube` varchar(60) DEFAULT NULL,
  `link_twitter` varchar(40) DEFAULT NULL,
  `link_whatsapp` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`NITempresa`),
  KEY `fk_tbEmpresa_tblCiudad1_idx` (`ciudad`),
  KEY `fk_tbEmpresa_tblusuarios1_idx` (`usuario`),
  CONSTRAINT `fk_tbEmpresa_tblCiudad1` FOREIGN KEY (`ciudad`) REFERENCES `tblciudad` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbEmpresa_tblusuarios1` FOREIGN KEY (`usuario`) REFERENCES `tblusuarios` (`docIdentidad`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblempresa`
--

LOCK TABLES `tblempresa` WRITE;
/*!40000 ALTER TABLE `tblempresa` DISABLE KEYS */;
INSERT INTO `tblempresa` VALUES ('34567643','Las delicias|',NULL,'delicias@delicias.com','31024','Los eventos mas deliciosos de tu zona',NULL,'876543',NULL,'https://www.facebook.com/profile.php?id=100009724578657','https://www.instagram.com/juanmurilloosorio/?hl=es','https://www.youtube.com/','https://twitter.com/?lang=es','https://web.whatsapp.com/'),('34654','El emperador',NULL,'emperador@emperador.com','3103474753','Lo mejor de lo mejor',NULL,'87653',NULL,'https://www.facebook.com/profile.php?id=100009724578657','https://www.instagram.com/juanmurilloosorio/?hl=es','https://www.youtube.com/','https://twitter.com/?lang=es','https://web.whatsapp.com/'),('4565654','La piedra',NULL,'jcosorio651@misena.edu.co','3103474767','La mejor opcion para tus eventos que puedes elegir ','2979685.jpg','0987543',NULL,'https://www.facebook.com/profile.php?id=100009724578657','https://www.instagram.com/juanmurilloosorio/?hl=es','https://www.youtube.com/','https://twitter.com/?lang=es','https://web.whatsapp.com/'),('4785995','Los eventos',NULL,'juancaom12@gmail.com','321045641','                    \r\n                Somos la mejor empresa organizadora de eventos de tu lugar, solo cotiza con nosotros y notaras la diferencia',NULL,'5287456',NULL,'hste','dfnfjn','rthnn','t4nwnw','b4ww5'),('47896','Los eventos',NULL,'juancaom12@gmail.com','321045641','                    \r\n                Somos la mejor empresa organizadora de eventos de tu lugar, solo cotiza con nosotros y notaras la diferencia',NULL,'147852',NULL,'hste','dfnfjn','rthnn','t4nwnw','b4ww5'),('56432','Casa blanca',NULL,'casaBlanca@casaBlanca.com','5432','La organizadora de eventos por excelencia en tu zona ',NULL,'6487665',NULL,'https://www.facebook.com/profile.php?id=100009724578657','https://www.instagram.com/juanmurilloosorio/?hl=es','https://www.youtube.com/','https://twitter.com/?lang=es','https://web.whatsapp.com/'),('6543','La pintura',NULL,'pintura@pintura.com','3103474753','                Una empresa destinada a la organizacion de eventos en todo medellin, somos confiables y tenemos un amplio conocimiento en estos temas, asi que, si quieres realizar tu evento nosotros somos los indicados\r\n            ','4709728.jpg','98675433',NULL,'https://www.facebook.com/profile.php?id=100009724578657','','','',''),('655433','San Jose',NULL,'jose@jose.com','3103474753','Todo nuestro poder para ti','4709728.jpg','7654986',NULL,'https://www.facebook.com/profile.php?id=100009724578657','https://www.instagram.com/juanmurilloosorio/?hl=es','https://www.youtube.com/','https://twitter.com/?lang=es','https://web.whatsapp.com/'),('745457','La macarena SAS',NULL,'juancaom12@gmail.com','3210456443','                                                                                                                                                                                                    \r\n                \r\n                Nuestro proposito es servirte\r\n                \r\n                    \r\n                    \r\n                    \r\n                    \r\n                    \r\n            ',NULL,'1040','null','https://www.facebook.com/profile.php?id=100009724578657','https://www.instagram.com/juanmurilloosorio/?hl=es','https://www.youtube.com/','https://twitter.com/?lang=es','https://web.whatsapp.com/'),('76543','Pinta',NULL,'jcosorio651@misena.edu.co','3103474753','                La organizadora de eventos por excelencia en tu zona \r\n            ','2979685.jpg','148614',NULL,'https://www.facebook.com/profile.php?id=100009724578657','https://www.instagram.com/juanmurilloosorio/?hl=es','https://www.youtube.com/','https://twitter.com/?lang=es','https://web.whatsapp.com/'),('876543455','Santa monica',NULL,'monica@monica.com','3146154772','Nos enfocamos en hacer tus sueÃ±os realidad','empresas-organizadoras-de-eventos.jpg','65432',NULL,'https://www.facebook.com/profile.php?id=100009724578657','','','',''),('8765490','Los eventos',NULL,'juancaom12@gmail.com','321045641','                    \r\n                Somos la mejor empresa organizadora de eventos de tu lugar, solo cotiza con nosotros y notaras la diferencia',NULL,'23644',NULL,'hste','dfnfjn','rthnn','t4nwnw','b4ww5');
/*!40000 ALTER TABLE `tblempresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblespecialidad`
--

DROP TABLE IF EXISTS `tblespecialidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblespecialidad` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(25) NOT NULL,
  `descripcion` varchar(2000) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblespecialidad`
--

LOCK TABLES `tblespecialidad` WRITE;
/*!40000 ALTER TABLE `tblespecialidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblespecialidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblespecialidad_empresa`
--

DROP TABLE IF EXISTS `tblespecialidad_empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblespecialidad_empresa` (
  `NITempresa` varchar(10) NOT NULL,
  `codigoEspecialidad` int(11) NOT NULL,
  PRIMARY KEY (`NITempresa`,`codigoEspecialidad`),
  KEY `fk_tblEspecialidad_Empresa_tblespecialidad1_idx` (`codigoEspecialidad`),
  CONSTRAINT `fk_tblEspecialidad_Empresa_tbEmpresa1` FOREIGN KEY (`NITempresa`) REFERENCES `tblempresa` (`NITempresa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblEspecialidad_Empresa_tblespecialidad1` FOREIGN KEY (`codigoEspecialidad`) REFERENCES `tblespecialidad` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblespecialidad_empresa`
--

LOCK TABLES `tblespecialidad_empresa` WRITE;
/*!40000 ALTER TABLE `tblespecialidad_empresa` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblespecialidad_empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblestado`
--

DROP TABLE IF EXISTS `tblestado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblestado` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblestado`
--

LOCK TABLES `tblestado` WRITE;
/*!40000 ALTER TABLE `tblestado` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblestado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblfotos`
--

DROP TABLE IF EXISTS `tblfotos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblfotos` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `foto` varchar(266) NOT NULL,
  `producto_servicio` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_tblFotos_tblproductosServicios1_idx` (`producto_servicio`),
  CONSTRAINT `fk_tblFotos_tblproductosServicios1` FOREIGN KEY (`producto_servicio`) REFERENCES `tblproductosservicios` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblfotos`
--

LOCK TABLES `tblfotos` WRITE;
/*!40000 ALTER TABLE `tblfotos` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblfotos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblopinionespagina`
--

DROP TABLE IF EXISTS `tblopinionespagina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblopinionespagina` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `docIdentidad_usuario` varchar(10) DEFAULT NULL,
  `descripcion` varchar(5000) NOT NULL,
  `fecha` datetime NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_tblopinionesPagina_tblusuarios1_idx` (`docIdentidad_usuario`),
  CONSTRAINT `fk_tblopinionesPagina_tblusuarios1` FOREIGN KEY (`docIdentidad_usuario`) REFERENCES `tblusuarios` (`docIdentidad`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblopinionespagina`
--

LOCK TABLES `tblopinionespagina` WRITE;
/*!40000 ALTER TABLE `tblopinionespagina` DISABLE KEYS */;
INSERT INTO `tblopinionespagina` VALUES (4,'785691','Me agrada mucho la pagina','2021-11-17 13:20:47'),(5,'785691','Me agrada mucho la pagina','2021-11-18 07:54:22'),(6,'785691','Me agrada mucho la pagina','2021-11-22 16:52:17'),(7,'98675433','Me agrada mucho la pagina','2021-11-22 17:26:29');
/*!40000 ALTER TABLE `tblopinionespagina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblpais`
--

DROP TABLE IF EXISTS `tblpais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblpais` (
  `codigo` varchar(9) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblpais`
--

LOCK TABLES `tblpais` WRITE;
/*!40000 ALTER TABLE `tblpais` DISABLE KEYS */;
INSERT INTO `tblpais` VALUES ('57','Colombia');
/*!40000 ALTER TABLE `tblpais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblproductosservicios`
--

DROP TABLE IF EXISTS `tblproductosservicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblproductosservicios` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(75) NOT NULL,
  `precioUnidad` int(8) NOT NULL,
  `descripcion` varchar(5000) NOT NULL,
  `tipo_productos_servicios` varchar(9) NOT NULL,
  `NITempresa` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_tblproductosServicios_tbEmpresa1_idx` (`NITempresa`),
  CONSTRAINT `fk_tblproductosServicios_tbEmpresa1` FOREIGN KEY (`NITempresa`) REFERENCES `tblempresa` (`NITempresa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblproductosservicios`
--

LOCK TABLES `tblproductosservicios` WRITE;
/*!40000 ALTER TABLE `tblproductosservicios` DISABLE KEYS */;
INSERT INTO `tblproductosservicios` VALUES (4,'Coca cola',4000,'Un litro','producto','4785995'),(5,'Vodka',100000,'Un vodka normal','producto','745457'),(6,'Musica',500000,'Servicio de musica estereo','servicio','745457'),(7,'Ron',50000,'Ron viejo de caldas x1litro','producto','745457'),(8,'Luces led',100000,'Luces por todo el evento','servicio','745457'),(9,'Vino tinto',50000,'                    Botella de vino tinto x3 litros aÃ±eja\r\n                ','producto','745457'),(10,'Piscina para niÃ±os',40000,'Una piscina inflable para los mas pequeÃ±os','servicio','47896'),(11,'Bufet',2000000,'Una barra todo incluido donde las personas podran sacar toda la comida que quieran ','servicio','47896'),(12,'Ron medellin',50000,'Una botella de ron MedellÃ­n aÃ±ejo','producto','47896'),(13,'Gomas',2000,'Paquete de gomas que van a estar en una mesa con tazas ','producto','47896'),(14,'Fuente de chocolate',50000,'Una fuente de chocolate caliente acompaÃ±ada con fresas','servicio','47896'),(15,'Fuegos artficiales',100000,'Caja de fuegos artificiales disparados por expertos','servicio','47896'),(16,'Vestido de 15s',700000,'Vestido blanco para fiesta de 15s','servicio','4785995'),(17,'Piscina',300000,'El servicio de piscina completo para todos los invitados','servicio','8765490'),(18,'Castillo inflable',500000,'                    Un castillo genial\r\n                \r\n                \r\n                ','servicio','745457'),(19,'Coca cola',3000,'                                    \r\n            Una coca cola normal\r\n                ','producto','745457'),(21,'Castillo inflable',600000,'                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                ','servicio','76543'),(22,'Coca cola',5000,'                \r\n        Coca cola x3 litros','producto','76543'),(23,'Sillas',5000,'                \r\n        Sillas blancas muy comodas','producto','876543455'),(24,'Musica',700000,'Servicios de musica estere por todo el evento                \r\n        ','servicio','876543455'),(25,'Banquete',2000000,'Banquete abierto para todo el publico                \r\n        ','servicio','876543455'),(26,'Lamparas',10000,'                \r\n        Lamparas grandes para iluminacion','servicio','745457');
/*!40000 ALTER TABLE `tblproductosservicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblproductosservicios_cotizacion`
--

DROP TABLE IF EXISTS `tblproductosservicios_cotizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblproductosservicios_cotizacion` (
  `codigoProductosServicios` int(11) NOT NULL,
  `codigoCotizacion` int(11) NOT NULL,
  `cantidadProductos` int(4) NOT NULL,
  `precioUnidad` int(8) NOT NULL,
  `nombreProducto` varchar(75) NOT NULL,
  `descripcionProducto` varchar(5000) NOT NULL,
  PRIMARY KEY (`codigoProductosServicios`,`codigoCotizacion`),
  KEY `fk_tblproductosServicios_cotizacion_tblcotizacion1_idx` (`codigoCotizacion`),
  CONSTRAINT `fk_tblproductosServicios_cotizacion_tblcotizacion1` FOREIGN KEY (`codigoCotizacion`) REFERENCES `tblcotizacion` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblproductosServicios_cotizacion_tblproductosServicios1` FOREIGN KEY (`codigoProductosServicios`) REFERENCES `tblproductosservicios` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblproductosservicios_cotizacion`
--

LOCK TABLES `tblproductosservicios_cotizacion` WRITE;
/*!40000 ALTER TABLE `tblproductosservicios_cotizacion` DISABLE KEYS */;
INSERT INTO `tblproductosservicios_cotizacion` VALUES (4,30,2,4000,'Coca cola','Un litro'),(4,34,1,4000,'Coca cola','Un litro'),(5,25,3,100000,'Vodka','Un vodka normal'),(5,27,2,100000,'Vodka','Un vodka normal'),(5,35,1,100000,'Vodka','Un vodka normal'),(5,36,2,100000,'Vodka','Un vodka normal'),(5,37,2,100000,'Vodka','Un vodka normal'),(5,38,2,100000,'Vodka','Un vodka normal'),(5,39,2,100000,'Vodka','Un vodka normal'),(5,40,2,100000,'Vodka','Un vodka normal'),(5,41,2,100000,'Vodka','Un vodka normal'),(5,42,1,100000,'Vodka','Un vodka normal'),(5,43,1,100000,'Vodka','Un vodka normal'),(5,44,1,100000,'Vodka','Un vodka normal'),(5,45,1,100000,'Vodka','Un vodka normal'),(5,46,1,100000,'Vodka','Un vodka normal'),(5,47,1,100000,'Vodka','Un vodka normal'),(5,48,1,100000,'Vodka','Un vodka normal'),(5,49,1,100000,'Vodka','Un vodka normal'),(5,51,1,100000,'Vodka','Un vodka normal'),(5,52,1,100000,'Vodka','Un vodka normal'),(5,59,1,100000,'Vodka','Un vodka normal'),(5,61,1,100000,'Vodka','Un vodka normal'),(5,68,1,100000,'Vodka','Un vodka normal'),(5,77,1,100000,'Vodka','Un vodka normal'),(5,78,2,100000,'Vodka','Un vodka normal'),(5,79,1,100000,'Vodka','Un vodka normal'),(5,86,1,100000,'Vodka','Un vodka normal'),(5,88,1,100000,'Vodka','Un vodka normal'),(5,89,2,100000,'Vodka','Un vodka normal'),(5,90,1,100000,'Vodka','Un vodka normal'),(5,91,1,100000,'Vodka','Un vodka normal'),(5,92,1,100000,'Vodka','Un vodka normal'),(5,94,1,100000,'Vodka','Un vodka normal'),(5,96,1,100000,'Vodka','Un vodka normal'),(5,97,1,100000,'Vodka','Un vodka normal'),(5,98,1,100000,'Vodka','Un vodka normal'),(5,99,1,100000,'Vodka','Un vodka normal'),(5,100,1,100000,'Vodka','Un vodka normal'),(5,101,1,100000,'Vodka','Un vodka normal'),(5,115,1,100000,'Vodka','Un vodka normal'),(5,117,2,100000,'Vodka','Un vodka normal'),(5,118,1,100000,'Vodka','Un vodka normal'),(5,127,1,100000,'Vodka','Un vodka normal'),(5,128,1,100000,'Vodka','Un vodka normal'),(5,129,1,100000,'Vodka','Un vodka normal'),(5,131,1,100000,'Vodka','Un vodka normal'),(5,132,1,100000,'Vodka','Un vodka normal'),(5,133,1,100000,'Vodka','Un vodka normal'),(5,134,1,100000,'Vodka','Un vodka normal'),(5,135,1,100000,'Vodka','Un vodka normal'),(5,136,1,100000,'Vodka','Un vodka normal'),(5,137,1,100000,'Vodka','Un vodka normal'),(5,138,1,100000,'Vodka','Un vodka normal'),(5,139,1,100000,'Vodka','Un vodka normal'),(5,140,1,100000,'Vodka','Un vodka normal'),(5,141,2,100000,'Vodka','Un vodka normal'),(5,142,1,100000,'Vodka','Un vodka normal'),(5,143,2,100000,'Vodka','Un vodka normal'),(5,144,1,100000,'Vodka','Un vodka normal'),(5,145,1,100000,'Vodka','Un vodka normal'),(5,146,1,100000,'Vodka','Un vodka normal'),(5,147,1,100000,'Vodka','Un vodka normal'),(5,148,1,100000,'Vodka','Un vodka normal'),(5,149,1,100000,'Vodka','Un vodka normal'),(5,150,1,100000,'Vodka','Un vodka normal'),(5,151,1,100000,'Vodka','Un vodka normal'),(5,152,1,100000,'Vodka','Un vodka normal'),(5,153,1,100000,'Vodka','Un vodka normal'),(5,154,1,100000,'Vodka','Un vodka normal'),(5,155,1,100000,'Vodka','Un vodka normal'),(5,156,1,100000,'Vodka','Un vodka normal'),(5,157,2,100000,'Vodka','Un vodka normal'),(5,160,1,100000,'Vodka','Un vodka normal'),(5,163,1,100000,'Vodka','Un vodka normal'),(5,164,1,100000,'Vodka','Un vodka normal'),(5,165,4,100000,'Vodka','Un vodka normal'),(5,166,3,100000,'Vodka','Un vodka normal'),(5,167,1,100000,'Vodka','Un vodka normal'),(5,168,2,100000,'Vodka','Un vodka normal'),(5,169,3,100000,'Vodka','Un vodka normal'),(5,170,2,100000,'Vodka','Un vodka normal'),(5,171,1,100000,'Vodka','Un vodka normal'),(5,172,5,100000,'Vodka','Un vodka normal'),(5,173,10,100000,'Vodka','Un vodka normal'),(5,174,2,100000,'Vodka','Un vodka normal'),(5,175,4,100000,'Vodka','Un vodka normal'),(6,25,2,500000,'Musica','Servicio de musica estereo'),(6,27,3,500000,'Musica','Servicio de musica estereo'),(6,31,1,500000,'Musica','Servicio de musica estereo'),(6,33,3,500000,'Musica','Servicio de musica estereo'),(6,35,2,500000,'Musica','Servicio de musica estereo'),(6,36,3,500000,'Musica','Servicio de musica estereo'),(6,37,3,500000,'Musica','Servicio de musica estereo'),(6,38,3,500000,'Musica','Servicio de musica estereo'),(6,39,3,500000,'Musica','Servicio de musica estereo'),(6,40,3,500000,'Musica','Servicio de musica estereo'),(6,41,3,500000,'Musica','Servicio de musica estereo'),(6,42,3,500000,'Musica','Servicio de musica estereo'),(6,43,3,500000,'Musica','Servicio de musica estereo'),(6,44,3,500000,'Musica','Servicio de musica estereo'),(6,45,3,500000,'Musica','Servicio de musica estereo'),(6,46,3,500000,'Musica','Servicio de musica estereo'),(6,47,3,500000,'Musica','Servicio de musica estereo'),(6,48,3,500000,'Musica','Servicio de musica estereo'),(6,49,3,500000,'Musica','Servicio de musica estereo'),(6,50,1,500000,'Musica','Servicio de musica estereo'),(6,51,2,500000,'Musica','Servicio de musica estereo'),(6,57,1,500000,'Musica','Servicio de musica estereo'),(6,59,1,500000,'Musica','Servicio de musica estereo'),(6,61,1,500000,'Musica','Servicio de musica estereo'),(6,63,1,500000,'Musica','Servicio de musica estereo'),(6,67,1,500000,'Musica','Servicio de musica estereo'),(6,68,2,500000,'Musica','Servicio de musica estereo'),(6,69,1,500000,'Musica','Servicio de musica estereo'),(6,77,1,500000,'Musica','Servicio de musica estereo'),(6,78,2,500000,'Musica','Servicio de musica estereo'),(6,79,2,500000,'Musica','Servicio de musica estereo'),(6,83,1,500000,'Musica','Servicio de musica estereo'),(6,84,1,500000,'Musica','Servicio de musica estereo'),(6,85,1,500000,'Musica','Servicio de musica estereo'),(6,86,1,500000,'Musica','Servicio de musica estereo'),(6,87,1,500000,'Musica','Servicio de musica estereo'),(6,89,1,500000,'Musica','Servicio de musica estereo'),(6,90,2,500000,'Musica','Servicio de musica estereo'),(6,92,1,500000,'Musica','Servicio de musica estereo'),(6,93,1,500000,'Musica','Servicio de musica estereo'),(6,94,1,500000,'Musica','Servicio de musica estereo'),(6,95,1,500000,'Musica','Servicio de musica estereo'),(6,96,1,500000,'Musica','Servicio de musica estereo'),(6,97,1,500000,'Musica','Servicio de musica estereo'),(6,98,1,500000,'Musica','Servicio de musica estereo'),(6,99,1,500000,'Musica','Servicio de musica estereo'),(6,100,1,500000,'Musica','Servicio de musica estereo'),(6,101,1,500000,'Musica','Servicio de musica estereo'),(6,103,1,500000,'Musica','Servicio de musica estereo'),(6,104,1,500000,'Musica','Servicio de musica estereo'),(6,105,1,500000,'Musica','Servicio de musica estereo'),(6,106,1,500000,'Musica','Servicio de musica estereo'),(6,107,1,500000,'Musica','Servicio de musica estereo'),(6,108,1,500000,'Musica','Servicio de musica estereo'),(6,109,1,500000,'Musica','Servicio de musica estereo'),(6,110,2,500000,'Musica','Servicio de musica estereo'),(6,111,2,500000,'Musica','Servicio de musica estereo'),(6,112,2,500000,'Musica','Servicio de musica estereo'),(6,113,3,500000,'Musica','Servicio de musica estereo'),(6,114,1,500000,'Musica','Servicio de musica estereo'),(6,115,2,500000,'Musica','Servicio de musica estereo'),(6,116,1,500000,'Musica','Servicio de musica estereo'),(6,117,1,500000,'Musica','Servicio de musica estereo'),(6,118,2,500000,'Musica','Servicio de musica estereo'),(6,120,1,500000,'Musica','Servicio de musica estereo'),(6,122,2,500000,'Musica','Servicio de musica estereo'),(6,123,1,500000,'Musica','Servicio de musica estereo'),(6,125,1,500000,'Musica','Servicio de musica estereo'),(6,128,1,500000,'Musica','Servicio de musica estereo'),(6,129,1,500000,'Musica','Servicio de musica estereo'),(6,131,1,500000,'Musica','Servicio de musica estereo'),(6,132,1,500000,'Musica','Servicio de musica estereo'),(6,133,1,500000,'Musica','Servicio de musica estereo'),(6,134,1,500000,'Musica','Servicio de musica estereo'),(6,135,1,500000,'Musica','Servicio de musica estereo'),(6,136,1,500000,'Musica','Servicio de musica estereo'),(6,137,1,500000,'Musica','Servicio de musica estereo'),(6,138,1,500000,'Musica','Servicio de musica estereo'),(6,139,1,500000,'Musica','Servicio de musica estereo'),(6,141,2,500000,'Musica','Servicio de musica estereo'),(6,142,1,500000,'Musica','Servicio de musica estereo'),(6,143,1,500000,'Musica','Servicio de musica estereo'),(6,144,1,500000,'Musica','Servicio de musica estereo'),(6,145,1,500000,'Musica','Servicio de musica estereo'),(6,146,1,500000,'Musica','Servicio de musica estereo'),(6,147,1,500000,'Musica','Servicio de musica estereo'),(6,148,1,500000,'Musica','Servicio de musica estereo'),(6,149,1,500000,'Musica','Servicio de musica estereo'),(6,151,1,500000,'Musica','Servicio de musica estereo'),(6,152,1,500000,'Musica','Servicio de musica estereo'),(6,153,1,500000,'Musica','Servicio de musica estereo'),(6,154,1,500000,'Musica','Servicio de musica estereo'),(6,155,1,500000,'Musica','Servicio de musica estereo'),(6,156,2,500000,'Musica','Servicio de musica estereo'),(6,157,2,500000,'Musica','Servicio de musica estereo'),(6,158,1,500000,'Musica','Servicio de musica estereo'),(6,159,3,500000,'Musica','Servicio de musica estereo'),(6,160,1,500000,'Musica','Servicio de musica estereo'),(6,161,2,500000,'Musica','Servicio de musica estereo'),(6,162,2,500000,'Musica','Servicio de musica estereo'),(6,163,2,500000,'Musica','Servicio de musica estereo'),(6,164,4,500000,'Musica','Servicio de musica estereo'),(6,165,2,500000,'Musica','Servicio de musica estereo'),(6,166,1,500000,'Musica','Servicio de musica estereo'),(6,167,1,500000,'Musica','Servicio de musica estereo'),(6,168,1,500000,'Musica','Servicio de musica estereo'),(6,169,1,500000,'Musica','Servicio de musica estereo'),(6,170,1,500000,'Musica','Servicio de musica estereo'),(6,171,1,500000,'Musica','Servicio de musica estereo'),(6,173,5,500000,'Musica','Servicio de musica estereo'),(6,174,1,500000,'Musica','Servicio de musica estereo'),(6,175,1,500000,'Musica','Servicio de musica estereo'),(6,218,1,500000,'Musica','Servicio de musica estereo'),(7,25,2,50000,'Ron','Ron viejo de caldas x1litro'),(7,27,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,31,3,50000,'Ron','Ron viejo de caldas x1litro'),(7,33,3,50000,'Ron','Ron viejo de caldas x1litro'),(7,36,3,50000,'Ron','Ron viejo de caldas x1litro'),(7,37,3,50000,'Ron','Ron viejo de caldas x1litro'),(7,38,3,50000,'Ron','Ron viejo de caldas x1litro'),(7,39,3,50000,'Ron','Ron viejo de caldas x1litro'),(7,40,3,50000,'Ron','Ron viejo de caldas x1litro'),(7,42,2,50000,'Ron','Ron viejo de caldas x1litro'),(7,43,2,50000,'Ron','Ron viejo de caldas x1litro'),(7,44,2,50000,'Ron','Ron viejo de caldas x1litro'),(7,45,2,50000,'Ron','Ron viejo de caldas x1litro'),(7,46,2,50000,'Ron','Ron viejo de caldas x1litro'),(7,47,2,50000,'Ron','Ron viejo de caldas x1litro'),(7,48,2,50000,'Ron','Ron viejo de caldas x1litro'),(7,49,3,50000,'Ron','Ron viejo de caldas x1litro'),(7,51,77,50000,'Ron','Ron viejo de caldas x1litro'),(7,52,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,55,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,57,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,61,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,63,2,50000,'Ron','Ron viejo de caldas x1litro'),(7,65,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,66,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,67,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,68,2,50000,'Ron','Ron viejo de caldas x1litro'),(7,77,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,78,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,79,2,50000,'Ron','Ron viejo de caldas x1litro'),(7,81,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,82,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,84,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,85,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,86,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,87,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,88,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,89,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,90,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,91,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,92,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,93,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,94,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,95,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,96,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,97,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,98,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,99,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,100,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,101,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,103,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,104,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,105,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,106,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,107,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,108,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,109,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,110,2,50000,'Ron','Ron viejo de caldas x1litro'),(7,111,2,50000,'Ron','Ron viejo de caldas x1litro'),(7,112,2,50000,'Ron','Ron viejo de caldas x1litro'),(7,113,3,50000,'Ron','Ron viejo de caldas x1litro'),(7,114,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,115,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,116,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,118,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,120,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,121,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,125,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,128,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,132,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,136,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,138,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,146,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,147,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,152,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,153,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,154,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,155,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,156,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,158,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,159,2,50000,'Ron','Ron viejo de caldas x1litro'),(7,167,2,50000,'Ron','Ron viejo de caldas x1litro'),(7,168,1,50000,'Ron','Ron viejo de caldas x1litro'),(7,171,3,50000,'Ron','Ron viejo de caldas x1litro'),(7,175,3,50000,'Ron','Ron viejo de caldas x1litro'),(7,218,1,50000,'Ron','Ron viejo de caldas x1litro'),(8,25,3,100000,'Luces led','Luces por todo el evento'),(8,31,1,100000,'Luces led','Luces por todo el evento'),(8,33,3,100000,'Luces led','Luces por todo el evento'),(8,35,2,100000,'Luces led','Luces por todo el evento'),(8,36,2,100000,'Luces led','Luces por todo el evento'),(8,37,2,100000,'Luces led','Luces por todo el evento'),(8,38,2,100000,'Luces led','Luces por todo el evento'),(8,39,2,100000,'Luces led','Luces por todo el evento'),(8,40,2,100000,'Luces led','Luces por todo el evento'),(8,55,1,100000,'Luces led','Luces por todo el evento'),(8,61,1,100000,'Luces led','Luces por todo el evento'),(8,63,1,100000,'Luces led','Luces por todo el evento'),(8,65,1,100000,'Luces led','Luces por todo el evento'),(8,66,1,100000,'Luces led','Luces por todo el evento'),(8,67,1,100000,'Luces led','Luces por todo el evento'),(8,68,1,100000,'Luces led','Luces por todo el evento'),(8,69,2,100000,'Luces led','Luces por todo el evento'),(8,77,1,100000,'Luces led','Luces por todo el evento'),(8,79,1,100000,'Luces led','Luces por todo el evento'),(8,87,1,100000,'Luces led','Luces por todo el evento'),(8,93,1,100000,'Luces led','Luces por todo el evento'),(8,94,1,100000,'Luces led','Luces por todo el evento'),(8,114,1,100000,'Luces led','Luces por todo el evento'),(8,115,2,100000,'Luces led','Luces por todo el evento'),(8,116,1,100000,'Luces led','Luces por todo el evento'),(8,120,1,100000,'Luces led','Luces por todo el evento'),(8,127,1,100000,'Luces led','Luces por todo el evento'),(8,130,1,100000,'Luces led','Luces por todo el evento'),(8,131,1,100000,'Luces led','Luces por todo el evento'),(8,132,1,100000,'Luces led','Luces por todo el evento'),(8,145,1,100000,'Luces led','Luces por todo el evento'),(8,150,1,100000,'Luces led','Luces por todo el evento'),(8,175,2,100000,'Luces led','Luces por todo el evento'),(8,218,1,100000,'Luces led','Luces por todo el evento'),(9,25,1,50000,'Vino tinto','Botella de vino tinto x3litros'),(9,31,1,50000,'Vino tinto','Botella de vino tinto x3litros'),(9,33,7,50000,'Vino claro','Botella de vino tinto x3litros'),(9,35,1,50000,'Vino tinto','Botella de vino tinto x3litros'),(9,51,1,50000,'Vino tinto','Botella de vino tinto x3litros'),(9,84,1,50000,'Vino tinto','                    Botella de vino tinto x3 litros aÃ±eja\r\n                '),(9,113,1,50000,'Vino tinto','                    Botella de vino tinto x3 litros aÃ±eja\r\n                '),(9,116,1,50000,'Vino tinto','                    Botella de vino tinto x3 litros aÃ±eja\r\n                '),(9,123,1,50000,'Vino tinto','                    Botella de vino tinto x3 litros aÃ±eja\r\n                '),(9,125,1,50000,'Vino tinto','                    Botella de vino tinto x3 litros aÃ±eja\r\n                '),(9,135,1,50000,'Vino tinto','                    Botella de vino tinto x3 litros aÃ±eja\r\n                '),(9,144,1,50000,'Vino tinto','                    Botella de vino tinto x3 litros aÃ±eja\r\n                '),(9,175,2,50000,'Vino tinto','                    Botella de vino tinto x3 litros aÃ±eja\r\n                '),(10,26,5,40000,'Piscina para niÃ±os','Una piscina inflable para los mas pequeÃ±os'),(10,28,3,40000,'Piscina para niÃ±os','Una piscina inflable para los mas pequeÃ±os'),(10,29,1,40000,'Piscina para niÃ±os','Una piscina inflable para los mas pequeÃ±os'),(10,50,1,40000,'Piscina para niÃ±os','Una piscina inflable para los mas pequeÃ±os'),(11,26,1,2000000,'Bufet','Una barra todo incluido donde las personas podran sacar toda la comida que quieran '),(11,28,1,2000000,'Bufet','Una barra todo incluido donde las personas podran sacar toda la comida que quieran '),(11,29,2,2000000,'Bufet','Una barra todo incluido donde las personas podran sacar toda la comida que quieran '),(11,32,3,2000000,'Bufet','Una barra todo incluido donde las personas podran sacar toda la comida que quieran '),(11,50,1,2000000,'Bufet','Una barra todo incluido donde las personas podran sacar toda la comida que quieran '),(12,29,2,50000,'Ron medellin','Una botella de ron MedellÃ­n aÃ±ejo'),(12,32,2,50000,'Ron medellin','Una botella de ron MedellÃ­n aÃ±ejo'),(12,50,1,50000,'Ron medellin','Una botella de ron MedellÃ­n aÃ±ejo'),(13,29,1,2000,'Gomas','Paquete de gomas que van a estar en una mesa con tazas '),(13,32,2,2000,'Gomas','Paquete de gomas que van a estar en una mesa con tazas '),(14,32,1,50000,'Fuente de chocolate','Una fuente de chocolate caliente acompaÃ±ada con fresas'),(16,30,1,700000,'Vestido de 15s','Vestido blanco para fiesta de 15s'),(16,34,3,700000,'Vestido de 15s','Vestido blanco para fiesta de 15s'),(18,52,1,400000,'Castillo inflable','                \r\n            Un excelente castillo inflable para niÃ±os, niÃ±as y adolescentes donde hasta los mas grandes querrÃ¡n entrar'),(18,55,1,400000,'Castillo inflable','                \r\n            Un excelente castillo inflable para niÃ±os, niÃ±as y adolescentes donde hasta los mas grandes querrÃ¡n entrar'),(18,57,1,400000,'Castillo inflable','                \r\n            Un excelente castillo inflable para niÃ±os, niÃ±as y adolescentes donde hasta los mas grandes querrÃ¡n entrar'),(18,59,1,400000,'Castillo inflable','                \r\n            Un excelente castillo inflable para niÃ±os, niÃ±as y adolescentes donde hasta los mas grandes querrÃ¡n entrar'),(18,65,1,400000,'Castillo inflable','                \r\n            Un excelente castillo inflable para niÃ±os, niÃ±as y adolescentes donde hasta los mas grandes querrÃ¡n entrar'),(18,66,1,400000,'Castillo inflable','                \r\n            Un excelente castillo inflable para niÃ±os, niÃ±as y adolescentes donde hasta los mas grandes querrÃ¡n entrar'),(18,67,1,400000,'Castillo inflable','                \r\n            Un excelente castillo inflable para niÃ±os, niÃ±as y adolescentes donde hasta los mas grandes querrÃ¡n entrar'),(18,68,2,400000,'Castillo inflable','                \r\n            Un excelente castillo inflable para niÃ±os, niÃ±as y adolescentes donde hasta los mas grandes querrÃ¡n entrar'),(18,69,1,400000,'Castillo inflable','                \r\n            Un excelente castillo inflable para niÃ±os, niÃ±as y adolescentes donde hasta los mas grandes querrÃ¡n entrar'),(18,77,1,400000,'Castillo inflable','                \r\n            Un excelente castillo inflable para niÃ±os, niÃ±as y adolescentes donde hasta los mas grandes querrÃ¡n entrar'),(18,81,1,400000,'Castillo inflable','                \r\n            Un excelente castillo inflable para niÃ±os, niÃ±as y adolescentes donde hasta los mas grandes querrÃ¡n entrar'),(18,82,1,400000,'Castillo inflable','                \r\n            Un excelente castillo inflable para niÃ±os, niÃ±as y adolescentes donde hasta los mas grandes querrÃ¡n entrar'),(18,83,1,400000,'Castillo inflable','                \r\n            Un excelente castillo inflable para niÃ±os, niÃ±as y adolescentes donde hasta los mas grandes querrÃ¡n entrar'),(18,85,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,86,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,87,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,88,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,89,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,91,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,92,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,93,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,94,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,95,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,101,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,103,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,104,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,105,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,106,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,107,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,108,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,109,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,110,2,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,111,2,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,112,2,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,113,2,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,117,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,126,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,171,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(18,175,1,500000,'Castillo inflable','                    Un castillo genial\r\n                \r\n                \r\n                '),(19,124,1,3000,'Coca cola','                                    \r\n            Una coca cola normal\r\n                '),(19,126,1,3000,'Coca cola','                                    \r\n            Una coca cola normal\r\n                '),(19,133,1,3000,'Coca cola','                                    \r\n            Una coca cola normal\r\n                '),(19,134,1,3000,'Coca cola','                                    \r\n            Una coca cola normal\r\n                '),(19,171,1,3000,'Coca cola','                                    \r\n            Una coca cola normal\r\n                '),(19,175,2,3000,'Coca cola','                                    \r\n            Una coca cola normal\r\n                '),(21,176,2,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,177,2,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,178,2,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,179,2,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,182,1,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,184,1,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,186,1,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,188,2,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,190,1,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,192,1,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,193,1,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,195,1,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,197,2,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,198,2,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,199,1,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,201,1,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,203,1,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,205,1,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,206,1,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,207,1,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,209,2,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,211,1,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,212,1,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,214,1,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,216,1,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(21,217,2,600000,'Castillo inflable','                                    \r\n        Un castillo inflable para que los invitados jueguen \r\n                '),(22,177,1,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(22,178,1,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(22,180,1,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(22,181,1,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(22,183,2,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(22,185,2,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(22,187,2,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(22,189,2,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(22,191,1,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(22,194,1,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(22,196,1,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(22,197,1,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(22,198,1,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(22,200,1,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(22,202,1,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(22,204,1,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(22,205,1,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(22,208,1,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(22,210,1,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(22,213,1,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(22,215,1,5000,'Coca cola','                \r\n        Coca cola x3 litros'),(23,219,1,5000,'Sillas','                \r\n        Sillas blancas muy comodas'),(23,220,10,5000,'Sillas','                \r\n        Sillas blancas muy comodas'),(23,221,1,5000,'Sillas','                \r\n        Sillas blancas muy comodas'),(23,222,4,5000,'Sillas','                \r\n        Sillas blancas muy comodas'),(24,219,1,700000,'Musica','Servicios de musica estere por todo el evento                \r\n        '),(24,220,1,700000,'Musica','Servicios de musica estere por todo el evento                \r\n        '),(24,221,1,700000,'Musica','Servicios de musica estere por todo el evento                \r\n        '),(24,222,1,700000,'Musica','Servicios de musica estere por todo el evento                \r\n        '),(25,221,1,2000000,'Banquete','Banquete abierto para todo el publico                \r\n        ');
/*!40000 ALTER TABLE `tblproductosservicios_cotizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblreseniasempresa`
--

DROP TABLE IF EXISTS `tblreseniasempresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblreseniasempresa` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `docIdentidad_usuario` varchar(10) DEFAULT NULL,
  `descripcion` varchar(5000) NOT NULL,
  `fecha` datetime NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_tblResenias_tblusuarios1_idx` (`docIdentidad_usuario`),
  CONSTRAINT `fk_tblResenias_tblusuarios1` FOREIGN KEY (`docIdentidad_usuario`) REFERENCES `tblusuarios` (`docIdentidad`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblreseniasempresa`
--

LOCK TABLES `tblreseniasempresa` WRITE;
/*!40000 ALTER TABLE `tblreseniasempresa` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblreseniasempresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblroles`
--

DROP TABLE IF EXISTS `tblroles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblroles` (
  `codigo` tinyint(4) NOT NULL AUTO_INCREMENT,
  `nombreRol` varchar(13) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblroles`
--

LOCK TABLES `tblroles` WRITE;
/*!40000 ALTER TABLE `tblroles` DISABLE KEYS */;
INSERT INTO `tblroles` VALUES (1,'Usuario'),(2,'Empresa'),(3,'Admin');
/*!40000 ALTER TABLE `tblroles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblusuarios`
--

DROP TABLE IF EXISTS `tblusuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblusuarios` (
  `docIdentidad` varchar(10) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `contrasenia` varchar(256) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `correo` varchar(76) NOT NULL,
  `avatar` varchar(260) DEFAULT NULL,
  `ciudad` varchar(7) DEFAULT NULL,
  `fecha_nacimiento` date NOT NULL,
  `rol` tinyint(4) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  PRIMARY KEY (`docIdentidad`),
  KEY `fk_tblusuarios_tblCiudad1_idx` (`ciudad`),
  KEY `fk_tblusuarios_tblroles1_idx` (`rol`),
  KEY `fk_tblusuarios_tblestado1_idx` (`estado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblusuarios`
--

LOCK TABLES `tblusuarios` WRITE;
/*!40000 ALTER TABLE `tblusuarios` DISABLE KEYS */;
INSERT INTO `tblusuarios` VALUES ('0987543','Michael','Osorio','202cb962ac59075b964b07152d234b70','3103474753','michael@michael.com','null',NULL,'2004-09-27',2,NULL),('1040','Valentina','Restrepo','202cb962ac59075b964b07152d234b70','3103474753','valen@valen.com','null',NULL,'2021-10-27',2,NULL),('1040871156','Juan Carlos','Marin','202cb962ac59075b964b07152d234b70','3103474753','juancaom12@gmail.com','null',NULL,'2021-10-19',3,NULL),('147852','Antionio','Alvarez','202cb962ac59075b964b07152d234b70','3103474753','antonio@antonio.com','null',NULL,'2021-10-14',2,NULL),('148614','Vanessa','Orozco','202cb962ac59075b964b07152d234b70','456871','vanessa@vanessa.com','null',NULL,'2021-10-20',2,NULL),('23644','Juan Carlos','Osorio Murillo','202cb962ac59075b964b07152d234b70','3103474753','juan@juan.com','null',NULL,'2021-10-12',2,NULL),('3455673','Federico','Llanos','202cb962ac59075b964b07152d234b70','3103474753','federico@federico.com','null',NULL,'2003-06-18',1,NULL),('412357','Andrea','Salvador','202cb962ac59075b964b07152d234b70','3210456','andrea@andrea.com','null',NULL,'2021-10-21',2,NULL),('5287456','Felipe','Bedoya','202cb962ac59075b964b07152d234b70','3103474753','felipe@felipe.com','null',NULL,'2021-10-21',2,NULL),('6487665','Adriana','Escobar','202cb962ac59075b964b07152d234b70','3103474753','adriana@gmail.com','null',NULL,'2004-09-27',2,NULL),('65432','Camila','Paez','202cb962ac59075b964b07152d234b70','3103474753','camila@camila.com','null',NULL,'2004-09-27',2,NULL),('7654','Ana','Salvador','202cb962ac59075b964b07152d234b70','3103474753','ana@ana.com','null',NULL,'2004-09-27',1,NULL),('7654986','Juan o','Osorio m','202cb962ac59075b964b07152d234b70','3103474753','juano@juano.com','null',NULL,'2021-11-03',2,NULL),('785691','Morgan Ferley','Freeman sanchez','202cb962ac59075b964b07152d234b70','42132554','morgan@morgan.com','null',NULL,'2021-10-21',1,NULL),('87653','Juan C','Osorio M','202cb962ac59075b964b07152d234b70','3103474753','juanc@juanc.com','null',NULL,'2021-11-18',2,NULL),('876543','Veronica','Escobar','202cb962ac59075b964b07152d234b70','12344','vero@vero.com','null',NULL,'2021-11-09',2,NULL),('98675433','Pedro','Garcia','202cb962ac59075b964b07152d234b70','31034747','pedro@pedro.com','null',NULL,'2004-09-27',2,NULL);
/*!40000 ALTER TABLE `tblusuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-23 11:51:29
