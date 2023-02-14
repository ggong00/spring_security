CREATE TABLE IF NOT EXISTS `dim_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `depth` bigint(20) DEFAULT NULL,
  `seq` bigint(20) DEFAULT NULL,
  `parent` bigint(20) DEFAULT NULL,
  `ico` varchar(255) DEFAULT NULL,
  `create_dtm` datetime DEFAULT NULL,
  `create_id` varchar(20) DEFAULT NULL,
  `update_dtm` datetime DEFAULT NULL,
  `update_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sepjbd1ucggmwwkht95y6yag5` (`url`),
  KEY `FKc6el2q77kyesu0kb3ba8hgi1f` (`parent`),
  CONSTRAINT `FKc6el2q77kyesu0kb3ba8hgi1f` FOREIGN KEY (`parent`) REFERENCES `dim_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

INSERT IGNORE INTO `dim_menu` (`id`, `url`, `name`, `depth`, `seq`, `parent`, `ico`, `create_dtm`, `create_id`, `update_dtm`, `update_id`) VALUES
	(1, '/main', '메인', NULL, 0, NULL, 'house', '2023-02-07 13:36:41', NULL, '2023-02-07 13:36:41', NULL),
	(2, '/system', '시스템관리', NULL, 1, NULL, 'gear', '2023-02-07 13:36:41', NULL, '2023-02-07 13:36:41', NULL);

CREATE TABLE IF NOT EXISTS `dim_menu_role` (
  `menu_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKdwfqb4obh5ki3nb800qgk7j7` (`role_id`),
  KEY `FKj5kb9aqkej753t7aa27o53q1c` (`menu_id`),
  CONSTRAINT `FKdwfqb4obh5ki3nb800qgk7j7` FOREIGN KEY (`role_id`) REFERENCES `dim_role` (`id`),
  CONSTRAINT `FKj5kb9aqkej753t7aa27o53q1c` FOREIGN KEY (`menu_id`) REFERENCES `dim_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT IGNORE INTO `dim_menu_role` (`menu_id`, `role_id`) VALUES
	(1, 12),
	(2, 12),
	(1, 11);

CREATE TABLE IF NOT EXISTS `dim_privilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `encoded_nm` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_duosobmqdpqdc1ig50f2s91eo` (`name`),
  KEY `FKgi0hs1cpiow9t3gba0hlp34lm` (`menu_id`),
  CONSTRAINT `FKgi0hs1cpiow9t3gba0hlp34lm` FOREIGN KEY (`menu_id`) REFERENCES `dim_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

INSERT IGNORE INTO `dim_privilege` (`id`, `name`, `encoded_nm`, `comment`, `menu_id`) VALUES
	(1, 'MAIN_READ', '메인 조회', NULL, 1),
	(2, 'MAIN_WRITE', '메인 편집', NULL, 1),
	(3, 'SYSTEM_READ', '시스템관리 조회', NULL, 2),
	(4, 'SYSTEM_WRITE', '시스템관리 편집', NULL, 2);

CREATE TABLE IF NOT EXISTS `dim_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `encoded_nm` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `create_dtm` datetime DEFAULT NULL,
  `create_id` varchar(20) DEFAULT NULL,
  `update_dtm` datetime DEFAULT NULL,
  `update_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6rewnnd2u3hj8mojsn74bb6i3` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

INSERT IGNORE INTO `dim_role` (`id`, `name`, `encoded_nm`, `comment`, `create_dtm`, `create_id`, `update_dtm`, `update_id`) VALUES
	(11, 'ROLE_USER', '일반 사용자', NULL, '2023-02-07 14:49:20', NULL, '2023-02-07 15:23:51', NULL),
	(12, 'ROLE_ADMIN', '관리자', NULL, '2023-02-07 14:49:20', NULL, '2023-02-07 15:23:51', NULL);

CREATE TABLE IF NOT EXISTS `dim_role_privilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `privilege_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1negxgyscj2bq68y58mb3hpo0` (`privilege_id`),
  KEY `FK3d1nbn2lh6a8rlxx96ubkpc52` (`role_id`),
  CONSTRAINT `FK1negxgyscj2bq68y58mb3hpo0` FOREIGN KEY (`privilege_id`) REFERENCES `dim_privilege` (`id`),
  CONSTRAINT `FK3d1nbn2lh6a8rlxx96ubkpc52` FOREIGN KEY (`role_id`) REFERENCES `dim_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8mb4;

INSERT IGNORE INTO `dim_role_privilege` (`id`, `role_id`, `privilege_id`) VALUES
	(129, 11, 1),
	(130, 12, 4),
	(131, 12, 3),
	(132, 12, 2),
	(134, 12, 1);

CREATE TABLE IF NOT EXISTS `dim_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) DEFAULT NULL,
  `password` varchar(200) NOT NULL,
  `user_nm` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `cell_no` varchar(20) DEFAULT NULL,
  `comment` text DEFAULT NULL,
  `adm_yn` varchar(1) DEFAULT NULL,
  `auth_cd` int(11) DEFAULT NULL,
  `dept_cd` varchar(20) DEFAULT NULL,
  `join_dt` char(1) DEFAULT NULL,
  `rank_cd` varchar(20) DEFAULT NULL,
  `use_yn` varchar(1) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `create_dtm` datetime DEFAULT NULL,
  `create_id` varchar(20) DEFAULT NULL,
  `update_dtm` datetime DEFAULT NULL,
  `update_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_to9e3vrvwfcb1dkcx9scp8j9t` (`user_id`),
  KEY `FKfpk1k5xh1jd8cumkkxs82yju2` (`role_id`),
  CONSTRAINT `FKfpk1k5xh1jd8cumkkxs82yju2` FOREIGN KEY (`role_id`) REFERENCES `dim_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

INSERT IGNORE INTO `dim_user` (`id`, `user_id`, `password`, `user_nm`, `email`, `cell_no`, `comment`, `adm_yn`, `auth_cd`, `dept_cd`, `join_dt`, `rank_cd`, `use_yn`, `role_id`, `create_dtm`, `create_id`, `update_dtm`, `update_id`) VALUES
	(1, 'test1', '1000:7503948023a73f707ae396050a76f19fd2960d77386d458b:bcd8bb8b8d3550a50f05587196587ba172e9008edb8c788f', NULL, 'test@naver.com', NULL, NULL, 'N', NULL, NULL, NULL, NULL, 'Y', 11, '2023-02-08 10:24:58', NULL, '2023-02-08 10:24:58', NULL);
