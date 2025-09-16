import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;

import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public void run() throws UnknownHostException, IOException {
        int port = 8010;
        InetAddress adress = InetAddress.getByName("localhost");
        Socket scoket = new Socket(adress,port);
        PrintWriter toSocket = new PrintWriter(scoket.getOutputStream());
        BufferedReader fromSccket = new BufferedReader(new InputStreamReader(scoket.getInputStream()));
        toSocket.println("hello from client side");
        toSocket.flush();
        String line = fromSccket.readLine();
        System.out.println("Response from the scoket is:" + line);
        toSocket.close();
        fromSccket.close();
        scoket.close();

    }

    public static void main(String[] args) {
        try {

            Client client1 = new Client();
            client1.run();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
