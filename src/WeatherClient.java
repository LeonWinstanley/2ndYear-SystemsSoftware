import java.io.*;
import java.net.*;
import java.util.*;

public class WeatherClient {

    PrintWriter OutputToServer;
    final static int ServerPort = 50000;
    DataOutputStream dos;
    
    // latitude between -90 and 90
    String wLat = doubleGenerator(-90, 90);
    // long between -180 and 180
    String wLong = doubleGenerator(-180,180);

    public Socket socket;

    public WeatherClient() throws UnknownHostException, IOException {
        // getting localhost ip
        InetAddress ip = InetAddress.getByName("localhost");

        // establish the connection
        socket = new Socket(ip, ServerPort);

        // obtaining input and out streams
        //DataInputStream dis = new DataInputStream(s.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
    }

    Thread sendMessage = new Thread(new Runnable() {
        private volatile boolean running = true;
        @Override
        public void run() {
            while (running) {
                // read the message to deliver.
                String msg = weatherData();
                msg = wLat + "," + wLong + "," + PasswordUtils.encrypt(msg, wLat, wLong);

                try {
                    // write on the output stream
                    dos.writeUTF(msg);
                    Thread.sleep(500);
                } catch (Exception e) { stop(); }
            }
        }

        public void stop() { running = false; }
    });

    public String doubleGenerator(int min, int max) {
        double randomDouble = new Random().nextDouble();
        double result = min + (randomDouble * (max - min));
        return Double.toString(result);
    }

    public String intGenerator(int min, int max) {
        int randomInt = new Random().nextInt(max);
        int result = randomInt;
        return Integer.toString(result);
    }
    
    public String weatherData() {
        // humidity percentage
        String humidity = intGenerator(0, 100);
        // temp in degrees centigrade
        String temperature = intGenerator(-5, 40);
        // windspeed in km/h as int
        String windSpeed = intGenerator(0, 60);
        // wind direction in degrees 0 to 360
        String windDirection = intGenerator(0, 359);
        // chance of rain percentage
        String rainVolume = intGenerator(0, 100);
        // uv index from 0 to 10
        String Cloudiness = intGenerator(0, 10);

        // add all the data into a single string
        String generatedWeatherData;
        generatedWeatherData = humidity + "," + temperature + "," + windSpeed + ","
                + windDirection + "," + rainVolume + "," + Cloudiness;

        return generatedWeatherData;
    }

    public static void main(String[] args) throws IOException {
        WeatherClient Client = new WeatherClient();
        Client.sendMessage.start();

        
    }
}
