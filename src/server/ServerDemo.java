package server;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;

public class ServerDemo {
    public static void main(String args[]) {
        System.out.println("-- serverDemo Main --"); // for test

        Thread st;  // <- 서버 스레드 (Server.java)
        Scanner sc;
        String msg; // 종료("quit") 확인
        
        
        // ip, port, (inetaddr), backlog 설정
        String ip = "127.0.0.1";
        int port = 8080;
        int backlog = 3;
        SocketAddress addr = new InetSocketAddress(ip, port);

        // server 스레드 생성
        st = new Thread(new Server(addr, backlog));
        st.setDaemon(true);
        st.start();

        // 종료 체크
        sc = new Scanner(System.in);

        while (true) {
            msg = sc.nextLine();
            if(msg.equalsIgnoreCase("quit") | msg.equalsIgnoreCase("q")) {
                // st.getThreadGroup().interrupt(); // server thread interrupt()
                break;
            }
        }
        
        // 자원 정리
        sc.close();
        System.out.println("clean ServerDemo");

        try (Socket tmp = new Socket()) { // 임시 소켓 연결
            tmp.connect(addr); 

            st.join();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("-- serverDemo Main end --"); // for test
    }
}