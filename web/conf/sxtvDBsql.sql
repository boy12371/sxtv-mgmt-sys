-- phpMyAdmin SQL Dump
-- version 3.2.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 05, 2010 at 09:49 PM
-- Server version: 5.0.75
-- PHP Version: 5.2.6-3ubuntu4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
CREATE DATABASE `sxtvdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sxtvdb`;

--
-- Database: `sxtvdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `audience`
--

CREATE TABLE IF NOT EXISTS `audience` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL default '',
  `age` int(11) default '0',
  `gender` int(4) default '0',
  `career` varchar(50) default '',
  `comments` varchar(255) default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `audience`
--

INSERT INTO `audience` (`id`, `name`, `age`, `gender`, `career`, `comments`) VALUES
(1, '张三', 1, 1, 'sad', 'asdasd'),
(2, '李四', 2, 0, 'sdfsd', 'sdfsd'),
(3, '王麻子', 2, 1, 'dfds', 'sdf'),
(4, '赵六', 0, 0, '', ''),
(5, '刘德华', 48, 1, '歌手', '老男人装嫩'),
(6, '郭富城', 47, 1, '歌手', '快老年人了'),
(7, '刘德华碎', 0, 1, '', ''),
(8, '斯蒂芬', 0, 1, '万恶非 ', '斯蒂芬我阿飞万恶'),
(9, '山东我', 0, 1, '范文芳', ' 风格如果');

-- --------------------------------------------------------

--
-- Table structure for table `audiencescore`
--

CREATE TABLE IF NOT EXISTS `audiencescore` (
  `id` int(11) NOT NULL auto_increment,
  `vedioID` varchar(128) NOT NULL default '0',
  `audienceID` int(11) NOT NULL default '0',
  `dateExamine` datetime NOT NULL default '1000-01-01 00:00:00',
  `result` int(11) NOT NULL default '0',
  `comments` varchar(512) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `unique_key` (`vedioID`,`audienceID`),
  KEY `vedioID` (`vedioID`),
  KEY `audienceID` (`audienceID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `audiencescore`
--

