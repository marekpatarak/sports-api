ALTER TABLE `user`
    ADD `enabled` BOOLEAN NOT NULL DEFAULT 0;

CREATE TABLE IF NOT EXISTS `authority` (
  `username` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


