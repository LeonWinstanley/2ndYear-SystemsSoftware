import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.*;
// below are Jframe imports
import java.awt.GraphicsConfiguration;
import javax.swing.JFrame;

public class Server {

    ServerSocket weatherSocket, userSocket;
    ArrayList userList, weatherList;
    // BufferedReader ServerInput, ServerOutput;
    // PrintWriter OutputToClient;
    Socket socket;
    Thread recieveingThread, sendingThread;
    // String in = "", out = "";
    static GraphicsConfiguration gc;

    public Server() {
        try {

            recieveingThread = new Thread(new WeatherClient());
            sendingThread = new Thread(new UserClient());
            weatherSocket = new ServerSocket(50000);
            userSocket = new ServerSocket(50001);

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

    public void setUserList(Socket input) {
        userList.add(input);
    }

    public ArrayList getWeatherList() {

        return weatherList;

    }

    public void setWeatherList(Socket input) {
        weatherList.add(input);
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
        frame.setVisable(true);

        new Server();

    }
}

class WeatherClient implements Runnable {

    public void start() {

    }

    public void run() {

        Server.

    }

}

class UserClient implements Runnable { // user could call function to pull data from server

    Socket UserSocket;

    public void start() {

    }

    public void run() {

        // do {
        // ClientInput = new BufferedReader(new InputStreamReader(System.in));
        // OutputToServer = new PrintWriter(socket.getOutputStream(), true);
        // in = ClientInput.readLine();
        // OutputToServer.println(in);
        // } while (!in.equals("END"));

    }

}