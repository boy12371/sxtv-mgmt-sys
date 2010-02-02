-- phpMyAdmin SQL Dump
-- version 3.2.5
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2010 年 02 月 01 日 22:28
-- 服务器版本: 5.0.75
-- PHP 版本: 5.2.6-3ubuntu4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `sxtvdb`
--

-- --------------------------------------------------------

--
-- 表的结构 `auditing`
--

CREATE TABLE IF NOT EXISTS `auditing` (
  `vedioID` varchar(128) NOT NULL default '',
  `auditor` int(11) NOT NULL,
  `auditDate` datetime NOT NULL,
  `result` int(11) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY  (`vedioID`),
  KEY `auditor` (`auditor`),
  KEY `result` (`result`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审核';

--
-- 转存表中的数据 `auditing`
--


-- --------------------------------------------------------

--
-- 表的结构 `company`
--

CREATE TABLE IF NOT EXISTS `company` (
  `companyID` int(11) NOT NULL auto_increment,
  `companyName` varchar(128) NOT NULL,
  `registrationNo` varchar(128) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `contactPerson` varchar(50) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY  (`companyID`),
  UNIQUE KEY `registrationNo` (`registrationNo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='公司信息' AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- 表的结构 `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `employeeID` int(11) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `birthday` date NOT NULL,
  `contractDate` date NOT NULL,
  `gender` int(11) NOT NULL,
  `tel` varchar(50) NOT NULL,
  `status` int(11) NOT NULL COMMENT '0=禁用，1=正常',
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY  (`employeeID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='员工信息' AUTO_INCREMENT=9 ;



-- --------------------------------------------------------

--
-- 表的结构 `playchangelog`
--

CREATE TABLE IF NOT EXISTS `playchangelog` (
  `Id` int(11) NOT NULL auto_increment,
  `vedioID` varchar(128) NOT NULL default '',
  `auditor` int(11) NOT NULL default '0',
  `fromDate` datetime default NULL,
  `toDate` datetime default '0000-00-00 00:00:00',
  `operation` varchar(50) default NULL,
  PRIMARY KEY  (`Id`),
  KEY `auditor` (`auditor`),
  KEY `vedioID` (`vedioID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `playchangelog`
--


-- --------------------------------------------------------

--
-- 表的结构 `playorder`
--

CREATE TABLE IF NOT EXISTS `playorder` (
  `Id` int(11) NOT NULL auto_increment,
  `playDate` datetime NOT NULL default '0000-00-00 00:00:00',
  `vedioID` varchar(128) NOT NULL default '',
  `arrangeDate` datetime NOT NULL default '0000-00-00 00:00:00',
  `auditor` int(11) NOT NULL default '0',
  PRIMARY KEY  (`Id`),
  KEY `vedioID` (`vedioID`),
  KEY `auditor` (`auditor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节目编排' AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `playorder`
--


-- --------------------------------------------------------

--
-- 表的结构 `resources`
--

CREATE TABLE IF NOT EXISTS `resources` (
  `id` int(11) NOT NULL auto_increment,
  `url` varchar(512) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `resources`
--


-- --------------------------------------------------------

--
-- 表的结构 `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(25) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `role`
--


-- --------------------------------------------------------

--
-- 表的结构 `role_resource`
--

CREATE TABLE IF NOT EXISTS `role_resource` (
  `roleid` int(11) NOT NULL,
  `resourceid` int(11) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY  (`roleid`,`resourceid`),
  KEY `resourceid` (`resourceid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `role_resource`
--


-- --------------------------------------------------------

--
-- 表的结构 `status`
--

CREATE TABLE IF NOT EXISTS `status` (
  `statusID` int(11) NOT NULL auto_increment,
  `status` varchar(50) character set utf8 NOT NULL,
  `comments` varchar(512) character set utf8 NOT NULL,
  PRIMARY KEY  (`statusID`)
) ENGINE=InnoDB  DEFAULT CHARSET=ucs2 COMMENT='状态' AUTO_INCREMENT=10 ;



-- --------------------------------------------------------

--
-- 表的结构 `subject`
--

CREATE TABLE IF NOT EXISTS `subject` (
  `subjectID` int(11) NOT NULL auto_increment,
  `subjectName` varchar(128) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY  (`subjectID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='栏目' AUTO_INCREMENT=3 ;



-- --------------------------------------------------------

--
-- 表的结构 `topic`
--

CREATE TABLE IF NOT EXISTS `topic` (
  `topicID` int(11) NOT NULL auto_increment,
  `topicName` varchar(128) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY  (`topicID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='题材' AUTO_INCREMENT=5 ;

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userID` int(11) NOT NULL auto_increment,
  `userName` varchar(50) character set ucs2 NOT NULL,
  `userPass` varchar(128) character set ucs2 NOT NULL,
  `employee` int(11) NOT NULL,
  `status` int(11) NOT NULL COMMENT '0=禁用，1=正常',
  PRIMARY KEY  (`userID`),
  KEY `userEmployee` (`employee`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户' AUTO_INCREMENT=2 ;



-- --------------------------------------------------------

--
-- 表的结构 `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY  (`userid`,`roleid`),
  KEY `roleid` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `user_role`
--


-- --------------------------------------------------------

--
-- 表的结构 `vedioscore`
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
  `audiendceVote` int(11) NOT NULL,
  `award` varchar(128) NOT NULL,
  `precision` float NOT NULL,
  `purchase` varchar(128) NOT NULL,
  `comments` varchar(512) default NULL,
  PRIMARY KEY  (`id`),
  KEY `vedioExaminer` (`examiner`),
  KEY `vedioID` (`vedioID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分表' AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `vedioscore`
--


-- --------------------------------------------------------

--
-- 表的结构 `vediotape`
--

CREATE TABLE IF NOT EXISTS `vediotape` (
  `vedioID` varchar(128) NOT NULL default '0',
  `vedioName` varchar(128) NOT NULL,
  `subject` int(11) NOT NULL,
  `topic` int(11) NOT NULL,
  `companyID` int(11) NOT NULL,
  `dateComing` date NOT NULL,
  `dateInput` datetime NOT NULL,
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
-- 转存表中的数据 `vediotape`
--


--
-- 限制导出的表
--

--
-- 限制表 `auditing`
--
ALTER TABLE `auditing`
  ADD CONSTRAINT `auditing_ibfk_1` FOREIGN KEY (`vedioID`) REFERENCES `vediotape` (`vedioID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `auditing_ibfk_2` FOREIGN KEY (`auditor`) REFERENCES `user` (`userID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `auditing_ibfk_3` FOREIGN KEY (`result`) REFERENCES `status` (`statusID`) ON UPDATE CASCADE;

--
-- 限制表 `playchangelog`
--
ALTER TABLE `playchangelog`
  ADD CONSTRAINT `playchangelog_ibfk_1` FOREIGN KEY (`auditor`) REFERENCES `user` (`userID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `playchangelog_ibfk_2` FOREIGN KEY (`vedioID`) REFERENCES `vediotape` (`vedioID`) ON UPDATE CASCADE;

--
-- 限制表 `playorder`
--
ALTER TABLE `playorder`
  ADD CONSTRAINT `playorder_ibfk_1` FOREIGN KEY (`vedioID`) REFERENCES `vediotape` (`vedioID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `playorder_ibfk_2` FOREIGN KEY (`auditor`) REFERENCES `user` (`userID`) ON UPDATE CASCADE;

--
-- 限制表 `role_resource`
--
ALTER TABLE `role_resource`
  ADD CONSTRAINT `role_resource_ibfk_2` FOREIGN KEY (`resourceid`) REFERENCES `resources` (`id`),
  ADD CONSTRAINT `role_resource_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`);

--
-- 限制表 `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `userEmployee` FOREIGN KEY (`employee`) REFERENCES `employee` (`employeeID`) ON UPDATE CASCADE;

--
-- 限制表 `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userID`);

--
-- 限制表 `vedioscore`
--
ALTER TABLE `vedioscore`
  ADD CONSTRAINT `vedioExaminer` FOREIGN KEY (`examiner`) REFERENCES `user` (`userID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `vedioscore_ibfk_1` FOREIGN KEY (`vedioID`) REFERENCES `vediotape` (`vedioID`);

--
-- 限制表 `vediotape`
--
ALTER TABLE `vediotape`
  ADD CONSTRAINT `inputer` FOREIGN KEY (`inputer`) REFERENCES `user` (`userID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `vedioCompany` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `vedioStatus` FOREIGN KEY (`status`) REFERENCES `status` (`statusID`),
  ADD CONSTRAINT `vedioSubject` FOREIGN KEY (`subject`) REFERENCES `subject` (`subjectID`),
  ADD CONSTRAINT `vedioTopic` FOREIGN KEY (`topic`) REFERENCES `topic` (`topicID`) ON UPDATE CASCADE;


--
-- 转存表中的数据 `company`
--

INSERT INTO `company` (`companyID`, `companyName`, `registrationNo`, `phone`, `contactPerson`, `comments`) VALUES
(1, '中华影视', '12331123123', '34534534534', '胡戈', '第三方万恶服务');

--
-- 转存表中的数据 `employee`
--

INSERT INTO `employee` (`employeeID`, `name`, `birthday`, `contractDate`, `gender`, `tel`, `status`, `comments`) VALUES
(1, '王文婷', '1983-02-19', '2006-01-16', 0, '13991366931', 0, '这是一个员工'),
(2, '王萌', '2010-01-21', '1970-01-01', 0, '13991366930', 0, '王萌王萌王萌王萌王萌王萌王萌王萌王萌王萌'),
(3, '周强', '2010-01-21', '2010-01-21', 1, '13991366931', 0, '这是一个员工'),
(4, '周zhou', '2010-01-21', '2010-01-21', 1, '13991366931', 0, '这是一个员工'),
(5, '里斯', '2010-01-25', '2010-01-20', 1, '123123123', 0, '的期望的钱'),
(6, '张武', '2006-01-09', '2007-01-15', 0, '123124234', 0, '撕得粉碎大放送'),
(7, '赵流', '2006-01-16', '2008-01-22', 0, '123123', 0, '扫的服务阿飞'),
(8, '王其', '2010-01-17', '1982-01-12', 0, '维吾尔', 0, '斯蒂芬');
--
-- 转存表中的数据 `status`
--

INSERT INTO `status` (`statusID`, `status`, `comments`) VALUES
(1, '待评', '新进剧目，带评分'),
(2, '待审', '剧目以评分，等待审核'),
(3, '通过', '审核已通过，等待指定播放时间'),
(4, '修改', '审核未通过，有待修改'),
(5, '重审', '修改已完成，需重新审核'),
(6, '待播', '审核通过，并已指定时间，等待播放'),
(7, '退回', '审核未通过，直接退带'),
(8, '已播', '剧目已播出，尚未得到市场反馈'),
(9, '结束', '剧目已播出并收到市场反馈');

--
-- 转存表中的数据 `subject`
--

INSERT INTO `subject` (`subjectID`, `subjectName`, `comments`) VALUES
(1, '都市碎戏', '都市碎戏都市碎戏都市碎戏都市碎戏都市碎戏'),
(2, '百家碎戏', '百家碎戏百家碎戏百家碎戏百家碎戏百家碎戏');


--
-- 转存表中的数据 `topic`
--

INSERT INTO `topic` (`topicID`, `topicName`, `comments`) VALUES
(1, '爱情', '爱情爱情爱情爱情爱情爱情'),
(2, '亲情', '亲情亲情亲情亲情亲情亲情亲情亲情'),
(3, '生活', '生活生活生活生活生活生活'),
(4, '社会', '社会社会社会社会社会');

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`userID`, `userName`, `userPass`, `employee`, `status`) VALUES
(1, 'tiger', 'command', 1, 0);
