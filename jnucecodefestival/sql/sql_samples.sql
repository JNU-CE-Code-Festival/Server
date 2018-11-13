/* 사용자 테이블 */
CREATE  TABLE users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username)
) character set utf8 collate utf8_general_ci;

/* 사용자 권한 테이블*/
CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username)
) character set utf8 collate utf8_general_ci;

/* 사용자 계정 삽입 */
INSERT INTO users(username,password,enabled) VALUES ('ssh','manager1', true);
INSERT INTO users(username,password,enabled) VALUES ('lcg','manager2', true);
INSERT INTO users(username,password,enabled) VALUES ('hjh','manager3', true);
INSERT INTO users(username,password,enabled) VALUES ('ksj','manager4', true);
INSERT INTO users(username,password,enabled) VALUES ('kyj','manager5', true);
INSERT INTO users(username,password,enabled) VALUES ('최원범','cwb123', true);
INSERT INTO users(username,password,enabled) VALUES ('김대남','kdn123', true);

/* 관리자 권한 삽입 */
INSERT INTO user_roles (username, role) VALUES ('ssh', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('lcg', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('hjh', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('ksj', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('kyj', 'ROLE_ADMIN');

/* 사용자 권한 삽입 */
INSERT INTO user_roles (username, role) VALUES ('최원범', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('김대남', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('ssh', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('lcg', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('hjh', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('ksj', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('kyj', 'ROLE_USER');