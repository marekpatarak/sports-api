CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sportsapi`.`hibernate_sequence`
(`next_val`)
VALUES
  (1);

CREATE TABLE IF NOT EXISTS `country` (
  `country_id` int(11) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `country_name` varchar(255) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `league` (
  `league_id` int(11) NOT NULL,
  `is_current` bit(1) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `season` int(11) DEFAULT NULL,
  `season_end` varchar(255) DEFAULT NULL,
  `season_start` varchar(255) DEFAULT NULL,
  `standings` bit(1) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  `xml_feed_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`league_id`),
  KEY `FKtdt2rqg50rlqti7yubvppq25e` (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `team` (
  `team_id` int(11) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `founded` int(11) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `venue_address` varchar(255) DEFAULT NULL,
  `venue_capacity` int(11) DEFAULT NULL,
  `venue_city` varchar(255) DEFAULT NULL,
  `venue_name` varchar(255) DEFAULT NULL,
  `league_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`team_id`),
  KEY `FK9rk8716asfr76xkn99aa3uvp` (`league_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `player` (
  `player_id` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `birth_date` varchar(255) DEFAULT NULL,
  `birth_place` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `height` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `weight` varchar(255) DEFAULT NULL,
  `team_id` int(11) DEFAULT NULL,
  `birth_country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`player_id`),
  KEY `FKdvd6ljes11r44igawmpm1mc5s` (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `playerstatistics` (
  `id` int(11) NOT NULL,
  `captain` bit(1) DEFAULT NULL,
  `cards_red` int(11) DEFAULT NULL,
  `cards_yellow` int(11) DEFAULT NULL,
  `cards_yellow_red` int(11) DEFAULT NULL,
  `dribble_attempts` int(11) DEFAULT NULL,
  `dribble_success` int(11) DEFAULT NULL,
  `duels_total` int(11) DEFAULT NULL,
  `duels_won` int(11) DEFAULT NULL,
  `fouls_commited` int(11) DEFAULT NULL,
  `fouls_drawn` int(11) DEFAULT NULL,
  `games_appearences` int(11) DEFAULT NULL,
  `games_line_ups` int(11) DEFAULT NULL,
  `games_minutes_played` int(11) DEFAULT NULL,
  `goals_assists` int(11) DEFAULT NULL,
  `goals_conceded` int(11) DEFAULT NULL,
  `goals_total` int(11) DEFAULT NULL,
  `injured` bit(1) DEFAULT NULL,
  `passes_accuracy` int(11) DEFAULT NULL,
  `passes_key` int(11) DEFAULT NULL,
  `passes_total` int(11) DEFAULT NULL,
  `penalty_commited` int(11) DEFAULT NULL,
  `penalty_missed` int(11) DEFAULT NULL,
  `penalty_saved` int(11) DEFAULT NULL,
  `penalty_success` int(11) DEFAULT NULL,
  `penalty_won` int(11) DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `shots_on` int(11) DEFAULT NULL,
  `shots_total` int(11) DEFAULT NULL,
  `substitutes_bench` int(11) DEFAULT NULL,
  `substitutes_in` int(11) DEFAULT NULL,
  `substitutes_out` int(11) DEFAULT NULL,
  `tackles_blocks` int(11) DEFAULT NULL,
  `tackles_interceptions` int(11) DEFAULT NULL,
  `tackles_total` int(11) DEFAULT NULL,
  `player_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3hcv5gqd29mc965fb4qm0efq1` (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `teamstatistics` (
  `id` int(11) NOT NULL,
  `draws_away` int(11) DEFAULT NULL,
  `draws_home` int(11) DEFAULT NULL,
  `goals_against_away` int(11) DEFAULT NULL,
  `goals_against_home` int(11) DEFAULT NULL,
  `goals_for_away` int(11) DEFAULT NULL,
  `goals_for_home` int(11) DEFAULT NULL,
  `loses_away` int(11) DEFAULT NULL,
  `loses_home` int(11) DEFAULT NULL,
  `wins_away` int(11) DEFAULT NULL,
  `wins_home` int(11) DEFAULT NULL,
  `team_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmfi1iba6rwrxdc8cne3ukgc38` (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `xmlarticlestub` (
  `guid` varchar(255) NOT NULL,
  `credit` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `league_id` int(11) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `pub_date` varchar(255) DEFAULT NULL,
  `teaser_img` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


