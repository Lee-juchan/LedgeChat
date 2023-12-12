package server.ExecModes;

import java.net.Socket;
import java.sql.Connection;
import java.util.HashMap;

import server.ExecModes.impl.AuthExecMode;
import server.ExecModes.impl.ChatExecMode;
import server.ExecModes.impl.ComExecMode;
import server.ExecModes.impl.EchoExecMode;
import server.ExecModes.impl.SqlExecMode;
import server.ExecModes.impl.UserExecMode;

public class Processor {
    private ExecMode execMode;

    private ExecMode auth;
    private ExecMode user;
    private ExecMode com;
    private ExecMode chat;
    private ExecMode echo;
    private ExecMode sql;

    public Processor(Socket sock, HashMap<String, Socket> sockets, Connection con) {
        this.auth = new AuthExecMode(sock, sockets, con);
        this.user = new UserExecMode(sock, sockets, con);
        this.com = new ComExecMode(sock, sockets, con);
        this.chat = new ChatExecMode(sock, sockets, con);
        this.echo = new EchoExecMode(sock, sockets, con);
        this.sql = new SqlExecMode(sock, sockets, con);
    }

    public void setExecMode(String execMode) {
        switch(execMode.toUpperCase()) {
            case "AUTH" -> {this.execMode = this.auth;}
            case "USER" -> {this.execMode = this.user;}
            case "CHAT" -> {this.execMode = this.chat;}
            case "COM" -> {this.execMode = this.com;}
            case "ECHO" -> {this.execMode = this.echo;}
            case "SQL" -> {this.execMode = this.sql;}
            default -> {this.execMode = null;}
        }
    }

    public void execute(String msg) {
        this.execMode.execute(msg);
    }
}
