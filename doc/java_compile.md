# java 다중 컴파일 (in Linux)

1. bin 폴더 생성  
: mkdir bin

2. 소스파일 리스트 생성  
: find ./src/server -name "*.java" > ./doc/server_src.txt           / -name ""  해당 이름 찾기
: find ./src/client -name "*.java" > ./doc/client_src.txt           / >         파일로 출력

3. 컴파일  
: javac -d ./bin @./doc/server_src.txt                              / -d        class파일 저장 위치
: javac -d ./bin @./doc/client_src.txt                              / @         .java 파일 위치가 기록된 파일로 컴파일

4. 실행  
: java -cp ./bin:$CLASSPATH ./src/server/ServerDemo.java
: java -cp ./bin ./src/client/Client.java

5. source 리스트 만들고, 컴파일 후 실행 (2~4)
: find ./src/server -name "*.java" > ./doc/server_src.txt && javac -d ./bin @./doc/server_src.txt && java -cp ./bin:$CLASSPATH ./src/server/ServerDemo.java
: find ./src/client -name "*.java" > ./doc/client_src.txt && javac -d ./bin @./doc/client_src.txt && java -cp ./bin ./src/client/Client.java

6. 기존 source 리스트, 컴파일 후 실행 (3~4)
: javac -d ./bin @./doc/server_src.txt && java -cp ./bin:$CLASSPATH ./src/server/ServerDemo.java
: javac -d ./bin @./doc/client_src.txt && java -cp ./bin ./src/client/Client.java

7. 다른 장치에서 실행
: java -cp ./bin:./bin/mysql-connector-j-8.2.0.jar ./src/server/ServerDemo.java
: java -cp ./bin ./src/client/Client.java



# JDBC connection

- GCP SQL : 34.64.126.174

- 내 컴퓨터의 mysql 끄고 접속! (port 겹쳐서 오류남)
- sudo systemctl stop mysql