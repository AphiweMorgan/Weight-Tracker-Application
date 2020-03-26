CREATE DATABASE `weighttrackingdb`;


USE `weighttrackingdb`;

CREATE TABLE `client` (
  `idClient` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Surname` varchar(45) NOT NULL,
  `Phone_No` bigint NOT NULL,
  `HealthRating` decimal(3,2) NOT NULL,
  `Passcode` varchar(255) NOT NULL,
  PRIMARY KEY (`idClient`)
);

CREATE TABLE `weight` (
  `idWeight` int NOT NULL AUTO_INCREMENT,
  `idClient` int NOT NULL,
  `Date` date NOT NULL,
  `Weight` double NOT NULL,
  PRIMARY KEY (`idWeight`),
  KEY `idClient_idx` (`idClient`) /*!80000 INVISIBLE */,
  CONSTRAINT `idClient` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`)
);
