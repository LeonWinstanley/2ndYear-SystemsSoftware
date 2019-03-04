import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

// below are Jframe imports
import java.awt.GraphicsConfiguration;
import javax.swing.JFrame;

public class Client{

    BufferedReader ClientInput, ClientOutput;
    PrintWriter OutputToServer;
    Socket socket;
    Thread t1, t2;
    String in = "", out = "", serverThread = "", clientThread = "";
    static GraphicsConfiguration gc;

    public Client() {
        while (true) {

            try {
                t1 = new Thread(this, serverThread);
                t2 = new Thread(this, clientThread);
                socket = new Socket("192.168.0.1", 50000);
                t1.start();
                t2.start();

            } catch (Exception e) {
            }
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
        JFrame frame = new JFrame(gc);
        frame.setSize(700, 500);

        frame.setTitle("Client GUI");

        
        frame.setVisible(true);
        new Client();
    }
}