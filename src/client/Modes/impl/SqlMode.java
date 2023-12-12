package client.Modes.impl;

import java.net.Socket;
import java.util.Scanner;

import client.Modes.Mode;

public class SqlMode extends Mode {

    public SqlMode(Socket sock, Scanner sc) {
        super(sock, sc);
    }

    @Override
    public void showHelp() {
        System.out.println("Here is SQL mode");
        System.out.println("------- commands --------");
        System.out.println("help");
        System.out.println("exit\n");
        System.out.println("[ANY SQL]   : execute SQL");
        System.out.println("-------------------------");
    }

    @Override
    public void execute() {
        while (true) {
            System.out.print("SQL> ");
            String sendMsg = super.sc.nextLine();
            
            super.out.println("SQL:" + sendMsg);
            super.out.flush();
            if (sendMsg.equalsIgnoreCase("help")) {
                this.showHelp();
            } else if(sendMsg.equalsIgnoreCase("exit")) {
                break;
            }
        }
    }
}
