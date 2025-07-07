-- --------------------------------------------------------
-- 호스트:                          db.tjoeun.midproject.o-r.kr
-- 서버 버전:                        10.11.8-MariaDB-0ubuntu0.24.04.1 - Ubuntu 24.04
-- 서버 OS:                        debian-linux-gnu
-- HeidiSQL 버전:                  12.7.0.6850
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- tjoeun 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `tjoeun` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `tjoeun`;

-- 테이블 tjoeun.board 구조 내보내기
CREATE TABLE IF NOT EXISTS `board` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `board_id` tinyint(1) NOT NULL,
  `title` varchar(200) NOT NULL,
  `content` longtext DEFAULT NULL,
  `date` date NOT NULL DEFAULT current_timestamp(),
  `user` int(11) NOT NULL,
  `file` varchar(100) DEFAULT NULL,
  `hits` int(11) NOT NULL DEFAULT 0,
  `is_notice` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idx`),
  KEY `fk_board_boardinfo` (`board_id`),
  KEY `fk_board_user_idx` (`user`),
  CONSTRAINT `fk_board_boardinfo` FOREIGN KEY (`board_id`) REFERENCES `boardinfo` (`board_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_board_user_idx` FOREIGN KEY (`user`) REFERENCES `user` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=387 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 tjoeun.boardinfo 구조 내보내기
CREATE TABLE IF NOT EXISTS `boardinfo` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `board_id` tinyint(1) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `uk_boardinfo_boardid` (`board_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 tjoeun.favorite 구조 내보내기
CREATE TABLE IF NOT EXISTS `favorite` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `user_idx` int(11) NOT NULL,
  `board_idx` int(11) NOT NULL,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `uk_favorite_user_board` (`user_idx`,`board_idx`),
  KEY `fk_favorite_board` (`board_idx`),
  CONSTRAINT `fk_favorite_board` FOREIGN KEY (`board_idx`) REFERENCES `board` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_favorite_user` FOREIGN KEY (`user_idx`) REFERENCES `user` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 tjoeun.file 구조 내보내기
CREATE TABLE IF NOT EXISTS `file` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `originalname` varchar(255) NOT NULL,
  `servername` varchar(60) NOT NULL,
  `board_idx` int(11) NOT NULL,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `uk_servername` (`servername`),
  KEY `fk_file_board` (`board_idx`),
  CONSTRAINT `fk_file_board` FOREIGN KEY (`board_idx`) REFERENCES `board` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 tjoeun.reply 구조 내보내기
CREATE TABLE IF NOT EXISTS `reply` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `user_idx` int(11) NOT NULL,
  `board_idx` int(11) DEFAULT NULL,
  `content` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_reply_board` (`board_idx`),
  KEY `fk_reply_user` (`user_idx`),
  CONSTRAINT `fk_reply_board` FOREIGN KEY (`board_idx`) REFERENCES `board` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_reply_user` FOREIGN KEY (`user_idx`) REFERENCES `user` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=346 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 tjoeun.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `id` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `username` varchar(40) NOT NULL,
  `role` tinyint(1) NOT NULL,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `인덱스 2` (`id`),
  UNIQUE KEY `인덱스 3` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `user` (`name`, `id`, `password`, `username`, `role`) VALUES ( 
  `테스트`,
  `test`,
  `test1234`,
  `testuser`,
  2
);
-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 tjoeun.USER_SEQ 구조 내보내기
CREATE TABLE IF NOT EXISTS `USER_SEQ` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) unsigned NOT NULL,
  `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 뷰 tjoeun.v_board_user 구조 내보내기
-- VIEW 종속성 오류를 극복하기 위해 임시 테이블을 생성합니다.
CREATE TABLE `v_board_user` (
	`idx` INT(11) NOT NULL,
	`board_id` TINYINT(1) NOT NULL,
	`title` VARCHAR(200) NOT NULL COLLATE 'utf8mb4_general_ci',
	`content` LONGTEXT NULL COLLATE 'utf8mb4_general_ci',
	`date` VARCHAR(10) NULL COLLATE 'utf8mb4_general_ci',
	`user` INT(11) NOT NULL,
	`file` VARCHAR(100) NULL COLLATE 'utf8mb4_general_ci',
	`hits` INT(11) NOT NULL,
	`username` VARCHAR(40) NOT NULL COLLATE 'utf8mb4_general_ci',
	`id` VARCHAR(20) NOT NULL COLLATE 'utf8mb4_general_ci',
	`favorite` BIGINT(21) NULL,
	`is_notice` TINYINT(1) NOT NULL
) ENGINE=MyISAM;

-- 뷰 tjoeun.v_reply_user 구조 내보내기
-- VIEW 종속성 오류를 극복하기 위해 임시 테이블을 생성합니다.
CREATE TABLE `v_reply_user` (
	`idx` INT(11) NOT NULL,
	`user_idx` INT(11) NOT NULL,
	`board_idx` INT(11) NULL,
	`content` VARCHAR(300) NULL COLLATE 'utf8mb4_general_ci',
	`username` VARCHAR(40) NOT NULL COLLATE 'utf8mb4_general_ci'
) ENGINE=MyISAM;

-- 임시 테이블을 제거하고 최종 VIEW 구조를 생성
DROP TABLE IF EXISTS `v_board_user`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `v_board_user` AS select `a`.`idx` AS `idx`,`a`.`board_id` AS `board_id`,`a`.`title` AS `title`,`a`.`content` AS `content`,date_format(`a`.`date`,'%Y-%m-%d') AS `date`,`a`.`user` AS `user`,`a`.`file` AS `file`,`a`.`hits` AS `hits`,`b`.`username` AS `username`,`b`.`id` AS `id`,(select count(0) from `favorite` where `favorite`.`board_idx` = `a`.`idx`) AS `favorite`,`a`.`is_notice` AS `is_notice` from (`board` `a` join `user` `b` on(`a`.`user` = `b`.`idx`));

-- 임시 테이블을 제거하고 최종 VIEW 구조를 생성
DROP TABLE IF EXISTS `v_reply_user`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `v_reply_user` AS select `r`.`idx` AS `idx`,`r`.`user_idx` AS `user_idx`,`r`.`board_idx` AS `board_idx`,`r`.`content` AS `content`,`u`.`username` AS `username` from (`reply` `r` join `user` `u` on(`r`.`user_idx` = `u`.`idx`));

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
