-- MySQL Workbench Synchronization
-- Generated: 2019-07-20 10:20
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Laercio

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `sara`.`OcorrenciaVoo` 
DROP COLUMN `TipoAeronave_idTipoAeronave`,
ADD COLUMN `TipoAeronave_idTipoAeronave` TIME NULL DEFAULT NULL AFTER `data`;

ALTER TABLE `sara`.`ProprietarioParticular` 
ADD INDEX `fk_ProprietarioParticular_Proprietario1_idx` (`Proprietario_idProprietario` ASC),
DROP INDEX `fk_ProprietarioParticular_Proprietario1_idx` ;

ALTER TABLE `sara`.`ProprietarioCiaAerea` 
ADD INDEX `fk_ProprietarioCiaAerea_Proprietario1_idx` (`Proprietario_idProprietario` ASC),
DROP INDEX `fk_ProprietarioCiaAerea_Proprietario1_idx` ;

ALTER TABLE `sara`.`Pista` 
DROP COLUMN `estaEmUso`,
DROP COLUMN `idRecurso`,
DROP COLUMN `idPista`,
ADD COLUMN `idPista` INT(11) NOT NULL FIRST,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`idPista`, `Recurso_idRecurso`),
ADD INDEX `fk_Pista_Recurso1_idx` (`Recurso_idRecurso` ASC),
DROP INDEX `fk_idRecurso_idx` ;

ALTER TABLE `sara`.`PosicaoPatio` 
DROP COLUMN `estaEmUso`,
DROP COLUMN `idRecurso`,
DROP COLUMN `idPosicaoPatio`,
ADD COLUMN `idPosicaoPatio` INT(11) NOT NULL FIRST,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`idPosicaoPatio`),
ADD INDEX `fk_PosicaoPatio_Recurso1_idx` (`idPosicaoPatio` ASC),
DROP INDEX `fk_PosicaoPatio_Recurso1_idx` ;

ALTER TABLE `sara`.`PortaoEmbarque` 
DROP COLUMN `estaEmUso`,
DROP COLUMN `idRecurso`,
CHANGE COLUMN `idPortaoEmbarque` `idPortaoEmbarque` INT(11) NOT NULL ,
DROP INDEX `fk_PortaoEmbarque_Recurso1_idx` ;

ALTER TABLE `sara`.`Esteira` 
DROP COLUMN `estaEmUso`,
DROP COLUMN `idRecurso`,
CHANGE COLUMN `idEsteiraDeBagagem` `idEsteiraDeBagagem` INT(11) NOT NULL ,
ADD COLUMN `Recurso_idRecurso` INT(11) NOT NULL AFTER `idEsteiraDeBagagem`,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`idEsteiraDeBagagem`, `Recurso_idRecurso`),
ADD INDEX `fk_Esteira_Recurso1_idx` (`Recurso_idRecurso` ASC),
DROP INDEX `idRecurso_idx` ;

ALTER TABLE `sara`.`PosicaoHeliponto` 
DROP COLUMN `estaEmUso`,
DROP COLUMN `idRecurso`,
DROP COLUMN `idPosicaoHeliponto`,
ADD COLUMN `Recurso_idRecurso` INT(11) NOT NULL FIRST,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`Recurso_idRecurso`),
DROP INDEX `fk_PosicaoHeliponto_Recurso1_idx` ;

CREATE TABLE IF NOT EXISTS `sara`.`Proprietario` (
  `idProprietario` INT(11) NOT NULL AUTO_INCREMENT,
  `nomeDoProprietario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idProprietario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `sara`.`Recurso_has_Proprietario` (
  `proprietario_idProprietario` INT(11) NOT NULL,
  `recurso_idRecurso` INT(11) NOT NULL,
  `prioridade` INT(11) NULL DEFAULT NULL,
  `Proprietario_ProprietarioParticular_idProprietario` INT(11) NOT NULL,
  PRIMARY KEY (`proprietario_idProprietario`, `recurso_idRecurso`, `Proprietario_ProprietarioParticular_idProprietario`),
  INDEX `fk_Recurso_has_Proprietario_Recurso1_idx` (`recurso_idRecurso` ASC),
  INDEX `fk_Recurso_has_Proprietario_Proprietario1_idx` (`Proprietario_ProprietarioParticular_idProprietario` ASC),
  CONSTRAINT `fk_Recurso_has_Proprietario_Recurso1`
    FOREIGN KEY (`recurso_idRecurso`)
    REFERENCES `sara`.`Recurso` (`idRecurso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Recurso_has_Proprietario_Proprietario1`
    FOREIGN KEY (`Proprietario_ProprietarioParticular_idProprietario`)
    REFERENCES `sara`.`Proprietario` (`idProprietario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `sara`.`ProprietarioParticular` 
ADD CONSTRAINT `fk_ProprietarioParticular_Proprietario1`
  FOREIGN KEY (`Proprietario_idProprietario`)
  REFERENCES `sara`.`Proprietario` (`idProprietario`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sara`.`ProprietarioCiaAerea` 
ADD CONSTRAINT `fk_ProprietarioCiaAerea_Proprietario1`
  FOREIGN KEY (`Proprietario_idProprietario`)
  REFERENCES `sara`.`Proprietario` (`idProprietario`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sara`.`Recurso` 
ADD CONSTRAINT `fk_Recurso_PortaoEmbarque1`
  FOREIGN KEY (`idRecurso`)
  REFERENCES `sara`.`PortaoEmbarque` (`idPortaoEmbarque`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sara`.`Pista` 
ADD CONSTRAINT `fk_Pista_Recurso`
  FOREIGN KEY (`Recurso_idRecurso`)
  REFERENCES `sara`.`Recurso` (`idRecurso`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sara`.`PosicaoPatio` 
ADD CONSTRAINT `fk_PosicaoPatio_Recurso1`
  FOREIGN KEY (`idPosicaoPatio`)
  REFERENCES `sara`.`Recurso` (`idRecurso`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sara`.`Esteira` 
ADD CONSTRAINT `fk_Esteira_Recurso1`
  FOREIGN KEY (`Recurso_idRecurso`)
  REFERENCES `sara`.`Recurso` (`idRecurso`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sara`.`PosicaoHeliponto` 
ADD CONSTRAINT `fk_PosicaoHeliponto_Recurso1`
  FOREIGN KEY (`Recurso_idRecurso`)
  REFERENCES `sara`.`Recurso` (`idRecurso`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


-- -----------------------------------------------------
-- Placeholder table for view `sara`.`portao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sara`.`portao` (`idPosicaoPatio` INT, `idRecurso` INT, `nomePosicao` INT, `comprimentoToleravel` INT, `envergaduraToleravel` INT, `aeronaveCritica` INT, `estaEmUso` INT);


USE `sara`;

-- -----------------------------------------------------
-- View `sara`.`portao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sara`.`portao`;
DROP VIEW IF EXISTS `sara`.`portao` ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
