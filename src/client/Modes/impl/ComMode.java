package client.Modes.impl;

import java.net.Socket;
import java.util.Scanner;

import client.Modes.Mode;

public class ComMode extends Mode {

    public ComMode(Socket sock, Scanner sc) {
        super(sock, sc);
    }

    @Override
    public void showHelp() {
        System.out.println("Here is COM mode");
        System.out.println("------- commands --------");
        System.out.println("help");
        System.out.println("exit\n");
        System.out.println("uni user [src_userId] [dst_userId]    : unicast to user");
        System.out.println("uni chat [src_userId] [d_chatid]      : unicast to chatRoom\n");
        System.out.println("multi user [src_userId] [dst_userId, dst_userId..]      : multicast to users");
        System.out.println("multi chat [src_userId] [d_chatId, d_chatId..]          : multicast to chatRooms\n");
        System.out.println("broad user [src_userId]     : broadcast all user");
        System.out.println("broad chat [src_userId]     : broa all chatRooms");
        System.out.println("-------------------------");
    }

    @Override
    public void execute() {
        while (true) {
            System.out.print("COM> ");
            String sendMsg = super.sc.nextLine();
            
            super.out.println("COM:" + sendMsg);
            super.out.flush();
            if (sendMsg.equalsIgnoreCase("help")) {
                this.showHelp();
            } else if(sendMsg.equalsIgnoreCase("exit")) {
                break;
            }
        }
    }
}
