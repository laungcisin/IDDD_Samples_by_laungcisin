DROP DATABASE IF EXISTS iddd_iam;
CREATE DATABASE iddd_iam;
USE iddd_iam;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS tbl_group;
CREATE TABLE `tbl_group` (
  `id`                  BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `description`         VARCHAR(250) NOT NULL,
  `name`                VARCHAR(100) NOT NULL,
  `tenant_id_id`        VARCHAR(36)  NOT NULL,
  `concurrency_version` INT(11)      NOT NULL,
  KEY `k_tenant_id_id` (`tenant_id_id`),
  UNIQUE KEY `k_tenant_id_name` (`name`, `tenant_id_id`),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

DROP TABLE IF EXISTS tbl_group_member;
CREATE TABLE `tbl_group_member` (
  `id`           BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `name`         VARCHAR(100) NOT NULL,
  `tenant_id_id` VARCHAR(36)  NOT NULL,
  `type`         VARCHAR(5)   NOT NULL,
  `group_id`     BIGINT(20)   NOT NULL,
  KEY `k_group_id` (`group_id`),
  KEY `k_tenant_id_id` (`tenant_id_id`),
  CONSTRAINT `fk_tbl_group_member_tbl_group` FOREIGN KEY (`group_id`) REFERENCES `tbl_group` (`id`),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

DROP TABLE IF EXISTS tbl_user;
CREATE TABLE `tbl_user` (
  `id`                    BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `enablement_enabled`    TINYINT(1)   NOT NULL,
  `enablement_end_date`   DATETIME,
  `enablement_start_date` DATETIME,
  `password`              VARCHAR(32)  NOT NULL,
  `tenant_id_id`          VARCHAR(36)  NOT NULL,
  `username`              VARCHAR(250) NOT NULL,
  `concurrency_version`   INT(11)      NOT NULL,
  KEY `k_tenant_id_id` (`tenant_id_id`),
  UNIQUE KEY `k_tenant_id_username` (`tenant_id_id`, `username`),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

DROP TABLE IF EXISTS tbl_person;
CREATE TABLE `tbl_person` (
-- primary key is my parent's pk, which is table 'tbl_user'
  `id`                                                BIGINT(20)   NOT NULL,
  `contact_information_email_address_address`         VARCHAR(100) NOT NULL,
  `contact_information_postal_address_city`           VARCHAR(100) NOT NULL,
  `contact_information_postal_address_country_code`   VARCHAR(2)   NOT NULL,
  `contact_information_postal_address_postal_code`    VARCHAR(12)  NOT NULL,
  `contact_information_postal_address_state_province` VARCHAR(100) NOT NULL,
  `contact_information_postal_address_street_address` VARCHAR(100),
  `contact_information_primary_telephone_number`      VARCHAR(20)  NOT NULL,
  `contact_information_secondary_telephone_number`    VARCHAR(20)  NOT NULL,
  `name_first_name`                                   VARCHAR(50)  NOT NULL,
  `name_last_name`                                    VARCHAR(50)  NOT NULL,
  `tenant_id_id`                                      VARCHAR(36)  NOT NULL,
  `concurrency_version`                               INT(11)      NOT NULL,
  KEY `k_tenant_id_id` (`tenant_id_id`),
  CONSTRAINT `fk_tbl_person_tbl_user` FOREIGN KEY (`id`) REFERENCES `tbl_user` (`id`),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

DROP TABLE IF EXISTS tbl_registration_invitation;
CREATE TABLE `tbl_registration_invitation` (
  `id`                  BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `description`         VARCHAR(100) NOT NULL,
  `invitation_id`       VARCHAR(36)  NOT NULL,
  `starting_on`         DATETIME,
  `tenant_id_id`        VARCHAR(36)  NOT NULL,
  `until`               DATETIME,
  `tenant_id`           BIGINT(20)   NOT NULL,
  `concurrency_version` INT(11)      NOT NULL,
  KEY `k_tenant_id` (`tenant_id`),
  KEY `k_tenant_id_id` (`tenant_id_id`),
  UNIQUE KEY `k_invitation_id` (`invitation_id`),
  CONSTRAINT `fk_tbl_registration_invitation_tbl_tenant` FOREIGN KEY (`tenant_id`) REFERENCES `tbl_tenant` (`id`),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

DROP TABLE IF EXISTS tbl_role;
CREATE TABLE `tbl_role` (
  `id`                  BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `description`         VARCHAR(250) NOT NULL,
  `group_id`            BIGINT(20)   NOT NULL,
  `name`                VARCHAR(100) NOT NULL,
  `supports_nesting`    TINYINT(1)   NOT NULL,
  `tenant_id_id`        VARCHAR(36)  NOT NULL,
  `concurrency_version` INT(11)      NOT NULL,
  KEY `k_tenant_id_id` (`tenant_id_id`),
  UNIQUE KEY `k_tenant_id_name` (`name`, `tenant_id_id`),
  CONSTRAINT `fk_tbl_role_tbl_group` FOREIGN KEY (`group_id`) REFERENCES `tbl_group` (`id`),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

DROP TABLE IF EXISTS tbl_tenant;
CREATE TABLE `tbl_tenant` (
  `id`                  BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `active`              TINYINT(1)   NOT NULL,
  `description`         VARCHAR(100),
  `name`                VARCHAR(100) NOT NULL,
  `tenant_id_id`        VARCHAR(36)  NOT NULL,
  `concurrency_version` INT(11)      NOT NULL,
  UNIQUE KEY `k_name` (`name`),
  UNIQUE KEY `k_tenant_id_id` (`tenant_id_id`),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;


