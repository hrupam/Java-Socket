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
public class Client {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        System.out.println("\n\n##### CLIENT #####\n");

        try (Socket stk = new Socket("localhost", 2000)) {
            BufferedReader keyb = new BufferedReader(new InputStreamReader(System.in));

            BufferedReader br = new BufferedReader(new InputStreamReader(stk.getInputStream()));
            PrintStream ps = new PrintStream(stk.getOutputStream());

            String message;

            do {
                System.out.println("\n");
                System.out.print("Enter message: ");
                message = keyb.readLine();

                ps.println(message);
                System.out.println("Message sent to Server");
                message = br.readLine();
                System.out.println("Read from Server");
                System.out.println("From Server: " + message);
            } while (!message.equals("dne"));
        }

        System.out.println("Client Disconnected");
    }

}
