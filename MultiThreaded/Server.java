import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {

    public Consumer<Socket> getConsumer(){
        return new Consumer<Socket>() {
            @Override
            public void accept(Socket clientScoket) {
                try {


                    PrintWriter toClient = new PrintWriter(clientScoket.getOutputStream(),true);




                    toClient.println("hello from server");




                    toClient.close();
                    clientScoket.close();

                } catch (IOException e) {
                    e.printStackTrace();

                }

            }
        };
    }

    public static void main(String[] args) {
        Server server = new Server();
        int port = 8010;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(700000);
            System.out.println("server is listening on port no:" + port);
            while ((true)) {

                Socket acceptedScoket = serverSocket.accept();
                Thread thread = new Thread(()->server.getConsumer().accept(acceptedScoket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
