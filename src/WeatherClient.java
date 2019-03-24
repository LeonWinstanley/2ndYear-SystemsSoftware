import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Scanner;

public class WeatherClient {

    PrintWriter OutputToServer;
    String serverThread = "", clientThread = "";
    final static int ServerPort = 1234;

    public WeatherClient() {
        try {
            Scanner scn = new Scanner(System.in);

            InetAddress ip = InetAddress.getByName("localhost");

            Socket s = new Socket(ip, ServerPort);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // Uses Lat Long System
    public double GPS() {

        double GPSData;
        double minimum = -90;
        double maximum = 90;
        double randomDouble = new Random().nextDouble();

        double result = minimum + (randomDouble * (maximum - minimum));

        GPSData = result;

        return GPSData;
    }

    public int Humidity() {
        int HumidityData;
        HumidityData = 1;
        return HumidityData;
    }

    // Uses Degrees Centigrade
    public int Temperature() {
        int Temperature;
        Temperature = 20;
        return Temperature;
    }

    public void run() {
        // Generate GPS, Humidity, Temperature and other data here using random
        // functions.
        System.out.println("Test");
    }

    public static void main(String[] args) {

        new WeatherClient();

    }
}