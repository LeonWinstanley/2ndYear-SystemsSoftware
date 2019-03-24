import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Scanner;

public class WeatherClient {

    PrintWriter OutputToServer;
    final static int ServerPort = 50000;

    public WeatherClient() {
        try {
            Scanner scn = new Scanner(System.in);

            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection
            Socket s = new Socket(ip, ServerPort);

            // obtaining input and out streams
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public String doubleGenerator(int min, int max) {
        double randomDouble = new Random().nextDouble();
        double result = min + (randomDouble * (max - min));
        return Double.toString(result);
    }

    public String intGenerator(int min, int max) {
        int randomInt = new Random().nextInt();
        int result = min + (randomInt * (max - min));
        return Integer.toString(result);

    }

    public String weatherData() {
        // latitude between -90 and 90
        String latitude = doubleGenerator(-90, 90);
        // long between -180 and 180
        String longitude = doubleGenerator(-180, 180);
        // humidity percentage
        String humidity = intGenerator(0, 100);
        // temp in degrees centigrade
        String temperature = intGenerator(-5, 40);
        // windspeed in km/h as int
        String windSpeed = intGenerator(0, 60);
        // wind direction in degrees 0 to 360
        String windDirection = intGenerator(0, 359);
        // pressure in hPa from 900 to 1100
        String pressure = intGenerator(900, 1100);
        // chance of rain percentage
        String chanceOfRain = intGenerator(0, 100);
        // uv index from 0 to 10
        String uvIndex = intGenerator(0, 10);

        // add all the data into a single string
        String generatedWeatherData;
        generatedWeatherData = latitude + "," + longitude + "," + humidity + "," + temperature + "," + windSpeed + ","
                + windDirection + "," + pressure + "," + chanceOfRain + "," + uvIndex;

        return generatedWeatherData;
    }

    public static void main(String[] args) {

        WeatherClient Client = new WeatherClient();

        while (true) {

            // generate data here every couple of minutes

            String Data = Client.WeatherData();

            try {
                // write on the output stream
                Client.dos.writeUTF(Data);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // send data to server

        }

    }
}
