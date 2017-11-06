# Host: localhost  (Version: 5.5.40)
# Date: 2017-11-06 09:41:50
# Generator: MySQL-Front 5.3  (Build 4.128)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "userbean"
#

DROP TABLE IF EXISTS `userbean`;
CREATE TABLE `userbean` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

#
# Data for table "userbean"
#

INSERT INTO `userbean` VALUES (8,'beini1','123456','22@qq.com',1),(9,'33','123456','11@qq.com',1),(10,'ff','123456','33@qq.com',1);

#
# Structure for table "daily"
#

DROP TABLE IF EXISTS `daily`;
CREATE TABLE `daily` (
  `daily_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `picUrl` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`daily_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `daily_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `userbean` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

#
# Data for table "daily"
#

INSERT INTO `daily` VALUES (1,'ff','2017.10.19','系映射)，说白了就是将面向对象编程语言里的对象与数据库关联起来的一种技术，而greenDao就是实现这种技术之一，所以说greenDao其实就是一种将java object 与SQLite Database关联起来的桥梁，它们之间的关系 如下图所示；','by beini',NULL,8),(2,'得了顶顶顶顶顶顶顶顶顶顶','2017.10.19','系映射)，说白了就是将面向对象编程语言里的对象与数据库关联起来的一种技术，而greenDao就是实现这种技术之一，所以说greenDao其实就是一种将java object 与SQLite Database关联起来的桥梁，它们之间的关系 如下图所示；','by beini',NULL,8),(3,'得了顶顶顶顶顶顶顶顶顶顶','2017.10.19','系映射)，说白了就是将面向对象编程语言里的对象与数据库关联起来的一种技术，而greenDao就是实现这种技术之一，所以说greenDao其实就是一种将java object 与SQLite Database关联起来的桥梁，它们之间的关系 如下图所示；','by beini',NULL,8),(4,'得了顶顶顶顶顶顶顶顶顶顶','2017.10.19','系映射)，说白了就是将面向对象编程语言里的对象与数据库关联起来的一种技术，而greenDao就是实现这种技术之一，所以说greenDao其实就是一种将java object 与SQLite Database关联起来的桥梁，它们之间的关系 如下图所示；','by beini',NULL,8),(5,'得了顶顶顶顶顶顶顶顶顶顶','2017.10.19','系映射)，说白了就是将面向对象编程语言里的对象与数据库关联起来的一种技术，而greenDao就是实现这种技术之一，所以说greenDao其实就是一种将java object 与SQLite Database关联起来的桥梁，它们之间的关系 如下图所示；','by beini',NULL,8),(6,'得了顶顶顶顶顶顶顶顶顶顶','2017.10.19','系映射)，说白了就是将面向对象编程语言里的对象与数据库关联起来的一种技术，而greenDao就是实现这种技术之一，所以说greenDao其实就是一种将java object 与SQLite Database关联起来的桥梁，它们之间的关系 如下图所示；','by beini',NULL,8),(7,'得了顶顶顶顶顶顶顶顶顶顶','2017.10.19','系映射)，说白了就是将面向对象编程语言里的对象与数据库关联起来的一种技术，而greenDao就是实现这种技术之一，所以说greenDao其实就是一种将java object 与SQLite Database关联起来的桥梁，它们之间的关系 如下图所示；','by beini',NULL,8),(8,'得了顶顶顶顶顶顶顶顶顶顶','2017.10.19','系映射)，说白了就是将面向对象编程语言里的对象与数据库关联起来的一种技术，而greenDao就是实现这种技术之一，所以说greenDao其实就是一种将java object 与SQLite Database关联起来的桥梁，它们之间的关系 如下图所示；','by beini',NULL,8),(9,'得了顶顶顶顶顶顶顶顶顶顶','2017.10.19','系映射)，说白了就是将面向对象编程语言里的对象与数据库关联起来的一种技术，而greenDao就是实现这种技术之一，所以说greenDao其实就是一种将java object 与SQLite Database关联起来的桥梁，它们之间的关系 如下图所示；','by beini',NULL,8),(10,'得了顶顶顶顶顶顶顶顶顶顶','2017.10.19','系映射)，说白了就是将面向对象编程语言里的对象与数据库关联起来的一种技术，而greenDao就是实现这种技术之一，所以说greenDao其实就是一种将java object 与SQLite Database关联起来的桥梁，它们之间的关系 如下图所示；','by beini',NULL,8),(11,'得了顶顶顶顶顶顶顶顶顶顶','2017.10.19','系映射)，说白了就是将面向对象编程语言里的对象与数据库关联起来的一种技术，而greenDao就是实现这种技术之一，所以说greenDao其实就是一种将java object 与SQLite Database关联起来的桥梁，它们之间的关系 如下图所示；','by beini',NULL,8),(12,'得了顶顶顶顶顶顶顶顶顶顶','2017.10.19','系映射)，说白了就是将面向对象编程语言里的对象与数据库关联起来的一种技术，而greenDao就是实现这种技术之一，所以说greenDao其实就是一种将java object 与SQLite Database关联起来的桥梁，它们之间的关系 如下图所示；','by beini',NULL,8),(13,'得了顶顶顶顶顶顶顶顶顶顶','2017.10.19','系映射)，说白了就是将面向对象编程语言里的对象与数据库关联起来的一种技术，而greenDao就是实现这种技术之一，所以说greenDao其实就是一种将java object 与SQLite Database关联起来的桥梁，它们之间的关系 如下图所示；','by beini',NULL,8),(14,'得了顶顶顶顶顶顶顶顶顶顶','2017.10.19','系映射)，说白了就是将面向对象编程语言里的对象与数据库关联起来的一种技术，而greenDao就是实现这种技术之一，所以说greenDao其实就是一种将java object 与SQLite Database关联起来的桥梁，它们之间的关系 如下图所示；','by beini',NULL,8),(15,'得了顶顶顶顶顶顶顶顶顶顶','2017.10.19','系映射)，说白了就是将面向对象编程语言里的对象与数据库关联起来的一种技术，而greenDao就是实现这种技术之一，所以说greenDao其实就是一种将java object 与SQLite Database关联起来的桥梁，它们之间的关系 如下图所示；','by beini',NULL,8),(16,'得了顶顶顶顶顶顶顶顶顶顶','2017.10.19','系映射)，说白了就是将面向对象编程语言里的对象与数据库关联起来的一种技术，而greenDao就是实现这种技术之一，所以说greenDao其实就是一种将java object 与SQLite Database关联起来的桥梁，它们之间的关系 如下图所示；','by beini',NULL,8),(17,'得了顶顶顶顶顶顶顶顶顶顶','2017.10.19','系映射)，说白了就是将面向对象编程语言里的对象与数据库关联起来的一种技术，而greenDao就是实现这种技术之一，所以说greenDao其实就是一种将java object 与SQLite Database关联起来的桥梁，它们之间的关系 如下图所示；','by beini',NULL,8),(18,'得了顶顶顶顶顶顶顶顶顶顶','2017.10.19','系映射)，说白了就是将面向对象编程语言里的对象与数据库关联起来的一种技术，而greenDao就是实现这种技术之一，所以说greenDao其实就是一种将java object 与SQLite Database关联起来的桥梁，它们之间的关系 如下图所示；','by beini',NULL,8),(19,'得了顶顶顶顶顶顶顶顶顶顶','2017.10.19','系映射)，说白了就是将面向对象编程语言里的对象与数据库关联起来的一种技术，而greenDao就是实现这种技术之一，所以说greenDao其实就是一种将java object 与SQLite Database关联起来的桥梁，它们之间的关系 如下图所示；','by beini',NULL,8);
