import java.net.*;

public class WeatherClient{

    PrintWriter OutputToServer;
    Socket socket;
    Thread t1, t2;
    String serverThread = "", clientThread = "";

    public WeatherClient() {
        while (true) {
            try {

                t1 = new Thread(this, serverThread);
                t2 = new Thread(this, clientThread);
                socket = new Socket("192.168.0.1", 50000);
                t1.start();
                t2.start();

            } 
            catch (Exception e) {

            }
        }
    }

    public void run() {
        // Generate GPS, Humidity, Temperature and other data here using random functions.
    }

    public static void main(String[] args) {
        
        new WeatherClient();

    }
}