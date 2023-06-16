DROP TABLE IF EXISTS conference_users;
CREATE TABLE conference_users ( userId INT AUTO_INCREMENT PRIMARY KEY ,
login VARCHAR(255),
email VARCHAR(255));