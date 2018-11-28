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


/* 실사용 */
INSERT INTO users(username,password,enabled,grade) VALUES ('공지혁18','44WjApuf', true, 1);
INSERT INTO users(username,password,enabled,grade) VALUES ('김민재18','WIiT6cfc', true, 1);
INSERT INTO users(username,password,enabled,grade) VALUES ('김현수18','eAD7aSLt', true, 1);
INSERT INTO users(username,password,enabled,grade) VALUES ('박명준18','FSli5MiF', true, 1);
INSERT INTO users(username,password,enabled,grade) VALUES ('박준형18','82tWPgfE', true, 1);
INSERT INTO users(username,password,enabled,grade) VALUES ('유휘빈18','B5bB63vu', true, 1);
INSERT INTO users(username,password,enabled,grade) VALUES ('임형준18','isQVeY1Z', true, 1);
INSERT INTO users(username,password,enabled,grade) VALUES ('이근영18','5Skx1bLM', true, 1);
INSERT INTO users(username,password,enabled,grade) VALUES ('현재혁18','QBoNh7Jz', true, 1);

INSERT INTO users(username,password,enabled,grade) VALUES ('양봉은14','cX83n558', true, 2);
INSERT INTO users(username,password,enabled,grade) VALUES ('강용재15','9PcXK22N', true, 2);
INSERT INTO users(username,password,enabled,grade) VALUES ('고득준15','ZzykV1Gh', true, 2);
INSERT INTO users(username,password,enabled,grade) VALUES ('김도원15','jadFN0qT', true, 2);
INSERT INTO users(username,password,enabled,grade) VALUES ('김범준15','Lb2xxMix', true, 2);
INSERT INTO users(username,password,enabled,grade) VALUES ('김한주15','cUplep0R', true, 2);
INSERT INTO users(username,password,enabled,grade) VALUES ('원동훈15','orHBH9g8', true, 2);
INSERT INTO users(username,password,enabled,grade) VALUES ('이민재15','RX0Zyruz', true, 2);
INSERT INTO users(username,password,enabled,grade) VALUES ('이훈15','C6N4cRlw', true, 2);
INSERT INTO users(username,password,enabled,grade) VALUES ('허지식15','R7rQS6bh', true, 2);
INSERT INTO users(username,password,enabled,grade) VALUES ('현지훈15','3kopIiQG', true, 2);
INSERT INTO users(username,password,enabled,grade) VALUES ('강수연17','pQbXpe4Q', true, 2);
INSERT INTO users(username,password,enabled,grade) VALUES ('강혁준17','kR7vytFX', true, 2);
INSERT INTO users(username,password,enabled,grade) VALUES ('곽효림17','5vdqkUmZ', true, 2);
INSERT INTO users(username,password,enabled,grade) VALUES ('박민주17','dNGp1a3x', true, 2);
INSERT INTO users(username,password,enabled,grade) VALUES ('유은지17','4Ufq2WMy', true, 2);
INSERT INTO users(username,password,enabled,grade) VALUES ('임혜원17','hN1kYwXX', true, 2);
INSERT INTO users(username,password,enabled,grade) VALUES ('조영우17','0RIi35M0', true, 2);

INSERT INTO users(username,password,enabled,grade) VALUES ('오승열13','XRj53cYM', true, 3);
INSERT INTO users(username,password,enabled,grade) VALUES ('최원범13','AhYGU34n', true, 3);
INSERT INTO users(username,password,enabled,grade) VALUES ('강민규14','F6OpLvez', true, 3);
INSERT INTO users(username,password,enabled,grade) VALUES ('김근우14','1WHg5DkE', true, 3);
INSERT INTO users(username,password,enabled,grade) VALUES ('박기배14','giUl0Brg', true, 3);
INSERT INTO users(username,password,enabled,grade) VALUES ('오현규14','T9h3CmfQ', true, 3);
INSERT INTO users(username,password,enabled,grade) VALUES ('한윤석14','EMCcU8cJ', true, 3);
INSERT INTO users(username,password,enabled,grade) VALUES ('구수정16','zJzt9kIi', true, 3);
INSERT INTO users(username,password,enabled,grade) VALUES ('김승현16','dP2zVFXz', true, 3);
INSERT INTO users(username,password,enabled,grade) VALUES ('윤다영16','VB1a7ggz', true, 3);

/*실사*/

INSERT INTO user_roles (username, role) VALUES ('공지혁18', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('김민재18', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('김현수18', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('박명준18', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('박준형18', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('유휘빈18', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('임형준18', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('이근영18', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('현재혁18', 'ROLE_USER');

INSERT INTO user_roles (username, role) VALUES ('양봉은14', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('강용재15', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('고득준15', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('김도원15', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('김범준15', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('김한주15', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('원동훈15', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('이민재15', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('이훈15', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('허지식15', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('현지훈15', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('강수연17', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('강혁준17', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('곽효림17', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('박민주17', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('유은지17', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('임혜원17', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('조영우17', 'ROLE_USER');

INSERT INTO user_roles (username, role) VALUES ('오승열13', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('최원범13', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('강민규14', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('김근우14', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('박기배14', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('오현규14', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('한윤석14', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('구수정16', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('김승현16', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('윤다영16', 'ROLE_USER');