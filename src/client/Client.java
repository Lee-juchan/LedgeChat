package client;

import java.net.Socket;
import java.net.InetSocketAddress;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.IOException;

import client.Modes.Send;


public class Client {
    // 소켓 생성
    public static void main(String args[]) {
        System.out.println("-- Client Main --");

        Socket sock;
        BufferedReader in;
        PrintWriter out;
        Thread rt;      // 수신 스레드 (Recv.java)
        Send send;      // 송신 클래스
        Scanner sc;
        String msg;
        
        
        // 소켓 생성
        try {
            String s_ip = "127.0.0.1";
            int s_port = 8080;
            InetSocketAddress s_addr = new InetSocketAddress(s_ip, s_port);
            
            // 소켓 생성
            sock = new Socket();
            sock.connect(s_addr);
            System.out.println("-- connected to " + s_addr + " --");
            
            // 스트림 생성
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new PrintWriter(sock.getOutputStream());
            
            // recv (Thread)
            rt = new Thread(new Recv(in));
            rt.setDaemon(true);
            rt.start();
            
            // send
            sc = new Scanner(System.in);
            send = new Send(sock, sc);
            send.setMode("AUTH"); // 초기 모드
            send.execute();
            
            while(!Thread.currentThread().isInterrupted()) {
                // 모드 선택
                System.out.println("--------- Modes ---------");
                System.out.println("USER");
                System.out.println("COM");
                System.out.println("CHAT");
                System.out.println("ECHO");
                System.out.println("SQL");
                System.out.println("-------------------------");
                
                System.out.print("> ");
                msg = sc.nextLine();
                out.println(":" + msg);
                out.flush();
                if(msg.equalsIgnoreCase("exit")) { // com thread 종료
                    break;
                }

                switch (msg.toUpperCase()) {
                    case "USER" -> send.setMode("user");
                    case "CHAT" -> send.setMode("chat");
                    case "COM" -> send.setMode("com");
                    case "ECHO" -> send.setMode("echo");
                    case "SQL" -> send.setMode("sql");
                    default -> {
                        System.out.println("This mode does not exist, input again");
                        continue;
                    }
                }

                send.showHelp();
                send.execute();

            }
            // rt.interrupt(); // Recv thread 중지
            
            // 자원 정리
            sc.close();
            in.close();
            out.close();
            sock.close();
            System.out.println("close ok");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("-- Client Main end --");
    }
}
