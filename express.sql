CREATE TABLE `user` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`username` varchar(255) NULL,
`password` varchar(255) NULL,
`join_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
`power` tinyint(1) NULL,
`avatar` varchar(255) NULL,
`address` varchar(255) NULL,
PRIMARY KEY (`id`) 
);
CREATE TABLE `express` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(255) NULL,
`price` decimal(10,2) NULL,
`introduce` varchar(255) NULL,
PRIMARY KEY (`id`) 
);
CREATE TABLE `express_company` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(255) NULL,
`introduce` varchar(255) NULL,
`avatar` varchar(255) NULL,
`address` varchar(255) NULL,
PRIMARY KEY (`id`) 
);
CREATE TABLE `send` (
`uuid` varchar(255) NOT NULL,
`express` int(11) NULL,
`price` decimal(10,2) NULL,
`post_user` int(11) NULL,
`receive_user` int(11) NULL,
`company` int(11) NULL,
`position` varchar(255) NULL,
`send_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
`from_address` varchar(255) NULL,
`to_address` varchar(255) NULL,
PRIMARY KEY (`uuid`) 
);
