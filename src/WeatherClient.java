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
        int result = r.nextInt(max-min) + min;
        HumidityData = result;
        return HumidityData;
    }

    // Uses Degrees Centigrade
    public int Temperature() {
        int Temperature;
        Random r = new Random();
        int min = -5;
        int max = 50;
        int result = r.nextInt(max-min) + min;
        Temperature = result;
        return Temperature;
    }

    public void run() {
        // Generate GPS, Humidity, Temperature and other data here using random functions.
    }

    public static void main(String[] args) {
        
        new WeatherClient();

    }
}