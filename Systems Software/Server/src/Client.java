import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.*;
import java.util.*;
import java.net.*;

// public class Client implements Runnable {

// BufferedReader br1, br2;
// PrintWriter pr1;
// Socket socket;
// Thread t1, t2;
// String in = "", out = "";

// public Client() {
// try {
// t1 = new Thread(this);
// t2 = new Thread(this);
// socket = new Socket("192.168.0.1", 5000);
// t1.start();
// ;
// t2.start();

// } catch (Exception e) {
// }
// }

// public static void main(String[] args) {
// new Client();
// }
// }

// Socket socket;
// Thread t1, t2;
// String in = "", out = "";

// public Client() {
// try {
// t1 = new Thread(this);
// t2 = new Thread(this);
// socket = new Socket("192.168.0.1", 5000);
// t1.start();
// ;
// t2.start();

// } catch (Exception e) {
// }
// }

// public void run() {

// try
// {
// if (Thread.currentThread() == t2) {
// do {
// br1 = new BufferedReader(new InputStreamReader(System.in));
// pr1 = new PrintWriter(socket.getOutputStream(), true);
// in = br1.readLine();
// pr1.println(in);
// } while (!in.equals("END"));
// } else {
// do {
// br2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
// out = br2.readLine();
// System.out.println("Server says : : : " + out);
// } while (!out.equals("END"));
// }
// }catch(
// Exception e)
// {
// }

// }

public class Client {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 9000);

        while (true) {

            System.out.println("Connected to server");
            
            do {
                BufferedReader ClientInput = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter ClientOutput = new PrintWriter(socket.getOutputStream(), true);

                Output.println(ClientInput);

            } while (!Input.equals("END"));

            DataInputStream inFromServer = new DataInputStream(socket.getInputStream()); // this reads the shit
            String line = inFromServer.readUTF();

            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream()); // this send the shit
            outToServer.writeUTF("Some Text"); // create a string for the text
        }

        // listen in socket
        // infinite loop hehe
    }

}