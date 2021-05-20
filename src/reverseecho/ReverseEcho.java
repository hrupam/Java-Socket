/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reverseecho;

import java.net.*;
import java.io.*;

/**
 *
 * @author rupam
 */
public class ReverseEcho {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        System.out.println("\n\n##### SERVER #####\n\n");

        ServerSocket ss = new ServerSocket(2000);
        System.out.println("Server Established on port 2000");

        Socket stk = ss.accept();
        System.out.println("Client Connected");

        BufferedReader br = new BufferedReader(new InputStreamReader(stk.getInputStream()));
        PrintStream ps = new PrintStream(stk.getOutputStream());

        String message;
        StringBuffer sb;
        do {
            System.out.println("\n");
            message = br.readLine();
            System.out.println("Read from Client");
            System.out.println("From Client: " + message);

            sb = new StringBuffer(message);
            sb.reverse();
            ps.println(sb.toString());
            System.out.println("Reply sent to Client");
        } while (!message.equals("end"));
    }

}
