package client.Modes.impl;

import java.net.Socket;
import java.util.Scanner;

import client.Modes.Mode;

public class UserMode extends Mode {

    public UserMode(Socket sock, Scanner sc) {
        super(sock, sc);
    }

    @Override
    public void showHelp() {
        System.out.println("Here is USER mode");
        System.out.println("------- commands --------");
        System.out.println("help");
        System.out.println("exit\n");
        System.out.println("sh user [id]            : show user info");
        System.out.println("rm user [id]            : delete user info\n");
        System.out.println("mk chat [id] [chatid]   : create chatRoom, of user");
        System.out.println("sh chat [id] [chatid]   : show chatRoom, of user");
        System.out.println("rm chat [id] [chatid]   : delete chatRoom, of user\n");
        System.out.println("-------------------------");
    }

    @Override
    public void execute() {
        while (true) {
            System.out.print("USER> ");
            String sendMsg = super.sc.nextLine();
            
            super.out.println("USER:" + sendMsg);
            super.out.flush();
            if (sendMsg.equalsIgnoreCase("help")) {
                this.showHelp();
            } else if(sendMsg.equalsIgnoreCase("exit")) {
                break;
            }
        }
    }
}
