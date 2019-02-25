import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.*;
import java.util.*;
import java.net.*;

public class Server {

    // vector of threads for clients

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9000);

        // ArrayList ClientList = new ArrayList<Socket>();

        while (true) {
            System.out.println("Waiting For Connection..");

            Socket client = serverSocket.accept();

            System.out.println("Client Connected");

            DataInputStream inFromClient = new DataInputStream(client.getInputStream());
            String line = inFromClient.readUTF();

            System.out.println("Client says: " + line);

            DataOutputStream outFromServer = new DataOutputStream(client.getOutputStream());
            outFromServer.writeUTF(line);

        }

        // listen in socket
        // infinite loop hehe
    }

}

// * { ServerSocket serversocket; BufferedReader br1, br2; PrintWriter pr1;
// Socket
// * socket; Vector threadVector; Thread serverThread; String in = "", out = "";
// *
// * public Server() { int MAXCONNECTIONS = 100; // threadVector.add(new
// * Thread(this)); // adds server thread
// *
// * for (int i = 0; i < MAXCONNECTIONS; i++) { try { threadVector.add(new
// * Thread(this)); // adds client thread serversocket = new ServerSocket(5000);
// * System.out.println("Server is waiting. . . . ");
// *
// * socket = serversocket.accept();
// * System.out.println("Client connected with Ip " +
// * socket.getInetAddress().getHostAddress()); // threadVector.get(0).start();
// * serverThread.start();
// *
// * } catch (Exception e) { } } }
// *
// * public void run() { try { if (Thread.currentThread() == t1) { do { br1 =
// new
// * BufferedReader(new InputStreamReader(System.in)); pr1 = new
// * PrintWriter(socket.getOutputStream(), true); in = br1.readLine();
// * pr1.println(in); } while (!in.equals("END")); } else { do { br2 = new
// * BufferedReader(new InputStreamReader(socket.getInputStream())); out =
// * br2.readLine(); System.out.println("Client says : : : " + out); } while
// * (!out.equals("END")); } } catch (Exception e) { } }
// *
// * public static void main(String[] args) { new Server(); } }
// */