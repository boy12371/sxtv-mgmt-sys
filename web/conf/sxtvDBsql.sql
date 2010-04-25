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
  `age` int(11) DEFAULT '0',
  `gender` int(4) DEFAULT '0',
  `career` varchar(50) DEFAULT '',
  `comments` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table audience
#


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table audiencescore
#


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
  KEY `auditor` (`auditor`),
  KEY `vedioID` (`vedioID`),
  KEY `result` (`result`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审核';

#
# Dumping data for table auditing
#


#
# Source for table company
#

CREATE TABLE `company` (
  `companyID` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(128) NOT NULL,
  `registrationNo` varchar(128) NOT NULL,
  `phone` varchar(50) DEFAULT '0',
  `contactPerson` varchar(50) DEFAULT '',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0=禁用,1=正常',
  `comments` varchar(512) DEFAULT '',
  PRIMARY KEY (`companyID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='公司信息';

#
# Dumping data for table company
#


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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='员工信息';

#
# Dumping data for table employee
#

INSERT INTO `employee` VALUES (1,'Admin','1000-01-01','1000-01-01',0,'0',1,'');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table playchangelog
#


#
# Source for table playorder
#

CREATE TABLE `playorder` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `playDate` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `vedioID` varchar(128) NOT NULL DEFAULT '',
  `arrangeDate` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `auditor` int(11) NOT NULL DEFAULT '0',
  `status` int(11) DEFAULT '1' COMMENT '0= 异常， 1=正常',
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

CREATE TABLE `resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(512) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

#
# Dumping data for table resources
#

INSERT INTO `resources` VALUES (1,'toAddingVedio.action','');
INSERT INTO `resources` VALUES (2,'isVediotapeExsits.action','');
INSERT INTO `resources` VALUES (3,'doAddingVedio.action','');
INSERT INTO `resources` VALUES (4,'toMarketRate.action','');
INSERT INTO `resources` VALUES (5,'toMarketRateModify.action','');
INSERT INTO `resources` VALUES (6,'searchVideoByName.action','');
INSERT INTO `resources` VALUES (7,'updateMarketRate.action','');
INSERT INTO `resources` VALUES (8,'searchVideoByNameOrID.action','');
INSERT INTO `resources` VALUES (9,'searchVideoByNameOrIDForModification.action','');
INSERT INTO `resources` VALUES (10,'searchVideoByNameOrIDForMarketRate.action','');
INSERT INTO `resources` VALUES (11,'searchVideoByNameOrIDForMarketRateModify.action','');
INSERT INTO `resources` VALUES (12,'updateVideoInfo.action','');
INSERT INTO `resources` VALUES (13,'toUpdateVideoInfo.action','');
INSERT INTO `resources` VALUES (14,'toVideoModification.action','');
INSERT INTO `resources` VALUES (15,'doModification.action','');
INSERT INTO `resources` VALUES (16,'doModificationBatch.action','');
INSERT INTO `resources` VALUES (17,'toEmployees.action','');
INSERT INTO `resources` VALUES (18,'getEmployees.action','');
INSERT INTO `resources` VALUES (19,'toAddEmployee.action','');
INSERT INTO `resources` VALUES (20,'toUpdateEmployee.action','');
INSERT INTO `resources` VALUES (21,'doUpdateEmployee.action','');
INSERT INTO `resources` VALUES (22,'doAddEmployee.action','');
INSERT INTO `resources` VALUES (23,'toCompanies.action','');
INSERT INTO `resources` VALUES (24,'getCompanies.action','');
INSERT INTO `resources` VALUES (25,'toUpdateCompany.action','');
INSERT INTO `resources` VALUES (26,'doUpdateCompany.action','');
INSERT INTO `resources` VALUES (27,'toAddCompany.action','');
INSERT INTO `resources` VALUES (28,'doAddCompany.action','');
INSERT INTO `resources` VALUES (29,'doDisableEnableCompany.action','');
INSERT INTO `resources` VALUES (30,'toSystemData.action','');
INSERT INTO `resources` VALUES (31,'getStatuses.action','');
INSERT INTO `resources` VALUES (32,'getTopices.action','');
INSERT INTO `resources` VALUES (33,'getSubjects.action','');
INSERT INTO `resources` VALUES (34,'getScoreWeights.action','');
INSERT INTO `resources` VALUES (35,'getScorelevels.action','');
INSERT INTO `resources` VALUES (36,'getUsers.action','');
INSERT INTO `resources` VALUES (37,'toAddUser.action','');
INSERT INTO `resources` VALUES (38,'doAddUser.action','');
INSERT INTO `resources` VALUES (39,'toUpdateUser.action','');
INSERT INTO `resources` VALUES (40,'doUpdateUser.action','');
INSERT INTO `resources` VALUES (41,'doChangePassword.action','');
INSERT INTO `resources` VALUES (42,'doAddSubject.action','');
INSERT INTO `resources` VALUES (43,'modifySubject.action','');
INSERT INTO `resources` VALUES (44,'doDisableEnableSubject.action','');
INSERT INTO `resources` VALUES (45,'doAddTopic.action','');
INSERT INTO `resources` VALUES (46,'modifyTopic.action','');
INSERT INTO `resources` VALUES (47,'doDisableEnableTopic.action','');
INSERT INTO `resources` VALUES (48,'toAllAudience.action','');
INSERT INTO `resources` VALUES (49,'getAllAudiences.action','');
INSERT INTO `resources` VALUES (50,'addAudience.action','');
INSERT INTO `resources` VALUES (51,'toAddAudience.action','');
INSERT INTO `resources` VALUES (52,'modifyWeight.action','');
INSERT INTO `resources` VALUES (53,'modifyLevel.action','');
INSERT INTO `resources` VALUES (54,'addScoreLevel.action','');
INSERT INTO `resources` VALUES (55,'autoCompleteForVideoName.action','');
INSERT INTO `resources` VALUES (56,'searchVideoByName.action','');
INSERT INTO `resources` VALUES (57,'toVideoDetail.action','');
INSERT INTO `resources` VALUES (58,'searchVideos.action','');
INSERT INTO `resources` VALUES (59,'toGenericSeaching.action','');
INSERT INTO `resources` VALUES (60,'toPrintVideosReport.action','');
INSERT INTO `resources` VALUES (61,'doPrintVideosReport.action','');
INSERT INTO `resources` VALUES (62,'toVideoSequence.action','');
INSERT INTO `resources` VALUES (63,'doSearchAndSequenceVideos.action','');
INSERT INTO `resources` VALUES (64,'toUnExaminedTapes.action','');
INSERT INTO `resources` VALUES (65,'toExaminedTapes.action','');
INSERT INTO `resources` VALUES (66,'getUnExaminedTapes.action','');
INSERT INTO `resources` VALUES (67,'getExaminedTapes.action','');
INSERT INTO `resources` VALUES (68,'toExamineTape.action','');
INSERT INTO `resources` VALUES (69,'doExamineTape.action','');
INSERT INTO `resources` VALUES (70,'doExamineTapeByInputer.action','');
INSERT INTO `resources` VALUES (71,'completeExamine.action','');
INSERT INTO `resources` VALUES (72,'toAudienceExamine.action','');
INSERT INTO `resources` VALUES (73,'getAudienceExamine.action','');
INSERT INTO `resources` VALUES (74,'doAudienceExamine.action','');
INSERT INTO `resources` VALUES (75,'toAuditVideos.action','');
INSERT INTO `resources` VALUES (76,'filterVideos.action','');
INSERT INTO `resources` VALUES (77,'getVideoScores.action','');
INSERT INTO `resources` VALUES (78,'videoOperation.action','');
INSERT INTO `resources` VALUES (79,'findVideoByNameOrID.action','');
INSERT INTO `resources` VALUES (80,'toArrange.action','');
INSERT INTO `resources` VALUES (81,'getUnarrangedTapes.action','');
INSERT INTO `resources` VALUES (82,'getArrangedTapes.action','');
INSERT INTO `resources` VALUES (83,'doArrange.action','');
INSERT INTO `resources` VALUES (84,'toArrangedHistory.action','');
INSERT INTO `resources` VALUES (85,'getArrangedHistory.action','');
INSERT INTO `resources` VALUES (86,'toAccuracy.action','');
INSERT INTO `resources` VALUES (87,'getAccuracy.action','');
INSERT INTO `resources` VALUES (88,'toArrangePrint.action','');
INSERT INTO `resources` VALUES (89, 'toPrintVideosSequenceOrderReport.action', '');
INSERT INTO `resources` VALUES (90, 'doPrintVideosSequenceOrderReport.action', '');
INSERT INTO `resources` VALUES (91, 'toAccuracyPrint.action','');
INSERT INTO `resources` VALUES (92, 'toAdjustVideoStatus', '');
INSERT INTO `resources` VALUES (93, 'searchVideoByNameOrIDForStatusAdjust', '');
INSERT INTO `resources` VALUES (94, 'doAdjustVideoStatus', '');
#
# Source for table role
#

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

#
# Dumping data for table role
#

INSERT INTO `role` VALUES (1,'系统管理员','管理员');
INSERT INTO `role` VALUES (2,'收录员','录入');
INSERT INTO `role` VALUES (3,'评分员','打分');
INSERT INTO `role` VALUES (4,'审核员','审核');
INSERT INTO `role` VALUES (5,'编排员','编排');
INSERT INTO `role` VALUES (6,'普通','普通');
INSERT INTO `role` VALUES (7,'档案管理员','观众管理');
INSERT INTO `role` VALUES (8,'评分管理员','影带信息修改，收集并录入所有评分');
INSERT INTO `role` VALUES (9,'信息回馈录入员','收视率，市场份额输入');
INSERT INTO `role` VALUES (10,'准确度查看','');

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
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8;

#
# Dumping data for table role_resource
#

INSERT INTO `role_resource` VALUES (1,1,17,'');
INSERT INTO `role_resource` VALUES (2,1,18,'');
INSERT INTO `role_resource` VALUES (3,1,19,'');
INSERT INTO `role_resource` VALUES (4,1,20,'');
INSERT INTO `role_resource` VALUES (5,1,21,'');
INSERT INTO `role_resource` VALUES (6,1,22,'');
INSERT INTO `role_resource` VALUES (7,1,23,'');
INSERT INTO `role_resource` VALUES (8,1,24,'');
INSERT INTO `role_resource` VALUES (9,1,25,'');
INSERT INTO `role_resource` VALUES (10,1,26,'');
INSERT INTO `role_resource` VALUES (11,1,27,'');
INSERT INTO `role_resource` VALUES (12,1,28,'');
INSERT INTO `role_resource` VALUES (13,1,29,'');
INSERT INTO `role_resource` VALUES (14,1,30,'');
INSERT INTO `role_resource` VALUES (15,1,31,'');
INSERT INTO `role_resource` VALUES (16,1,32,'');
INSERT INTO `role_resource` VALUES (17,1,33,'');
INSERT INTO `role_resource` VALUES (18,1,34,'');
INSERT INTO `role_resource` VALUES (19,1,35,'');
INSERT INTO `role_resource` VALUES (20,1,36,'');
INSERT INTO `role_resource` VALUES (21,1,37,'');
INSERT INTO `role_resource` VALUES (22,1,38,'');
INSERT INTO `role_resource` VALUES (23,1,39,'');
INSERT INTO `role_resource` VALUES (24,1,40,'');
INSERT INTO `role_resource` VALUES (25,1,41,'');
INSERT INTO `role_resource` VALUES (26,1,42,'');
INSERT INTO `role_resource` VALUES (27,1,43,'');
INSERT INTO `role_resource` VALUES (28,1,44,'');
INSERT INTO `role_resource` VALUES (29,1,45,'');
INSERT INTO `role_resource` VALUES (30,1,46,'');
INSERT INTO `role_resource` VALUES (31,1,47,'');
INSERT INTO `role_resource` VALUES (32,1,52,'');
INSERT INTO `role_resource` VALUES (33,1,53,'');
INSERT INTO `role_resource` VALUES (34,1,54,'');
INSERT INTO `role_resource` VALUES (35,6,57,'');
INSERT INTO `role_resource` VALUES (36,6,58,'');
INSERT INTO `role_resource` VALUES (37,6,59,'');
INSERT INTO `role_resource` VALUES (38,6,60,'');
INSERT INTO `role_resource` VALUES (39,6,61,'');
INSERT INTO `role_resource` VALUES (40,6,62,'');
INSERT INTO `role_resource` VALUES (41,6,63,'');
INSERT INTO `role_resource` VALUES (42,6,6,'');
INSERT INTO `role_resource` VALUES (43,2,1,'');
INSERT INTO `role_resource` VALUES (44,2,2,'');
INSERT INTO `role_resource` VALUES (45,2,3,'');
INSERT INTO `role_resource` VALUES (46,8,8,'');
INSERT INTO `role_resource` VALUES (47,8,12,'');
INSERT INTO `role_resource` VALUES (48,8,13,'');
INSERT INTO `role_resource` VALUES (49,4,75,'');
INSERT INTO `role_resource` VALUES (50,4,76,'');
INSERT INTO `role_resource` VALUES (51,4,77,'');
INSERT INTO `role_resource` VALUES (52,4,78,'');
INSERT INTO `role_resource` VALUES (53,4,79,'');
INSERT INTO `role_resource` VALUES (54,5,9,'');
INSERT INTO `role_resource` VALUES (55,5,14,'');
INSERT INTO `role_resource` VALUES (56,5,15,'');
INSERT INTO `role_resource` VALUES (57,5,16,'');
INSERT INTO `role_resource` VALUES (58,5,80,'');
INSERT INTO `role_resource` VALUES (59,5,81,'');
INSERT INTO `role_resource` VALUES (60,5,82,'');
INSERT INTO `role_resource` VALUES (61,5,83,'');
INSERT INTO `role_resource` VALUES (62,5,84,'');
INSERT INTO `role_resource` VALUES (63,5,85,'');
INSERT INTO `role_resource` VALUES (64,7,48,'');
INSERT INTO `role_resource` VALUES (65,7,49,'');
INSERT INTO `role_resource` VALUES (66,7,50,'');
INSERT INTO `role_resource` VALUES (67,7,51,'');
INSERT INTO `role_resource` VALUES (68,9,4,'');
INSERT INTO `role_resource` VALUES (69,9,5,'');
INSERT INTO `role_resource` VALUES (70,9,6,'');
INSERT INTO `role_resource` VALUES (71,9,10,'');
INSERT INTO `role_resource` VALUES (72,9,11,'');
INSERT INTO `role_resource` VALUES (73,9,76,'');
INSERT INTO `role_resource` VALUES (74,5,76,'');
INSERT INTO `role_resource` VALUES (75,10,86,'');
INSERT INTO `role_resource` VALUES (76,10,87,'');
INSERT INTO `role_resource` VALUES (77,3,64,'');
INSERT INTO `role_resource` VALUES (79,3,66,'');
INSERT INTO `role_resource` VALUES (81,3,68,'');
INSERT INTO `role_resource` VALUES (82,3,69,'');
INSERT INTO `role_resource` VALUES (88,2,41,'');
INSERT INTO `role_resource` VALUES (89,3,41,'');
INSERT INTO `role_resource` VALUES (90,4,41,'');
INSERT INTO `role_resource` VALUES (91,5,41,'');
INSERT INTO `role_resource` VALUES (92,6,41,'');
INSERT INTO `role_resource` VALUES (93,7,41,'');
INSERT INTO `role_resource` VALUES (94,8,41,'');
INSERT INTO `role_resource` VALUES (95,9,41,'');
INSERT INTO `role_resource` VALUES (96,1,6,'');
INSERT INTO `role_resource` VALUES (97,3,6,'');
INSERT INTO `role_resource` VALUES (98,4,6,'');
INSERT INTO `role_resource` VALUES (99,5,6,'');
INSERT INTO `role_resource` VALUES (100,2,6,'');
INSERT INTO `role_resource` VALUES (101,7,6,'');
INSERT INTO `role_resource` VALUES (102,8,6,'');
INSERT INTO `role_resource` VALUES (103,1,55,'');
INSERT INTO `role_resource` VALUES (104,2,55,'');
INSERT INTO `role_resource` VALUES (105,3,55,'');
INSERT INTO `role_resource` VALUES (106,4,55,'');
INSERT INTO `role_resource` VALUES (107,5,55,'');
INSERT INTO `role_resource` VALUES (108,6,55,'');
INSERT INTO `role_resource` VALUES (109,7,55,'');
INSERT INTO `role_resource` VALUES (110,8,55,'');
INSERT INTO `role_resource` VALUES (111,9,55,'');
INSERT INTO `role_resource` VALUES (112,8,2,'');
INSERT INTO `role_resource` VALUES (113,8,70,'');
INSERT INTO `role_resource` VALUES (114,8,71,'');
INSERT INTO `role_resource` VALUES (115,8,72,'');
INSERT INTO `role_resource` VALUES (116,8,73,'');
INSERT INTO `role_resource` VALUES (117,8,74,'');
INSERT INTO `role_resource` VALUES (118,8,64,'');
INSERT INTO `role_resource` VALUES (119,8,65,'');
INSERT INTO `role_resource` VALUES (120,8,66,'');
INSERT INTO `role_resource` VALUES (121,8,67,'');
INSERT INTO `role_resource` VALUES (123,8,68,'');
INSERT INTO `role_resource` VALUES (124,8,69,'');
INSERT INTO `role_resource` VALUES (125,9,7,'');
INSERT INTO `role_resource` VALUES (126,5,88,'');
INSERT INTO `role_resource` VALUES (127,6,89,'');
INSERT INTO `role_resource` VALUES (128,6,90,'');
INSERT INTO `role_resource` VALUES (129,10,91,'');
INSERT INTO `role_resource` VALUES (130,1,92,'');
INSERT INTO `role_resource` VALUES (131,1,93,'');
INSERT INTO `role_resource` VALUES (132,1,94,'');
#
# Source for table scorelevel
#

CREATE TABLE `scorelevel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` int(11) NOT NULL DEFAULT '0',
  `start` int(11) NOT NULL DEFAULT '0',
  `end` int(11) NOT NULL DEFAULT '0',
  `levelscore` float NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `level` (`level`),
  UNIQUE KEY `level_2` (`level`),
  KEY `level_3` (`level`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Dumping data for table scorelevel
#

INSERT INTO `scorelevel` VALUES (1,1,1,3,95);

#
# Source for table scoreweight
#

CREATE TABLE `scoreweight` (
  `factor` varchar(50) NOT NULL DEFAULT '',
  `weight` float NOT NULL DEFAULT '0',
  `weightName` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`factor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table scoreweight
#

INSERT INTO `scoreweight` VALUES ('innovateScore',0.1,'创新');
INSERT INTO `scoreweight` VALUES ('performScore',0.3,'表演');
INSERT INTO `scoreweight` VALUES ('storyScore',0.4,'故事');
INSERT INTO `scoreweight` VALUES ('techScore',0.2,'技术');

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
INSERT INTO `status` VALUES (7,'淘汰','审核未通过，直接退带');
INSERT INTO `status` VALUES (8,'已播','剧目已播出，尚未得到市场反馈');
INSERT INTO `status` VALUES (9,'结束','剧目已播出并收到市场反馈');

#
# Source for table subject
#

CREATE TABLE `subject` (
  `subjectID` int(11) NOT NULL AUTO_INCREMENT,
  `subjectName` varchar(128) NOT NULL,
  `status` int(11) NOT NULL COMMENT '0=禁用,1=正常',
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY (`subjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='栏目';

#
# Dumping data for table subject
#


#
# Source for table topic
#

CREATE TABLE `topic` (
  `topicID` int(11) NOT NULL AUTO_INCREMENT,
  `topicName` varchar(128) NOT NULL,
  `status` int(11) NOT NULL COMMENT '0=禁用,1=正常',
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY (`topicID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='题材';

#
# Dumping data for table topic
#


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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='用户';

#
# Dumping data for table user
#

INSERT INTO `user` VALUES (1,'Admin','e3afed0047b08059d0fada10f400c1e5',1,1);

#
# Source for table user_role
#

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `comments` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `union index` (`userid`,`roleid`),
  KEY `roleid` (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table user_role
#

INSERT INTO `user_role` VALUES (1,1,1,NULL);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分表';

#
# Dumping data for table vedioscore
#


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
