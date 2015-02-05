DROP DATABASE IF EXISTS iddd_common_test;
CREATE DATABASE iddd_common_test;
USE iddd_common_test;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS tbl_testable_time_constrained_process;
CREATE TABLE `tbl_testable_time_constrained_process` (
  `id`                      BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `allowable_duration`      BIGINT(20)  NOT NULL,
  `confirm1`                TINYINT(1)  NOT NULL,
  `confirm2`                TINYINT(1)  NOT NULL,
  `description`             VARCHAR(200),
  `process_id_id`           VARCHAR(36) NOT NULL,
  `process_completion_type` VARCHAR(50) NOT NULL,
  `start_time`              DATETIME    NOT NULL,
  `tenant_id`               VARCHAR(36) NOT NULL,
  `timed_out_date`          DATETIME,
  `concurrency_version`     INT(11)     NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;
