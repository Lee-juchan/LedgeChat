package server.ExecModes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.util.HashMap;

public abstract class ExecMode {

    protected Socket sock;
    protected HashMap<String, Socket> sockets;
    protected Connection con;

    protected BufferedReader in;
    protected PrintWriter out;
    
    public ExecMode(Socket sock, HashMap<String, Socket> sockets, Connection con) {
        this.sock = sock;
        this.sockets = sockets;
        this.con = con;

        try {
            this.in = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
            this.out = new PrintWriter(new OutputStreamWriter(this.sock.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void execute(String msg);
}