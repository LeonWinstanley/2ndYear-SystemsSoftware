import java.io.*;
import java.util.*;
import java.net.*;

// Server class 
public class Server {

    // Vector to store active clients
    static Vector<ClientHandler> ClientList = new Vector<>();
    static Vector<WeatherHandler> WeatherList = new Vector<>();

    static WeatherAPI api = new WeatherAPI();

    // counter for clients
    static int ClientCounter = 0;
    static int WeatherCounter = 0;

    public static ServerSocket weatherSocket;
    public static ServerSocket clientSocket;

    private static String strWeatherList = "@";

    private void Weather() throws Exception {

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
                WeatherHandler wHandler = new WeatherHandler(socket, "weather client " + WeatherCounter, dis, this);

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

    private void Client() throws Exception {
        
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
                    ClientHandler cHandler = new ClientHandler(socket, "user client " + ClientCounter, dis, dos, strWeatherList, this);

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

    public static String[] getWeatherArr(){
        ArrayList<String> weatherList = new ArrayList<String>();
            for(int i = 0; i < WeatherCounter; i++){
                weatherList.add("Weather Client " + i);
            }

            String[] weatherArr = new String[weatherList.size()];
            return weatherArr = weatherList.toArray(weatherArr);
    }

    public static String[] getClientArr(){
        ArrayList<String> clientList = new ArrayList<String>();
        for(int i = 0; i < ClientCounter; i++){
            clientList.add("Client " + i);
        }
        
        String[] clientArr = new String[clientList.size()];
        return clientArr = clientList.toArray(clientArr);
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

        while (true) 
        {
            server.Weather();
            server.Client();

            gui.setWeather(getWeatherArr());
            gui.setClients(getClientArr());

            api.SetLocation(52.0,1.0);
            System.out.println(api.GetData());

            for (ClientHandler client : ClientList)
            {
                if (client.currentWeatherStationID > 3)
                {
                    try {client.AddWeatherData("#" + WeatherList.get(client.currentWeatherStationID - 3).GetData());}
                    catch (ArrayIndexOutOfBoundsException e){}
                }
                else
                {
                    switch (client.currentWeatherStationID) {
                        case 0:
                            api.SetLocation(40.712776, -74.005974);
                            break;
                        case 1:
                            api.SetLocation(51.507351, -0.127758);
                            break;
                        case 2:
                            api.SetLocation(52.954784, -1.158109);
                            break;
                    }
                    
                    try {client.AddWeatherData("#" + api.GetData());}
                    catch (ArrayIndexOutOfBoundsException e){}
                }
                try { client.SendData(client.GetDataFromQueue()); } 
                catch (Exception e) {}  
            }
        }
    }
}

// ClientHandler class
class ClientHandler implements Runnable {
    private String name;
    public DataInputStream dis;
    public DataOutputStream dos;
    Socket socket;
    String received;
    Server server;
    private volatile boolean running = true;
    private static Queue<String> clientDataQueue = new LinkedList<>();
    int currentWeatherStationID;

    // constructor
    public ClientHandler(Socket socket, String name, DataInputStream dis, DataOutputStream dos, String WeatherList, Server server) {
        this.dis = dis;
        this.dos = dos;
        this.name = name;
        this.socket = socket;
        this.server = server;
        SendData(WeatherList);
        int currentWeatherStationID = 0;
    }

    public void SendData(String dataToSend)
    {
        try { dos.writeUTF(dataToSend); } 
        catch (Exception e) {}
    }

    public void AddWeatherData(String dataToSend) { clientDataQueue.add(dataToSend); }

    public void GetStationID() { currentWeatherStationID = Integer.parseInt(received) - 1; }

    public String GetDataFromQueue() { return clientDataQueue.remove(); }

    void ClearQueue() { while(clientDataQueue.peek() != null) { clientDataQueue.remove(); } }

    public void close()
    {
        running = false;
        server.ClientCounter--;
        try { this.socket.close(); } 
        catch (Exception e) {}  
    }

    @Override
    public void run() {

        while (running) {
            try {
                // receive the string
                received = dis.readUTF();
                if (received.equals("-1")) { close(); }
                else if ((Integer.parseInt(received) - 1) != currentWeatherStationID)
                {
                    ClearQueue();
                    GetStationID(); 
                }

            } catch (IOException e) { break; }
        }
        try {
            // closing resources
            this.dis.close();
            this.dos.close();
        } catch (IOException e) {}
    }
}

class WeatherHandler implements Runnable {
    private String name;
    final DataInputStream dis;
    Socket socket;
    boolean isloggedin;
    String received;
    private volatile boolean running = true;
    Server server;
  
    // constructor
    public WeatherHandler(Socket socket, String name, DataInputStream dis, Server server) {
        this.dis = dis;
        this.name = name;
        this.socket = socket;
        this.isloggedin = true;
        this.server = server;
    }

    public void close()
    {
        running = false;
        server.WeatherCounter--;
    }

    public String GetData() { return received; }

    @Override
    public void run() {

        while (running) 
        { 
            try { received = dis.readUTF(); } 
            catch (IOException e) { close(); }
        }
        try { this.dis.close(); } 
        catch (IOException e) {}
    }
}