package ECHO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
            while (true){

                String s = in.readUTF();
                if(s.compareTo("Quit") == 0){
                    break;
                }
                System.out.println("Recieved a " + s);
                out.writeUTF("ECHO: "  + s);

            }

            clientCon.close();

        } catch (IOException e){
            System.out.println("Error on client socket!");
        }

    }
}
