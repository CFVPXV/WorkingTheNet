package ECHO;

import java.util.Scanner;

public class main {
    public static void main(String[] args){
        Thread driver;
        Scanner s = new Scanner(System.in);
        String ans = s.next();
        //Note that the flushing of the threads must occur so all output occurs
        //But also so that the ports become "free"
        //As simple as closing the applications?
        if(ans.compareTo("S") == 0){
            //The server in essence is that which will be deployed upon many threads
            driver = new Thread(new Listener(8000, 100));
        }

        else if (ans.compareTo("C") == 0) {
            System.out.println("Name");
            String n = s.next();
            System.out.println("Starting client");
            driver = new Thread(new Client("127.0.0.1",8000,n));
        }

        else {
            System.out.println("nope");
            return;
        }

        driver.start();
        try{
            driver.join();
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

    }
}
