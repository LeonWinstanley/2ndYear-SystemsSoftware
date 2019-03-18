import java.io.*;
import java.net.*;
import java.util.*;

public class WeatherClient{

    PrintWriter OutputToServer;
    Socket socket;
    Thread t1, t2;
    String serverThread = "", clientThread = "";

    public WeatherClient() {
        while (true) {
            try {

                //t1 = new Thread(this, serverThread);
                //t2 = new Thread(this, clientThread);
                socket = new Socket("192.168.0.1", 50000);
                t1.start();
                t2.start();

            } 
            catch (Exception e) {

            }
        }
    }

    // Uses Lat Long System
    public Double GPS() {
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
        // Generate GPS, Humidity, Temperature and other data here using random functions.
        System.out.println("Test");
    }

    public static void main(String[] args) {
        
        new WeatherClient();
        //Pair <Double, Double> epic = new GPS();
        //System.out.println(epic);
        System.out.println("Test");
    }
}