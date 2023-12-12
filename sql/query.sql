-- Active: 1702105981041@@34.64.126.174@3306@hedgeChat

-- 1. Auth
-- Auth : join (id) (pwd)       : user 프로필 생성
INSERT IGNORE INTO hedgeChat.user (id, pwd) VALUES ("juchan", "1234");

-- Auth : login (id) (pwd)      : user 로그인
SELECT * FROM hedgeChat.user WHERE id="juchan" AND pwd="1234";


-- 2. User
-- User : sh user (id) 			: user 프로필 확인
SELECT * FROM hedgeChat.user WHERE id="juchan";

-- User : rm user (id) 			: user 프로필 삭제
DELETE FROM hedgeChat.user WHERE id="juchan";

-- User : mk chat (id) chatid (body) 	: 사용자가 포함된 챗방 생성
SELECT * FROM hedgeChat.user WHERE id="juchan"; -- user 존재 확인
INSERT IGNORE INTO hedgeChat.chatRoom (id) VALUES ("home"); -- (unless) 생성
INSERT IGNORE INTO hedgeChat.chatting (userId, chatRoomId) VALUES ("juchan", "home");

-- User : sh chat (id) chatid 			: 사용자가 참여한 챗방
SELECT * FROM hedgeChat.chatRoom
WHERE id IN (
    SELECT chatRoomId FROM hedgeChat.chatting
    WHERE userId="juchan"
);

-- User : rm chat (id) chatid 			: 사용자가 참여한 챗방 나가기
DELETE FROM hedgeChat.chatting WHERE userId="juchan" AND chatRoomId="home";


-- 3. Chat
-- Chat : mk user (chatid) id			: 채팅방에 사용자 추가
INSERT IGNORE INTO hedgeChat.chatting VALUES ("juchan", "home");

-- Chat : sh user (chatid) ((id))		: 채팅방 사용자 목록 확인 (id가 필요한가??)
SELECT * FROM hedgeChat.user
WHERE id IN (
    SELECT userId FROM hedgeChat.chatting
    WHERE chatRoomId="home"
);

-- Chat : rm user (chatid) id			: 채팅방에 사용자 삭제
DELETE FROM hedgeChat.chatting WHERE userId="juchan" AND chatRoomId="home";

-- Chat : mk chat (chatid)			: 채팅방 생성
INSERT IGNORE INTO hedgeChat.chatRoom (id) VALUES ("home");

-- Chat : sh chat (chatid)			: 채팅방 로그(메시지 기록) 확인
SELECT * FROM hedgeChat.message
WHERE id IN (
    SELECT messageId FROM hedgeChat.sending
    WHERE chatRoomId="home"
);

-- Chat : rm chat (chatid)			: 채팅방 삭제
DELETE FROM hedgeChat.chatRoom WHERE id="home"


-- 4. Com
-- Com : uni user (id) id		: 유저에게 유니캐스트
SET @chatRoomId = CONCAT(LEAST("juchan", "sebin"), "_", GREATEST("juchan", "sebin"));
INSERT IGNORE INTO hedgeChat.chatRoom (id) VALUES (@chatRoomId);
INSERT INTO hedgeChat.message (userId, content) VALUES ("juchan", "hi sebin!");
INSERT INTO hedgeChat.sending VALUES (@chatRoomId, LAST_INSERT_ID());



-- Com : uni chat (id) chatid		: 채팅방에 유니캐스트
INSERT INTO hedgeChat.message (userId, content) VALUES ("juchan", "hello ~");
INSERT INTO hedgeChat.sending VALUES ("home", LAST_INSERT_ID());

-- Com : multi user (id) id, id, ..		: 유저들에게 멀티캐스트
-- Com : multi chat (id) chatid, chatid, ..	: 채팅방들에 멀티캐스트

-- Com : broad user (id)			: 모든 유저에게 브로드캐스트 (관리자) = 로그 안 남김
-- Com : broad chat (id)			: 모든 채팅방에 브로드캐스트 (관리자)