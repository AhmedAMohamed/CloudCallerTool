-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 26, 2014 at 11:42 AM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `aws_connection`
--
CREATE DATABASE IF NOT EXISTS `aws_connection` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `aws_connection`;

-- --------------------------------------------------------

--
-- Table structure for table `credintials`
--
-- Creation: Aug 24, 2014 at 07:13 AM
--

DROP TABLE IF EXISTS `credintials`;
CREATE TABLE IF NOT EXISTS `credintials` (
  `type_id` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL DEFAULT '0',
  `type_data` varchar(255) NOT NULL,
  PRIMARY KEY (`type_id`,`user_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `credintials`:
--   `user_id`
--       `users` -> `user_id`
--

--
-- Dumping data for table `credintials`
--

INSERT INTO `credintials` (`type_id`, `user_id`, `type_data`) VALUES
(1, 2, 'ahmed try one'),
(1, 4, 'jkbh'),
(3, 2, 'was here');

-- --------------------------------------------------------

--
-- Table structure for table `tools`
--
-- Creation: Aug 24, 2014 at 08:50 AM
--

DROP TABLE IF EXISTS `tools`;
CREATE TABLE IF NOT EXISTS `tools` (
  `type_id` int(11) NOT NULL DEFAULT '0',
  `tool_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`type_id`,`tool_id`),
  KEY `program_id` (`tool_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `tools`:
--   `tool_id`
--       `tools_data` -> `tool_id`
--   `type_id`
--       `credintials` -> `type_id`
--

--
-- Dumping data for table `tools`
--

INSERT INTO `tools` (`type_id`, `tool_id`) VALUES
(1, 1),
(3, 1),
(1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `tools_data`
--
-- Creation: Aug 26, 2014 at 09:33 AM
--

DROP TABLE IF EXISTS `tools_data`;
CREATE TABLE IF NOT EXISTS `tools_data` (
  `tool_id` int(11) NOT NULL DEFAULT '0',
  `tool_name` varchar(255) DEFAULT NULL,
  `calling_name` varchar(255) NOT NULL,
  PRIMARY KEY (`tool_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tools_data`
--

INSERT INTO `tools_data` (`tool_id`, `tool_name`, `calling_name`) VALUES
(1, 'calculator', ''),
(2, 'phone note', ''),
(3, 'asd', '');

-- --------------------------------------------------------

--
-- Table structure for table `tools_registered_by_user`
--
-- Creation: Aug 26, 2014 at 06:22 AM
--

DROP TABLE IF EXISTS `tools_registered_by_user`;
CREATE TABLE IF NOT EXISTS `tools_registered_by_user` (
  `user_id` int(11) NOT NULL DEFAULT '0',
  `tool_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`tool_id`,`user_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `tools_registered_by_user`:
--   `user_id`
--       `users` -> `user_id`
--   `tool_id`
--       `tools` -> `tool_id`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--
-- Creation: Aug 26, 2014 at 06:14 AM
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `user_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_id` int(255) NOT NULL AUTO_INCREMENT,
  `due_date` bigint(10) NOT NULL,
  `start_date` bigint(10) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`,`email`,`password`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_name`, `email`, `password`, `user_id`, `due_date`, `start_date`) VALUES
('ahmed', 'alaa', 'mohamed', 2, 0, 0),
('asd', 'asd', 'asd', 4, 0, 0);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `credintials`
--
ALTER TABLE `credintials`
  ADD CONSTRAINT `credintials_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `tools`
--
ALTER TABLE `tools`
  ADD CONSTRAINT `tools_ibfk_2` FOREIGN KEY (`tool_id`) REFERENCES `tools_data` (`tool_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tools_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `credintials` (`type_id`);

--
-- Constraints for table `tools_registered_by_user`
--
ALTER TABLE `tools_registered_by_user`
  ADD CONSTRAINT `tools_registered_by_user_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tools_registered_by_user_ibfk_1` FOREIGN KEY (`tool_id`) REFERENCES `tools` (`tool_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
