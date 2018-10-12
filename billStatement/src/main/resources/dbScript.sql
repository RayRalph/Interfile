drop table `interfile`.`ADDRESS`;
drop table `interfile`.`BILL`;
drop table `interfile`.`CONTACT`;
drop table `interfile`.`BILL_STATEMENT`;
drop table `interfile`.`ACCOUNT`;

CREATE TABLE IF NOT EXISTS `interfile`.`ACCOUNT` (
  `ACCOUNT_NO` BIGINT NOT NULL,
  `ACCOUNT_HOLDER_NAME` VARCHAR(65) NOT NULL,
  `ACCOUNT_HOLDER_ID_NO` BIGINT NOT NULL,
  PRIMARY KEY (`ACCOUNT_NO`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `interfile`.`ADDRESS` (
  `id` INT AUTO_INCREMENT,
  `ADDRESS_LINE1` VARCHAR(45) NOT NULL,
  `ADDRESS_LINE2` VARCHAR(45) NOT NULL,
  `ADDRESS_LINE3` VARCHAR(45) NOT NULL,
  `POSTAL_CODE` BIGINT NOT NULL,
  `ACCOUNT_ID` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ADDRESS_ACCOUNT_idx` (`ACCOUNT_ID` ASC),
  CONSTRAINT `fk_ADDRESS_ACCOUNT`
    FOREIGN KEY (`ACCOUNT_ID`)
    REFERENCES `interfile`.`ACCOUNT` (`ACCOUNT_NO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    auto_increment = 1
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `interfile`.`BILL` (
  `id` INT AUTO_INCREMENT,
  `ACCOUNT_ID` BIGINT NOT NULL,
  `BILL_DUE_DATE` DATE NOT NULL,
  `BILL_DATE` DATE NOT NULL,
  `BILL_CHARGES` DECIMAL NOT NULL,
  `BILL_OUTSTANDING` DECIMAL NOT NULL,
  `BILL_PERIOD` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_BILL_ACCOUNT_idx` (`ACCOUNT_ID` ASC),
  CONSTRAINT `fk_BILL_ACCOUNT`
    FOREIGN KEY (`ACCOUNT_ID`)
    REFERENCES `interfile`.`ACCOUNT` (`ACCOUNT_NO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
auto_increment = 1
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `interfile`.`CONTACT` (
  `id` INT AUTO_INCREMENT,
  `PHONE_HOME` VARCHAR(10) NOT NULL,
  `PHONE_MOBILE` BIGINT NOT NULL,
  `PHONE_WORK` VARCHAR(10) NOT NULL,
  `ACCOUNT_ID` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CONTACT_ACCOUNT_idx` (`ACCOUNT_ID` ASC),
  CONSTRAINT `fk_CONTACT_ACCOUNT`
    FOREIGN KEY (`ACCOUNT_ID`)
    REFERENCES `interfile`.`ACCOUNT` (`ACCOUNT_NO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
auto_increment = 1;
CREATE TABLE IF NOT EXISTS `interfile`.`BILL_STATEMENT` (
  `id` INT AUTO_INCREMENT,
  `ACCOUNT_ID` BIGINT NOT NULL,
  `STATEMENT_ID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_STATEMENT_ACCOUNT_idx` (`ACCOUNT_ID` ASC),
  CONSTRAINT `fk_STATEMENT_ACCOUNT`
    FOREIGN KEY (`ACCOUNT_ID`)
    REFERENCES `interfile`.`ACCOUNT` (`ACCOUNT_NO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
auto_increment = 1
ENGINE = InnoDB;

ALTER TABLE `interfile`.`ADDRESS` ADD INDEX (id);
ALTER TABLE `interfile`.`BILL` ADD INDEX (id);
ALTER TABLE `interfile`.`CONTACT` ADD INDEX (id);
ALTER TABLE `interfile`.`BILL_STATEMENT` ADD INDEX (id);