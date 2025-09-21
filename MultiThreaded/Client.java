import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public Runnable getRunnable(){
        return new Runnable() {
            @Override
            public void run() {
                int port = 8010;
                try {

                    InetAddress adress = InetAddress.getByName("localhost");
                    Socket scoket = new Socket(adress,port);

                    try (PrintWriter toSocket = new PrintWriter(scoket.getOutputStream());
                         BufferedReader fromSccket = new BufferedReader(new InputStreamReader(scoket.getInputStream()));){


                        toSocket.println("hello from client side" + scoket.getLocalSocketAddress());
                        String line = fromSccket.readLine();
                        System.out.println("Response from the scoket is:" + line);
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }







                }
                catch (IOException e){
                    e.printStackTrace();
                }

            }
        };

    }
    public static void main(String[] args) {
        Client client = new Client();
        for (int i = 0; i<50; i++){
            try {
                Thread thread = new Thread(client.getRunnable());
                thread.start();

            }
            catch (Exception e){
                e.printStackTrace();
            }





        }
    }
}
