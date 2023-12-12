# HedgeChat

## 목표

- cli 채팅방
- socket 통신
- protocol 설계  
- db 연동 (GCP)
- cli / mode 구현



## 특징 (자랑할 점)

- state pattern         -> 모드 확장성, 통일성 (help, exit)

- GCP mysql-server      -> 구글 클라우드에 서버 실행 (항시)



## 주요 기능 (모드) 
- 로그인 관리   (Auth)  
- 사용자 관리   (User)  
- 채팅방 관리   (Chat)  
- 메시지 전송   (Com)  
- 에코 통신     (Echo)
- 쿼리 실행     (Sql)



## 프로토콜 (MODE | DATA)

## Client (Request)  
### 1. Auth  
    Auth : login [userId] [pwd]			    : user 생성 (회원가입)  
    Auth : logout [userId] [pwd]	        : user 로그인(인증)  

### 2. User  
    User : sh user [userId] 			    : user 프로필 확인  
    User : rm user [userId] 			    : user 프로필 삭제  

    User : mk chat [userId] [chatId] 		: 사용자가 포함된 챗방 생성  
    User : sh chat [userId] [chatId] 		: 사용자가 참여한 챗방  
    User : rm chat [userId] [chatId] 		: 사용자가 참여한 챗방 나가기  

### 3. Chat
    Chat : mk user [chatId] [userId]		: 채팅방에 사용자 추가  
    Chat : sh user [chatId]		            : 채팅방 사용자 목록 확인  
    Chat : rm user [chatId] [userId]		: 채팅방에 사용자 삭제  

    Chat : mk chat [chatId]			        : 채팅방 생성
    Chat : sh chat [chatId]			        : 채팅방 로그 확인
    Chat : rm chat [chatId]			        : 채팅방 삭제

### 4. Com
    Com : uni user [userId] [userId]		        : 유저에게 유니캐스트
    Com : uni chat [userId] [chatId]		        : 채팅방에 유니캐스트

    Com : multi user [userId] [userId, userId ..]	: 유저들에게 멀티캐스트
    Com : multi chat [userId] [chatId, chatId ..]	: 채팅방들에 멀티캐스트

    Com : broad user [userId]			            : 모든 유저에게 브로드캐스트 (관리자)
    Com : broad chat [userId]			            : 모든 채팅방에 브로드캐스트 (관리자)

### 5. Echo  

    Echo : [any]                : 서버에게 echo 메시지 전송


### 6. SQL  

     Sql : [any SQL query]      : 서버에게 sql 명령 전송



## Server (Response)

    [code] : [method] : [info]  

### [code]  
    - 성공 : 1(Auth), 2(User), 3(Chat), 4(Com), 5(Echo), 6(Sql)  
    - 실패 : 0  
  

## 시스템 구조

### Server

ServerDemo              : ip, port, backlog 할당 (main)

    Server              : accept() 받기 (Thread)

        Communication   : 실제 통신 수행 (Thread)
        
        Processor       : mode별 처리 (state exec)


## Client

Client            : socket 생성 (main)

    Recv          : 항시 듣기 (Thread)
    
    Send    : mode 별 커맨드 생성 (state exec)