-- phpMyAdmin SQL Dump
-- version 3.2.5
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2010 年 02 月 12 日 13:56
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
-- 表的结构 `audience`
--

CREATE TABLE IF NOT EXISTS `audience` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL default '',
  `age` int(11) default NULL,
  `gender` int(4) default NULL,
  `career` varchar(50) default NULL,
  `comments` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- 转存表中的数据 `audience`
--

INSERT INTO `audience` (`id`, `name`, `age`, `gender`, `career`, `comments`) VALUES
(1, '张三', NULL, NULL, NULL, NULL),
(2, '李四', NULL, NULL, NULL, NULL),
(3, '王麻子', NULL, NULL, NULL, NULL),
(4, '赵六', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `audiencescore`
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- 转存表中的数据 `audiencescore`
--

INSERT INTO `audiencescore` (`id`, `vedioID`, `audienceID`, `dateExamine`, `result`, `comments`) VALUES
(1, '0', 2, '2009-03-01 00:00:00', 1, NULL),
(3, '2', 2, '1000-01-01 00:00:00', 0, NULL),
(4, '2', 3, '1000-01-01 00:00:00', 1, NULL),
(5, '2', 4, '1000-01-01 00:00:00', 1, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `auditing`
--

CREATE TABLE IF NOT EXISTS `auditing` (
  `id` int(11) NOT NULL auto_increment,
  `vedioID` varchar(128) NOT NULL default '',
  `auditor` int(11) NOT NULL,
  `auditDate` datetime NOT NULL default '1000-01-01 00:00:01',
  `result` int(11) NOT NULL,
  `comments` varchar(512) default '',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `unique_key` (`vedioID`,`auditor`),
  KEY `auditor` (`auditor`),
  KEY `vedioID` (`vedioID`),
  KEY `result` (`result`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='审核' AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `auditing`
--

INSERT INTO `auditing` (`id`, `vedioID`, `auditor`, `auditDate`, `result`, `comments`) VALUES
(1, '2', 2, '2010-02-12 12:25:44', 7, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `company`
--

CREATE TABLE IF NOT EXISTS `company` (
  `companyID` int(11) NOT NULL auto_increment,
  `companyName` varchar(128) NOT NULL,
  `registrationNo` varchar(128) NOT NULL,
  `phone` varchar(50) default '0',
  `contactPerson` varchar(50) default '',
  `comments` varchar(512) default '',
  PRIMARY KEY  (`companyID`),
  UNIQUE KEY `registrationNo` (`registrationNo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='公司信息' AUTO_INCREMENT=4 ;

--
-- 转存表中的数据 `company`
--

INSERT INTO `company` (`companyID`, `companyName`, `registrationNo`, `phone`, `contactPerson`, `comments`) VALUES
(1, '中华影视', '12331123123', '34534534534', '胡戈', '第三方万恶服务'),
(2, '美国影视', '123123', '123123', '万恶范围阿飞', '发送到非 '),
(3, '法国影视', '34234', 'werwef', 'fwefwef', '发送到非万恶 ');

-- --------------------------------------------------------

--
-- 表的结构 `employee`
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='员工信息' AUTO_INCREMENT=10 ;

--
-- 转存表中的数据 `employee`
--

INSERT INTO `employee` (`employeeID`, `name`, `birthday`, `contractDate`, `gender`, `tel`, `status`, `comments`) VALUES
(1, '王文婷', '1983-02-19', '2006-01-16', 0, '13991366931', 0, '这是一个员sdf工'),
(2, '王萌', '2010-01-21', '1970-01-01', 0, '13991366930', 0, '王萌王萌王萌王萌王萌王萌王萌王萌王萌王萌'),
(3, '周强', '2010-01-21', '2010-01-21', 1, '13991366931', 0, '这是一个员工'),
(4, '周zhou', '2010-01-21', '2010-01-21', 1, '13991366931', 0, '这是一个员工'),
(5, '里斯', '2010-01-25', '2010-01-20', 1, '123123123', 0, '的期望的钱'),
(6, '张武', '2006-01-09', '2007-01-15', 0, '123124234', 1, '撕得粉碎大放送'),
(7, '赵流', '2006-01-16', '2008-01-22', 0, '123123', 0, '扫的服务阿飞'),
(8, '王其', '2010-01-17', '1982-01-12', 0, '维吾尔', 0, '斯蒂芬'),
(9, 'dqwd', '1994-02-14', '2006-02-14', 0, '12312d', 0, 'wfweffwefwf');

-- --------------------------------------------------------

--
-- 表的结构 `playchangelog`
--

CREATE TABLE IF NOT EXISTS `playchangelog` (
  `Id` int(11) NOT NULL auto_increment,
  `vedioID` varchar(128) NOT NULL default '',
  `auditor` int(11) NOT NULL default '0',
  `fromDate` datetime default '1000-01-01 00:00:00',
  `toDate` datetime default '1000-01-01 00:00:00',
  `operation` varchar(50) default '',
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
  `playDate` datetime NOT NULL default '1000-01-01 00:00:00',
  `vedioID` varchar(128) NOT NULL default '',
  `arrangeDate` datetime NOT NULL default '1000-01-01 00:00:00',
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
-- 表的结构 `scoreweight`
--

CREATE TABLE IF NOT EXISTS `scoreweight` (
  `factor` varchar(50) NOT NULL default '',
  `wieght` float NOT NULL default '0',
  PRIMARY KEY  (`factor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `scoreweight`
--

INSERT INTO `scoreweight` (`factor`, `wieght`) VALUES
('innovateScore', 0.1),
('performScore', 0.3),
('storyScore', 0.4),
('techScore', 0.2);

-- --------------------------------------------------------

--
-- 表的结构 `status`
--

CREATE TABLE IF NOT EXISTS `status` (
  `statusID` int(11) NOT NULL auto_increment,
  `status` varchar(50) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY  (`statusID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='状态' AUTO_INCREMENT=10 ;

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

--
-- 转存表中的数据 `subject`
--

INSERT INTO `subject` (`subjectID`, `subjectName`, `comments`) VALUES
(1, '都市碎戏', '都市碎戏都市碎戏都市碎戏都市碎戏都市碎戏'),
(2, '百家碎戏', '百家碎戏百家碎戏百家碎戏百家碎戏百家碎戏');

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

--
-- 转存表中的数据 `topic`
--

INSERT INTO `topic` (`topicID`, `topicName`, `comments`) VALUES
(1, '爱情', '爱情爱情爱情爱情爱情爱情'),
(2, '亲情', '亲情亲情亲情亲情亲情亲情亲情亲情'),
(3, '生活', '生活生活生活生活生活生活'),
(4, '社会', '社会社会社会社会社会');

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userID` int(11) NOT NULL auto_increment,
  `userName` varchar(50) NOT NULL default '',
  `userPass` varchar(50) NOT NULL default '',
  `employee` int(11) NOT NULL default '0',
  `status` int(11) NOT NULL default '1' COMMENT '0=禁用，1=正常',
  PRIMARY KEY  (`userID`),
  KEY `userEmployee` (`employee`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户' AUTO_INCREMENT=4 ;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`userID`, `userName`, `userPass`, `employee`, `status`) VALUES
(1, 'tiger', 'command', 1, 1),
(2, 'cat', '123', 2, 1),
(3, 'pencil', '123', 3, 1);

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
  `award` int(11) NOT NULL default '0',
  `accuracy` float default NULL,
  `purchase` int(11) NOT NULL default '0',
  `dateExamine` datetime NOT NULL default '1000-01-01 00:00:00',
  `comments` varchar(512) default NULL,
  PRIMARY KEY  (`id`),
  KEY `vedioExaminer` (`examiner`),
  KEY `vedioID` (`vedioID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='评分表' AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `vedioscore`
--

INSERT INTO `vedioscore` (`id`, `vedioID`, `examiner`, `storyScore`, `techScore`, `performScore`, `innovateScore`, `score`, `award`, `accuracy`, `purchase`, `dateExamine`, `comments`) VALUES
(1, '2', 2, 24, 35, 46, 56, 36, 1, 0, 0, '2010-01-30 00:00:00', NULL);

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
-- 转存表中的数据 `vediotape`
--

INSERT INTO `vediotape` (`vedioID`, `vedioName`, `subject`, `topic`, `companyID`, `dateComing`, `dateInput`, `inputer`, `status`, `comments`, `marketShare`, `audienceRating`) VALUES
('0', '阿凡达', 1, 3, 1, '2001-01-30', '2001-01-31 00:00:00', 3, 2, NULL, NULL, NULL),
('1', 'Fight Club', 2, 4, 1, '2000-01-30', '2001-01-31 00:00:00', 3, 4, NULL, NULL, NULL),
('12', '12qdfw', 2, 3, 1, '2010-02-11', '2010-02-11 08:44:47', 1, 1, 'ddvsdfsdsdvsdv', NULL, NULL),
('12asd', 'dqw', 1, 1, 1, '2010-02-10', '2010-02-10 14:17:59', 1, 5, 'asdacasc', NULL, NULL),
('12dq', 'dqwd', 2, 1, 1, '2010-02-10', '2010-02-10 14:10:59', 1, 3, 'zxcasc', NULL, NULL),
('1ewfv', 'zxvcsdv', 1, 3, 1, '2010-02-10', '2010-02-10 14:21:42', 1, 2, 'sdv', NULL, NULL),
('1ssd', 'zxcasc', 1, 3, 1, '2010-02-10', '2010-02-10 14:10:59', 1, 2, 'zxcasczx zx zx zx zx ', NULL, NULL),
('2', '12 Monkey', 1, 4, 1, '2001-12-30', '2001-01-31 00:00:00', 3, 7, NULL, NULL, NULL),
('23', 'sfsdf', 2, 3, 1, '2010-02-10', '2010-02-10 13:39:18', 1, 4, 'fwefwe', NULL, NULL),
('23eg', 'fwefwef', 1, 2, 1, '2010-02-10', '2010-02-10 14:21:42', 1, 2, 'sdvvsdv', NULL, NULL),
('23g', 'dfbdfbf', 2, 4, 1, '2010-02-10', '2010-02-10 14:21:42', 1, 1, 'sdvvsdv', NULL, NULL),
('324aa', 'sfwe43', 2, 1, 1, '2010-02-10', '2010-02-10 14:21:42', 1, 2, 'sdvvsdv', NULL, NULL),
('32f', 'fwef', 2, 3, 2, '2010-02-12', '2010-02-12 10:33:18', 1, 1, 'sd', NULL, NULL),
('32ff2', 'ffewef', 1, 2, 3, '2010-02-12', '2010-02-12 10:33:18', 1, 1, 'sdfwe', NULL, NULL),
('sf1', 'sdfw', 1, 1, 1, '2010-02-11', '2010-02-11 08:44:47', 1, 1, 'ddv', NULL, NULL),
('sf1vsd', 'weqdfw', 1, 1, 1, '2010-02-11', '2010-02-11 08:44:47', 1, 1, 'ddvsdfsd', NULL, NULL),
('sfw', '1v2asdsv', 1, 1, 1, '2010-02-10', '2010-02-10 14:20:35', 1, 1, 'sdvsdv', NULL, NULL),
('sw12', 'dqwe', 2, 3, 1, '2010-02-12', '2010-02-12 10:33:18', 1, 1, 'ssdv', NULL, NULL);

--
-- 限制导出的表
--

--
-- 限制表 `audiencescore`
--
ALTER TABLE `audiencescore`
  ADD CONSTRAINT `audiencescore_ibfk_1` FOREIGN KEY (`vedioID`) REFERENCES `vediotape` (`vedioID`),
  ADD CONSTRAINT `audiencescore_ibfk_2` FOREIGN KEY (`audienceID`) REFERENCES `audience` (`id`);

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
  ADD CONSTRAINT `role_resource_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `role_resource_ibfk_2` FOREIGN KEY (`resourceid`) REFERENCES `resources` (`id`);

--
-- 限制表 `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `userEmployee` FOREIGN KEY (`employee`) REFERENCES `employee` (`employeeID`) ON UPDATE CASCADE;

--
-- 限制表 `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userID`),
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`);

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
