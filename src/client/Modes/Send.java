package client.Modes;

import java.net.Socket;
import java.util.Scanner;

import client.Modes.impl.AuthMode;
import client.Modes.impl.ChatMode;
import client.Modes.impl.ComMode;
import client.Modes.impl.EchoMode;
import client.Modes.impl.SqlMode;
import client.Modes.impl.UserMode;


public class Send {
    private Mode mode;

    private Mode auth;
    private Mode user;
    private Mode chat;
    private Mode com;
    private Mode echo;
    private Mode sql;

    public Send(Socket sock, Scanner sc) {
        this.auth = new AuthMode(sock, sc);
        this.user = new UserMode(sock, sc);
        this.com = new ComMode(sock, sc);
        this.chat = new ChatMode(sock, sc);
        this.sql = new SqlMode(sock, sc);
        this.echo = new EchoMode(sock, sc);
        
        this.mode = auth;
    }

    public void setMode(String mode) {
        switch (mode.toUpperCase()) {
            case "USER" -> {this.mode = this.user;}
            case "CHAT" -> {this.mode = this.chat;}
            case "COM" -> {this.mode = this.com;}
            case "ECHO" -> {this.mode = this.echo;}
            case "SQL" -> {this.mode = this.sql;}
        }
    }

    public void showHelp() {
        this.mode.showHelp();
    }

    public void execute() {
        this.mode.execute();
    }
}
