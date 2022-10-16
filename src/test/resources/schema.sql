-- -----------------------------------------------------
-- Schema ttl_banking
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ttl_banking
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ttl_banking`;
USE `ttl_banking` ;

-- -----------------------------------------------------
-- Table `ttl_banking`.`tbl_accountType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ttl_banking`.`tbl_accountType`;

CREATE TABLE IF NOT EXISTS `tbl_accountType` (
  `typeId` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `accountType` VARCHAR(45) NOT NULL);


-- -----------------------------------------------------
-- Table `ttl_banking`.`tbl_account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ttl_banking`.`tbl_account` ;

CREATE TABLE IF NOT EXISTS `ttl_banking`.`tbl_account` (
  `accountId` INT NOT NULL AUTO_INCREMENT,
  `typeId` INT NOT NULL,
  `userId` INT NOT NULL,
  `balance` DECIMAL(60,2) NULL,
  `statementDate` DATE NOT NULL,
  `closed` TINYINT NULL,
  PRIMARY KEY (`accountId`));


-- -----------------------------------------------------
-- Table `ttl_banking`.`tbl_card_network`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ttl_banking`.`tbl_card_network` ;

CREATE TABLE IF NOT EXISTS `ttl_banking`.`tbl_card_network` (
  `networkId` INT NOT NULL AUTO_INCREMENT,
  `networkName` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`networkId`));


-- -----------------------------------------------------
-- Table `ttl_banking`.`tbl_card`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ttl_banking`.`tbl_card` ;

CREATE TABLE IF NOT EXISTS `ttl_banking`.`tbl_card` (
  `cardId` INT NOT NULL AUTO_INCREMENT,
  `cardNumber` CHAR(16) NOT NULL,
  `networkId` INT NOT NULL,
  `interestAPR` DECIMAL(2,2) NULL,
  `maintenanceFee` DECIMAL(2,2) NULL,
  `limit` BIGINT NULL,
  PRIMARY KEY (`cardId`));


-- -----------------------------------------------------
-- Table `ttl_banking`.`tbl_card_account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ttl_banking`.`tbl_card_account` ;

CREATE TABLE IF NOT EXISTS `ttl_banking`.`tbl_card_account` (
  `accountId` INT NOT NULL AUTO_INCREMENT,
  `cardId` INT NOT NULL,
  PRIMARY KEY (`accountId`, `cardId`),
  CONSTRAINT `fk_cardId`
    FOREIGN KEY (`cardId`)
    REFERENCES `ttl_banking`.`tbl_card` (`cardId`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_accountId`
    FOREIGN KEY (`accountId`)
    REFERENCES `ttl_banking`.`tbl_account` (`accountId`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT);


-- -----------------------------------------------------
-- Table `ttl_banking`.`tbl_card_offer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ttl_banking`.`tbl_card_offer` ;

CREATE TABLE IF NOT EXISTS `ttl_banking`.`tbl_card_offer` (
  `offerId` INT NOT NULL  AUTO_INCREMENT,
  `startDate` DATE NULL,
  `limit` BIGINT NULL,
  `introAPR` DECIMAL(4,2) NULL,
  `annualAPR` DECIMAL(4,2) NULL,
  `maintenanceFee` DECIMAL(4,2) NULL,
  `networkId` INT NOT NULL,
  PRIMARY KEY (`offerId`),
  CONSTRAINT `fk_card_offer_card_network`
    FOREIGN KEY (`networkId`)
    REFERENCES `ttl_banking`.`tbl_card_network` (`networkId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `ttl_banking`.`tbl_signup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ttl_banking`.`tbl_signup` ;

CREATE TABLE IF NOT EXISTS `ttl_banking`.`tbl_signup` (
  `signupId` INT NOT NULL AUTO_INCREMENT,
  `offerId` INT NOT NULL,
  `userId` INT NOT NULL,
  `accepted` TINYINT NULL,
  PRIMARY KEY (`signupId`),
  CONSTRAINT `fk_signup_card_offer`
    FOREIGN KEY (`offerId`)
    REFERENCES `ttl_banking`.`tbl_card_offer` (`offerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

DROP TABLE IF EXISTS `ttl_banking`.`tbl_transaction`;

CREATE TABLE IF NOT EXISTS `ttl_banking`.`tbl_transaction` (
  `transactionId` int NOT NULL AUTO_INCREMENT,
  `amount` decimal(17,2) NOT NULL,
  `accountId` int NOT NULL,
  `reversed` int DEFAULT NULL,
  `transactionDate` date DEFAULT NULL,
  `transactionName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`transactionId`),
  CONSTRAINT `fk_transaction_account_accountId`
    FOREIGN KEY (`accountId`)
    REFERENCES `tbl_account` (`accountId`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `ttl_banking`.`tbl_stock`(
    `userId` INT NOT NULL,
    `stockId` VARCHAR(10) NOT NULL,
    `count` INT NOT NULL,
    PRIMARY KEY (`userId`, `stockId`));