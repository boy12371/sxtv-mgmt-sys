-- phpMyAdmin SQL Dump
-- version 3.2.5
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2010 年 03 月 31 日 00:41
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
  `age` int(11) default '0',
  `gender` int(4) default '0',
  `career` varchar(50) default '',
  `comments` varchar(255) default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- 转存表中的数据 `audience`
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- 转存表中的数据 `audiencescore`
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
  KEY `auditor` (`auditor`),
  KEY `vedioID` (`vedioID`),
  KEY `result` (`result`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='审核' AUTO_INCREMENT=47 ;

--
-- 转存表中的数据 `auditing`
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
(46, '1457', 1, '2010-03-28 22:57:10', 5, NULL);

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
  `status` int(11) NOT NULL default '1' COMMENT '0=禁用,1=正常',
  `comments` varchar(512) default '',
  PRIMARY KEY  (`companyID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='公司信息' AUTO_INCREMENT=9 ;

--
-- 转存表中的数据 `company`
--

INSERT INTO `company` (`companyID`, `companyName`, `registrationNo`, `phone`, `contactPerson`, `status`, `comments`) VALUES
(1, '中华影视', '12331123123', '34534534534', '胡戈', 1, '第三方万恶服务'),
(2, '美国影视', '123123', '123123', '万恶范围阿飞', 1, '发送到非 '),
(3, '法国影视', '34234', 'werwef', 'fwefwef', 1, '发送到非万恶 '),
(4, '3werwer', '', '234234', 'fwefwef', 1, ''),
(5, '土耳其影视公司', '8987786', '', '', 1, ''),
(6, 'dsvsdv', 'fwefwefwefwef', '234234', '快速', 1, 'ffffffffffffffffffffffwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwefwefwefwef'),
(7, '韩国影视公司', '1231209809302983', '09807986', '乐山大佛', 1, '似懂非懂发'),
(8, 'sdf ', '', '', '', 1, '');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='员工信息' AUTO_INCREMENT=11 ;

--
-- 转存表中的数据 `employee`
--

INSERT INTO `employee` (`employeeID`, `name`, `birthday`, `contractDate`, `gender`, `tel`, `status`, `comments`) VALUES
(1, '王文婷', '1983-02-19', '2006-01-16', 0, '13991366931', 0, 'dddd'),
(2, '王萌', '2010-01-21', '1970-01-01', 0, '13991366930', 1, '王萌王萌王萌王萌王萌王萌王萌王萌王萌王萌'),
(3, '周强', '2010-01-21', '2010-01-21', 1, '13991366931', 1, '这是一个员工'),
(4, '周zhou', '2010-01-21', '2010-01-21', 1, '13991366931', 1, '这是一个员工'),
(5, '李斯', '2010-01-25', '2010-01-20', 1, '123123123', 1, '的期望的钱'),
(6, '张武', '2006-01-09', '2007-01-15', 0, '123124234', 0, '撕得粉碎大放送'),
(7, '赵流', '2006-01-16', '2008-01-22', 0, '123123', 1, '扫的服务阿飞'),
(8, '王其', '2010-01-17', '1982-01-12', 0, '维吾尔', 1, '斯蒂芬'),
(9, '身体', '2010-03-24', '2010-03-24', 1, '3fwefwe', 1, 'sdfsdf'),
(10, '色图', '2010-03-24', '2010-03-24', 1, '', 1, 'asdqwd');

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
  `date` datetime NOT NULL default '1000-01-01 00:00:00',
  PRIMARY KEY  (`Id`),
  KEY `auditor` (`auditor`),
  KEY `vedioID` (`vedioID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=27 ;

--
-- 转存表中的数据 `playchangelog`
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='节目编排' AUTO_INCREMENT=34 ;

--
-- 转存表中的数据 `playorder`
--

INSERT INTO `playorder` (`Id`, `playDate`, `vedioID`, `arrangeDate`, `auditor`) VALUES
(28, '2010-02-02 00:00:00', '3', '2010-02-26 23:40:50', 2),
(30, '2010-02-03 00:00:00', '1', '2010-02-26 23:40:50', 2),
(32, '2010-02-01 00:00:00', 'sw12', '2010-02-27 16:59:47', 2),
(33, '2010-03-28 00:00:00', '1457', '2010-03-08 23:50:14', 5);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- 转存表中的数据 `role`
--

INSERT INTO `role` (`id`, `name`, `comments`) VALUES
(1, 'ADMIN', '管理员'),
(2, 'INPUTER', '录入'),
(3, 'EXAMINER', '打分'),
(4, 'AUDITOR', '审核'),
(5, 'ARRANGER', '编排'),
(6, 'NORMAL', '普通');

-- --------------------------------------------------------

--
-- 表的结构 `role_resource`
--

CREATE TABLE IF NOT EXISTS `role_resource` (
  `id` int(11) NOT NULL auto_increment,
  `roleid` int(11) NOT NULL,
  `resourceid` int(11) NOT NULL,
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `union_key` (`roleid`,`resourceid`),
  KEY `resourceid` (`resourceid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `role_resource`
--


-- --------------------------------------------------------

--
-- 表的结构 `scorelevel`
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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- 转存表中的数据 `scorelevel`
--

INSERT INTO `scorelevel` (`id`, `level`, `start`, `end`, `levelscore`) VALUES
(1, 1, 1, 3, 95),
(2, 2, 4, 10, 90),
(3, 3, 11, 15, 85),
(4, 4, 16, 10000000, 70);

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
('innovateScore', 0.3),
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
(5, '待排', '剧目已经通过并等待编排。'),
(6, '待播', '审核通过，并已指定时间，等待播放'),
(7, '淘汰', '审核未通过，直接退带'),
(8, '已播', '剧目已播出，尚未得到市场反馈'),
(9, '结束', '剧目已播出并收到市场反馈');

-- --------------------------------------------------------

--
-- 表的结构 `subject`
--

CREATE TABLE IF NOT EXISTS `subject` (
  `subjectID` int(11) NOT NULL auto_increment,
  `subjectName` varchar(128) NOT NULL,
  `status` int(11) NOT NULL COMMENT '0=禁用,1=正常',
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY  (`subjectID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='栏目' AUTO_INCREMENT=5 ;

--
-- 转存表中的数据 `subject`
--

INSERT INTO `subject` (`subjectID`, `subjectName`, `status`, `comments`) VALUES
(1, '都市碎戏', 1, '都市碎戏都'),
(2, '百家碎戏', 1, '百家碎戏百家碎戏百家碎戏百家碎戏百家碎戏'),
(3, '狼人虎剧', 0, '西安电视台'),
(4, '物业剧场', 0, '斯蒂芬');

-- --------------------------------------------------------

--
-- 表的结构 `topic`
--

CREATE TABLE IF NOT EXISTS `topic` (
  `topicID` int(11) NOT NULL auto_increment,
  `topicName` varchar(128) NOT NULL,
  `status` int(11) NOT NULL COMMENT '0=禁用,1=正常',
  `comments` varchar(512) NOT NULL,
  PRIMARY KEY  (`topicID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='题材' AUTO_INCREMENT=7 ;

--
-- 转存表中的数据 `topic`
--

INSERT INTO `topic` (`topicID`, `topicName`, `status`, `comments`) VALUES
(1, '冒险', 1, '冒险冒险冒险冒险'),
(2, '社会', 1, '社会社会社会社会社会5u56u'),
(3, '生活', 1, '生活生活生活生活生活生活'),
(4, '社会', 1, '社会社会社会社会社会5u56u'),
(5, '民生', 1, '民生民生民生民生'),
(6, '爱情', 1, '爱情爱情爱情');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户' AUTO_INCREMENT=12 ;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`userID`, `userName`, `userPass`, `employee`, `status`) VALUES
(1, 'tiger', 'c4ca4238a0b923820dcc509a6f75849b', 1, 1),
(2, 'cat', '123', 2, 1),
(3, 'pencil', '123', 3, 1),
(4, 'dog', '123', 4, 1),
(5, 'chick', '123', 5, 1),
(9, 'fefwefwef', 'weffef', 7, 1),
(10, 'cewerwere', 'fwefwef', 8, 1),
(11, 'fdweffff', 'ffffff', 9, 1);

-- --------------------------------------------------------

--
-- 表的结构 `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
  `id` int(11) NOT NULL auto_increment,
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `comments` varchar(512) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `union index` (`userid`,`roleid`),
  KEY `roleid` (`roleid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC AUTO_INCREMENT=24 ;

--
-- 转存表中的数据 `user_role`
--

INSERT INTO `user_role` (`id`, `userid`, `roleid`, `comments`) VALUES
(1, 1, 1, ''),
(2, 2, 3, ''),
(3, 3, 2, ''),
(4, 4, 3, ''),
(5, 5, 3, ''),
(15, 9, 1, NULL),
(16, 9, 2, NULL),
(17, 9, 3, NULL),
(18, 9, 4, NULL),
(19, 9, 5, NULL),
(20, 9, 6, NULL),
(21, 10, 4, NULL),
(22, 11, 1, NULL),
(23, 11, 3, NULL);

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
  `operator` int(11) NOT NULL default '0',
  `orientation` int(11) NOT NULL default '0',
  `comments` varchar(512) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `unique_key` (`vedioID`,`examiner`),
  KEY `vedioExaminer` (`examiner`),
  KEY `vedioID` (`vedioID`),
  KEY `operator` (`operator`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='评分表' AUTO_INCREMENT=22 ;

--
-- 转存表中的数据 `vedioscore`
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
(21, '9991', 1, 20, 25, 35, 45, 37, 1, NULL, 1, '2010-03-28 11:22:40', 1, 0, NULL);

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
('0', '阿凡达', 1, 3, 1, '2001-01-30', '2001-01-31 00:00:00', 3, 5, NULL, NULL, NULL),
('1', 'Fight Club', 2, 4, 1, '2000-01-30', '2001-01-31 00:00:00', 3, 2, NULL, 0.5, 0.6),
('12', '12qdfw', 2, 3, 1, '2010-02-11', '2010-02-11 08:44:47', 1, 1, 'ddvsdfsdsdvsdv', NULL, NULL),
('123s', 'fwefxfdg', 1, 4, 1, '2010-03-25', '2010-03-25 22:04:05', 1, 1, 'sdfwef', NULL, NULL),
('12asd', 'dqw', 1, 1, 1, '2010-02-10', '2010-02-10 14:17:59', 1, 2, 'asdacasc', NULL, NULL),
('12dq', 'dqwd', 2, 1, 1, '2010-02-10', '2010-02-10 14:10:59', 1, 6, 'zxcasc', NULL, NULL),
('1457', 'X战警', 2, 4, 2, '2010-03-08', '2010-03-08 22:55:39', 2, 5, 'xxxxxx', 70, 50),
('1ewfv', 'zxvcsdv', 1, 3, 1, '2010-02-10', '2010-02-10 14:21:42', 1, 2, 'sdv', NULL, NULL),
('1ssd', 'zxcasc', 1, 3, 1, '2010-02-10', '2010-02-10 14:10:59', 1, 1, 'zxcasczx zx zx zx zx ', NULL, NULL),
('2', '12 Monkey', 2, 4, 2, '2001-12-30', '2001-01-31 00:00:00', 3, 7, '', NULL, NULL),
('23eg', 'fwefwef', 1, 2, 1, '2010-02-10', '2010-02-10 14:21:42', 1, 2, 'sdvvsdv', NULL, NULL),
('23g', 'dfbdfbf', 2, 4, 1, '2010-02-10', '2010-02-10 14:21:42', 1, 1, 'sdvvsdv', NULL, NULL),
('3', '蝴蝶效应', 2, 3, 1, '2009-01-01', '2009-01-02 00:00:00', 3, 2, NULL, 0.7, 0.8),
('324aa', 'sfwe43', 2, 1, 1, '2010-02-10', '2010-02-10 14:21:42', 1, 7, 'sdvvsdv', NULL, NULL),
('32f', 'fwef', 2, 3, 2, '2010-02-12', '2010-02-12 10:33:18', 1, 1, 'sd', NULL, NULL),
('32ff2', 'ffewef', 1, 2, 3, '2010-02-12', '2010-02-12 10:33:18', 1, 3, 'sdfwe', NULL, NULL),
('3334df', 'dsss', 1, 1, 2, '2010-03-28', '2010-03-28 09:43:42', 1, 1, 'cvsdv', NULL, NULL),
('4', '超黑特警', 2, 4, 1, '2009-02-01', '2009-02-02 00:00:00', 3, 5, NULL, NULL, NULL),
('452s11', 'ddfe1', 1, 3, 2, '2010-03-25', '2010-03-25 22:17:55', 1, 1, 'xcvsdvsdvsdvs', NULL, NULL),
('452s12', 'ddfe2', 1, 3, 1, '2010-03-25', '2010-03-25 22:17:55', 1, 1, 'xcvsdvsdvsdvs', NULL, NULL),
('452s13', 'ddfe3', 1, 3, 2, '2010-03-25', '2010-03-25 22:17:55', 1, 1, 'xcvsdvsdvsdvs', NULL, NULL),
('452s14', 'ddfe4', 1, 3, 5, '2010-03-25', '2010-03-25 22:17:55', 1, 1, 'xcvsdvsdvsdvs', NULL, NULL),
('452s15', 'ddfe5', 2, 3, 5, '2010-03-25', '2010-03-25 22:17:55', 1, 1, 'xcvsdvsdvsdvs', NULL, NULL),
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
('sf1vsd', 'weqdfw', 1, 1, 1, '2010-02-11', '2010-02-11 08:44:47', 1, 5, 'ddvsdfsd', NULL, NULL),
('sfw', '1v2asdsv', 1, 1, 1, '2010-02-10', '2010-02-10 14:20:35', 1, 3, 'sdvsdv', NULL, NULL),
('sw12', 'dqwe', 2, 3, 1, '2010-02-12', '2010-02-12 10:33:18', 1, 9, 'ssdv', 47, 43);

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
  ADD CONSTRAINT `vedioscore_ibfk_1` FOREIGN KEY (`vedioID`) REFERENCES `vediotape` (`vedioID`),
  ADD CONSTRAINT `vedioscore_ibfk_2` FOREIGN KEY (`operator`) REFERENCES `user` (`userID`);

--
-- 限制表 `vediotape`
--
ALTER TABLE `vediotape`
  ADD CONSTRAINT `inputer` FOREIGN KEY (`inputer`) REFERENCES `user` (`userID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `vedioCompany` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `vedioStatus` FOREIGN KEY (`status`) REFERENCES `status` (`statusID`),
  ADD CONSTRAINT `vedioSubject` FOREIGN KEY (`subject`) REFERENCES `subject` (`subjectID`),
  ADD CONSTRAINT `vedioTopic` FOREIGN KEY (`topic`) REFERENCES `topic` (`topicID`) ON UPDATE CASCADE;