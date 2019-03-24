import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Scanner;

public class WeatherClient {

    PrintWriter OutputToServer;
    final static int ServerPort = 1234;

    public WeatherClient() {
        try {
            Scanner scn = new Scanner(System.in);

            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection
            Socket s = new Socket(ip, ServerPort);

            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // Uses Lat Long System
    public double GPS() {

        double GPSData;
        double min = -90;
        double max = 90;
        double randomDouble = new Random().nextDouble();
        double result = min + (randomDouble * (max - min));

        GPSData = result;

        return GPSData;
    }

    public int Humidity() {
        int HumidityData;
        Random r = new Random();
        int min = 1;
        int max = 100;
        int result = r.nextInt(max - min) + min;
        HumidityData = result;
        return HumidityData;
    }

    // Uses Degrees Centigrade
    public int Temperature() {
        int Temperature;
        Random r = new Random();
        int min = -5;
        int max = 50;
        int result = r.nextInt(max - min) + min;
        Temperature = result;
        return Temperature;
    }

    public void run() {
        // Generate GPS, Humidity, Temperature and other data here using random
        // functions.
    }

    public static void main(String[] args) {

        new WeatherClient();

    }
}