-- Active: 1702105981041@@34.64.126.174@3306@hedgeChat
-- Active: 1700125034408@@127.0.0.1@3306@chatting

-- chatting DATABASE
CREATE SCHEMA chatting;

-- user TABLE
CREATE TABLE user (
    id VARCHAR(20) NOT NULL,
    pwd VARCHAR(20) NOT NULL,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8

-- chat TABLE
CREATE TABLE chatRoom (
    id VARCHAR(20) NOT NULL,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8

-- message TABLE
CREATE TABLE message (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    userId VARCHAR(20) NOT NULL,
    content TEXT NOT NULL,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY(userId) REFERENCES hedgeChat.user(id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB CHARSET=utf8

-- user_chat TABLE
CREATE TABLE chatting (
    userId VARCHAR(20) NOT NULL,
    chatRoomId VARCHAR(20) NOT NULL,
    PRIMARY KEY (userId, chatRoomId),
    FOREIGN KEY(userId) REFERENCES hedgeChat.user(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(chatRoomId) REFERENCES hedgeChat.chatRoom(id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB CHARSET=utf8

-- chat_message TABLE
CREATE TABLE sending (
    chatRoomId VARCHAR(20) NOT NULL,
    messageId INT UNSIGNED NOT NULL,
    PRIMARY KEY (chatRoomId, messageId),
    FOREIGN KEY(chatRoomId) REFERENCES hedgeChat.chatRoom(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(messageId) REFERENCES hedgeChat.message(id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB CHARSET=utf8