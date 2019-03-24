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

    public double doubleGenerator(int min, int max) {
        double generatedDouble;
        double randomDouble = new Random().nextDouble();
        double result = min + (randomDouble * (max - min));
        generatedDouble = result;
        return generatedDouble;
    }

    public int intGenerator(int min, int max) {
        int generatedInt;
        int result = r.nextInt(max - min) + min;
        generatedInt = result;
        return generatedInt;

    }

    public String weatherData() {
        // latitude between -90 and 90
        latitude = doubleGenerator(-90, 90);
        // long between -180 and 180
        longitude = doubleGenerator(-180, 180);
        // humidity percentage
        humidity = intGenerator(0, 100);
        // temp in degrees centigrade
        temperature = intGenerator(-5, 40);
        // windspeed in km/h as int
        windSpeed = intGenerator(0, 60);
        // wind direction in degrees 0 to 360
        windDirection = intGenerator(0, 359);
        // pressure in hPa from 900 to 1100
        pressure = intGenerator(900, 1100);
        // chance of rain percentage
        chanceOfRain = intGenerator(0, 100);
        // uv index from 0 to 10
        uvIndex = intGenerator(0, 10);

        // add all the data into a single string

    }

    public void run() {
        // Generate GPS, Humidity, Temperature and other data here using random
        // functions.
    }

    public static void main(String[] args) {

        new WeatherClient();

        while (1) {

        }

        // genreate data here every couple of minutes
        // send data to server

    }
}