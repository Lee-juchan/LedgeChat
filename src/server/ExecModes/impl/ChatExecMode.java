package server.ExecModes.impl;

import java.net.Socket;
import java.sql.Connection;
import java.util.HashMap;

import server.ExecModes.ExecMode;

public class ChatExecMode extends ExecMode {

    public ChatExecMode(Socket sock, HashMap<String, Socket> sockets, Connection con) {
        super(sock, sockets, con);
    }

    @Override
    public void execute(String msg) {
        System.out.println("---------------- chat mode");
        System.out.println("c:" + msg);
        
        String tokens[] = msg.split(" ");
    }
}
