package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.net.InetSocketAddress;
import java.io.IOException;

public class Server implements Runnable {
    // property
    private SocketAddress addr; // address
    private int backlog;

    // constructor
    public Server(SocketAddress addr, int backlog) {
        this.addr = addr;
        this.backlog = backlog;
    }
    public Server(String ip, int port, int backlog) { // ip, port -> SocketAddress
        this.addr = new InetSocketAddress(ip, port);
        this.backlog = backlog;
    }


    @Override
    public void run() {
        System.out.println("-- server Thread --");
        ServerSocket s_sock = null;
        Socket c_sock = null;
        Connection con = null;                      // mysql connection
        HashMap<String, Socket> sockets = null;     // id-socket map
        SocketAddress c_addr;
        Thread ct = null;                           // communication Thread
        

        // hashmap 생성  (id - socket)
        sockets = new HashMap<>();

        // db connection 생성
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://:3306/hedgeChat";
            String id = "";
            String password = "";

            Class.forName(driver);
            con = DriverManager.getConnection(url, id, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try {
            // server 소켓 생성
            s_sock = new ServerSocket();
            s_sock.bind(addr, backlog);
            
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("-- listening.. --");
                
                // client 소켓 생성
                c_sock = s_sock.accept();
                c_addr = c_sock.getRemoteSocketAddress();
                System.out.println("-- " + c_addr + " connected --"); // ip/port 확인

                // 통신 스레드 실행
                ct = new Thread(new Communication(c_sock, sockets, con));
                ct.setDaemon(true);
                ct.start();
            }

            // 자원 반납
            ct.interrupt(); // 안기다림

            c_sock.close();
            s_sock.close();
            con.close();
            System.out.println("clean Server");
            
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        System.out.println("-- server Thread end --"); // for test
    }
}
