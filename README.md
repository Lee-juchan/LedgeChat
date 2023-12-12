## 실행법

### 1. 파일 실행 (jdk 설치, $JAVA_HOME 설정했다고 가정) 

- 실행 위치: /HedgeChat  
- 실행 방법: cli        (아래 커맨드 붙여넣기)  

<서버>  
java -cp ./bin:./bin/mysql-connector-j-8.2.0.jar ./src/server/ServerDemo.java  

<클라이언트>  
java -cp ./bin ./src/client/Client.java  


### 2. 실행 흐름

1. AUTH> help               : protocol 보기
2. AUTH> join [ID] [PWD]    : 회원가입
3. AUTH> login [ID] [PWD]   : 로그인
4. AUTH> break              : AUTH> 모드 나가기
5. > [MODE]                 : 모드 선택

* db, socket 작업은 AUTH, ECHO만 작동 (미완) *
