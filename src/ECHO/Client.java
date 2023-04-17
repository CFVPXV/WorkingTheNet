package ECHO;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Logger;

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


        try {
            //But again, its a new object each time...
            Card x = new Card('0',0);
            ObjectInputStream wr = null;
            wr = new ObjectInputStream(con.getInputStream());
            System.out.println("Sending card");
            x = (Card) wr.readObject();
            ObjectOutputStream r = new ObjectOutputStream(con.getOutputStream());
            r.writeObject(x);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
