DROP TABLE IF EXISTS conference_users;
CREATE TABLE conference_users (userId INT AUTO_INCREMENT PRIMARY KEY ,
                                login VARCHAR(255),
                                email VARCHAR(255));
-- DROP TABLE IF EXISTS lecture;
-- CREATE TABLE lecture (lectureId INT,path INT, startDate TIMESTAMP,endDate TIMESTAMP);
--insert into lecture values ( 1,1,'2008-11-11 13:23:44','2008-11-11 13:23:44');