# MySQL-Front 5.1  (Build 2.7)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: sxtvdb
# ------------------------------------------------------
# Server version 5.1.42-community

CREATE DATABASE `sxtvdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sxtvdb`;

#
# Source for table audience
#

CREATE TABLE `audience` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `age` int(11) DEFAULT NULL,
  `gender` int(4) DEFAULT NULL,
  `career` varchar(50) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Dumping data for table audience
#

INSERT INTO `audience` VALUES (1,'张三',NULL,NULL,NULL,NULL);
INSERT INTO `audience` VALUES (2,'李四',NULL,NULL,NULL,NULL);
INSERT INTO `audience` VALUES (3,'王麻子',NULL,NULL,NULL,NULL);
INSERT INTO `audience` VALUES (4,'赵六',NULL,NULL,NULL,NULL);

#
# Source for table audiencescore
#

CREATE TABLE `audiencescore` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vedioID` varchar(128) NOT NULL DEFAULT '0',
  `audienceID` int(11) NOT NULL DEFAULT '0',
  `dateExamine` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `result` int(11) NOT NULL DEFAULT '0',
  `comments` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_key` (`vedioID`,`audienceID`),
  KEY `vedioID` (`vedioID`),
  KEY `audienceID` (`audienceID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

#
# Dumping data for table audiencescore
#

INSERT INTO `audiencescore` VALUES (1,'0',2,'2010-02-12 20:15:03',1,NULL);
INSERT INTO `audiencescore` VALUES (2,'0',3,'2010-02-13 09:41:43',1,NULL);
INSERT INTO `audiencescore` VALUES (3,'0',1,'2010-02-13 09:41:43',1,NULL);
INSERT INTO `audiencescore` VALUES (4,'1',3,'2010-02-12 18:36:48',1,NULL);
INSERT INTO `audiencescore` VALUES (7,'0',4,'2010-02-12 20:47:48',1,NULL);
INSERT INTO `audiencescore` VALUES (8,'1',4,'2010-02-12 20:55:29',0,NULL);
INSERT INTO `audiencescore` VALUES (9,'23g',1,'2010-02-28 18:33:39',1,NULL);
INSERT INTO `audiencescore` VALUES (10,'23g',2,'2010-02-28 18:33:39',1,NULL);
INSERT INTO `audiencescore` VALUES (11,'1457',1,'2010-03-08 23:06:46',1,NULL);

#
# Source for table auditing
#

CREATE TABLE `auditing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vedioID` varchar(128) NOT NULL DEFAULT '',
  `auditor` int(11) NOT NULL,
  `auditDate` datetime NOT NULL DEFAULT '1000-01-01 00:00:01',
  `result` int(11) NOT NULL,
  `comments` varchar(512) DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_key` (`vedioID`,`auditor`),
  KEY `auditor` (`auditor`),
  KEY `vedioID` (`vedioID`),
  KEY `result` (`result`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='审核';

#
# Dumping data for table auditing
#

INSERT INTO `auditing` VALUES (1,'2',2,'2010-02-12 12:25:44',7,NULL);
INSERT INTO `auditing` VALUES (2,'2',1,'2010-02-25 23:00:04',7,NULL);
INSERT INTO `auditing` VALUES (3,'1457',5,'2010-03-08 23:45:09',3,NULL);

#
# Source for table company
#

CREATE TABLE `company` (
  `companyID` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(128) NOT NULL,
  `registrationNo` varchar(128) NOT NULL,
  `phone` varchar(50) DEFAULT '0',
  `contactPerson` varchar(50) DEFAULT '',
  `comments` varchar(512) DEFAULT '',
  PRIMARY KEY (`companyID`),
  UNIQUE KEY `registrationNo` (`registrationNo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='公司信息';

#
# Dumping data for table company
#

INSERT INTO `company` VALUES (1,'中华影视','12331123123','34534534534','胡戈','第三方万恶服务');
INSERT INTO `company` VALUES (2,'美国影视','123123','123123','万恶范围阿飞','发送到非 ');
INSERT INTO `company` VALUES (3,'法国影视','34234','werwef','fwefwef','发送到非万恶 ');
INSERT INTO `company` VALUES (4,'1234','','','','');

#
# Source for table employee
#

CREATE TABLE `employee` (
  `employeeID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `birthday` date DEFAULT '1000-01-01',
  `contractDate` date NOT NULL DEFAULT '1000-01-01',
  `gender` int(11) NOT NULL DEFAULT '0',
  `tel` varchar(50) DEFAULT '0',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0=禁用，1=正常',
  `comments` varchar(512) DEFAULT '',
  PRIMARY KEY (`employeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='员工信息';

#
# Dumping data for table employee
#

INSERT INTO `employee` VALUES (1,'王文婷','1983-02-19','2006-01-16',0,'13991366931',0,'这是一个员工');
INSERT INTO `employee` VALUES (2,'王萌','2010-01-21','1970-01-01',0,'13991366930',0,'王萌王萌王萌王萌王萌王萌王萌王萌王萌王萌');
INSERT INTO `employee` VALUES (3,'周强','2010-01-21','2010-01-21',1,'13991366931',0,'这是一个员工');
INSERT INTO `employee` VALUES (4,'周zhou','2010-01-21','2010-01-21',1,'13991366931',0,'这是一个员工');
INSERT INTO `employee` VALUES (5,'里斯','2010-01-25','2010-01-20',1,'123123123',0,'的期望的钱');
INSERT INTO `employee` VALUES (6,'张武','2006-01-09','2007-01-15',0,'123124234',0,'撕得粉碎大放送');
INSERT INTO `employee` VALUES (7,'赵流','2006-01-16','2008-01-22',0,'123123',0,'扫的服务阿飞');
INSERT INTO `employee` VALUES (8,'王其','2010-01-17','1982-01-12',0,'维吾尔',0,'斯蒂芬');

#
# Source for table playchangelog
#

CREATE TABLE `playchangelog` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `vedioID` varchar(128) NOT NULL DEFAULT '',
  `auditor` int(11) NOT NULL DEFAULT '0',
  `fromDate` datetime DEFAULT '1000-01-01 00:00:00',
  `toDate` datetime DEFAULT '1000-01-01 00:00:00',
  `operation` varchar(50) DEFAULT '',
  `date` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `auditor` (`auditor`),
  KEY `vedioID` (`vedioID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

#
# Dumping data for table playchangelog
#

INSERT INTO `playchangelog` VALUES (16,'4',2,'2010-02-02','2010-02-01','更新','2010-02-26 23:40:50');
INSERT INTO `playchangelog` VALUES (17,'3',2,'2010-02-03','2010-02-02','更新','2010-02-26 23:40:50');
INSERT INTO `playchangelog` VALUES (18,'1',2,'2010-02-04','2010-02-03','更新','2010-02-26 23:40:50');
INSERT INTO `playchangelog` VALUES (22,'4',2,NULL,'2010-02-04','加入','2010-02-27 17:16:22');
INSERT INTO `playchangelog` VALUES (23,'sfw',2,NULL,'2010-02-05','加入','2010-02-27 17:16:22');
INSERT INTO `playchangelog` VALUES (24,'4',2,'2010-02-04',NULL,'移除','2010-02-27 17:16:58');
INSERT INTO `playchangelog` VALUES (25,'sfw',2,'2010-02-05',NULL,'移除','2010-02-27 17:16:58');
INSERT INTO `playchangelog` VALUES (26,'1457',5,NULL,'2010-03-28','加入','2010-03-08 23:50:14');

#
# Source for table playorder
#

CREATE TABLE `playorder` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `playDate` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `vedioID` varchar(128) NOT NULL DEFAULT '',
  `arrangeDate` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `auditor` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `vedioID` (`vedioID`),
  KEY `auditor` (`auditor`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='节目编排';

#
# Dumping data for table playorder
#

INSERT INTO `playorder` VALUES (28,'2010-02-02','3','2010-02-26 23:40:50',2);
INSERT INTO `playorder` VALUES (30,'2010-02-03','1','2010-02-26 23:40:50',2);
INSERT INTO `playorder` VALUES (32,'2010-02-01','sw12','2010-02-27 16:59:47',2);
INSERT INTO `playorder` VALUES (33,'2010-03-28','1457','2010-03-08 23:50:14',5);

#
# Source for table resources
#

CREATE TABLE `resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(512) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table resources
#


#
# Source for table role
#

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

#
# Dumping data for table role
#

INSERT INTO `role` VALUES (1,'ADMIN','管理员');
INSERT INTO `role` VALUES (2,'INPUTER','录入');
INSERT INTO `role` VALUES (3,'EXAMINER','打分');
INSERT INTO `role` VALUES (4,'AUDITOR','审核');
INSERT INTO `role` VALUES (5,'ARRANGER','编排');
INSERT INTO `role` VALUES (6,'NORMAL','普通');

#
# Source for table role_resource
#

CREATE TABLE `role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NOT NULL,
  `resourceid` int(11) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `union_key` (`roleid`,`resourceid`),
  KEY `resourceid` (`resourceid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table role_resource
#


#
# Source for table scoreweight
#

CREATE TABLE `scoreweight` (
  `factor` varchar(50) NOT NULL DEFAULT '',
  `wieght` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`factor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table scoreweight
#

INSERT INTO `scoreweight` VALUES ('innovateScore',0.1);
INSERT INTO `scoreweight` VALUES ('performScore',0.3);
INSERT INTO `scoreweight` VALUES ('storyScore',0.4);
INSERT INTO `scoreweight` VALUES ('techScore',0.2);

#
# Source for table status
#

CREATE TABLE `status` (
  `statusID` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(50) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY (`statusID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='状态';

#
# Dumping data for table status
#

INSERT INTO `status` VALUES (1,'待评','新进剧目，带评分');
INSERT INTO `status` VALUES (2,'待审','剧目以评分，等待审核');
INSERT INTO `status` VALUES (3,'通过','审核已通过，等待指定播放时间');
INSERT INTO `status` VALUES (5,'待排','剧目已经通过并等待编排。');
INSERT INTO `status` VALUES (6,'待播','审核通过，并已指定时间，等待播放');
INSERT INTO `status` VALUES (7,'退回','审核未通过，直接退带');
INSERT INTO `status` VALUES (8,'已播','剧目已播出，尚未得到市场反馈');
INSERT INTO `status` VALUES (9,'结束','剧目已播出并收到市场反馈');

#
# Source for table subject
#

CREATE TABLE `subject` (
  `subjectID` int(11) NOT NULL AUTO_INCREMENT,
  `subjectName` varchar(128) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY (`subjectID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='栏目';

#
# Dumping data for table subject
#

INSERT INTO `subject` VALUES (1,'都市碎戏','都市碎戏都市碎戏都市碎戏都市碎戏都市碎戏');
INSERT INTO `subject` VALUES (2,'百家碎戏','百家碎戏百家碎戏百家碎戏百家碎戏百家碎戏');

#
# Source for table topic
#

CREATE TABLE `topic` (
  `topicID` int(11) NOT NULL AUTO_INCREMENT,
  `topicName` varchar(128) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY (`topicID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='题材';

#
# Dumping data for table topic
#

INSERT INTO `topic` VALUES (1,'爱情','爱情爱情爱情爱情爱情爱情');
INSERT INTO `topic` VALUES (2,'亲情','亲情亲情亲情亲情亲情亲情亲情亲情');
INSERT INTO `topic` VALUES (3,'生活','生活生活生活生活生活生活');
INSERT INTO `topic` VALUES (4,'社会','社会社会社会社会社会');

#
# Source for table user
#

CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL DEFAULT '',
  `userPass` varchar(50) NOT NULL DEFAULT '',
  `employee` int(11) NOT NULL DEFAULT '0',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0=禁用，1=正常',
  PRIMARY KEY (`userID`),
  KEY `userEmployee` (`employee`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户';

#
# Dumping data for table user
#

INSERT INTO `user` VALUES (1,'tiger','command',1,1);
INSERT INTO `user` VALUES (2,'cat','123',2,1);
INSERT INTO `user` VALUES (3,'pencil','123',3,1);
INSERT INTO `user` VALUES (4,'dog','123',4,1);
INSERT INTO `user` VALUES (5,'chick','123',5,1);

#
# Source for table user_role
#

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `union index` (`userid`,`roleid`),
  KEY `roleid` (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table user_role
#

INSERT INTO `user_role` VALUES (1,1,1,'');
INSERT INTO `user_role` VALUES (2,2,3,'');
INSERT INTO `user_role` VALUES (3,3,2,'');
INSERT INTO `user_role` VALUES (4,4,3,'');
INSERT INTO `user_role` VALUES (5,5,3,'');

#
# Source for table vedioscore
#

CREATE TABLE `vedioscore` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vedioID` varchar(128) NOT NULL DEFAULT '',
  `examiner` int(11) NOT NULL,
  `storyScore` float NOT NULL,
  `techScore` float NOT NULL,
  `performScore` float NOT NULL,
  `innovateScore` float NOT NULL,
  `score` float NOT NULL,
  `award` int(11) NOT NULL DEFAULT '0',
  `accuracy` float DEFAULT NULL,
  `purchase` int(11) NOT NULL DEFAULT '0',
  `dateExamine` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `operator` int(11) NOT NULL DEFAULT '0',
  `orientation` int(11) NOT NULL DEFAULT '0',
  `comments` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_key` (`vedioID`,`examiner`),
  KEY `vedioExaminer` (`examiner`),
  KEY `vedioID` (`vedioID`),
  KEY `operator` (`operator`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='评分表';

#
# Dumping data for table vedioscore
#

INSERT INTO `vedioscore` VALUES (1,'2',2,22,88,66,99,56.1,1,NULL,1,'2010-02-24 23:54:54',1,0,NULL);
INSERT INTO `vedioscore` VALUES (2,'23g',2,12,11,67,45,31.6,0,NULL,0,'2010-02-27 22:09:25',1,0,NULL);
INSERT INTO `vedioscore` VALUES (3,'12',2,22,22,22,22,22,1,NULL,1,'2010-03-08 22:53:29',1,0,NULL);
INSERT INTO `vedioscore` VALUES (4,'1457',2,77,77,77,77,77,1,NULL,1,'2010-03-08 23:06:19',1,0,NULL);
INSERT INTO `vedioscore` VALUES (5,'1457',4,99,99,99,99,99,0,NULL,0,'2010-03-08 23:08:01',1,0,NULL);
INSERT INTO `vedioscore` VALUES (9,'1457',5,67,67,67,67,67,0,NULL,0,'2010-03-08 23:25:09',1,0,NULL);
INSERT INTO `vedioscore` VALUES (11,'1',2,66,77,88,99,77,0,17,0,'2010-02-11',1,0,NULL);
INSERT INTO `vedioscore` VALUES (12,'1',4,56,67,78,89,80,0,20,0,'2010-02-12',1,0,NULL);
INSERT INTO `vedioscore` VALUES (13,'1',5,34,34,45,23,60,0,1,0,'2010-02-21',1,0,NULL);
INSERT INTO `vedioscore` VALUES (14,'3',2,88,88,88,88,88,0,8,0,'2010-02-01',1,0,NULL);
INSERT INTO `vedioscore` VALUES (15,'3',4,66,66,66,66,66,0,14,0,'2010-02-01',1,0,NULL);
INSERT INTO `vedioscore` VALUES (16,'3',5,44,44,44,44,44,0,1,0,'2010-02-01',1,0,NULL);
INSERT INTO `vedioscore` VALUES (17,'sw12',2,55,55,55,55,55,0,5,0,'2010-02-01',1,0,NULL);
INSERT INTO `vedioscore` VALUES (18,'sw12',4,67,67,67,67,67,0,17,0,'2010-02-01',1,0,NULL);
INSERT INTO `vedioscore` VALUES (19,'sw12',5,33,33,33,33,33,0,1,0,'2010-02-01',1,0,NULL);

#
# Source for table vediotape
#

CREATE TABLE `vediotape` (
  `vedioID` varchar(128) NOT NULL DEFAULT '0',
  `vedioName` varchar(128) NOT NULL,
  `subject` int(11) NOT NULL,
  `topic` int(11) NOT NULL,
  `companyID` int(11) NOT NULL,
  `dateComing` date NOT NULL DEFAULT '1000-01-01',
  `dateInput` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `inputer` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `comments` varchar(512) DEFAULT NULL,
  `marketShare` float DEFAULT NULL,
  `audienceRating` float DEFAULT NULL,
  PRIMARY KEY (`vedioID`),
  KEY `vedioCompany` (`companyID`),
  KEY `vedioStatus` (`status`),
  KEY `vedioSubject` (`subject`),
  KEY `vedioTopic` (`topic`),
  KEY `inputer` (`inputer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table vediotape
#

INSERT INTO `vediotape` VALUES ('0','阿凡达',1,3,1,'2001-01-30','2001-01-31',3,5,NULL,NULL,NULL);
INSERT INTO `vediotape` VALUES ('1','Fight Club',2,4,1,'2000-01-30','2001-01-31',3,9,NULL,0.5,0.6);
INSERT INTO `vediotape` VALUES ('12','12qdfw',2,3,1,'2010-02-11','2010-02-11 08:44:47',1,1,'ddvsdfsdsdvsdv',NULL,NULL);
INSERT INTO `vediotape` VALUES ('12asd','dqw',1,1,1,'2010-02-10','2010-02-10 14:17:59',1,2,'asdacasc',NULL,NULL);
INSERT INTO `vediotape` VALUES ('12dq','dqwd',2,1,1,'2010-02-10','2010-02-10 14:10:59',1,6,'zxcasc',NULL,NULL);
INSERT INTO `vediotape` VALUES ('1457','X战警',2,4,2,'2010-03-08','2010-03-08 22:55:39',2,8,'xxxxxx',70,50);
INSERT INTO `vediotape` VALUES ('1ewfv','zxvcsdv',1,3,1,'2010-02-10','2010-02-10 14:21:42',1,2,'sdv',NULL,NULL);
INSERT INTO `vediotape` VALUES ('1ssd','zxcasc',1,3,1,'2010-02-10','2010-02-10 14:10:59',1,2,'zxcasczx zx zx zx zx ',NULL,NULL);
INSERT INTO `vediotape` VALUES ('2','12 Monkey',2,4,2,'2001-12-30','2001-01-31',3,7,'',NULL,NULL);
INSERT INTO `vediotape` VALUES ('23eg','fwefwef',1,2,1,'2010-02-10','2010-02-10 14:21:42',1,2,'sdvvsdv',NULL,NULL);
INSERT INTO `vediotape` VALUES ('23g','dfbdfbf',2,4,1,'2010-02-10','2010-02-10 14:21:42',1,1,'sdvvsdv',NULL,NULL);
INSERT INTO `vediotape` VALUES ('3','蝴蝶效应',2,3,1,'2009-01-01','2009-01-02',3,9,NULL,0.7,0.8);
INSERT INTO `vediotape` VALUES ('324aa','sfwe43',2,1,1,'2010-02-10','2010-02-10 14:21:42',1,2,'sdvvsdv',NULL,NULL);
INSERT INTO `vediotape` VALUES ('32f','fwef',2,3,2,'2010-02-12','2010-02-12 10:33:18',1,1,'sd',NULL,NULL);
INSERT INTO `vediotape` VALUES ('32ff2','ffewef',1,2,3,'2010-02-12','2010-02-12 10:33:18',1,1,'sdfwe',NULL,NULL);
INSERT INTO `vediotape` VALUES ('4','超黑特警',2,4,1,'2009-02-01','2009-02-02',3,5,NULL,NULL,NULL);
INSERT INTO `vediotape` VALUES ('sf1','sdfw',1,1,1,'2010-02-11','2010-02-11 08:44:47',1,1,'ddv',NULL,NULL);
INSERT INTO `vediotape` VALUES ('sf1vsd','weqdfw',1,1,1,'2010-02-11','2010-02-11 08:44:47',1,5,'ddvsdfsd',NULL,NULL);
INSERT INTO `vediotape` VALUES ('sfw','1v2asdsv',1,1,1,'2010-02-10','2010-02-10 14:20:35',1,5,'sdvsdv',NULL,NULL);
INSERT INTO `vediotape` VALUES ('sw12','dqwe',2,3,1,'2010-02-12','2010-02-12 10:33:18',1,9,'ssdv',0.6,0.5);

#
#  Foreign keys for table audiencescore
#

ALTER TABLE `audiencescore`
ADD CONSTRAINT `audiencescore_ibfk_1` FOREIGN KEY (`vedioID`) REFERENCES `vediotape` (`vedioID`),
ADD CONSTRAINT `audiencescore_ibfk_2` FOREIGN KEY (`audienceID`) REFERENCES `audience` (`id`);

#
#  Foreign keys for table auditing
#

ALTER TABLE `auditing`
ADD CONSTRAINT `auditing_ibfk_1` FOREIGN KEY (`vedioID`) REFERENCES `vediotape` (`vedioID`) ON UPDATE CASCADE,
ADD CONSTRAINT `auditing_ibfk_2` FOREIGN KEY (`auditor`) REFERENCES `user` (`userID`) ON UPDATE CASCADE,
ADD CONSTRAINT `auditing_ibfk_3` FOREIGN KEY (`result`) REFERENCES `status` (`statusID`) ON UPDATE CASCADE;

#
#  Foreign keys for table playchangelog
#

ALTER TABLE `playchangelog`
ADD CONSTRAINT `playchangelog_ibfk_1` FOREIGN KEY (`auditor`) REFERENCES `user` (`userID`) ON UPDATE CASCADE,
ADD CONSTRAINT `playchangelog_ibfk_2` FOREIGN KEY (`vedioID`) REFERENCES `vediotape` (`vedioID`) ON UPDATE CASCADE;

#
#  Foreign keys for table playorder
#

ALTER TABLE `playorder`
ADD CONSTRAINT `playorder_ibfk_1` FOREIGN KEY (`vedioID`) REFERENCES `vediotape` (`vedioID`) ON UPDATE CASCADE,
ADD CONSTRAINT `playorder_ibfk_2` FOREIGN KEY (`auditor`) REFERENCES `user` (`userID`) ON UPDATE CASCADE;

#
#  Foreign keys for table role_resource
#

ALTER TABLE `role_resource`
ADD CONSTRAINT `role_resource_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`),
ADD CONSTRAINT `role_resource_ibfk_2` FOREIGN KEY (`resourceid`) REFERENCES `resources` (`id`);

#
#  Foreign keys for table user
#

ALTER TABLE `user`
ADD CONSTRAINT `userEmployee` FOREIGN KEY (`employee`) REFERENCES `employee` (`employeeID`) ON UPDATE CASCADE;

#
#  Foreign keys for table user_role
#

ALTER TABLE `user_role`
ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userID`),
ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`);

#
#  Foreign keys for table vedioscore
#

ALTER TABLE `vedioscore`
ADD CONSTRAINT `vedioExaminer` FOREIGN KEY (`examiner`) REFERENCES `user` (`userID`) ON UPDATE CASCADE,
ADD CONSTRAINT `vedioscore_ibfk_1` FOREIGN KEY (`vedioID`) REFERENCES `vediotape` (`vedioID`),
ADD CONSTRAINT `vedioscore_ibfk_2` FOREIGN KEY (`operator`) REFERENCES `user` (`userID`);

#
#  Foreign keys for table vediotape
#

ALTER TABLE `vediotape`
ADD CONSTRAINT `inputer` FOREIGN KEY (`inputer`) REFERENCES `user` (`userID`) ON UPDATE CASCADE,
ADD CONSTRAINT `vedioCompany` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON UPDATE CASCADE,
ADD CONSTRAINT `vedioStatus` FOREIGN KEY (`status`) REFERENCES `status` (`statusID`),
ADD CONSTRAINT `vedioSubject` FOREIGN KEY (`subject`) REFERENCES `subject` (`subjectID`),
ADD CONSTRAINT `vedioTopic` FOREIGN KEY (`topic`) REFERENCES `topic` (`topicID`) ON UPDATE CASCADE;


/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
