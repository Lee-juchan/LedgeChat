package client.Modes.impl;

import java.net.Socket;
import java.util.Scanner;

import client.Modes.Mode;

public class EchoMode extends Mode {

    public EchoMode(Socket sock, Scanner sc) {
        super(sock, sc);
    }

    @Override
    public void showHelp() {
        System.out.println("Here is ECHO mode");
        System.out.println("------- commands --------");
        System.out.println("help");
        System.out.println("exit\n");
        System.out.println("[ANY TEXT]  : recv echo message");
        System.out.println("-------------------------");
    }

    @Override
    public void execute() {
        while (true) {
            System.out.print("ECHO> ");
            String sendMsg = super.sc.nextLine();
            
            super.out.println("ECHO:" + sendMsg);
            super.out.flush();
            if (sendMsg.equalsIgnoreCase("help")) {
                this.showHelp();
            } else if(sendMsg.equalsIgnoreCase("exit")) {
                break;
            }
        }
    }
}
