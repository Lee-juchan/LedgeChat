package server.ExecModes.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import server.ExecModes.ExecMode;

public class AuthExecMode extends ExecMode {

    private PrintWriter out;

    public AuthExecMode(Socket sock, HashMap<String, Socket> sockets, Connection con) { // client sock 값만 추가로 입력
        super(sock, sockets, con);
        try {
            this.out = new PrintWriter(super.sock.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(String msg) {
        System.out.println("---------------- auth");
        System.out.println("c: " + msg);
        /*
            login [id] [pwd]    사용자 로그인
            join [id] [pwd]     사용자 가입
        */       

        // 토큰 분할
        String tokens[] = msg.split(" "); // " "(공백)으로 구분
        if (tokens.length == 3) {
            String method = tokens[0];
            String id = tokens[1];
            String pwd = tokens[2];
    
            // 처리
            if (method.equalsIgnoreCase("join")) {
                join(id, pwd);
    
            } else if (method.equalsIgnoreCase("login")) {
                login(id, pwd);

            } else if (method.equalsIgnoreCase("break")) { // 임시로 오류 방지
    
            } else {
                out.println("0:wrong format");
                out.flush();
                return;
            }

        } else {
            out.println("0:wrong format");
            out.flush();
            return;
        }
    }

    // user 생성 (회원가입)
    private void join(String id, String pwd) {
        try {
            PreparedStatement pstmt;
            int rows;

            pstmt = super.con.prepareStatement("INSERT IGNORE INTO hedgeChat.user (id, pwd) VALUES (?, ?)");
            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            rows = pstmt.executeUpdate();

            if (rows == 1) { // 변경된 row가 존재
                out.println("1:join");
                out.flush();
                return;
                
            } else { // rows == 0
                out.println("0:join:duplicated id");
                out.flush();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // user 로그인(인증)
    private void login(String id, String pwd) {
        PreparedStatement pstmt;
        ResultSet rs;

        try {
            pstmt = super.con.prepareStatement("SELECT * FROM hedgeChat.user WHERE id= ? AND pwd= ?");
            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            rs = pstmt.executeQuery();
    
            if (rs.next()) { // 퀴리 결과 존재
                out.println("1:login");
                out.flush();
                super.sockets.put(id, super.sock);
                System.out.println(super.sockets);
                return;

            } else { // rows == 0
                out.println("0:login:non-existent id/pwd");
                out.flush();
                return;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
