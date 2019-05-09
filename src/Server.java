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

    public static ServerSocket weatherSocket;
    public static ServerSocket clientSocket;

    public String WeatherData;
    private static String strWeatherList = "@";

    private static void Weather() throws Exception {

        Socket socket = new Socket();
        try { socket = weatherSocket.accept(); }
        catch (SocketTimeoutException e) {}

        try
        {
            if (socket.isConnected())
            {
                System.out.println("New weather client request received : " + socket);

                // obtain input and output streams
                DataInputStream dis = new DataInputStream(socket.getInputStream());

                System.out.println("Creating a new handler for this weather client...");

                // Create a new handler object for handling this request.
                WeatherHandler wHandler = new WeatherHandler(socket, "weather client " + WeatherCounter, dis);

                // Create a new Thread with this object.
                Thread weatherThread = new Thread(wHandler);

                System.out.println("Adding this weather client to active weather client list");

                // add this client to active clients list
                WeatherList.add(wHandler);

                // start the thread.
                weatherThread.start();

                WeatherCounter++;

                if (strWeatherList.equals("@")) { strWeatherList = strWeatherList.concat(Integer.toString(WeatherCounter)); }
                else { strWeatherList = strWeatherList.concat("," + Integer.toString(WeatherCounter) ); }

                for (ClientHandler client : ClientList)
                {
                    client.SendData(strWeatherList);
                }
            }
        }
        catch (NullPointerException e) {}
    }

    private static void Client() throws Exception {
        
            Socket socket = new Socket();
            try { socket = clientSocket.accept(); }
            catch (SocketTimeoutException e) {}

            try {
                if (socket.isConnected()) {
                    System.out.println("New user client request received : " + socket);

                    // obtain input and output streams
                    DataInputStream dis = new DataInputStream(socket.getInputStream());
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                    System.out.println("Creating a new handler for this user client...");

                    // Create a new handler object for handling this request.
                    ClientHandler cHandler = new ClientHandler(socket, "user client " + ClientCounter, dis, dos, strWeatherList);

                    // Create a new Thread with this object.
                    Thread clientThread = new Thread(cHandler);

                    System.out.println("Adding this client to active user client list");

                    // add this client to active clients list
                    ClientList.add(cHandler);

                    // start the thread.
                    clientThread.start();

                    ClientCounter++;
                }
            }
            catch (NullPointerException e) {}
    }

    public static void main(String[] args) throws Exception {

        ServerPanel gui = new ServerPanel();
        gui.setVisible(true);

        Server server = new Server();
        // server is listening on port 50000 && 50001
        server.weatherSocket = new ServerSocket(50000);
        server.clientSocket = new ServerSocket(50001);

        server.weatherSocket.setSoTimeout(1000);
        server.clientSocket.setSoTimeout(1000);

        while (true) {
            server.Weather();
            server.Client();

            for (WeatherHandler weather : WeatherList)
            {
                for (ClientHandler client : ClientList)
                {
                    client.SendData("#" + weather.GetData());
                }
            }
        }
    }
}

// ClientHandler class
class ClientHandler implements Runnable {
    Scanner scn = new Scanner(System.in);
    private String name;
    public DataInputStream dis;
    public DataOutputStream dos;
    Socket socket;
    boolean isloggedin;
    int CurrentStationID;

    // constructor
    public ClientHandler(Socket socket, String name, DataInputStream dis, DataOutputStream dos, String WeatherList) {
        this.dis = dis;
        this.dos = dos;
        this.name = name;
        this.socket = socket;
        this.isloggedin = true;
        SendData(WeatherList);
    }

    public void SendData(String dataToSend)
    {
        try { dos.writeUTF(dataToSend); } 
        catch (Exception e) {}
    }

    @Override
    public void run() {

        String received;
        while (this.isloggedin) {
            try {
                // receive the string
                received = dis.readUTF();
                System.out.println(received);

                if (received.equals("logout")) {
                    this.isloggedin = false;
                    this.socket.close();
                    break;
                }
            } catch (IOException e) { break; }

        }
        try {
            // closing resources
            this.dis.close();
            this.dos.close();
        } catch (IOException e) { e.printStackTrace(); }
    }
}

class WeatherHandler implements Runnable {
    Scanner scn = new Scanner(System.in);
    private String name;
    final DataInputStream dis;
    Socket socket;
    boolean isloggedin;
    String received;
    
    // constructor
    public WeatherHandler(Socket socket, String name, DataInputStream dis) {
        this.dis = dis;
        this.name = name;
        this.socket = socket;
        this.isloggedin = true;
    }

    public String GetData() { return received; }

    @Override
    public void run() {

        while (true) 
        { 
            try { received = dis.readUTF(); } 
            catch (IOException e) { break; } 
        }
        try { this.dis.close(); } 
        catch (IOException e) {}
    }
}