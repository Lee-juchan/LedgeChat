package server.ExecModes.impl;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import server.ExecModes.ExecMode;

public class ComExecMode extends ExecMode {

    public ComExecMode(Socket sock, HashMap<String, Socket> sockets, Connection con) {
        super(sock, sockets, con);
    }

    @Override
    public void execute(String msg) {
        System.out.println("---------------- com mode");
        System.out.println("c: " + msg);
        /*
            uni user [src_userId] [dst_userId] [content]    사용자에게 유니캐스트
            uni chat [src_userId] [d_chatid] [content]      채팅방에 유니캐스트
            multi user [src_userId] [dst_userId, dst_userId..] [content]  사용자들에게 멀티캐스트
            multi chat [src_userId] [d_chatId, d_chatId..] [content]      채팅방들에게= 멀티캐스트
            broad user [src_userId] [content]     : broadcast all user")  전체 사용자들에 브로드캐스트
            broad chat [src_userId] [content]     : broa all chatRooms")  전체 채팅방에 브로드캐스트
        */       

        // 토큰 분할
        String tokens[] = msg.split(" "); // " "(공백)으로 구분
        if (tokens.length == 4 || tokens.length == 5) {
            String method = tokens[0];
            String type = tokens[1];
            String src = tokens[2];

            if(tokens.length == 4){ // broadcast
        
            } else { // tokens.length == 5
                String dst = tokens[3];
                String content = tokens[4];

                if (method.equalsIgnoreCase("uni")) {
                    uniToUser(src, dst, content);

                } else if (method.equalsIgnoreCase("multi")) {

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

    private void uniToUser(String src, String dst, String content) {
        // try {
        //     Socket dst_sock = super.sockets.get(dst);
        //     // PreparedStatement pstmt;
        //     // int rows;

        //     // pstmt = super.con.prepareStatement("SET @chatRoomId = CONCAT(LEAST(?, ?), _, GREATEST(?, ?));");
        //     // pstmt.setString(1, src);
        //     // pstmt.setString(2, dst);
        //     // pstmt.setString(3, src);
        //     // pstmt.setString(4, dst);
        //     // pstmt.executeUpdate();
            
        //     // pstmt = super.con.prepareStatement("INSERT IGNORE INTO hedgeChat.chatRoom (id) VALUES (@chatRoomId)");
        //     // pstmt.executeUpdate();

        //     // pstmt = super.con.prepareStatement("INSERT INTO hedgeChat.message (userId, content) VALUES (?, ?)");
        //     // pstmt.setString(1, src);
        //     // pstmt.setString(2, content);
        //     // pstmt.executeUpdate();

        //     // pstmt = super.con.prepareStatement("INSERT INTO hedgeChat.sending VALUES (@chatRoomId, LAST_INSERT_ID())");
        //     // pstmt.executeQuery();
            
        //     super.out.println("4:uni user:success");
        //     super.out.flush();

        //     // 에러 처리
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
    }
}
