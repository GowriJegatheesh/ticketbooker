CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `theatre` (
  `theatre_id` bigint NOT NULL AUTO_INCREMENT,
  `theatre_name` varchar(100) NOT NULL,
  PRIMARY KEY (`theatre_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `movie` (
  `movie_id` bigint NOT NULL AUTO_INCREMENT,
  `movie_name` varchar(100) NOT NULL,
  `theatre_fid` bigint NOT NULL,
  PRIMARY KEY (`movie_id`),
  KEY `fk_movie_theatre_idx` (`theatre_fid`),
  CONSTRAINT `fk_movie_theatre` FOREIGN KEY (`theatre_fid`) REFERENCES `theatre` (`theatre_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `seat_user_association` (
  `seat_number` int NOT NULL AUTO_INCREMENT,
  `user_fid` bigint DEFAULT NULL,
  `movie_fid` bigint DEFAULT NULL,
  PRIMARY KEY (`seat_number`),
  KEY `fk_seat_user_association_idx` (`user_fid`),
  KEY `fk_seat_movie_idx` (`movie_fid`),
  CONSTRAINT `fk_seat_movie` FOREIGN KEY (`movie_fid`) REFERENCES `movie` (`movie_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_seat_user_association` FOREIGN KEY (`user_fid`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
