
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() throws IOException {
         int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(100000); // opean server scoket for only 10 sec

        while (true){
            try {
                System.out.println("Server is listening on port no :" + port);
                // accept() it will block until someone connect to server before 10 sec or severscket will be closed
                Socket acceptedConnection = socket.accept();
                System.out.println("Connection acepted from client" + acceptedConnection.getInetAddress());
                // for reading from client and wrappering Scoket class object into bufferreade and print writter for easy
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                // for writting to socket
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream());
                toClient.println(" hello client from the server");
                toClient.flush();
                fromClient.close();
                toClient.close();
                acceptedConnection.close();

            }
            catch (IOException ex){
                // print untill or before exception occur
                ex.printStackTrace();
            }
        }

    }



    public static void main(String[] args) {

        Server server = new Server();
        try {
            server.run();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
