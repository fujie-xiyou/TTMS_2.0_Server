-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2019-06-24 17:27:33
-- 服务器版本： 5.6.43
-- PHP 版本： 7.1.5


--
-- 数据库： `HLW_TTMS_2.0`
--
CREATE DATABASE IF NOT EXISTS `HLW_TTMS_2.0` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `HLW_TTMS_2.0`;

-- --------------------------------------------------------

--
-- 表的结构 `accounts`
--

CREATE TABLE `accounts` (
  `uid` int(11) NOT NULL,
  `type` varchar(6) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(33) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `accounts`
--

INSERT INTO `accounts` (`uid`, `type`, `username`, `password`) VALUES
(1, 'ADMIN', 'fujie', '7c32841c18f635e5eed4bcf07afec757'),
(2, 'MANG', 'zqn', 'f01a4c19a3110da469e61ca78421bb87'),
(10, 'CLERK', 'pyy', '93c0e7e698ed879ddb0b0926f376622f'),
(29, 'CLERK', 'jl', '54768df6eeb13e17b9524dbd9bb44a24'),
(35, 'CLERK', '周一', '202cb962ac59075b964b07152d234b70'),
(36, 'CLERK', '周二', '202cb962ac59075b964b07152d234b70'),
(40, 'CLERK', '周三', '202cb962ac59075b964b07152d234b70');

-- --------------------------------------------------------

--
-- 表的结构 `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `datetime` datetime DEFAULT CURRENT_TIMESTAMP,
  `uid` int(11) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `total` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `orders`
--

INSERT INTO `orders` (`id`, `datetime`, `uid`, `type`, `status`, `total`) VALUES
(8, '2018-06-14 02:22:38', 29, 'BUY', 'REFUNDED', 100),
(9, '2018-06-14 02:38:25', 29, 'BUY', 'REFUNDED', 25),
(10, '2018-06-14 02:45:55', 29, 'BUY', 'REFUNDED', 50),
(11, '2018-06-14 02:45:55', 29, 'REFUND', 'REFUNDED', 50),
(12, '2018-06-14 02:38:25', 29, 'REFUND', 'REFUNDED', 25),
(13, '2018-06-14 02:58:58', 29, 'BUY', 'REFUNDED', 75),
(14, '2018-06-14 02:58:58', 29, 'REFUND', 'REFUNDED', 75),
(15, '2018-06-14 03:01:15', 10, 'BUY', 'TBU', 50),
(16, '2018-06-14 09:31:05', 10, 'BUY', 'REFUNDED', 75),
(17, '2018-06-14 09:31:05', 10, 'REFUND', 'REFUNDED', 75),
(18, '2018-06-14 23:35:17', 29, 'BUY', 'TBU', 105),
(19, '2018-06-14 20:41:37', 10, 'BUY', 'TBU', 70),
(20, '2018-06-14 22:40:06', 29, 'BUY', 'TBU', 100),
(21, '2018-06-14 22:42:05', 10, 'BUY', 'TBU', 50),
(22, '2018-06-25 10:30:06', 2, 'BUY', 'REFUNDED', 100),
(23, '2018-06-25 10:30:06', 2, 'REFUND', 'REFUNDED', 100),
(24, '2019-01-27 01:19:40', 10, 'BUY', 'TBU', 50);

-- --------------------------------------------------------

--
-- 表的结构 `order_items`
--

CREATE TABLE `order_items` (
  `id` int(11) NOT NULL,
  `orderID` int(11) DEFAULT NULL,
  `ticketID` int(11) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `order_items`
--

INSERT INTO `order_items` (`id`, `orderID`, `ticketID`, `status`, `price`) VALUES
(22, 8, 787, 'REFUNDED', 25),
(23, 8, 795, 'REFUNDED', 25),
(24, 8, 789, 'REFUNDED', 25),
(25, 8, 796, 'REFUNDED', 25),
(26, 9, 789, 'REFUNDED', 25),
(27, 10, 790, 'REFUNDED', 25),
(28, 10, 791, 'REFUNDED', 25),
(29, 13, 792, 'REFUNDED', 25),
(30, 13, 799, 'REFUNDED', 25),
(31, 13, 800, 'REFUNDED', 25),
(32, 15, 798, 'TBU', 25),
(33, 15, 797, 'TBU', 25),
(34, 16, 789, 'REFUNDED', 25),
(35, 16, 790, 'REFUNDED', 25),
(36, 16, 791, 'REFUNDED', 25),
(37, 18, 831, 'TBU', 35),
(38, 18, 832, 'TBU', 35),
(39, 18, 840, 'TBU', 35),
(40, 19, 849, 'TBU', 35),
(41, 19, 850, 'TBU', 35),
(42, 20, 794, 'TBU', 25),
(43, 20, 795, 'TBU', 25),
(44, 20, 802, 'TBU', 25),
(45, 20, 801, 'TBU', 25),
(46, 21, 792, 'TBU', 25),
(47, 21, 793, 'TBU', 25),
(48, 22, 789, 'REFUNDED', 25),
(49, 22, 796, 'REFUNDED', 25),
(50, 22, 803, 'REFUNDED', 25),
(51, 22, 790, 'REFUNDED', 25),
(52, 24, 796, 'TBU', 25),
(53, 24, 804, 'TBU', 25);

-- --------------------------------------------------------

--
-- 表的结构 `play`
--

CREATE TABLE `play` (
  `id` int(4) NOT NULL,
  `name` varchar(20) NOT NULL,
  `type` varchar(6) NOT NULL,
  `area` varchar(10) NOT NULL,
  `rating` varchar(10) NOT NULL,
  `duration` int(4) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `price` int(4) NOT NULL,
  `imgUrl` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `play`
--

INSERT INTO `play` (`id`, `name`, `type`, `area`, `rating`, `duration`, `startDate`, `endDate`, `price`, `imgUrl`) VALUES
(1, '哆啦A梦', 'FILE', '日本', 'CHILD', 120, '2018-05-15', '2018-05-22', 25, 'https://img.alicdn.com/bao/uploaded/i4/TB1zFl8uCtYBeNjSspaXXaOOFXa_.jpg_300x300.jpg'),
(3, '复仇者联盟3：无限战争', 'FILE', '美国', 'TEENAGE', 151, '2018-06-22', '2018-05-25', 0, 'https://img.alicdn.com/bao/uploaded/i4/TB1xcwSheySBuNjy1zdXXXPxFXa_.jpg_300x300.jpg'),
(4, '潜艇总动员：海底两万里', 'FILE', '中国大陆', 'CHILD', 75, '2018-06-08', '2018-06-15', 23, 'https://img.alicdn.com/bao/uploaded/i3/TB1BkNfu21TBuNjy0FjXXajyXXa_.jpg_300x300.jpg'),
(5, '魔镜奇缘2', 'FILE', '中国大陆', 'CHILD', 77, '2018-05-31', '2018-06-28', 33, 'https://img.alicdn.com/bao/uploaded/i1/TB16PMlrxGYBuNjy0FnXXX5lpXa_.jpg_300x300.jpg'),
(6, '寂静之地', 'FILE', '美国', 'TEENAGE', 91, '2018-06-07', '2018-06-14', 23, 'https://img.alicdn.com/bao/uploaded/i4/TB12.ylqmtYBeNjSspkXXbU8VXa_.jpg_300x300.jpg'),
(7, '我的宠物恐龙2', 'FILE', '澳大利亚', 'CHILD', 88, '2018-06-01', '2018-06-30', 34, 'https://img.alicdn.com/bao/uploaded/i1/TB1KWdzsh9YBuNjy0FfXXXIsVXa_.jpg_300x300.jpg');

-- --------------------------------------------------------

--
-- 表的结构 `schedule`
--

CREATE TABLE `schedule` (
  `id` int(11) NOT NULL,
  `playID` int(11) NOT NULL,
  `studioID` int(11) NOT NULL,
  `date` datetime NOT NULL COMMENT '开始时间',
  `time` time NOT NULL,
  `ticketCount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `schedule`
--

INSERT INTO `schedule` (`id`, `playID`, `studioID`, `date`, `time`, `ticketCount`) VALUES
(45, 1, 32, '2018-06-13 00:00:00', '02:22:01', 26),
(46, 3, 33, '2018-06-13 00:00:00', '02:22:09', 36),
(47, 4, 35, '2019-01-15 00:00:00', '00:00:37', 31);

-- --------------------------------------------------------

--
-- 表的结构 `seat`
--

CREATE TABLE `seat` (
  `id` int(11) NOT NULL,
  `studioID` int(11) NOT NULL,
  `row` int(11) NOT NULL,
  `col` int(11) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `seat`
--

INSERT INTO `seat` (`id`, `studioID`, `row`, `col`, `status`) VALUES
(742, 32, 1, 1, 'GOOD'),
(743, 32, 1, 2, 'GOOD'),
(744, 32, 1, 3, 'GOOD'),
(745, 32, 1, 4, 'GOOD'),
(746, 32, 1, 5, 'GOOD'),
(747, 32, 1, 6, 'GOOD'),
(748, 32, 1, 7, 'GOOD'),
(749, 32, 2, 1, 'GOOD'),
(750, 32, 2, 2, 'GOOD'),
(751, 32, 2, 3, 'BROKEN'),
(752, 32, 2, 4, 'GOOD'),
(753, 32, 2, 5, 'GOOD'),
(754, 32, 2, 6, 'GOOD'),
(755, 32, 2, 7, 'GOOD'),
(756, 32, 3, 1, 'GOOD'),
(757, 32, 3, 2, 'GOOD'),
(758, 32, 3, 3, 'GOOD'),
(759, 32, 3, 4, 'GOOD'),
(760, 32, 3, 5, 'BROKEN'),
(761, 32, 3, 6, 'GOOD'),
(762, 32, 3, 7, 'GOOD'),
(763, 32, 4, 1, 'GOOD'),
(764, 32, 4, 2, 'GOOD'),
(765, 32, 4, 3, 'GOOD'),
(766, 32, 4, 4, 'GOOD'),
(767, 32, 4, 5, 'GOOD'),
(768, 32, 4, 6, 'GOOD'),
(769, 32, 4, 7, 'GOOD'),
(770, 32, 5, 1, 'GOOD'),
(771, 32, 5, 2, 'GOOD'),
(772, 32, 5, 3, 'GOOD'),
(773, 32, 5, 4, 'GOOD'),
(774, 32, 5, 5, 'GOOD'),
(775, 32, 5, 6, 'GOOD'),
(776, 32, 5, 7, 'GOOD'),
(777, 33, 1, 1, 'BROKEN'),
(778, 33, 1, 2, 'GOOD'),
(779, 33, 1, 3, 'GOOD'),
(780, 33, 1, 4, 'GOOD'),
(781, 33, 1, 5, 'GOOD'),
(782, 33, 1, 6, 'GOOD'),
(783, 33, 1, 7, 'GOOD'),
(784, 33, 1, 8, 'NONE'),
(785, 33, 2, 1, 'GOOD'),
(786, 33, 2, 2, 'BROKEN'),
(787, 33, 2, 3, 'GOOD'),
(788, 33, 2, 4, 'GOOD'),
(789, 33, 2, 5, 'GOOD'),
(790, 33, 2, 6, 'GOOD'),
(791, 33, 2, 7, 'NONE'),
(792, 33, 2, 8, 'GOOD'),
(793, 33, 3, 1, 'GOOD'),
(794, 33, 3, 2, 'GOOD'),
(795, 33, 3, 3, 'BROKEN'),
(796, 33, 3, 4, 'GOOD'),
(797, 33, 3, 5, 'GOOD'),
(798, 33, 3, 6, 'NONE'),
(799, 33, 3, 7, 'GOOD'),
(800, 33, 3, 8, 'GOOD'),
(801, 33, 4, 1, 'GOOD'),
(802, 33, 4, 2, 'GOOD'),
(803, 33, 4, 3, 'GOOD'),
(804, 33, 4, 4, 'BROKEN'),
(805, 33, 4, 5, 'GOOD'),
(806, 33, 4, 6, 'GOOD'),
(807, 33, 4, 7, 'GOOD'),
(808, 33, 4, 8, 'GOOD'),
(809, 33, 5, 1, 'GOOD'),
(810, 33, 5, 2, 'GOOD'),
(811, 33, 5, 3, 'GOOD'),
(812, 33, 5, 4, 'NONE'),
(813, 33, 5, 5, 'BROKEN'),
(814, 33, 5, 6, 'GOOD'),
(815, 33, 5, 7, 'GOOD'),
(816, 33, 5, 8, 'GOOD'),
(817, 33, 6, 1, 'GOOD'),
(818, 33, 6, 2, 'GOOD'),
(819, 33, 6, 3, 'NONE'),
(820, 33, 6, 4, 'GOOD'),
(821, 33, 6, 5, 'GOOD'),
(822, 33, 6, 6, 'BROKEN'),
(823, 33, 6, 7, 'GOOD'),
(824, 33, 6, 8, 'GOOD'),
(827, 35, 1, 1, 'GOOD'),
(828, 35, 1, 2, 'GOOD'),
(829, 35, 1, 3, 'GOOD'),
(830, 35, 1, 4, 'GOOD'),
(831, 35, 1, 5, 'GOOD'),
(832, 35, 1, 6, 'GOOD'),
(833, 35, 2, 1, 'GOOD'),
(834, 35, 2, 2, 'GOOD'),
(835, 35, 2, 3, 'GOOD'),
(836, 35, 2, 4, 'GOOD'),
(837, 35, 2, 5, 'GOOD'),
(838, 35, 2, 6, 'GOOD'),
(839, 35, 3, 1, 'GOOD'),
(840, 35, 3, 2, 'GOOD'),
(841, 35, 3, 3, 'GOOD'),
(842, 35, 3, 4, 'GOOD'),
(843, 35, 3, 5, 'GOOD'),
(844, 35, 3, 6, 'GOOD'),
(845, 35, 4, 1, 'GOOD'),
(846, 35, 4, 2, 'GOOD'),
(847, 35, 4, 3, 'BROKEN'),
(848, 35, 4, 4, 'GOOD'),
(849, 35, 4, 5, 'GOOD'),
(850, 35, 4, 6, 'GOOD'),
(851, 35, 5, 1, 'GOOD'),
(852, 35, 5, 2, 'BROKEN'),
(853, 35, 5, 3, 'GOOD'),
(854, 35, 5, 4, 'BROKEN'),
(855, 35, 5, 5, 'GOOD'),
(856, 35, 5, 6, 'GOOD'),
(857, 35, 6, 1, 'BROKEN'),
(858, 35, 6, 2, 'GOOD'),
(859, 35, 6, 3, 'GOOD'),
(860, 35, 6, 4, 'GOOD'),
(861, 35, 6, 5, 'BROKEN'),
(862, 35, 6, 6, 'GOOD'),
(915, 36, 1, 1, 'GOOD'),
(916, 36, 1, 2, 'GOOD'),
(917, 36, 1, 3, 'GOOD'),
(918, 36, 1, 4, 'GOOD'),
(919, 36, 1, 5, 'GOOD'),
(920, 36, 2, 1, 'GOOD'),
(921, 36, 2, 2, 'GOOD'),
(922, 36, 2, 3, 'BROKEN'),
(923, 36, 2, 4, 'NONE'),
(924, 36, 2, 5, 'NONE'),
(925, 36, 3, 1, 'GOOD'),
(926, 36, 3, 2, 'BROKEN'),
(927, 36, 3, 3, 'BROKEN'),
(928, 36, 3, 4, 'NONE'),
(929, 36, 3, 5, 'GOOD'),
(930, 36, 4, 1, 'GOOD'),
(931, 36, 4, 2, 'GOOD'),
(932, 36, 4, 3, 'GOOD'),
(933, 36, 4, 4, 'BROKEN'),
(934, 36, 4, 5, 'GOOD'),
(935, 36, 5, 1, 'GOOD'),
(936, 36, 5, 2, 'GOOD'),
(937, 36, 5, 3, 'GOOD'),
(938, 36, 5, 4, 'NONE'),
(939, 36, 5, 5, 'GOOD');

-- --------------------------------------------------------

--
-- 表的结构 `studio`
--

CREATE TABLE `studio` (
  `studio_id` int(11) NOT NULL,
  `studio_name` varchar(100) NOT NULL,
  `studio_row_count` int(11) DEFAULT NULL,
  `studio_col_count` int(11) DEFAULT NULL,
  `seat_count` int(11) NOT NULL,
  `studio_introduction` varchar(2000) DEFAULT NULL,
  `theEndPlayTime` bigint(20) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `studio`
--

INSERT INTO `studio` (`studio_id`, `studio_name`, `studio_row_count`, `studio_col_count`, `seat_count`, `studio_introduction`, `theEndPlayTime`) VALUES
(32, '一号厅', 5, 7, 33, 'null', 0),
(33, '2号厅', 6, 8, 37, 'null', 0),
(35, '4号厅', 6, 6, 31, 'null', 0),
(36, '不测试了', 5, 5, 17, 'null', 0);

-- --------------------------------------------------------

--
-- 表的结构 `ticket`
--

CREATE TABLE `ticket` (
  `id` int(11) NOT NULL,
  `scheduleID` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `seat_row` int(11) DEFAULT NULL,
  `seat_col` int(11) DEFAULT NULL,
  `status` varchar(10) NOT NULL COMMENT '售出,待售,锁定',
  `locked_uid` int(11) NOT NULL DEFAULT '0',
  `locked_time` bigint(20) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `ticket`
--

INSERT INTO `ticket` (`id`, `scheduleID`, `price`, `seat_row`, `seat_col`, `status`, `locked_uid`, `locked_time`) VALUES
(785, 45, 25, 1, 1, 'SOLD', 29, 1528914909),
(786, 45, 25, 1, 2, 'SOLD', 29, 1528914909),
(787, 45, 25, 1, 3, 'SOLD', 29, 1528914909),
(788, 45, 25, 1, 4, 'SOLD', 29, 1528914994),
(789, 45, 25, 1, 5, 'AVL', 0, 0),
(790, 45, 25, 1, 6, 'AVL', 0, 0),
(791, 45, 25, 1, 7, 'AVL', 29, 1529034911),
(792, 45, 25, 2, 1, 'SOLD', 10, 1528916338),
(793, 45, 25, 2, 2, 'SOLD', 10, 1528916338),
(794, 45, 25, 2, 3, 'SOLD', 29, 0),
(795, 45, 25, 2, 4, 'SOLD', 29, 0),
(796, 45, 25, 2, 5, 'SOLD', 10, 0),
(797, 45, 25, 2, 6, 'SOLD', 10, 1528916474),
(798, 45, 25, 2, 7, 'SOLD', 10, 1528916474),
(799, 45, 25, 3, 1, 'AVL', 29, 1528916338),
(800, 45, 25, 3, 2, 'AVL', 29, 1528916338),
(801, 45, 25, 3, 3, 'SOLD', 29, 0),
(802, 45, 25, 3, 4, 'SOLD', 29, 0),
(803, 45, 25, 3, 5, 'AVL', 0, 0),
(804, 45, 25, 3, 6, 'SOLD', 10, 0),
(805, 45, 25, 3, 7, 'AVL', 0, 0),
(806, 45, 25, 4, 1, 'AVL', 0, 0),
(807, 45, 25, 4, 2, 'AVL', 0, 0),
(808, 45, 25, 4, 3, 'AVL', 0, 0),
(809, 45, 25, 4, 4, 'AVL', 0, 0),
(810, 45, 25, 4, 5, 'AVL', 0, 0),
(811, 45, 25, 4, 6, 'AVL', 0, 0),
(812, 45, 25, 4, 7, 'AVL', 0, 0),
(813, 45, 25, 5, 1, 'AVL', 0, 0),
(814, 45, 25, 5, 2, 'AVL', 0, 0),
(815, 45, 25, 5, 3, 'AVL', 0, 0),
(816, 45, 25, 5, 4, 'AVL', 0, 0),
(817, 45, 25, 5, 5, 'AVL', 0, 0),
(818, 45, 25, 5, 6, 'AVL', 0, 0),
(819, 45, 25, 5, 7, 'AVL', 0, 0),
(820, 46, 35, 1, 1, 'NONE', 0, 0),
(821, 46, 35, 1, 2, 'AVL', 2, 1529941771),
(822, 46, 35, 1, 3, 'AVL', 2, 1529941775),
(823, 46, 35, 1, 4, 'AVL', 0, 0),
(824, 46, 35, 1, 5, 'AVL', 0, 0),
(825, 46, 35, 1, 6, 'AVL', 0, 0),
(826, 46, 35, 1, 7, 'AVL', 0, 0),
(827, 46, 35, 1, 8, 'NONE', 0, 0),
(828, 46, 35, 2, 1, 'AVL', 0, 0),
(829, 46, 35, 2, 2, 'NONE', 0, 0),
(830, 46, 35, 2, 3, 'AVL', 0, 0),
(831, 46, 35, 2, 4, 'SOLD', 29, 0),
(832, 46, 35, 2, 5, 'SOLD', 29, 0),
(833, 46, 35, 2, 6, 'AVL', 29, 1528990691),
(834, 46, 35, 2, 7, 'NONE', 0, 0),
(835, 46, 35, 2, 8, 'AVL', 29, 1528990692),
(836, 46, 35, 3, 1, 'AVL', 0, 0),
(837, 46, 35, 3, 2, 'AVL', 0, 0),
(838, 46, 35, 3, 3, 'NONE', 0, 0),
(839, 46, 35, 3, 4, 'AVL', 0, 0),
(840, 46, 35, 3, 5, 'SOLD', 29, 0),
(841, 46, 35, 3, 6, 'NONE', 0, 0),
(842, 46, 35, 3, 7, 'AVL', 0, 0),
(843, 46, 35, 3, 8, 'AVL', 0, 0),
(844, 46, 35, 4, 1, 'AVL', 0, 0),
(845, 46, 35, 4, 2, 'AVL', 0, 0),
(846, 46, 35, 4, 3, 'AVL', 0, 0),
(847, 46, 35, 4, 4, 'NONE', 0, 0),
(848, 46, 35, 4, 5, 'NONE', 0, 0),
(849, 46, 35, 4, 6, 'SOLD', 10, 0),
(850, 46, 35, 4, 7, 'SOLD', 10, 0),
(851, 46, 35, 4, 8, 'AVL', 0, 0),
(852, 46, 35, 5, 1, 'AVL', 0, 0),
(853, 46, 35, 5, 2, 'AVL', 0, 0),
(854, 46, 35, 5, 3, 'AVL', 0, 0),
(855, 46, 35, 5, 4, 'NONE', 0, 0),
(856, 46, 35, 5, 5, 'NONE', 0, 0),
(857, 46, 35, 5, 6, 'AVL', 0, 0),
(858, 46, 35, 5, 7, 'AVL', 0, 0),
(859, 46, 35, 5, 8, 'AVL', 0, 0),
(860, 46, 35, 6, 1, 'AVL', 0, 0),
(861, 46, 35, 6, 2, 'AVL', 0, 0),
(862, 46, 35, 6, 3, 'NONE', 0, 0),
(863, 46, 35, 6, 4, 'AVL', 0, 0),
(864, 46, 35, 6, 5, 'AVL', 0, 0),
(865, 46, 35, 6, 6, 'NONE', 0, 0),
(866, 46, 35, 6, 7, 'AVL', 0, 0),
(867, 46, 35, 6, 8, 'AVL', 0, 0),
(868, 47, 23, 1, 1, 'AVL', 0, 0),
(869, 47, 23, 1, 2, 'AVL', 0, 0),
(870, 47, 23, 1, 3, 'AVL', 0, 0),
(871, 47, 23, 1, 4, 'AVL', 0, 0),
(872, 47, 23, 1, 5, 'AVL', 0, 0),
(873, 47, 23, 1, 6, 'AVL', 0, 0),
(874, 47, 23, 2, 1, 'AVL', 0, 0),
(875, 47, 23, 2, 2, 'AVL', 0, 0),
(876, 47, 23, 2, 3, 'AVL', 0, 0),
(877, 47, 23, 2, 4, 'AVL', 0, 0),
(878, 47, 23, 2, 5, 'AVL', 0, 0),
(879, 47, 23, 2, 6, 'AVL', 0, 0),
(880, 47, 23, 3, 1, 'AVL', 0, 0),
(881, 47, 23, 3, 2, 'AVL', 0, 0),
(882, 47, 23, 3, 3, 'AVL', 0, 0),
(883, 47, 23, 3, 4, 'AVL', 0, 0),
(884, 47, 23, 3, 5, 'AVL', 0, 0),
(885, 47, 23, 3, 6, 'AVL', 0, 0),
(886, 47, 23, 4, 1, 'AVL', 0, 0),
(887, 47, 23, 4, 2, 'AVL', 0, 0),
(888, 47, 23, 4, 3, 'NONE', 0, 0),
(889, 47, 23, 4, 4, 'AVL', 0, 0),
(890, 47, 23, 4, 5, 'AVL', 0, 0),
(891, 47, 23, 4, 6, 'AVL', 0, 0),
(892, 47, 23, 5, 1, 'AVL', 0, 0),
(893, 47, 23, 5, 2, 'NONE', 0, 0),
(894, 47, 23, 5, 3, 'AVL', 0, 0),
(895, 47, 23, 5, 4, 'NONE', 0, 0),
(896, 47, 23, 5, 5, 'AVL', 0, 0),
(897, 47, 23, 5, 6, 'AVL', 0, 0),
(898, 47, 23, 6, 1, 'NONE', 0, 0),
(899, 47, 23, 6, 2, 'AVL', 0, 0),
(900, 47, 23, 6, 3, 'AVL', 0, 0),
(901, 47, 23, 6, 4, 'AVL', 0, 0),
(902, 47, 23, 6, 5, 'NONE', 0, 0),
(903, 47, 23, 6, 6, 'AVL', 0, 0);

--
-- 转储表的索引
--

--
-- 表的索引 `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`uid`);

--
-- 表的索引 `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `order_id_uindex` (`id`);

--
-- 表的索引 `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_items_orders_id_fk` (`orderID`);

--
-- 表的索引 `play`
--
ALTER TABLE `play`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`id`),
  ADD KEY `PlayID` (`playID`),
  ADD KEY `FK_StudioID_Sch` (`studioID`);

--
-- 表的索引 `seat`
--
ALTER TABLE `seat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `StudioID` (`studioID`);

--
-- 表的索引 `studio`
--
ALTER TABLE `studio`
  ADD PRIMARY KEY (`studio_id`);

--
-- 表的索引 `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_ScheduleID_Schedule` (`scheduleID`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `accounts`
--
ALTER TABLE `accounts`
  MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- 使用表AUTO_INCREMENT `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- 使用表AUTO_INCREMENT `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- 使用表AUTO_INCREMENT `play`
--
ALTER TABLE `play`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- 使用表AUTO_INCREMENT `schedule`
--
ALTER TABLE `schedule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- 使用表AUTO_INCREMENT `seat`
--
ALTER TABLE `seat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=940;

--
-- 使用表AUTO_INCREMENT `studio`
--
ALTER TABLE `studio`
  MODIFY `studio_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- 使用表AUTO_INCREMENT `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=904;

--
-- 限制导出的表
--

--
-- 限制表 `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `order_items_orders_id_fk` FOREIGN KEY (`orderID`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `FK_StudioID_Sch` FOREIGN KEY (`studioID`) REFERENCES `studio` (`studio_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `PlayID` FOREIGN KEY (`playID`) REFERENCES `play` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `seat`
--
ALTER TABLE `seat`
  ADD CONSTRAINT `StudioID` FOREIGN KEY (`studioID`) REFERENCES `studio` (`studio_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `FK_ScheduleID_Schedule` FOREIGN KEY (`scheduleID`) REFERENCES `schedule` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