INSERT INTO `audiencescore` (`id`, `vedioID`, `audienceID`, `dateExamine`, `result`, `comments`) VALUES
(1, '0', 2, '2010-02-12 20:15:03', 1, NULL),
(2, '0', 3, '2010-02-13 09:41:43', 1, NULL),
(3, '0', 1, '2010-02-13 09:41:43', 1, NULL),
(4, '1', 3, '2010-02-12 18:36:48', 1, NULL),
(7, '0', 4, '2010-02-12 20:47:48', 1, NULL),
(8, '1', 4, '2010-02-12 20:55:29', 0, NULL),
(9, '23g', 1, '2010-02-28 18:33:39', 1, NULL),
(10, '23g', 2, '2010-02-28 18:33:39', 1, NULL),
(11, '1457', 1, '2010-03-08 23:06:46', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `auditing`
--

CREATE TABLE IF NOT EXISTS `auditing` (
  `id` int(11) NOT NULL auto_increment,
  `vedioID` varchar(128) NOT NULL default '',
  `auditor` int(11) NOT NULL,
  `auditDate` datetime NOT NULL default '1000-01-01 00:00:01',
  `result` int(11) NOT NULL,
  `comments` varchar(512) default '',
  PRIMARY KEY  (`id`),
  KEY `auditor` (`auditor`),
  KEY `vedioID` (`vedioID`),
  KEY `result` (`result`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='审核' AUTO_INCREMENT=66 ;

--
-- Dumping data for table `auditing`
--

INSERT INTO `auditing` (`id`, `vedioID`, `auditor`, `auditDate`, `result`, `comments`) VALUES
(1, '2', 2, '2010-02-12 12:25:44', 7, NULL),
(2, '2', 1, '2010-02-25 23:00:04', 7, NULL),
(3, '1457', 5, '2010-03-08 23:45:09', 3, NULL),
(4, '32ff2', 1, '2010-03-25 22:26:42', 5, NULL),
(5, '0', 1, '2010-03-25 22:26:47', 3, NULL),
(6, '4', 1, '2010-03-25 22:26:47', 3, NULL),
(7, 'sfw', 1, '2010-03-25 22:26:47', 3, NULL),
(8, 'sf1vsd', 1, '2010-03-25 22:26:47', 3, NULL),
(9, '32ff2', 1, '2010-03-25 22:35:16', 3, NULL),
(10, '0', 1, '2010-03-25 22:35:33', 5, NULL),
(11, '4', 1, '2010-03-25 22:35:33', 5, NULL),
(12, 'sfw', 1, '2010-03-25 22:35:33', 5, NULL),
(13, 'sf1vsd', 1, '2010-03-25 22:35:33', 5, NULL),
(14, '32ff2', 1, '2010-03-25 22:35:33', 5, NULL),
(15, '0', 1, '2010-03-25 22:35:38', 3, NULL),
(16, 'sf1vsd', 1, '2010-03-25 22:35:39', 3, NULL),
(17, '32ff2', 1, '2010-03-25 22:35:39', 3, NULL),
(18, '0', 1, '2010-03-25 22:38:35', 5, NULL),
(19, 'sf1vsd', 1, '2010-03-25 22:38:35', 5, NULL),
(20, '32ff2', 1, '2010-03-25 22:38:35', 5, NULL),
(21, '1457', 1, '2010-03-25 23:33:26', 7, NULL),
(22, '1457', 1, '2010-03-25 23:34:36', 3, NULL),
(23, '324aa', 1, '2010-03-25 23:34:57', 7, NULL),
(24, '1457', 1, '2010-03-27 19:53:48', 5, NULL),
(25, '0', 1, '2010-03-27 19:53:56', 3, NULL),
(26, '4', 1, '2010-03-27 19:53:56', 3, NULL),
(27, 'sfw', 1, '2010-03-27 19:53:56', 3, NULL),
(28, 'sf1vsd', 1, '2010-03-27 19:53:56', 3, NULL),
(29, '32ff2', 1, '2010-03-27 19:53:56', 3, NULL),
(30, '1457', 1, '2010-03-27 19:53:56', 3, NULL),
(31, '32ff2', 1, '2010-03-27 21:35:25', 5, NULL),
(32, '1457', 1, '2010-03-27 21:35:26', 5, NULL),
(33, '32ff2', 1, '2010-03-28 10:48:56', 3, NULL),
(34, '1457', 1, '2010-03-28 10:48:56', 3, NULL),
(35, '0', 1, '2010-03-28 10:51:00', 5, NULL),
(36, '4', 1, '2010-03-28 10:51:00', 5, NULL),
(37, 'sfw', 1, '2010-03-28 10:51:00', 5, NULL),
(38, 'sf1vsd', 1, '2010-03-28 10:51:00', 5, NULL),
(39, '32ff2', 1, '2010-03-28 10:51:00', 5, NULL),
(40, '1457', 1, '2010-03-28 10:51:00', 5, NULL),
(41, '32ff2', 1, '2010-03-28 10:51:13', 3, NULL),
(42, '32ff2', 1, '2010-03-28 11:02:58', 5, NULL),
(43, '1457', 1, '2010-03-28 17:09:39', 3, NULL),
(44, 'sfw', 1, '2010-03-28 22:56:56', 3, NULL),
(45, '32ff2', 1, '2010-03-28 22:57:05', 3, NULL),
(46, '1457', 1, '2010-03-28 22:57:10', 5, NULL),
(47, '1', 1, '2010-03-31 20:53:45', 7, NULL),
(48, '0', 1, '2010-03-31 21:48:10', 3, NULL),
(49, '0', 1, '2010-03-31 21:48:20', 5, NULL),
(50, '0', 1, '2010-03-31 21:48:26', 3, NULL),
(51, '12asd', 1, '2010-03-31 21:50:26', 3, NULL),
(52, '0', 1, '2010-03-31 21:51:25', 5, NULL),
(53, '12asd', 1, '2010-03-31 21:51:25', 5, NULL),
(54, 'sfw', 1, '2010-03-31 21:51:25', 5, NULL),
(55, '32ff2', 1, '2010-03-31 21:51:25', 5, NULL),
(56, '0', 1, '2010-03-31 21:51:54', 3, NULL),
(57, '4', 1, '2010-03-31 21:51:54', 3, NULL),
(58, '12asd', 1, '2010-03-31 21:51:54', 3, NULL),
(59, 'sfw', 1, '2010-03-31 21:51:55', 3, NULL),
(60, 'sf1vsd', 1, '2010-03-31 21:51:55', 3, NULL),
(61, '32ff2', 1, '2010-03-31 21:51:55', 3, NULL),
(62, '1457', 1, '2010-03-31 21:51:55', 3, NULL),
(63, '0', 1, '2010-03-31 21:52:54', 5, NULL),
(64, '4', 1, '2010-03-31 21:52:54', 5, NULL),
(65, '12asd', 1, '2010-03-31 21:52:54', 5, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE IF NOT EXISTS `company` (
  `companyID` int(11) NOT NULL auto_increment,
  `companyName` varchar(128) NOT NULL,
  `registrationNo` varchar(128) NOT NULL,
  `phone` varchar(50) default '0',
  `contactPerson` varchar(50) default '',
  `status` int(11) NOT NULL default '1' COMMENT '0=禁用,1=正常',
  `comments` varchar(512) default '',
  PRIMARY KEY  (`companyID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='公司信息' AUTO_INCREMENT=10 ;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`companyID`, `companyName`, `registrationNo`, `phone`, `contactPerson`, `status`, `comments`) VALUES
(1, '中华影视公司', '12331123123', '34534534534', '胡戈', 1, '第三方万恶服务'),
(2, '美国影视', '123123', '123123', '万恶范围阿飞', 1, '发送到非 '),
(3, '法国影视', '34234', 'werwef', 'fwefwef', 1, '发送到非万恶 '),
(4, '3werwer', '', '234234', 'fwefwef', 1, ''),
(5, '土耳其影视公司', '8987786', '', '', 1, ''),
(6, 'dsvsdv', 'fwefwefwefwef', '234234', '快速', 1, 'ffffffffffffffffffffffwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwefwefwefwef'),
(7, '韩国影视公司', '1231209809302983', '09807986', '乐山大佛', 1, '似懂非懂发'),
(8, 'sdf ', '', '', '', 1, ''),
(9, '奥地利影视公司', '123123', '839227', '蕾丝', 1, '违反违反');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `employeeID` int(11) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `birthday` date default '1000-01-01',
  `contractDate` date NOT NULL default '1000-01-01',
  `gender` int(11) NOT NULL default '0',
  `tel` varchar(50) default '0',
  `status` int(11) NOT NULL default '1' COMMENT '0=禁用，1=正常',
  `comments` varchar(512) default '',
  PRIMARY KEY  (`employeeID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='员工信息' AUTO_INCREMENT=13 ;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employeeID`, `name`, `birthday`, `contractDate`, `gender`, `tel`, `status`, `comments`) VALUES
(1, '王文婷', '1983-02-19', '2006-01-16', 0, '13991366931', 1, 'dddd'),
(2, '王萌', '2010-01-21', '1970-01-01', 0, '13991366930', 1, '王萌王萌王萌王萌王萌王萌王萌王萌王萌王萌'),
(3, '周强', '2010-01-21', '2010-01-21', 1, '13991366931', 1, '这是一个员工'),
(4, '周zhou', '2010-01-21', '2010-01-21', 1, '13991366931', 1, '这是一个员工'),
(5, '李斯', '2010-01-25', '2010-01-20', 1, '123123123', 1, '的期望的钱'),
(6, '张武', '2006-01-09', '2007-01-15', 0, '123124234', 0, '撕得粉碎大放送'),
(7, '赵流', '2006-01-16', '2008-01-22', 0, '123123', 1, '扫的服务阿飞'),
(8, '王其', '2010-01-17', '1982-01-12', 0, '维吾尔', 1, '斯蒂芬'),
(9, '身体', '2010-03-24', '2010-03-24', 1, '3fwefwe', 1, 'sdfsdf'),
(10, '色图', '2010-03-24', '2010-03-24', 1, '', 1, 'asdqwd'),
(11, '章子怡', '2010-04-01', '2010-04-01', 1, '', 1, ''),
(12, '李宇春', '2010-04-05', '2010-04-05', 1, '的服务俄', 1, '无关热管');

-- --------------------------------------------------------

--
-- Table structure for table `playchangelog`
--

CREATE TABLE IF NOT EXISTS `playchangelog` (
  `Id` int(11) NOT NULL auto_increment,
  `vedioID` varchar(128) NOT NULL default '',
  `auditor` int(11) NOT NULL default '0',
  `fromDate` datetime default '1000-01-01 00:00:00',
  `toDate` datetime default '1000-01-01 00:00:00',
  `operation` varchar(50) default '',
  `date` datetime NOT NULL default '1000-01-01 00:00:00',
  PRIMARY KEY  (`Id`),
  KEY `auditor` (`auditor`),
  KEY `vedioID` (`vedioID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=27 ;

--
-- Dumping data for table `playchangelog`
--

INSERT INTO `playchangelog` (`Id`, `vedioID`, `auditor`, `fromDate`, `toDate`, `operation`, `date`) VALUES
(16, '4', 2, '2010-02-02 00:00:00', '2010-02-01 00:00:00', '更新', '2010-02-26 23:40:50'),
(17, '3', 2, '2010-02-03 00:00:00', '2010-02-02 00:00:00', '更新', '2010-02-26 23:40:50'),
(18, '1', 2, '2010-02-04 00:00:00', '2010-02-03 00:00:00', '更新', '2010-02-26 23:40:50'),
(22, '4', 2, NULL, '2010-02-04 00:00:00', '加入', '2010-02-27 17:16:22'),
(23, 'sfw', 2, NULL, '2010-02-05 00:00:00', '加入', '2010-02-27 17:16:22'),
(24, '4', 2, '2010-02-04 00:00:00', NULL, '移除', '2010-02-27 17:16:58'),
(25, 'sfw', 2, '2010-02-05 00:00:00', NULL, '移除', '2010-02-27 17:16:58'),
(26, '1457', 5, NULL, '2010-03-28 00:00:00', '加入', '2010-03-08 23:50:14');

-- --------------------------------------------------------

--
-- Table structure for table `playorder`
--

CREATE TABLE IF NOT EXISTS `playorder` (
  `Id` int(11) NOT NULL auto_increment,
  `playDate` datetime NOT NULL default '1000-01-01 00:00:00',
  `vedioID` varchar(128) NOT NULL default '',
  `arrangeDate` datetime NOT NULL default '1000-01-01 00:00:00',
  `auditor` int(11) NOT NULL default '0',
  PRIMARY KEY  (`Id`),
  KEY `vedioID` (`vedioID`),
  KEY `auditor` (`auditor`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='节目编排' AUTO_INCREMENT=34 ;

--
-- Dumping data for table `playorder`
--

INSERT INTO `playorder` (`Id`, `playDate`, `vedioID`, `arrangeDate`, `auditor`) VALUES
(28, '2010-02-02 00:00:00', '3', '2010-02-26 23:40:50', 2),
(30, '2010-02-03 00:00:00', '1', '2010-02-26 23:40:50', 2),
(32, '2010-02-01 00:00:00', 'sw12', '2010-02-27 16:59:47', 2),
(33, '2010-04-03 00:00:00', '1457', '2010-03-08 23:50:14', 5);

-- --------------------------------------------------------

--
-- Table structure for table `resources`
--

CREATE TABLE IF NOT EXISTS `resources` (
  `id` int(11) NOT NULL auto_increment,
  `url` varchar(512) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=88 ;

--
-- Dumping data for table `resources`
--

INSERT INTO `resources` (`id`, `url`, `comments`) VALUES
(1, 'toAddingVedio.action', ''),
(2, 'isVediotapeExsits.action', ''),
(3, 'doAddingVedio.action', ''),
(4, 'toMarketRate.action', ''),
(5, 'toMarketRateModify.action', ''),
(6, 'searchVideoByName.action', ''),
(7, 'updateMarketRate.action', ''),
(8, 'searchVideoByNameOrID.action', ''),
(9, 'searchVideoByNameOrIDForModification.action', ''),
(10, 'searchVideoByNameOrIDForMarketRate.action', ''),
(11, 'searchVideoByNameOrIDForMarketRateModify.action', ''),
(12, 'updateVideoInfo.action', ''),
(13, 'toUpdateVideoInfo.action', ''),
(14, 'toVideoModification.action', ''),
(15, 'doModification.action', ''),
(16, 'doModificationBatch.action', ''),
(17, 'toEmployees.action', ''),
(18, 'getEmployees.action', ''),
(19, 'toAddEmployee.action', ''),
(20, 'toUpdateEmployee.action', ''),
(21, 'doUpdateEmployee.action', ''),
(22, 'doAddEmployee.action', ''),
(23, 'toCompanies.action', ''),
(24, 'getCompanies.action', ''),
(25, 'toUpdateCompany.action', ''),
(26, 'doUpdateCompany.action', ''),
(27, 'toAddCompany.action', ''),
(28, 'doAddCompany.action', ''),
(29, 'doDisableEnableCompany.action', ''),
(30, 'toSystemData.action', ''),
(31, 'getStatuses.action', ''),
(32, 'getTopices.action', ''),
(33, 'getSubjects.action', ''),
(34, 'getScoreWeights.action', ''),
(35, 'getScorelevels.action', ''),
(36, 'getUsers.action', ''),
(37, 'toAddUser.action', ''),
(38, 'doAddUser.action', ''),
(39, 'toUpdateUser.action', ''),
(40, 'doUpdateUser.action', ''),
(41, 'doChangePassword.action', ''),
(42, 'doAddSubject.action', ''),
(43, 'modifySubject.action', ''),
(44, 'doDisableEnableSubject.action', ''),
(45, 'doAddTopic.action', ''),
(46, 'modifyTopic.action', ''),
(47, 'doDisableEnableTopic.action', ''),
(48, 'toAllAudience.action', ''),
(49, 'getAllAudiences.action', ''),
(50, 'addAudience.action', ''),
(51, 'toAddAudience.action', ''),
(52, 'modifyWeight.action', ''),
(53, 'modifyLevel.action', ''),
(54, 'addScoreLevel.action', ''),
(55, 'autoCompleteForVideoName.action', ''),
(56, 'searchVideoByName.action', ''),
(57, 'toVideoDetail.action', ''),
(58, 'searchVideos.action', ''),
(59, 'toGenericSeaching.action', ''),
(60, 'toPrintVideosReport.action', ''),
(61, 'doPrintVideosReport.action', ''),
(62, 'toVideoSequence.action', ''),
(63, 'doSearchAndSequenceVideos.action', ''),
(64, 'toUnExaminedTapes.action', ''),
(65, 'toExaminedTapes.action', ''),
(66, 'getUnExaminedTapes.action', ''),
(67, 'getExaminedTapes.action', ''),
(68, 'toExamineTape.action', ''),
(69, 'doExamineTape.action', ''),
(70, 'doExamineTapeByInputer.action', ''),
(71, 'completeExamine.action', ''),
(72, 'toAudienceExamine.action', ''),
(73, 'getAudienceExamine.action', ''),
(74, 'doAudienceExamine.action', ''),
(75, 'toAuditVideos.action', ''),
(76, 'filterVideos.action', ''),
(77, 'getVideoScores.action', ''),
(78, 'videoOperation.action', ''),
(79, 'findVideoByNameOrID.action', ''),
(80, 'toArrange.action', ''),
(81, 'getUnarrangedTapes.action', ''),
(82, 'getArrangedTapes.action', ''),
(83, 'doArrange.action', ''),
(84, 'toArrangedHistory.action', ''),
(85, 'getArrangedHistory.action', ''),
(86, 'toAccuracy.action', ''),
(87, 'getAccuracy.action', '');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(25) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`, `comments`) VALUES
(1, '系统管理员', '管理员'),
(2, '收录员', '录入'),
(3, '评分员', '打分'),
(4, '审核员', '审核'),
(5, '编排员', '编排'),
(6, '普通', '普通'),
(7, '档案管理员', '观众管理'),
(8, '评分管理员', '影带信息修改，收集并录入所有评分'),
(9, '信息回馈录入员', '收视率，市场份额输入'),
(10, '准确度查看', '');

-- --------------------------------------------------------

--
-- Table structure for table `role_resource`
--

CREATE TABLE IF NOT EXISTS `role_resource` (
  `id` int(11) NOT NULL auto_increment,
  `roleid` int(11) NOT NULL,
  `resourceid` int(11) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `union_key` (`roleid`,`resourceid`),
  KEY `resourceid` (`resourceid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=113 ;

--
-- Dumping data for table `role_resource`
--

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
INSERT INTO `role_resource` VALUES (78,3,65,'');
INSERT INTO `role_resource` VALUES (79,3,66,'');
INSERT INTO `role_resource` VALUES (80,3,67,'');
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

-- --------------------------------------------------------

--
-- Table structure for table `scorelevel`
--

CREATE TABLE IF NOT EXISTS `scorelevel` (
  `id` int(11) NOT NULL auto_increment,
  `level` int(11) NOT NULL default '0',
  `start` int(11) NOT NULL default '0',
  `end` int(11) NOT NULL default '0',
  `levelscore` float NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `level` (`level`),
  UNIQUE KEY `level_2` (`level`),
  KEY `level_3` (`level`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `scorelevel`
--

INSERT INTO `scorelevel` (`id`, `level`, `start`, `end`, `levelscore`) VALUES
(1, 1, 1, 2, 95),
(2, 2, 3, 10, 90),
(3, 3, 11, 20, 85),
(4, 4, 21, 50, 80),
(6, 5, 51, 100, 70),
(7, 6, 1000, 1231231, 45);

-- --------------------------------------------------------

--
-- Table structure for table `scoreweight`
--

CREATE TABLE IF NOT EXISTS `scoreweight` (
  `factor` varchar(50) NOT NULL default '',
  `weight` float NOT NULL default '0',
  `weightName` varchar(128) default NULL,
  PRIMARY KEY  (`factor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `scoreweight`
--

INSERT INTO `scoreweight` (`factor`, `weight`, `weightName`) VALUES
('innovateScore', 0.1, '创新'),
('performScore', 0.4, '表演'),
('storyScore', 0.3, '故事'),
('techScore', 0.2, '技术');

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE IF NOT EXISTS `status` (
  `statusID` int(11) NOT NULL auto_increment,
  `status` varchar(50) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY  (`statusID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='状态' AUTO_INCREMENT=10 ;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`statusID`, `status`, `comments`) VALUES
(1, '待评', '新进剧目，带评分'),
(2, '待审', '剧目以评分，等待审核'),
(3, '通过', '审核已通过，等待指定播放时间'),
(5, '待排', '剧目已经通过并等待编排。'),
(6, '待播', '审核通过，并已指定时间，等待播放'),
(7, '淘汰', '审核未通过，直接退带'),
(8, '已播', '剧目已播出，尚未得到市场反馈'),
(9, '结束', '剧目已播出并收到市场反馈');

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE IF NOT EXISTS `subject` (
  `subjectID` int(11) NOT NULL auto_increment,
  `subjectName` varchar(128) NOT NULL,
  `status` int(11) NOT NULL COMMENT '0=禁用,1=正常',
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY  (`subjectID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='栏目' AUTO_INCREMENT=8 ;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`subjectID`, `subjectName`, `status`, `comments`) VALUES
(1, '都市碎戏1', 1, '都市都市都市都市都市1'),
(2, '百家碎戏', 1, '百家碎戏百家碎戏百家碎戏百家碎戏百家碎戏'),
(3, '狼人虎剧', 0, '西安电视台'),
(4, '物业剧场', 0, '斯蒂芬'),
(5, 'asdf', 1, 'sdfwef'),
(6, '栏目B', 1, 'B栏目B栏目B'),
(7, '栏目A', 1, 'fwef');

-- --------------------------------------------------------

--
-- Table structure for table `topic`
--

CREATE TABLE IF NOT EXISTS `topic` (
  `topicID` int(11) NOT NULL auto_increment,
  `topicName` varchar(128) NOT NULL,
  `status` int(11) NOT NULL COMMENT '0=禁用,1=正常',
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY  (`topicID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='题材' AUTO_INCREMENT=13 ;

--
-- Dumping data for table `topic`
--

INSERT INTO `topic` (`topicID`, `topicName`, `status`, `comments`) VALUES
(1, '言情', 1, '言情言情'),
(2, '社会', 1, '社会社会社会社会社会5u56u'),
(3, '生活', 1, '生活生活生活生活生活生活'),
(4, '社会', 1, '社会社会社会社会社会5u56u'),
(5, '民生', 1, '民生民生民生民生'),
(6, '爱情', 1, '爱情爱情爱情'),
(7, '科技', 1, '科技科技科技'),
(8, '学术', 1, '学术学术学术'),
(9, '探秘', 1, '探秘探秘探秘'),
(10, '考古', 1, '考古考古考古'),
(11, '历史', 1, '历史历史历史ff'),
(12, '友情', 1, '友情友情友情');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userID` int(11) NOT NULL auto_increment,
  `userName` varchar(50) NOT NULL default '',
  `userPass` varchar(50) NOT NULL default '',
  `employee` int(11) NOT NULL default '0',
  `status` int(11) NOT NULL default '1' COMMENT '0=禁用，1=正常',
  PRIMARY KEY  (`userID`),
  KEY `userEmployee` (`employee`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户' AUTO_INCREMENT=13 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userID`, `userName`, `userPass`, `employee`, `status`) VALUES
(1, 'tiger', '43b90920409618f188bfc6923f16b9fa', 1, 1),
(2, 'cat', 'c4ca4238a0b923820dcc509a6f75849b', 2, 1),
(3, 'pencil', 'a8f6830bce790a8a67fc2e84e12093ba', 3, 1),
(4, 'dog', '123', 4, 1),
(5, 'chick', '123', 5, 1),
(9, 'fefwefwef', 'weffef', 7, 1),
(10, 'cewerwere', 'fwefwef', 8, 1),
(11, 'fdweffff', 'ffffff', 9, 1),
(12, 'zhangziyi', 'dc198f0856e0563246d3acca724cf5cd', 11, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
  `id` int(11) NOT NULL auto_increment,
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `comments` varchar(512) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `union index` (`userid`,`roleid`),
  KEY `roleid` (`roleid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC AUTO_INCREMENT=13 ;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`id`, `userid`, `roleid`, `comments`) VALUES
(8, 12, 2, NULL),
(10, 2, 2, NULL),
(11, 1, 1, NULL),
(12, 1, 6, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `vedioscore`
--

CREATE TABLE IF NOT EXISTS `vedioscore` (
  `id` int(11) NOT NULL auto_increment,
  `vedioID` varchar(128) NOT NULL default '',
  `examiner` int(11) NOT NULL,
  `storyScore` float NOT NULL,
  `techScore` float NOT NULL,
  `performScore` float NOT NULL,
  `innovateScore` float NOT NULL,
  `score` float NOT NULL,
  `award` int(11) NOT NULL default '0',
  `accuracy` float default NULL,
  `purchase` int(11) NOT NULL default '0',
  `dateExamine` datetime NOT NULL default '1000-01-01 00:00:00',
  `operator` int(11) NOT NULL default '0',
  `orientation` int(11) NOT NULL default '0',
  `comments` varchar(512) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `unique_key` (`vedioID`,`examiner`),
  KEY `vedioExaminer` (`examiner`),
  KEY `vedioID` (`vedioID`),
  KEY `operator` (`operator`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='评分表' AUTO_INCREMENT=23 ;

--
-- Dumping data for table `vedioscore`
--

INSERT INTO `vedioscore` (`id`, `vedioID`, `examiner`, `storyScore`, `techScore`, `performScore`, `innovateScore`, `score`, `award`, `accuracy`, `purchase`, `dateExamine`, `operator`, `orientation`, `comments`) VALUES
(1, '2', 2, 22, 88, 66, 99, 56.1, 1, NULL, 1, '2010-02-24 23:54:54', 1, 0, NULL),
(2, '23g', 2, 12, 11, 67, 45, 31.6, 0, NULL, 0, '2010-02-27 22:09:25', 1, 0, NULL),
(3, '12', 2, 22, 22, 22, 22, 22, 1, NULL, 1, '2010-03-08 22:53:29', 1, 0, NULL),
(4, '1457', 2, 77, 77, 77, 77, 77, 1, NULL, 1, '2010-03-08 23:06:19', 1, 0, NULL),
(5, '1457', 4, 99, 99, 99, 99, 99, 0, NULL, 0, '2010-03-08 23:08:01', 1, 0, NULL),
(9, '1457', 5, 67, 67, 67, 67, 67, 0, NULL, 0, '2010-03-08 23:25:09', 1, 0, NULL),
(11, '1', 2, 66, 77, 88, 99, 77, 0, 17, 0, '2010-02-11 00:00:00', 1, 0, NULL),
(12, '1', 4, 56, 67, 78, 89, 80, 0, 20, 0, '2010-02-12 00:00:00', 1, 0, NULL),
(13, '1', 5, 34, 34, 45, 23, 60, 0, 1, 0, '2010-02-21 00:00:00', 1, 0, NULL),
(14, '3', 2, 88, 88, 88, 88, 88, 0, 8, 0, '2010-02-01 00:00:00', 1, 0, NULL),
(15, '3', 4, 66, 66, 66, 66, 66, 0, 14, 0, '2010-02-01 00:00:00', 1, 0, NULL),
(16, '3', 5, 44, 44, 44, 44, 44, 0, 1, 0, '2010-02-01 00:00:00', 1, 0, NULL),
(17, 'sw12', 2, 55, 55, 55, 55, 55, 0, 5, 0, '2010-02-01 00:00:00', 1, 0, NULL),
(18, 'sw12', 4, 67, 67, 67, 67, 67, 0, 17, 0, '2010-02-01 00:00:00', 1, 0, NULL),
(19, 'sw12', 5, 33, 33, 33, 33, 33, 0, 1, 0, '2010-02-01 00:00:00', 1, 0, NULL),
(20, '23g', 1, 0.5, 0.23, 0.4, 0.6, 0.546, 0, NULL, 0, '2010-03-23 23:55:58', 1, 1, NULL),
(21, '9991', 1, 20, 25, 35, 45, 37, 1, NULL, 1, '2010-03-28 11:22:40', 1, 0, NULL),
(22, '13sfw2w', 2, 32, 33, 34, 35, 40.1, 1, NULL, 1, '2010-03-31 23:19:09', 1, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `vediotape`
--

CREATE TABLE IF NOT EXISTS `vediotape` (
  `vedioID` varchar(128) NOT NULL default '0',
  `vedioName` varchar(128) NOT NULL,
  `subject` int(11) NOT NULL,
  `topic` int(11) NOT NULL,
  `companyID` int(11) NOT NULL,
  `dateComing` date NOT NULL default '1000-01-01',
  `dateInput` datetime NOT NULL default '1000-01-01 00:00:00',
  `inputer` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `comments` varchar(512) default NULL,
  `marketShare` float default NULL,
  `audienceRating` float default NULL,
  PRIMARY KEY  (`vedioID`),
  KEY `vedioCompany` (`companyID`),
  KEY `vedioStatus` (`status`),
  KEY `vedioSubject` (`subject`),
  KEY `vedioTopic` (`topic`),
  KEY `inputer` (`inputer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `vediotape`
--

INSERT INTO `vediotape` (`vedioID`, `vedioName`, `subject`, `topic`, `companyID`, `dateComing`, `dateInput`, `inputer`, `status`, `comments`, `marketShare`, `audienceRating`) VALUES
('0', '阿凡达', 1, 3, 1, '2001-01-30', '2001-01-31 00:00:00', 3, 5, 'sdfsdf撒旦佛诶我始终里斯哦我里斯哦诶提问品味来刺激斯蒂夫俄，每次vd sdvs   老婆的哦fsw  瑞特全文俄热', NULL, NULL),
('1', 'Fight Club', 2, 4, 1, '2000-01-30', '2001-01-31 00:00:00', 3, 7, NULL, 0.5, 0.6),
('12', '12qdfw', 2, 3, 1, '2010-02-11', '2010-02-11 08:44:47', 1, 1, 'ddvsdfsdsdvsdv', NULL, NULL),
('123s', 'fwefxfdg', 1, 4, 1, '2010-03-25', '2010-03-25 22:04:05', 1, 1, 'sdfwef', NULL, NULL),
('12asd', 'dqw', 1, 1, 1, '2010-02-10', '2010-02-10 14:17:59', 1, 5, 'asdacasc', NULL, NULL),
('12dq', 'dqwd', 2, 1, 1, '2010-02-10', '2010-02-10 14:10:59', 1, 6, 'zxcasc', NULL, NULL),
('13sfw2w', '我们都是炎黄子孙', 3, 5, 5, '2010-03-31', '2010-03-31 23:17:47', 1, 1, 'fwefwefwefwefwef', NULL, NULL),
('1457', 'X战警', 2, 4, 2, '2010-03-08', '2010-03-08 22:55:39', 2, 8, 'xxxxxx', 70, 50),
('1ewfv', 'zxvcsdv', 1, 3, 1, '2010-02-10', '2010-02-10 14:21:42', 1, 2, 'sdv', NULL, NULL),
('1ssd', 'zxcasc', 1, 3, 1, '2010-02-10', '2010-02-10 14:10:59', 1, 1, 'zxcasczx zx zx zx zx ', NULL, NULL),
('2', '12 Monkey', 2, 4, 2, '2001-12-30', '2001-01-31 00:00:00', 3, 7, '', NULL, NULL),
('21323', '334', 1, 1, 7, '2010-04-03', '2010-04-03 23:39:47', 1, 1, 'erwer3r', NULL, NULL),
('21365', '爱丽丝', 2, 6, 5, '2010-04-03', '2010-04-03 23:39:47', 1, 1, 'erwer3r', NULL, NULL),
('23442', '的份额', 3, 6, 5, '2010-04-03', '2010-04-03 22:21:40', 1, 1, 'sdfe', NULL, NULL),
('23444', 'fwed', 2, 3, 2, '2010-04-03', '2010-04-03 22:21:40', 1, 1, 'sdfe', NULL, NULL),
('23eg', 'fwefwef', 1, 2, 1, '2010-02-10', '2010-02-10 14:21:42', 1, 2, 'sdvvsdv', NULL, NULL),
('23g', 'dfbdfbf', 2, 4, 1, '2010-02-10', '2010-02-10 14:21:42', 1, 1, 'sdvvsdv', NULL, NULL),
('3', '蝴蝶效应', 2, 3, 1, '2009-01-01', '2009-01-02 00:00:00', 3, 2, NULL, 0.7, 0.8),
('324aa', 'sfwe43', 2, 1, 1, '2010-02-10', '2010-02-10 14:21:42', 1, 7, 'sdvvsdv', NULL, NULL),
('32f', 'fwef', 2, 3, 2, '2010-02-12', '2010-02-12 10:33:18', 1, 1, 'sd', NULL, NULL),
('32ff2', 'ffewef', 1, 2, 3, '2010-02-12', '2010-02-12 10:33:18', 1, 3, 'sdfwe', NULL, NULL),
('3334df', 'dsss', 1, 1, 2, '2010-03-28', '2010-03-28 09:43:42', 1, 1, 'cvsdv', NULL, NULL),
('33422', '里斯哦俄', 1, 2, 2, '2010-04-05', '2010-04-05 21:04:14', 2, 1, 'fefwefwef', NULL, NULL),
('4', '超黑特警', 2, 4, 1, '2009-02-01', '2009-02-02 00:00:00', 3, 5, NULL, NULL, NULL),
('452s11', 'ddfe1', 1, 3, 2, '2010-03-25', '2010-03-25 22:17:55', 1, 1, 'xcvsdvsdvsdvs', NULL, NULL),
('452s12', 'ddfe2', 1, 3, 1, '2010-03-25', '2010-03-25 22:17:55', 1, 1, 'xcvsdvsdvsdvs', NULL, NULL),
('452s13', 'ddfe3', 1, 3, 2, '2010-03-25', '2010-03-25 22:17:55', 1, 1, 'xcvsdvsdvsdvs', NULL, NULL),
('452s14', 'ddfe4', 1, 3, 5, '2010-03-25', '2010-03-25 22:17:55', 1, 9, 'xcvsdvsdvsdvs', 56.7, 45.3),
('452s15', 'ddfe5', 2, 3, 5, '2010-03-25', '2010-03-25 22:17:55', 1, 9, 'xcvsdvsdvsdvs', 47.5, 56),
('452s1d', 'ddfe', 1, 2, 2, '2010-03-25', '2010-03-25 22:17:55', 1, 1, 'xcvsdvsdvsdvs', NULL, NULL),
('452s6', 'ddfe6', 2, 3, 5, '2010-03-25', '2010-03-25 22:17:55', 1, 1, 'xcvsdvsdvsdvs', NULL, NULL),
('452s7', 'ddfe7', 2, 3, 5, '2010-03-25', '2010-03-25 22:17:55', 1, 1, 'xcvsdvsdvsdvs', NULL, NULL),
('452s8', 'ddfe8', 2, 3, 3, '2010-03-25', '2010-03-25 22:17:55', 1, 1, 'xcvsdvsdvsdvs', NULL, NULL),
('452s9', 'ddfe9', 2, 2, 7, '2010-03-25', '2010-03-25 22:17:55', 1, 1, 'xcvsdvsdvsdvs', NULL, NULL),
('452sd', 'fwfwef', 1, 2, 1, '2010-03-25', '2010-03-25 22:17:55', 1, 1, 'xcvsdvsdv', NULL, NULL),
('6556hg', 'hhthth', 2, 3, 1, '2010-03-25', '2010-03-25 22:05:44', 1, 1, 'bfgbfg', NULL, NULL),
('6880hg', 'ghrh', 1, 1, 3, '2010-03-25', '2010-03-25 22:05:44', 1, 1, 'vbnfgnfgn', NULL, NULL),
('9991', '老大的幸福', 1, 3, 3, '2010-03-28', '2010-03-28 11:21:21', 1, 1, '纷飞', NULL, NULL),
('9992', '老二的幸福', 2, 4, 1, '2010-03-28', '2010-03-28 11:21:21', 1, 1, '纷纷', NULL, NULL),
('dthw23', 'rhrhrth', 2, 3, 1, '2010-03-25', '2010-03-25 22:05:44', 1, 1, 'bfgbfg', NULL, NULL),
('ert34', 'fggrgrg', 1, 3, 1, '2010-03-25', '2010-03-25 22:05:44', 1, 9, 'bfgbfg', 43.2, 30.5),
('sf1', 'sdfw', 1, 1, 1, '2010-02-11', '2010-02-11 08:44:47', 1, 9, 'ddv', 34, 37),
('sf1vsd', 'weqdfw', 1, 1, 1, '2010-02-11', '2010-02-11 08:44:47', 1, 3, 'ddvsdfsd', NULL, NULL),
('sfw', '1v2asdsv', 1, 1, 1, '2010-02-10', '2010-02-10 14:20:35', 1, 3, 'sdvsdv', NULL, NULL),
('sw12', 'dqwe', 2, 3, 1, '2010-02-12', '2010-02-12 10:33:18', 1, 9, 'ssdv', 47, 43);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `audiencescore`
--
ALTER TABLE `audiencescore`
  ADD CONSTRAINT `audiencescore_ibfk_1` FOREIGN KEY (`vedioID`) REFERENCES `vediotape` (`vedioID`),
  ADD CONSTRAINT `audiencescore_ibfk_2` FOREIGN KEY (`audienceID`) REFERENCES `audience` (`id`);

--
-- Constraints for table `auditing`
--
ALTER TABLE `auditing`
  ADD CONSTRAINT `auditing_ibfk_1` FOREIGN KEY (`vedioID`) REFERENCES `vediotape` (`vedioID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `auditing_ibfk_2` FOREIGN KEY (`auditor`) REFERENCES `user` (`userID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `auditing_ibfk_3` FOREIGN KEY (`result`) REFERENCES `status` (`statusID`) ON UPDATE CASCADE;

--
-- Constraints for table `playchangelog`
--
ALTER TABLE `playchangelog`
  ADD CONSTRAINT `playchangelog_ibfk_1` FOREIGN KEY (`auditor`) REFERENCES `user` (`userID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `playchangelog_ibfk_2` FOREIGN KEY (`vedioID`) REFERENCES `vediotape` (`vedioID`) ON UPDATE CASCADE;

--
-- Constraints for table `playorder`
--
ALTER TABLE `playorder`
  ADD CONSTRAINT `playorder_ibfk_1` FOREIGN KEY (`vedioID`) REFERENCES `vediotape` (`vedioID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `playorder_ibfk_2` FOREIGN KEY (`auditor`) REFERENCES `user` (`userID`) ON UPDATE CASCADE;

--
-- Constraints for table `role_resource`
--
ALTER TABLE `role_resource`
  ADD CONSTRAINT `role_resource_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `role_resource_ibfk_2` FOREIGN KEY (`resourceid`) REFERENCES `resources` (`id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `userEmployee` FOREIGN KEY (`employee`) REFERENCES `employee` (`employeeID`) ON UPDATE CASCADE;

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userID`),
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`);

--
-- Constraints for table `vedioscore`
--
ALTER TABLE `vedioscore`
  ADD CONSTRAINT `vedioExaminer` FOREIGN KEY (`examiner`) REFERENCES `user` (`userID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `vedioscore_ibfk_1` FOREIGN KEY (`vedioID`) REFERENCES `vediotape` (`vedioID`),
  ADD CONSTRAINT `vedioscore_ibfk_2` FOREIGN KEY (`operator`) REFERENCES `user` (`userID`);

--
-- Constraints for table `vediotape`
--
ALTER TABLE `vediotape`
  ADD CONSTRAINT `inputer` FOREIGN KEY (`inputer`) REFERENCES `user` (`userID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `vedioCompany` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `vedioStatus` FOREIGN KEY (`status`) REFERENCES `status` (`statusID`),
  ADD CONSTRAINT `vedioSubject` FOREIGN KEY (`subject`) REFERENCES `subject` (`subjectID`),
  ADD CONSTRAINT `vedioTopic` FOREIGN KEY (`topic`) REFERENCES `topic` (`topicID`) ON UPDATE CASCADE;
