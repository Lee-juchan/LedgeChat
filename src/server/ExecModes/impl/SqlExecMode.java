package server.ExecModes.impl;

import java.net.Socket;
import java.sql.Connection;
import java.util.HashMap;

import server.ExecModes.ExecMode;

public class SqlExecMode extends ExecMode {

    public SqlExecMode(Socket sock, HashMap<String, Socket> sockets, Connection con) {
        super(sock, sockets, con);
    }

    @Override
    public void execute(String msg) {
        System.out.println("---------------- sql mode");
    }
}
