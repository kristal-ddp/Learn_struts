CREATE USER mini IDENTIFIED BY a123
DEFAULT TABLESPACE USERS
TEMPORARY TABLESPACE TEMP;

GRANT CONNECT,RESOURCE,UNLIMITED TABLESPACE TO mini;

CREATE TABLE MEMBER (
    USERID VARCHAR2(20),
    USERPWD VARCHAR2(20),
    USERNAME VARCHAR2(20),
    USERGENDER VARCHAR2(20),
    USERBIRTH DATE,
    USERADDR VARCHAR2(1000),
    USEREMAIL VARCHAR2(50),
    USERTEL CHAR(11),
    REGISTRATION NUMBER(1),
    CONSTRAINT PK_MEMBER_USERID PRIMARY KEY (USERID));

CREATE TABLE BOARDS (
   BOARDNUM NUMBER,
   USERID VARCHAR2(20),
   SUBJECT VARCHAR2(100),
   CONTENT VARCHAR2(1024),
   POSTDATE DATE,
   HITS NUMBER,
   CONSTRAINT PK_BOARDS_BOARDNUM PRIMARY KEY (BOARDNUM),
   CONSTRAINT FK_BOARDS_USERID FOREIGN KEY (USERID) REFERENCES MEMBER(USERID));

INSERT INTO MEMBER VALUES('asd', 'asd', 'asd', 'M', SYSDATE, 'asd', 'asd', 01011111111, 1);

INSERT INTO BOARDS VALUES(1, 'asd', 1, 1, SYSDATE, 0);
INSERT INTO BOARDS VALUES(2, 'asd', 2, 2, SYSDATE, 0);
INSERT INTO BOARDS VALUES(3, 'asd', 3, 3, SYSDATE, 0);
INSERT INTO BOARDS VALUES(4, 'asd', 4, 4, SYSDATE, 0);
INSERT INTO BOARDS VALUES(5, 'asd', 5, 5, SYSDATE, 0);
INSERT INTO BOARDS VALUES(6, 'asd', 6, 6, SYSDATE, 0);
INSERT INTO BOARDS VALUES(7, 'asd', 7, 7, SYSDATE, 0);
INSERT INTO BOARDS VALUES(8, 'asd', 8, 8, SYSDATE, 0);
INSERT INTO BOARDS VALUES(9, 'asd', 9, 9, SYSDATE, 0);
INSERT INTO BOARDS VALUES(10, 'asd', 10, 10, SYSDATE, 0);
INSERT INTO BOARDS VALUES(11, 'asd', 11, 11, SYSDATE, 0);
INSERT INTO BOARDS VALUES(12, 'asd', 12, 12, SYSDATE, 0);
INSERT INTO BOARDS VALUES(13, 'asd', 13, 13, SYSDATE, 0);
INSERT INTO BOARDS VALUES(14, 'asd', 14, 14, SYSDATE, 0);
INSERT INTO BOARDS VALUES(15, 'asd', 15, 15, SYSDATE, 0);
INSERT INTO BOARDS VALUES(16, 'asd', 16, 16, SYSDATE, 0);
INSERT INTO BOARDS VALUES(17, 'asd', 17, 17, SYSDATE, 0);
INSERT INTO BOARDS VALUES(18, 'asd', 18, 18, SYSDATE, 0);
INSERT INTO BOARDS VALUES(19, 'asd', 19, 19, SYSDATE, 0);
INSERT INTO BOARDS VALUES(20, 'asd', 20, 20, SYSDATE, 0);
INSERT INTO BOARDS VALUES(21, 'asd', 21, 21, SYSDATE, 0);
INSERT INTO BOARDS VALUES(22, 'asd', 22, 22, SYSDATE, 0);
INSERT INTO BOARDS VALUES(23, 'asd', 23, 23, SYSDATE, 0);
INSERT INTO BOARDS VALUES(24, 'asd', 24, 24, SYSDATE, 0);
INSERT INTO BOARDS VALUES(25, 'asd', 25, 25, SYSDATE, 0);
INSERT INTO BOARDS VALUES(26, 'asd', 26, 26, SYSDATE, 0);
INSERT INTO BOARDS VALUES(27, 'asd', 27, 27, SYSDATE, 0);
INSERT INTO BOARDS VALUES(28, 'asd', 28, 28, SYSDATE, 0);
INSERT INTO BOARDS VALUES(29, 'asd', 29, 29, SYSDATE, 0);
INSERT INTO BOARDS VALUES(30, 'asd', 30, 30, SYSDATE, 0);
INSERT INTO BOARDS VALUES(31, 'asd', 31, 31, SYSDATE, 0);