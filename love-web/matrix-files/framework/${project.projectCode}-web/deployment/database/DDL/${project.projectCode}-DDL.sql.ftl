CREATE DATABASE `${project.projectCode}db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `${project.projectCode}db`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `userName` VARCHAR(40) NOT NULL ,
  `pwd` VARCHAR(24) NOT NULL ,
  `nickName` VARCHAR(40) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = ndbcluster
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `${project.projectCode}db`.`family` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `familyName` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(200) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = ndbcluster
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `${project.projectCode}db`.`roles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `roleName` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = ndbcluster
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `${project.projectCode}db`.`userFamilyRef` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `userId` INT(11) NOT NULL ,
  `familyId` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = ndbcluster
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `guarddb`.`userRoleRef` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `userId` INT(11) NOT NULL ,
  `roleId` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = ndbcluster
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `${project.projectCode}db`.`roleModuleRef` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `roleId` INT NOT NULL ,
  `moduleId` INT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = ndbcluster
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `${project.projectCode}db`.`sys_config` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `module_id` INT NOT NULL ,
  `key` VARCHAR(45) NOT NULL ,
  `value` VARCHAR(100) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = ndbcluster
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `${project.projectCode}db`.`sysModule` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `moduleName` VARCHAR(45) NOT NULL ,
  `picture` VARCHAR(100) NULL ,
  `text1` VARCHAR(45) NULL ,
  `text2` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = ndbcluster
DEFAULT CHARACTER SET = utf8;