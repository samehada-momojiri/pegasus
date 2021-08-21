CREATE TABLE IF NOT EXISTS t_users (
    ID              INT(11)       NOT NULL auto_increment PRIMARY KEY COMMENT 'ID',
    MAIL_ADDRESS    VARCHAR(256)  NOT NULL                            COMMENT '���[���A�h���X',
    USER_NAME       VARCHAR(15)   NOT NULL                            COMMENT '���[�U��',
    PASSWORD        VARCHAR(256)  NOT NULL                            COMMENT '�p�X���[�h',
    LAST_LOGIN_TIME DATETIME                                          COMMENT '�ŏI���O�C������',
    REGIST_TIME     DATETIME      NOT NULL                            COMMENT '�o�^����',
    UPDATE_TIME     DATETIME      NOT NULL                            COMMENT '�X�V����',
    DELETED         TINYINT(1)    NOT NULL DEFAULT 0                  COMMENT '�폜�t���O'
) DEFAULT CHARSET=utf8mb4
;

INSERT INTO t_users (MAIL_ADDRESS, USER_NAME, PASSWORD, LAST_LOGIN_TIME, REGIST_TIME, UPDATE_TIME)
VALUES
('jack@hoge.example.jp', 'Jack', SHA2('1234567', 256), NULL, NOW(), NOW()),
('lary@hoge.example.jp', 'Lary', SHA2('1234567', 256), NULL, NOW(), NOW()),
('pitt@hoge.example.jp', 'Pitt', SHA2('1234567', 256), NULL, NOW(), NOW());
