﻿# MySQL-Front 5.1  (Build 2.7)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: sxtvdb
# ------------------------------------------------------
# Server version 5.1.42-community

DROP DATABASE IF EXISTS `sxtvdb`;
CREATE DATABASE `sxtvdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sxtvdb`;

#
# Source for table audience
#

DROP TABLE IF EXISTS `audience`;
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

DROP TABLE IF EXISTS `audiencescore`;
CREATE TABLE `audiencescore` (
  `vedioID` varchar(128) NOT NULL DEFAULT '0',
  `audienceID` int(11) NOT NULL DEFAULT '0',
  `dateExamine` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `result` int(11) NOT NULL DEFAULT '0',
  `comments` varchar(512) DEFAULT NULL,
  UNIQUE KEY `pk` (`vedioID`,`audienceID`),
  KEY `audienceID` (`audienceID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table audiencescore
#

INSERT INTO `audiencescore` VALUES ('0',2,'2009-03-01',1,NULL);

#
# Source for table auditing
#

DROP TABLE IF EXISTS `auditing`;
CREATE TABLE `auditing` (
  `vedioID` varchar(128) NOT NULL DEFAULT '',
  `auditor` int(11) NOT NULL,
  `auditDate` datetime NOT NULL DEFAULT '1000-01-01 00:00:01',
  `result` int(11) NOT NULL,
  `comments` varchar(512) DEFAULT '',
  PRIMARY KEY (`vedioID`),
  KEY `auditor` (`auditor`),
  KEY `result` (`result`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审核';

#
# Dumping data for table auditing
#


#
# Source for table company
#

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `companyID` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(128) NOT NULL,
  `registrationNo` varchar(128) NOT NULL,
  `phone` varchar(50) DEFAULT '0',
  `contactPerson` varchar(50) DEFAULT '',
  `comments` varchar(512) DEFAULT '',
  PRIMARY KEY (`companyID`),
  UNIQUE KEY `registrationNo` (`registrationNo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='公司信息';

#
# Dumping data for table company
#

INSERT INTO `company` VALUES (1,'中华影视','12331123123','34534534534','胡戈','第三方万恶服务');

#
# Source for table employee
#

DROP TABLE IF EXISTS `employee`;
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

DROP TABLE IF EXISTS `playchangelog`;
CREATE TABLE `playchangelog` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `vedioID` varchar(128) NOT NULL DEFAULT '',
  `auditor` int(11) NOT NULL DEFAULT '0',
  `fromDate` datetime DEFAULT '1000-01-01 00:00:00',
  `toDate` datetime DEFAULT '1000-01-01 00:00:00',
  `operation` varchar(50) DEFAULT '',
  PRIMARY KEY (`Id`),
  KEY `auditor` (`auditor`),
  KEY `vedioID` (`vedioID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table playchangelog
#


#
# Source for table playorder
#

DROP TABLE IF EXISTS `playorder`;
CREATE TABLE `playorder` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `playDate` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `vedioID` varchar(128) NOT NULL DEFAULT '',
  `arrangeDate` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `auditor` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `vedioID` (`vedioID`),
  KEY `auditor` (`auditor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节目编排';

#
# Dumping data for table playorder
#


#
# Source for table resources
#

DROP TABLE IF EXISTS `resources`;
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

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table role
#


#
# Source for table role_resource
#

DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `roleid` int(11) NOT NULL,
  `resourceid` int(11) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY (`roleid`,`resourceid`),
  KEY `resourceid` (`resourceid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table role_resource
#


#
# Source for table scoreweight
#

DROP TABLE IF EXISTS `scoreweight`;
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

DROP TABLE IF EXISTS `status`;
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
INSERT INTO `status` VALUES (4,'修改','审核未通过，有待修改');
INSERT INTO `status` VALUES (5,'重审','修改已完成，需重新审核');
INSERT INTO `status` VALUES (6,'待播','审核通过，并已指定时间，等待播放');
INSERT INTO `status` VALUES (7,'退回','审核未通过，直接退带');
INSERT INTO `status` VALUES (8,'已播','剧目已播出，尚未得到市场反馈');
INSERT INTO `status` VALUES (9,'结束','剧目已播出并收到市场反馈');

#
# Source for table subject
#

DROP TABLE IF EXISTS `subject`;
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

DROP TABLE IF EXISTS `topic`;
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

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL DEFAULT '',
  `userPass` varchar(50) NOT NULL DEFAULT '',
  `employee` int(11) NOT NULL DEFAULT '0',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0=禁用，1=正常',
  PRIMARY KEY (`userID`),
  KEY `userEmployee` (`employee`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户';

#
# Dumping data for table user
#

INSERT INTO `user` VALUES (1,'tiger','command',1,1);
INSERT INTO `user` VALUES (2,'cat','123',2,1);
INSERT INTO `user` VALUES (3,'pencil','123',3,1);

#
# Source for table user_role
#

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY (`userid`,`roleid`),
  KEY `roleid` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table user_role
#


#
# Source for table vedioscore
#

DROP TABLE IF EXISTS `vedioscore`;
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
  `comments` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `vedioExaminer` (`examiner`),
  KEY `vedioID` (`vedioID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='评分表';

#
# Dumping data for table vedioscore
#

INSERT INTO `vedioscore` VALUES (1,'2',2,24,35,46,56,36,1,0,0,'2010-01-30',NULL);

#
# Source for table vediotape
#

DROP TABLE IF EXISTS `vediotape`;
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

INSERT INTO `vediotape` VALUES ('0','阿凡达',1,3,1,'2001-01-30','2001-01-31',3,1,NULL,NULL,NULL);
INSERT INTO `vediotape` VALUES ('1','Fight Club',2,4,1,'2000-01-30','2001-01-31',3,1,NULL,NULL,NULL);
INSERT INTO `vediotape` VALUES ('2','12 Monkey',1,4,1,'2001-12-30','2001-01-31',3,2,NULL,NULL,NULL);

#
#  Foreign keys for table audiencescore
#

ALTER TABLE `audiencescore`
ADD CONSTRAINT `audiencescore_ibfk_1` FOREIGN KEY (`audienceID`) REFERENCES `audience` (`id`),
ADD CONSTRAINT `audiencescore_ibfk_2` FOREIGN KEY (`vedioID`) REFERENCES `vediotape` (`vedioID`);

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
ADD CONSTRAINT `vedioscore_ibfk_1` FOREIGN KEY (`vedioID`) REFERENCES `vediotape` (`vedioID`);

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
