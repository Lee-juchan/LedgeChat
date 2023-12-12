package client.Modes.impl;
import java.net.Socket;
import java.util.Scanner;

import client.Modes.Mode;

public class AuthMode extends Mode {

    public AuthMode(Socket sock, Scanner sc) {
        super(sock, sc);
    }

    @Override
    public void showHelp() {
        System.out.println("Here is USER mode");
        System.out.println("------- commands --------");
        System.out.println("help");
        System.out.println("exit\n");
        System.out.println("login [id] [pwd]");
        System.out.println("join [id] [pwd]");
        System.out.println("-------------------------");
    }

    @Override
    public void execute() {
        String sendMsg;
        // String recvMsg;
        // String[] tokens;

        while (true) {
            // send
            System.out.print("AUTH> ");
            sendMsg = super.sc.nextLine();
            super.out.println("AUTH:" + sendMsg);
            super.out.flush();
            if (sendMsg.equalsIgnoreCase("help")) {
                this.showHelp();
            }

            if (sendMsg.equalsIgnoreCase("break")) {
                break;
            }

            // 서버 측에 정보 받도록
            // try {
            //     // recv
            //     recvMsg = super.in.readLine();
            //     // tokens = recvMsg.split(":");
            //     // System.out.println(recvMsg);
            //     // System.out.println(tokens.length);
            //     if (recvMsg.equalsIgnoreCase("1:join")) {
            //         break;
            //     }

            // } catch (IOException e) {
            //     e.printStackTrace();
            // }
        }
    }
}
