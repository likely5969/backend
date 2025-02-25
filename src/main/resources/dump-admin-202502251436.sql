-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: admin
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_api`
--

DROP TABLE IF EXISTS `tb_api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_api` (
  `api_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'API 정보아이디',
  `api_url` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'API주소',
  PRIMARY KEY (`api_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_api`
--

LOCK TABLES `tb_api` WRITE;
/*!40000 ALTER TABLE `tb_api` DISABLE KEYS */;
INSERT INTO `tb_api` VALUES (1,'/auth/main'),(2,'/auth/test'),(3,'/board/list'),(4,'/board/write'),(5,'/board/writeAction');
/*!40000 ALTER TABLE `tb_api` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_article`
--

DROP TABLE IF EXISTS `tb_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_article` (
  `article_id` bigint NOT NULL AUTO_INCREMENT COMMENT '게시글ID',
  `article_content` varchar(600) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '게시글내용',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `reg_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '등록자',
  `article_title` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '게시글제목',
  `upd_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '등록자',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `board_id` bigint NOT NULL COMMENT '게시판ID',
  PRIMARY KEY (`article_id`),
  KEY `FKoanolkhu5fnhr4ep0ijocr2y9` (`board_id`),
  CONSTRAINT `FKoanolkhu5fnhr4ep0ijocr2y9` FOREIGN KEY (`board_id`) REFERENCES `tb_board` (`board_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_article`
--

LOCK TABLES `tb_article` WRITE;
/*!40000 ALTER TABLE `tb_article` DISABLE KEYS */;
INSERT INTO `tb_article` VALUES (1,'내용1','2025-02-18 22:59:58','admin','기사제목1','admin','2025-02-18 22:59:58',1),(3,'내용3','2025-02-18 22:59:59','admin','기사제목3','admin','2025-02-18 22:59:59',1),(4,'내용4','2025-02-18 22:59:59','admin','기사제목4','admin','2025-02-18 22:59:59',1),(5,'내용5','2025-02-18 22:59:59','admin','기사제목5','admin','2025-02-18 22:59:59',1),(6,'내용6','2025-02-18 22:59:59','admin','기사제목6','admin','2025-02-18 22:59:59',1),(7,'내용7','2025-02-18 22:59:59','admin','기사제목7','admin','2025-02-18 22:59:59',1),(8,'112222222222222','2025-02-25 04:58:52','yellowplace','11111111111','yellowplace','2025-02-25 04:58:52',1),(9,'vzxcvzxcvz','2025-02-25 04:58:52','yellowplace','zxcvzxcvzxc','yellowplace','2025-02-25 04:58:52',1),(10,'vzxcvzxcvz','2025-02-25 04:58:52','yellowplace','zxcvzxcvzxc','yellowplace','2025-02-25 04:58:52',1),(11,'112222222','2025-02-25 04:58:52','yellowplace','111111','yellowplace','2025-02-25 04:58:52',1),(12,'123123123','2025-02-25 04:58:52','yellowplace','123123132','yellowplace','2025-02-25 04:58:52',1),(13,'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ','2025-02-25 04:58:52','yellowplace','ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ','yellowplace','2025-02-25 04:58:52',1);
/*!40000 ALTER TABLE `tb_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_board`
--

DROP TABLE IF EXISTS `tb_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_board` (
  `board_id` bigint NOT NULL AUTO_INCREMENT COMMENT '게시판ID',
  `board_nm` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '게시판명',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `reg_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '등록자',
  `upd_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '등록자',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`board_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_board`
--

LOCK TABLES `tb_board` WRITE;
/*!40000 ALTER TABLE `tb_board` DISABLE KEYS */;
INSERT INTO `tb_board` VALUES (1,'게시판A','2025-02-18 22:58:16','admin','admin','2025-02-18 22:58:16'),(2,'게시판B','2025-02-18 22:58:16','admin','admin','2025-02-18 22:58:16');
/*!40000 ALTER TABLE `tb_board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_member`
--

DROP TABLE IF EXISTS `tb_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_member` (
  `member_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '사용자계정명',
  `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '사용자비번',
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_member`
--

LOCK TABLES `tb_member` WRITE;
/*!40000 ALTER TABLE `tb_member` DISABLE KEYS */;
INSERT INTO `tb_member` VALUES ('yellowplace','$2a$10$oWptnmRZYHVzIPSjuT0Breit2ir7l.0Ohff/zVnNY4zmnCTcwGLki');
/*!40000 ALTER TABLE `tb_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_member_role`
--

DROP TABLE IF EXISTS `tb_member_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_member_role` (
  `member_role_id` bigint NOT NULL AUTO_INCREMENT,
  `member_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '사용자계정명',
  `role_id` bigint NOT NULL COMMENT '권한아이디',
  PRIMARY KEY (`member_role_id`),
  KEY `FK141qx2xu9vbf12lv6uu8r3ndk` (`member_id`),
  KEY `FKn39mjawa5f3wrelu5y6x8pr1r` (`role_id`),
  CONSTRAINT `FK141qx2xu9vbf12lv6uu8r3ndk` FOREIGN KEY (`member_id`) REFERENCES `tb_member` (`member_id`),
  CONSTRAINT `FKn39mjawa5f3wrelu5y6x8pr1r` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_member_role`
--

LOCK TABLES `tb_member_role` WRITE;
/*!40000 ALTER TABLE `tb_member_role` DISABLE KEYS */;
INSERT INTO `tb_member_role` VALUES (1,'yellowplace',1),(2,'yellowplace',2);
/*!40000 ALTER TABLE `tb_member_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_menu`
--

DROP TABLE IF EXISTS `tb_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '메뉴아이디',
  `menu_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '메뉴명',
  `menu_url` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '메뉴URL',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_menu`
--

LOCK TABLES `tb_menu` WRITE;
/*!40000 ALTER TABLE `tb_menu` DISABLE KEYS */;
INSERT INTO `tb_menu` VALUES (1,'메인','/main'),(2,'게시판','/board'),(3,'게시판쓰기','/board/write'),(4,'게시판보기','/board/view');
/*!40000 ALTER TABLE `tb_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_menu_api`
--

DROP TABLE IF EXISTS `tb_menu_api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_menu_api` (
  `menu_api_id` bigint NOT NULL AUTO_INCREMENT COMMENT '메뉴 API아이디',
  `api_id` bigint DEFAULT NULL COMMENT 'API 정보아이디',
  `menu_id` bigint DEFAULT NULL COMMENT '메뉴아이디',
  PRIMARY KEY (`menu_api_id`),
  KEY `FK3ol9fc0bgd2p1isc014609m4v` (`api_id`),
  KEY `FK8439tiuhs91pels7tannaf7ju` (`menu_id`),
  CONSTRAINT `FK3ol9fc0bgd2p1isc014609m4v` FOREIGN KEY (`api_id`) REFERENCES `tb_api` (`api_id`),
  CONSTRAINT `FK8439tiuhs91pels7tannaf7ju` FOREIGN KEY (`menu_id`) REFERENCES `tb_menu` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_menu_api`
--

LOCK TABLES `tb_menu_api` WRITE;
/*!40000 ALTER TABLE `tb_menu_api` DISABLE KEYS */;
INSERT INTO `tb_menu_api` VALUES (1,1,1),(2,2,1),(3,3,2),(4,4,2),(5,5,3);
/*!40000 ALTER TABLE `tb_menu_api` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_menuperm`
--

DROP TABLE IF EXISTS `tb_menuperm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_menuperm` (
  `menu_perm_id` bigint NOT NULL AUTO_INCREMENT COMMENT '권한별 메뉴',
  `menu_perm_value` bigint NOT NULL COMMENT '실제메뉴권한 수치',
  `menu_id` bigint NOT NULL COMMENT '메뉴아이디',
  PRIMARY KEY (`menu_perm_id`),
  KEY `FK27tg3t365j6ohh299j1djdnxc` (`menu_id`),
  CONSTRAINT `FK27tg3t365j6ohh299j1djdnxc` FOREIGN KEY (`menu_id`) REFERENCES `tb_menu` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_menuperm`
--

LOCK TABLES `tb_menuperm` WRITE;
/*!40000 ALTER TABLE `tb_menuperm` DISABLE KEYS */;
INSERT INTO `tb_menuperm` VALUES (1,15,1),(2,15,2),(3,15,3),(4,15,4);
/*!40000 ALTER TABLE `tb_menuperm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_perm`
--

DROP TABLE IF EXISTS `tb_perm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_perm` (
  `perm_id` bigint NOT NULL AUTO_INCREMENT COMMENT '사용자권한ID',
  `perm_nm` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '사용자권한명',
  `perm_val` bigint NOT NULL COMMENT '사용자권한총수치',
  PRIMARY KEY (`perm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_perm`
--

LOCK TABLES `tb_perm` WRITE;
/*!40000 ALTER TABLE `tb_perm` DISABLE KEYS */;
INSERT INTO `tb_perm` VALUES (1,'조회',1),(2,'추가',2),(3,'수정',4),(4,'삭제',8);
/*!40000 ALTER TABLE `tb_perm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role`
--

DROP TABLE IF EXISTS `tb_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '권한아이디',
  `role_description` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '권한설명',
  `role_nm` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '권한명',
  `role_priorty` bigint NOT NULL COMMENT '권한우선순위',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `UKo6gj01ajs6yweoxo7topbf1y5` (`role_nm`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role`
--

LOCK TABLES `tb_role` WRITE;
/*!40000 ALTER TABLE `tb_role` DISABLE KEYS */;
INSERT INTO `tb_role` VALUES (1,'관리자권한','admin',1),(2,'사용자권한','user',2);
/*!40000 ALTER TABLE `tb_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role_menu_api`
--

DROP TABLE IF EXISTS `tb_role_menu_api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_role_menu_api` (
  `role_menu_api_id` bigint NOT NULL AUTO_INCREMENT,
  `menu_api_id` bigint DEFAULT NULL COMMENT '메뉴 API아이디',
  `role_id` bigint DEFAULT NULL COMMENT '권한아이디',
  PRIMARY KEY (`role_menu_api_id`),
  KEY `FK73eklypynptqc9q2pj1u12bct` (`menu_api_id`),
  KEY `FK6cpg7m10jhi4r17m4l9b12puk` (`role_id`),
  CONSTRAINT `FK6cpg7m10jhi4r17m4l9b12puk` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`role_id`),
  CONSTRAINT `FK73eklypynptqc9q2pj1u12bct` FOREIGN KEY (`menu_api_id`) REFERENCES `tb_menu_api` (`menu_api_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role_menu_api`
--

LOCK TABLES `tb_role_menu_api` WRITE;
/*!40000 ALTER TABLE `tb_role_menu_api` DISABLE KEYS */;
INSERT INTO `tb_role_menu_api` VALUES (1,1,1),(2,2,1),(3,3,1),(4,1,2),(5,2,2),(6,5,1);
/*!40000 ALTER TABLE `tb_role_menu_api` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role_menuperm`
--

DROP TABLE IF EXISTS `tb_role_menuperm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_role_menuperm` (
  `role_menuperm_id` bigint NOT NULL AUTO_INCREMENT,
  `menu_perm_id` bigint NOT NULL COMMENT '권한별 메뉴',
  `role_id` bigint NOT NULL COMMENT '권한아이디',
  PRIMARY KEY (`role_menuperm_id`),
  KEY `FKk01ekkhljydk1yvnab051lo6y` (`menu_perm_id`),
  KEY `FKc68dif7k4bg0rpeya4ksruir2` (`role_id`),
  CONSTRAINT `FKc68dif7k4bg0rpeya4ksruir2` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`role_id`),
  CONSTRAINT `FKk01ekkhljydk1yvnab051lo6y` FOREIGN KEY (`menu_perm_id`) REFERENCES `tb_menuperm` (`menu_perm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role_menuperm`
--

LOCK TABLES `tb_role_menuperm` WRITE;
/*!40000 ALTER TABLE `tb_role_menuperm` DISABLE KEYS */;
INSERT INTO `tb_role_menuperm` VALUES (1,1,1),(2,2,1),(3,1,2),(4,3,1),(5,4,1);
/*!40000 ALTER TABLE `tb_role_menuperm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'admin'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-25 14:36:30
