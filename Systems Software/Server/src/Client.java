import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Client implements Runnable {

    BufferedReader ClientInput, ClientOutput;
    PrintWriter OutputToServer;
    Socket socket;
    Thread t1, t2;
    String in = "", out = "";

    public Client() {
        try {
            t1 = new Thread(this);
            t2 = new Thread(this);
            socket = new Socket("192.168.0.2", 50000);
            t1.start();
            ;
            t2.start();

        } catch (Exception e) {
        }
    }

    public void run() {

        try {
            if (Thread.currentThread() == t2) {
                do {
                    ClientInput = new BufferedReader(new InputStreamReader(System.in));
                    OutputToServer = new PrintWriter(socket.getOutputStream(), true);
                    in = ClientInput.readLine();
                    OutputToServer.println(in);
                } while (!in.equals("END"));
            } else {
                do {
                    ClientOutput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = ClientOutput.readLine();
                    System.out.println("Server says : : : " + out);
                } while (!out.equals("END"));
            }
        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {
        new Client();
    }
}