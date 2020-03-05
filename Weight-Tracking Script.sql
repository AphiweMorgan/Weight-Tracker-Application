CREATE DATABASE `weighttrackingdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;


USE `weighttrackingdb`;

CREATE TABLE `client` (
  `idClient` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Surname` varchar(45) NOT NULL,
  `Phone_No` bigint NOT NULL,
  `HealthRating` decimal(3,2) NOT NULL,
  `Passcode` varchar(255) NOT NULL,
  PRIMARY KEY (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `weight` (
  `idWeight` int NOT NULL AUTO_INCREMENT,
  `idClient` int NOT NULL,
  `Date` date NOT NULL,
  `Weight` double NOT NULL,
  PRIMARY KEY (`idWeight`),
  KEY `idClient_idx` (`idClient`) /*!80000 INVISIBLE */,
  CONSTRAINT `idClient` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
