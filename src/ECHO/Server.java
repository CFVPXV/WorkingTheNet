package ECHO;

import java.io.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SocketHandler;
import java.net.Socket;

//The server's gonna take the client Listener listened to and perform some logic on it!

public class Server implements Runnable{

    private Socket clientCon;

    public Server(Socket c){
        clientCon = c;
    }

    @Override
    public void run() {

        try {
            DataInputStream in = new DataInputStream(clientCon.getInputStream());
            DataOutputStream out = new DataOutputStream(clientCon.getOutputStream());
            Random rand = new Random();
            while (true){

                /*String s = in.readUTF();
                if(s.compareTo("Quit") == 0){
                    break;
                }
                System.out.println("Recieved a " + s);
                out.writeUTF("ECHO: "  + s);*/

                Card x = new Card((char) (rand.nextInt((int)'A') + (int)'E'), rand.nextInt(1) + 6);
                ObjectOutputStream wr = new ObjectOutputStream(clientCon.getOutputStream());
                System.out.println("Sending card");
                wr.writeObject(x);

                ObjectInputStream r = new ObjectInputStream(clientCon.getInputStream());
                try {
                    System.out.println("Client says: " + (Card)r.readObject());
                }catch (ClassNotFoundException ex){
                    Logger.getLogger(Server.class.getCanonicalName());
                    break;
                }

            }

            clientCon.close();

        } catch (IOException e){
            System.out.println("Error on client socket!");
        }

    }
}
