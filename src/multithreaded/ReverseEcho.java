/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreaded;

import java.net.*;
import java.io.*;

/**
 *
 * @author rupam
 */
public class ReverseEcho extends Thread {

    Socket stk;
    int clientId;

    public ReverseEcho(Socket stk, int clientId) {
        this.stk = stk;
        this.clientId = clientId;
    }

    @Override
    public void run() {
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(stk.getInputStream()));
            PrintStream ps = new PrintStream(stk.getOutputStream());

            String message;
            StringBuffer sb;
            do {
                System.out.println("\n");
                message = br.readLine();
                System.out.println("Read from Client " + clientId);
                System.out.println("From Client " + clientId + ": " + message);

                sb = new StringBuffer(message);
                sb.reverse();
                ps.println(sb.toString());
                System.out.println("Reply sent to Client " + clientId);
            } while (!message.equals("end"));
            stk.close();
            System.out.println("\nClient Disconnected: " + clientId + "\n");
        } catch (IOException e) {
        }
    }

    public static void main(String args[]) throws IOException {
        ServerSocket ss = new ServerSocket(2000);
        System.out.println("Server Established on port 2000");
        Socket stk;
        int clientCount = 0;
        ReverseEcho re;
        while (true) {
            stk = ss.accept();
            System.out.println("\nClient Connected: " + (++clientCount));
            re = new ReverseEcho(stk, clientCount);
            re.start();
        }
    }
}
