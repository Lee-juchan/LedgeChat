package server.ExecModes.impl;

import java.net.Socket;
import java.sql.Connection;
import java.util.HashMap;

import server.ExecModes.ExecMode;

public class EchoExecMode extends ExecMode {

    public EchoExecMode(Socket sock, HashMap<String, Socket> sockets, Connection con) {
        super(sock, sockets, con);
    }

    @Override
    public void execute(String msg) {
        System.out.println("---------------- echo mode");
        System.out.println("c: " + msg);
        super.out.println("s: " + msg);
        super.out.flush();
    }
}
