import java.io.*;
import java.util.*;
import java.net.*;

// Server class 
public class Server {

    // Vector to store active clients
    static Vector<ClientHandler> ClientList = new Vector<>();
    static Vector<WeatherHandler> WeatherList = new Vector<>();

    // counter for clients
    static int ClientCounter = 0;
    static int WeatherCounter = 0;

    static ServerSocket weatherSocket;
    static ServerSocket clientSocket;

    static Socket socket;

    private static void Weather() throws IOException {

        socket = weatherSocket.accept();

        System.out.println("New client request received : " + socket);

        // obtain input and output streams
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        System.out.println("Creating a new handler for this client...");

        // Create a new handler object for handling this request.
        WeatherHandler wHandler = new WeatherHandler(socket, "client " + WeatherCounter, dis, dos);

        // Create a new Thread with this object.
        Thread weatherThread = new Thread(wHandler);

        System.out.println("Adding this client to active client list");

        // add this client to active clients list
        WeatherList.add(wHandler);

        // start the thread.
        weatherThread.start();

        WeatherCounter++;

    }

    private static void Client() throws IOException {

        socket = weatherSocket.accept();

        System.out.println("New client request received : " + socket);
        // obtain input and output streams
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        System.out.println("Creating a new handler for this client...");

        // Create a new handler object for handling this request.
        ClientHandler cHandler = new ClientHandler(socket, "client " + ClientCounter, dis, dos);

        // Create a new Thread with this object.
        Thread clientThread = new Thread(cHandler);

        System.out.println("Adding this client to active client list");

        // add this client to active clients list
        ClientList.add(cHandler);

        // start the thread.
        clientThread.start();

        ClientCounter++;

    }

    public static void main(String[] args) throws IOException {
        // server is listening on port 1234

        weatherSocket = new ServerSocket(50000);
        clientSocket = new ServerSocket(50001);

        while (true) {

            Weather();
            Client();
        }
    }
}

// ClientHandler class
class ClientHandler implements Runnable {
    Scanner scn = new Scanner(System.in);
    private String name;
    final DataInputStream dis;
    final DataOutputStream dos;
    Socket socket;
    boolean isloggedin;

    // constructor
    public ClientHandler(Socket socket, String name, DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        this.name = name;
        this.socket = socket;
        this.isloggedin = true;
    }

    @Override
    public void run() {

        String received;
        while (true) {
            try {

                // receive the string
                received = dis.readUTF();

                System.out.println(received);

                if (received.equals("logout")) {
                    this.isloggedin = false;
                    this.socket.close();
                    break;
                }

                // break the string into message and recipient part
                StringTokenizer st = new StringTokenizer(received, "#");
                String MsgToSend = st.nextToken();
                String recipient = st.nextToken();

                // search for the recipient in the connected devices list.
                // ClientList is the vector storing client of active users
                for (ClientHandler mc : Server.ClientList) {
                    // if the recipient is found, write on its
                    // output stream
                    if (mc.name.equals(recipient) && mc.isloggedin == true) {
                        mc.dos.writeUTF(this.name + " : " + MsgToSend);
                        break;
                    }
                }
            } catch (IOException e) {
                break;
            }

        }
        try {
            // closing resources
            this.dis.close();
            this.dos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class WeatherHandler implements Runnable {
    Scanner scn = new Scanner(System.in);
    private String name;
    final DataInputStream dis;
    final DataOutputStream dos;
    Socket socket;
    boolean isloggedin;

    // constructor
    public WeatherHandler(Socket socket, String name, DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        this.name = name;
        this.socket = socket;
        this.isloggedin = true;
    }

    @Override
    public void run() {

        String received;
        while (true) {
            try {
                // receive the string
                received = dis.readUTF();

                System.out.println(received);

                if (received.equals("logout")) {
                    this.isloggedin = false;
                    this.socket.close();
                    break;
                }

                // break the string into message and recipient part
                StringTokenizer st = new StringTokenizer(received, "#");
                String MsgToSend = st.nextToken();
                String recipient = st.nextToken();

                // search for the recipient in the connected devices list.
                // ClientList is the vector storing client of active users
                for (WeatherHandler mc : Server.WeatherList) {
                    // if the recipient is found, write on its
                    // output stream
                    if (mc.name.equals(recipient) && mc.isloggedin == true) {
                        mc.dos.writeUTF(this.name + " : " + MsgToSend);
                        break;
                    }
                }
            } catch (IOException e) {

                break;
            }

        }
        try {
            // closing resources
            this.dis.close();
            this.dos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}