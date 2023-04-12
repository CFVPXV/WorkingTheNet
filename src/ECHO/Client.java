package ECHO;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class Client implements Runnable{

    private String ip;
    private int port;
    Socket con;
    DataInputStream in;
    DataOutputStream out;
    String clientID;

    public Client(String i, int p, String name){

        ip = i;
        port = p;
        clientID = name;

        try{

            System.out.println("Connecting to server...");
            con = new Socket(ip,port);
            in = new DataInputStream(con.getInputStream());
            out = new DataOutputStream(con.getOutputStream());

        } catch (IOException e) {
           System.out.println(e.getMessage());
        }
    }


    @Override
    public void run() {

        while(con.isConnected()){
            try {
                Random r = new Random();
                String nm = "";
                for(int i =0; i < 10; i++){
                    nm += (char)r.nextInt(65) + 91;
                }
                System.out.println("Sending message " + nm);
                out.writeUTF(nm);
                System.out.println("Read from server" + in.readUTF());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
