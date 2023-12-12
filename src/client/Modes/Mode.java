package client.Modes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public abstract class Mode {

    protected Socket sock;
    protected Scanner sc;

    protected BufferedReader in;
    protected PrintWriter out;

    public Mode(Socket sock, Scanner sc) {
        this.sock = sock;
        this.sc = sc;

        try {
            this.in = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
            this.out = new PrintWriter(new OutputStreamWriter(this.sock.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void showHelp();
    public abstract void execute();
}