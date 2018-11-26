/* 테이블 생성 */
CREATE TABLE solve(
id INT(11) NOT NULL PRIMARY KEY auto_increment,
userName VARCHAR (45) NOT NULL,
problemNum INT(11) NOT NULL DEFAULT 0,
submitCount INT(11) NOT NULL DEFAULT 0,
timeStamp DATETIME(3) NOT NULL DEFAULT '0000-00-00 00:00:00',
language VARCHAR(10),
score tinyint(1) NOT NULL DEFAULT 0) CHARACTER SET utf8 COLLATE utf8_general_ci;

/* RankDao - addRank */
INSERT INTO rank(id, userName, problemNum, submitCount, timeStamp, language, score) VALUES(?, ?, ?, ?, ?, ?, ?);