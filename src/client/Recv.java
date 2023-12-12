package client;

import java.io.BufferedReader;
import java.io.IOException;

public class Recv implements Runnable {
    private BufferedReader in;

    public Recv(BufferedReader in) {
        this.in = in;
    }

    // recv
    @Override
    public void run() {
        System.out.println("-- recv Thread --");
        String msg = "";
        String[] tokens;
        String code;
        String data;

        while(!Thread.currentThread().isInterrupted()) {
            try {
                msg = in.readLine();
                // tokens = msg.split(":");
                // if (tokens.length == 2) {
                //     code = tokens[0];
                //     data = tokens[1];

                //     if(!code.equals("")) {
                //         System.out.println(data);
                //     } else {}
                // }

                System.out.println("\n" + msg);
                if(msg == null) { // 서버 종료 시 (서버가 quit ro ctrl+C)
                    System.out.println("-- disconnected --");
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        Thread.currentThread().getThreadGroup().interrupt(); // Client thread interrupt
        System.out.println("-- recv Thread end--");
    }
}
