package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.sql.Connection;
import java.util.HashMap;

import server.ExecModes.Processor;
import server.ExecModes.impl.AuthExecMode;

public class Communication implements Runnable {
    private Socket c_sock;
    private HashMap<String, Socket> sockets;
    private Connection con;

    // constructor
    public Communication(Socket c_sock, HashMap<String, Socket> sockets, Connection con) {
        this.c_sock = c_sock;
        this.sockets = sockets;
        this.con = con;
    }
    
    @Override
    public void run() {
        System.out.println("-- com Thread --"); // for test

        PrintWriter out = null;
        BufferedReader in = null;
        SocketAddress c_addr;
        Processor proc;


        try {
            // 스트림 생성
            in = new BufferedReader(new InputStreamReader(c_sock.getInputStream()));
            out = new PrintWriter(c_sock.getOutputStream());

            proc = new Processor(c_sock, sockets, con);
            proc.setExecMode("auth");

            // 통신 수행 (send, recv)
            while (true) {
                // recv
                String msg = in.readLine();
                System.out.println("c: " + msg);
                if(msg == null || msg.equalsIgnoreCase("exit")) { // 클라이언트가 ctrl + c 누르면 null로 나옴
                    break;
                }

                /*
                                token.length        token[0]    token[1]

                    USER:hello  = 2 (execute)       USER        hello
                    :SQL        = 2 (setMode)       ""          SQL
                    User:       = 1 (x)             User        x
                    :           = 0 (x)             x           x
                */
                // 받은 메시지의 타입에 따라 처리 (send, DB)
                String[] tokens = msg.split(":");
                String mode;
                String data;

                if (tokens.length == 2) {
                    mode = tokens[0];
                    data = tokens[1];

                    if (mode.equals("")) {      // :USER
                        proc.setExecMode(data);
                    } else {                    // USER:make
                        proc.execute(data);
                    }
                } else {}    // USER:        // :
            }

            c_addr = c_sock.getRemoteSocketAddress();
            System.out.println("-- " + c_addr + " closed --");
    
            // 자원 반납
            if (!c_sock.isClosed()) {
                in.close();
                out.close();
                System.out.println("clean Com");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("-- com Thread end--"); // for test
    }
}
