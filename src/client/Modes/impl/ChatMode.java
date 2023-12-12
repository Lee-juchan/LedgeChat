package client.Modes.impl;

import java.net.Socket;
import java.util.Scanner;

import client.Modes.Mode;

public class ChatMode extends Mode {

    public ChatMode(Socket sock, Scanner sc) {
        super(sock, sc);
    }

    @Override
    public void showHelp() {
        System.out.println("Here is CHAT mode");
        System.out.println("------- commands --------");
        System.out.println("help");
        System.out.println("exit\n");
        System.out.println("add user [chatId] [userId]      : add user, in chatRoom");
        System.out.println("sh user [chatId]                : show users, of chatRoom profile\n");
        System.out.println("rm user [chatId] [userId]       : remove user, in chatRoom\n");
        System.out.println("mk chat [chatId]        : create chatRoom");
        System.out.println("sh chat [chatId]        : show messages, of chatRoom");
        System.out.println("rm chat [chatId]        : delete chatRoom");
        System.out.println("-------------------------");
    }

    @Override
    public void execute() {
        while (true) {
            System.out.print("CHAT> ");
            String sendMsg = super.sc.nextLine();
            
            super.out.println("CHAT:" + sendMsg);
            super.out.flush();
            if (sendMsg.equalsIgnoreCase("help")) {
                this.showHelp();
            } else if(sendMsg.equalsIgnoreCase("exit")) {
                break;
            }
        }
    }
}
