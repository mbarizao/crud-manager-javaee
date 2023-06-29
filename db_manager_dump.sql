-- MariaDB dump 10.19  Distrib 10.4.21-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: db_manager
-- ------------------------------------------------------
-- Server version	10.4.21-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE SCHEMA `db_manager` DEFAULT CHARACTER SET latin1 COLLATE latin1_bin;
USE db_manager;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `profissao` varchar(255) DEFAULT NULL,
  `idSetor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idSetor` (`idSetor`),
  CONSTRAINT `funcionario_ibfk_1` FOREIGN KEY (`idSetor`) REFERENCES `setor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (1,'João Silva','Vendedor',1),(2,'Maria Santos','Analista de Marketing',2),(3,'Pedro Oliveira','Analista de RH',3),(4,'Lucas Santos','Analista Financeiro',4),(5,'Gabriel Lima','Analista de TI',5),(6,'Rafaela Pereira','Analista de Logística',6),(7,'Ana Souza','Operador de Produção',7),(8,'Marcelo Fernandes','Atendente de SAC',8),(9,'Amanda Costa','Analista de Qualidade',9),(10,'Paulo Rodrigues','Gerente Administrativo',10);
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs_de_acesso`
--

DROP TABLE IF EXISTS `logs_de_acesso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logs_de_acesso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pagina` varchar(65) NOT NULL,
  `enderecoIp` varchar(15) NOT NULL,
  `usuarioId` int(11) DEFAULT NULL,
  `criadoEm` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `setor`
--

DROP TABLE IF EXISTS `setor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `setor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setor`
--

LOCK TABLES `setor` WRITE;
/*!40000 ALTER TABLE `setor` DISABLE KEYS */;
INSERT INTO `setor` VALUES (1,'Vendas'),(2,'Marketing'),(3,'Recursos Humanos'),(4,'Financeiro'),(5,'Tecnologia da Informação'),(6,'Logística'),(7,'Produção'),(8,'Atendimento ao Cliente'),(9,'Qualidade'),(10,'Administração');
/*!40000 ALTER TABLE `setor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  `endereco` varchar(100) NOT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(32) NOT NULL,
  `criadoEm` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Lucas Oliveira','Rua dos Flores, 123','lucas.oliveira@example.com','senhalucas123','2023-05-20 19:54:12'),(2,'Fernanda Santos','Av. das Palmeiras, 456','fernanda.santos@example.com','senhafernanda456','2023-05-20 19:54:12'),(3,'Mateus Lima','Travessa dos Pinheiros, 789','mateus.lima@example.com','senhamateus789','2023-05-20 19:54:12'),(4,'Camila Silva','Rua das Acácias, 987','camila.silva@example.com','senhacamila987','2023-05-20 19:54:12'),(5,'André Rodrigues','Av. dos Ipês, 654','andre.rodrigues@example.com','senhaandre654','2023-05-20 19:54:12'),(6,'Juliana Costa','Rua dos Cravos, 321','juliana.costa@example.com','senhajuliana321','2023-05-20 19:54:12'),(7,'Guilherme Fernandes','Travessa das Margaridas, 789','guilherme.fernandes@example.com','senhaguilherme789','2023-05-20 19:54:12'),(8,'Laura Oliveira','Av. das Rosas, 456','laura.oliveira@example.com','senhalaura456','2023-05-20 19:54:12'),(9,'Rafaela Santos','Rua das Orquídeas, 987','rafaela.santos@example.com','senharafaela987','2023-05-20 19:54:12'),(10,'Thiago Lima','Travessa dos Lírios, 654','thiago.lima@example.com','senhathiago654','2023-05-20 19:54:12'),(11,'Marllon Barizão','Rua Augusto Bernardo de Paula','mbarizao07@gmail.com','mbarizao','2023-05-20 19:54:12');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-22 10:34:23
