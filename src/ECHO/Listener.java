package ECHO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//In short, the listener listens to new potential clients which will then pass on data to the actual Server.

public class Listener implements Runnable{

    ServerSocket serve;
    int maxNumber;
    ExecutorService connections;

    //From a design perspective, we can use this printing of an exception from a black box perspective, that is,
    //if only we are to use it!
    //But if we are to release this as a library, might as well pass throw the exception so others can use it and
    //potentially deal with it.
    public Listener(int port, int max) {
        connections = Executors.newFixedThreadPool(max);
        maxNumber = max;
        try {
            serve = new ServerSocket(port);
        } catch (IOException e){
            System.out.println("ERROR Creating the server: " + e.getMessage());
        }
    }

    @Override
    public void run() {

        while(true){
            System.out.println("Listening for connections...");

            try{
                //Recall that accept method locks the thread! It sits and listens...
                Socket c = serve.accept();
                System.out.println("Client received");
                connections.execute(new Server(c));
            } catch (IOException e) {
                System.out.println("Listener failed: " + e.getMessage());
                try {
                    serve.close();
                } catch (IOException t){
                    System.out.println("Listener we already closed");
                }
                break;
            }
        }

    }

    public int getMaxNumber(){
        return maxNumber;
    }



}

