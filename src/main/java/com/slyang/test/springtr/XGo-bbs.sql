
DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `name` varchar(255) NOT NULL,
  `money` double DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `account` VALUES ('sly', '1210'), ('sly2', '1254');