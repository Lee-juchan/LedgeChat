package server.ExecModes.impl;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import server.ExecModes.ExecMode;

public class UserExecMode extends ExecMode {

    public UserExecMode(Socket sock, HashMap<String, Socket> sockets, Connection con) {
        super(sock, sockets, con);
    }

    @Override
    public void execute(String msg) {
        System.out.println("---------------- user mode");
        System.out.println("c:" + msg);
        /*
            sh user [id]    사용자 정보 확인
            rm user [id]    사용자 삭제
            
            mk chat [id] [chatid]   채팅방 생성         (사용자의)
            sh chat [id] [chatid]   채팅방 목록 확인    (사용자의)
            rm chat [id] [chatid]   채팅방 나가기       (사용자의)
        */
        
        // 토큰 분할
        String tokens[] = msg.split(" "); // " "(공백)으로 구분
        if (tokens.length == 3 || tokens.length == 4) {
            String method = tokens[0];
            // String type = tokens[1];
            String userId = tokens[2];

            //
            if (tokens.length == 3) {
                if (method.equalsIgnoreCase("sh")) {
                    showUser(userId);

                } else if(method.equalsIgnoreCase("rm")) {
                    removeUser(userId);

                } else {
                    out.println("0:wrong format");
                    out.flush();
                    return;
                }
                
            //
            } else { // tokens.length == 4
                String chatId = tokens[3];

                if (method.equalsIgnoreCase("mk")) {
                    createChatRoom(userId, chatId);

                } else if (method.equalsIgnoreCase("sh")) {
                    showChatRoom(userId, chatId);

                } else if(method.equalsIgnoreCase("rm")) {
                    removeChatRoom(userId, chatId);

                } else {
                    out.println("0:wrong format");
                    out.flush();
                    return;
                }
            }
        } else {
            out.println("0:wrong format");
            out.flush();
            return;
        }
    }

    private void showUser(String userId) {
        PreparedStatement pstmt;
        ResultSet rs;
        
        try {
            pstmt = super.con.prepareStatement("SELECT * FROM hedgeChat.user WHERE id= ?");
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            ////////////////
            if (rs.next()) { // 퀴리 결과 존재
                out.println("1:login");
                out.flush();
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
    private void removeUser(String userId) {
    }

    private void showChatRoom(String userId, String chatId) {
    }
    private void createChatRoom(String userId, String chatId) {
    }
    private void removeChatRoom(String userId, String chatId) {
    }
}
