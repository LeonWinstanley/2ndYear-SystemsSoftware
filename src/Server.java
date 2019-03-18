import java.io.*;
import java.net.*;
import java.util.*;
// below are Jframe imports
import java.awt.*;
import javax.swing.*;

public class Server {

    static ServerSocket weatherSocket, userSocket;
    static ArrayList<Socket> userList = new ArrayList<Socket>();
    static ArrayList<Socket> weatherList = new ArrayList<Socket>();
    static ArrayList<Thread> threadList = new ArrayList<Thread>();
    static GraphicsConfiguration gc;

    // BufferedReader ServerInput, ServerOutput;
    // PrintWriter OutputToClient;
    // Socket socket;
    // Thread recieveingThread, sendingThread, listenThread;
    // String in = "", out = "";

    public Server() {
        try {

            // recieveingThread = new Thread(new WeatherClient());
            // sendingThread = new Thread(new UserClient());
            weatherSocket = new ServerSocket(50000);
            userSocket = new ServerSocket(50001);

            threadList.add(new Thread(new Listen()));

            threadList.get(0).start();
            
            threadList.get(1).start();
            
            // System.out.println("Client connected with Ip " +
            // socket.getInetAddress().getHostAddress());
            // t1.start();
            // t2.start();
            
            

        } catch (Exception e) {
        }
    }

    public ArrayList getUserList() {

        return userList;
    }

    public static void setUserList(Socket input) {
        userList.add(input);
    }

    public ArrayList getWeatherList() {

        return weatherList;
    }

    public static void setWeatherList(Socket input) {
        weatherList.add(input);
    }

    public ServerSocket getWeatherSocket() {
        return weatherSocket;
    }

    public void setWeatherSocket(ServerSocket input) {
        weatherSocket = input;
    }

    // public void run() {
    // try {
    // if (Thread.currentThread() == t1) {
    // } else {
    // do {
    // ServerOutput = new BufferedReader(new
    // InputStreamReader(socket.getInputStream()));
    // out = ServerOutput.readLine();
    // System.out.println("Client says : : : " + out);
    // } while (!out.equals("END"));
    // }
    // } catch (Exception e) {
    // }
    // }

    public static void main(String[] args) {

        JFrame frame = new JFrame(gc);
        // frame.setVisable(true);

        new Server();

    }
}

class WeatherClientThread implements Runnable {

    @Override
    public void run() {
    
    
    System.out.println(" New Server Data");

    

    }

}

class UserClientThread implements Runnable { // user could call function to pull data from server

    Socket UserSocket;

    @Override
    public void run() {
    
    
    
    System.out.println(" New Client Connected");
    

        // do {
        // ClientInput = new BufferedReader(new InputStreamReader(System.in));
        // OutputToServer = new PrintWriter(socket.getOutputStream(), true);
        // in = ClientInput.readLine();
        // OutputToServer.println(in);
        // } while (!in.equals("END"));

    }
    
    

}

class Listen implements Runnable {

    @Override
    public void run() {
        while (true) {
            ServerSocket weathersocket = Server.weatherSocket;
            try {
                 Server.setWeatherList(weathersocket.accept());
                 Server.threadList.add(new Thread(new WeatherClientThread())); //"Weather" +
                // Integer.toString(Server.weatherList.size()))));

            } catch (Exception e) {
                // TODO: handle exception
            }

            ServerSocket usersocket = Server.userSocket;
            try {
                 Server.setUserList(usersocket.accept());
                 Server.threadList.add(new Thread(new UserClientThread()));

            } catch (Exception e) {
                // TODO: handle exception
            }

        }
    }

}
