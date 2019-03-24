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
        double minimum = -90;
        double maximum = 90;
        double randomDouble = new Random().nextDouble();
        
        double result = minimum + (randomDouble * (maximum - minimum));

        GPSData = result;

        return GPSData;
    }


    public int Humidity() {
        int HumidityData;
        Random r = new Random();
        int low = 1;
        int high = 100;
        int result = r.nextInt(high-low) + low;
        HumidityData = result;
        return HumidityData;
    }

    // Uses Degrees Centigrade
    public int Temperature() {
        int Temperature;
        Random r = new Random();
        int low = -5;
        int high = 50;
        int result = r.nextInt(high-low) + low;
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