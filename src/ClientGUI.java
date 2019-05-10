import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.regex.Pattern;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class listenToServer implements Runnable {

    DataInputStream dis;
    ClientGUI client;
    private volatile boolean running = true;
    String received;

    public listenToServer(DataInputStream dis, ClientGUI client) 
    {
        this.dis = dis;
        this.client = client;
    }

    @Override
    public void run()
    {

        while(running)
        {
            try { received = dis.readUTF(); }
            catch (Exception e) { e.printStackTrace(); }

            String charAtZero = String.valueOf(received.charAt(0));
        
            if (charAtZero.equals("#"))
            {
                StringBuilder sb = new StringBuilder(received);
                sb.deleteCharAt(0);
                received = sb.toString();
                client.splitDISData(received);  
            }
            else if (charAtZero.equals("@"))
            {
                StringBuilder sb = new StringBuilder(received);
                sb.deleteCharAt(0);
                received = sb.toString();
                client.splitDISDataWeatherClients(received);   
            }  
        }       
    }
    
    public void terminate() { running = false; }
}

public class ClientGUI extends JFrame {

    final static int ServerPort = 50001;
    DataInputStream dis; // = new DataInputStream(s.getInputStream());
    DataOutputStream dos; // = new DataOutputStream(s.getOutputStream());
    String[] weatherClients = {};
    Socket socket;
    listenToServer lis;
    Thread listenThread;
    String selectionID = "";
    boolean SelectedItem = false;
    MapView map = new MapView();
    PasswordUtils passwordUtils = new PasswordUtils();
        

    public ClientGUI() {
        initComponents();
        this.setVisible(true);
        map.setVisible(true);

        try { ConnectToServer(); } 
        catch (Exception e) { System.out.println("ClientGUI.java :: ClientGUI() " + e); }
    }

    private void ConnectToServer() throws UnknownHostException, IOException {

        // getting localhost ip
        InetAddress ip = InetAddress.getByName("localhost");

        // establish the connection
        socket = new Socket(ip, ServerPort);

        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());

        lis = new listenToServer(dis, this);
        listenThread = new Thread(lis);

        listenThread.start();
    }

    private void DisconnectFromServer() throws UnknownHostException, IOException
    {
        String logout = "-1";
        dos.writeUTF(logout);
        dis.close();
        dos.close();
        socket.close();
        lis.terminate();
    }

    private void resetComponents()
    {
        Latitude01.setText("");
        Latitude02.setText("");
        Latitude03.setText("");
        Latitude04.setText("");
        Latitude05.setText("");
        Latitude06.setText("");
        Latitude07.setText("");

        Longitude01.setText("");
        Longitude02.setText("");
        Longitude03.setText("");
        Longitude04.setText("");
        Longitude05.setText("");
        Longitude06.setText("");
        Longitude07.setText("");

        Humidity01.setText("");
        Humidity02.setText("");
        Humidity03.setText("");
        Humidity05.setText("");
        Humidity04.setText("");
        Humidity06.setText("");
        Humidity07.setText("");

        Temperature01.setText("");
        Temperature02.setText("");
        Temperature03.setText("");
        Temperature04.setText("");
        Temperature05.setText("");
        Temperature06.setText("");
        Temperature07.setText("");

        WindSpeed01.setText("");
        WindSpeed02.setText("");
        WindSpeed03.setText("");
        WindSpeed04.setText("");
        WindSpeed05.setText("");
        WindSpeed06.setText("");
        WindSpeed07.setText("");

        WindDirection01.setText("");
        WindDirection02.setText("");
        WindDirection03.setText("");
        WindDirection04.setText("");
        WindDirection05.setText("");
        WindDirection06.setText("");
        WindDirection07.setText("");

        rainVol01.setText("");
        rainVol02.setText("");
        rainVol03.setText("");
        rainVol04.setText("");
        rainVol05.setText("");
        rainVol06.setText("");
        rainVol07.setText("");

        clouds01.setText("");
        clouds02.setText("");
        clouds03.setText("");
        clouds04.setText("");
        clouds05.setText("");
        clouds06.setText("");
        clouds07.setText("");
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxWeather = new javax.swing.JComboBox<>();
        LogOutButton = new javax.swing.JButton();
        SaveToFileButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        Latitude01 = new javax.swing.JLabel();
        Latitude02 = new javax.swing.JLabel();
        Latitude03 = new javax.swing.JLabel();
        Longitude01 = new javax.swing.JLabel();
        Longitude02 = new javax.swing.JLabel();
        Latitude04 = new javax.swing.JLabel();
        Longitude03 = new javax.swing.JLabel();
        Longitude04 = new javax.swing.JLabel();
        Longitude05 = new javax.swing.JLabel();
        Longitude06 = new javax.swing.JLabel();
        Latitude06 = new javax.swing.JLabel();
        Latitude07 = new javax.swing.JLabel();
        Humidity03 = new javax.swing.JLabel();
        Humidity05 = new javax.swing.JLabel();
        Humidity04 = new javax.swing.JLabel();
        Humidity06 = new javax.swing.JLabel();
        Longitude07 = new javax.swing.JLabel();
        Humidity01 = new javax.swing.JLabel();
        Humidity02 = new javax.swing.JLabel();
        Humidity07 = new javax.swing.JLabel();
        Temperature02 = new javax.swing.JLabel();
        Temperature01 = new javax.swing.JLabel();
        Latitude05 = new javax.swing.JLabel();
        Temperature03 = new javax.swing.JLabel();
        Temperature04 = new javax.swing.JLabel();
        Temperature05 = new javax.swing.JLabel();
        WindSpeed01 = new javax.swing.JLabel();
        Temperature06 = new javax.swing.JLabel();
        Temperature07 = new javax.swing.JLabel();
        WindSpeed02 = new javax.swing.JLabel();
        WindSpeed03 = new javax.swing.JLabel();
        WindSpeed04 = new javax.swing.JLabel();
        WindSpeed05 = new javax.swing.JLabel();
        WindDirection05 = new javax.swing.JLabel();
        WindSpeed06 = new javax.swing.JLabel();
        WindDirection04 = new javax.swing.JLabel();
        WindSpeed07 = new javax.swing.JLabel();
        WindDirection01 = new javax.swing.JLabel();
        WindDirection03 = new javax.swing.JLabel();
        WindDirection06 = new javax.swing.JLabel();
        WindDirection02 = new javax.swing.JLabel();
        WindDirection07 = new javax.swing.JLabel();
        clouds01 = new javax.swing.JLabel();
        rainVol03 = new javax.swing.JLabel();
        rainVol01 = new javax.swing.JLabel();
        rainVol02 = new javax.swing.JLabel();
        rainVol04 = new javax.swing.JLabel();
        rainVol06 = new javax.swing.JLabel();
        rainVol05 = new javax.swing.JLabel();
        rainVol07 = new javax.swing.JLabel();
        clouds02 = new javax.swing.JLabel();
        clouds03 = new javax.swing.JLabel();
        clouds04 = new javax.swing.JLabel();
        clouds05 = new javax.swing.JLabel();
        clouds06 = new javax.swing.JLabel();
        clouds07 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Latitude");

        jLabel2.setText("Longitude");

        jLabel3.setText("Temperature");

        jLabel4.setText("Wind Speed");

        jLabel5.setText("Humidity");

        jLabel6.setText("Rain Volume");

        jLabel7.setText("Wind Pressure");

        jLabel8.setText("Cloudiness");

        jComboBoxWeather.setFont(new java.awt.Font("Dialog", 0, 30));
        jComboBoxWeather.setModel(new javax.swing.DefaultComboBoxModel<>(weatherClients));

        jComboBoxWeather.insertItemAt("", 0);

        //jComboBoxWeather.setBorder(javax.swing.BorderFactory.createLineBorder(null));

        LogOutButton.setText("Log Out");
        LogOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutButtonActionPerformed(evt);
            }
        });

        SaveToFileButton.setText("Save To File");
        SaveToFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveToFileButtonActionPerformed(evt);
            }
        });

        jComboBoxWeather.addActionListener(new java.awt.event.ActionListener()
        {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                        jComboBoxWeatherActionPerformed(evt);
                }
        });   

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(Latitude01, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34).addComponent(Longitude01,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(Latitude02, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Longitude02, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(Latitude03, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Longitude03, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(Latitude04, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Longitude04, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(Latitude05, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Longitude05, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(Latitude06, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Longitude06, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(Latitude07, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Longitude07, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Humidity01, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Humidity02, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Humidity03, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Humidity04, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Humidity05, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Humidity06, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Humidity07, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Temperature01, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Temperature02, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Temperature03, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Temperature04, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Temperature05, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Temperature06, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Temperature07, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(WindSpeed01, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(WindSpeed02, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(WindSpeed03, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(WindSpeed04, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(WindSpeed05, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(WindSpeed06, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(WindSpeed07, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(WindDirection01, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(WindDirection02, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(WindDirection03, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(WindDirection04, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(WindDirection05, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(WindDirection06, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(WindDirection07, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rainVol06, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rainVol05, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rainVol04, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rainVol03, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rainVol07, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rainVol02, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rainVol01, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(76, 76, 76)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(clouds02, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(clouds01, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(clouds07, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(clouds03, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(clouds04, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(clouds05, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(clouds06, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Latitude01).addComponent(Longitude01).addComponent(Humidity01)
                                .addComponent(Temperature01).addComponent(WindSpeed01).addComponent(WindDirection01)
                                .addComponent(rainVol01).addComponent(clouds01))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Latitude02).addComponent(Longitude02).addComponent(Humidity02)
                                .addComponent(Temperature02).addComponent(WindSpeed02).addComponent(WindDirection02)
                                .addComponent(rainVol02).addComponent(clouds02))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Latitude03).addComponent(Longitude03).addComponent(Humidity03)
                                .addComponent(Temperature03).addComponent(WindSpeed03).addComponent(WindDirection03)
                                .addComponent(rainVol03).addComponent(clouds03))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Latitude04).addComponent(Longitude04).addComponent(Humidity04)
                                .addComponent(Temperature04).addComponent(WindSpeed04).addComponent(WindDirection04)
                                .addComponent(rainVol04).addComponent(clouds04))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Latitude05).addComponent(Longitude05).addComponent(Humidity05)
                                .addComponent(Temperature05).addComponent(WindSpeed05).addComponent(WindDirection05)
                                .addComponent(rainVol05).addComponent(clouds05))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Latitude06).addComponent(Longitude06).addComponent(Humidity06)
                                .addComponent(Temperature06).addComponent(WindSpeed06).addComponent(WindDirection06)
                                .addComponent(rainVol06).addComponent(clouds06))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Latitude07).addComponent(Longitude07).addComponent(Humidity07)
                                .addComponent(Temperature07).addComponent(WindSpeed07).addComponent(WindDirection07)
                                .addComponent(rainVol07).addComponent(clouds07))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 24, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBoxWeather,javax.swing.GroupLayout.PREFERRED_SIZE, 350,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(170, 170, 170)
                        
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(LogOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(SaveToFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(117, 117, 117))
                .addGroup(jPanel1Layout.createSequentialGroup().addGap(35, 35, 35).addComponent(jLabel1)
                        .addGap(39, 39, 39).addComponent(jLabel2).addGap(46, 46, 46).addComponent(jLabel5)
                        .addGap(44, 44, 44).addComponent(jLabel3).addGap(40, 40, 40).addComponent(jLabel4)
                        .addGap(39, 39, 39).addComponent(jLabel7).addGap(39, 39, 39).addComponent(jLabel6)
                        .addGap(40, 40, 40).addComponent(jLabel8)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup().addGap(76, 76, 76)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1).addComponent(jLabel2).addComponent(jLabel5).addComponent(jLabel3)
                                .addComponent(jLabel4).addComponent(jLabel7).addComponent(jLabel6)
                                .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(SaveToFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                        jPanel1Layout.createSequentialGroup()
                                                .addComponent(jComboBoxWeather, javax.swing.GroupLayout.PREFERRED_SIZE, 42,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(100, 100, 100))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                        jPanel1Layout.createSequentialGroup()
                                                .addComponent(LogOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(100, 100, 100)))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }

    private void SaveToFileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        LocalDateTime now = LocalDateTime.now();

        var current_time = "";
        String Weather_Data = "";

        current_time = dtf.format(now);
        Weather_Data = current_time + Current_Weather_Data();
        boolean bool = false;

        try {

                File data_File = new File("save.txt");

                bool = data_File.exists();

                if (bool != true) {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt"));
                        System.out.println("New File");
                        writer.close();
                }

                if (bool == true) {

                        BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt", true));
                        writer.newLine();
                        System.out.println("New Data");
                        writer.write(Weather_Data);
                        writer.close();

                }

        }catch (IOException e) {
                e.printStackTrace();
        }

    }

    private String Current_Weather_Data() {
            String new_data = "............Latitude - " + Latitude01.getText() + " Longitude - " + Longitude01.getText() + " Humidity - " + Humidity01.getText() + " Temperature - " + Temperature01.getText() + " Wind Speed - " + WindSpeed01.getText() + " Wind Direction - " + WindDirection01.getText() + " Rain Volume - " + rainVol01.getText() + " Cloudiness - " + clouds01.getText();

            return new_data;
    }

    private void LogOutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        Login login = new Login();
        login.setVisible(true);

        try { DisconnectFromServer(); } 
        catch (Exception e) {}
        
        dispose();
    }

    private void jComboBoxWeatherActionPerformed(java.awt.event.ActionEvent evt)
    {
        String selection = (String) jComboBoxWeather.getSelectedItem();
        String selectionID = selection.substring(15,selection.length());
        sendDataToServer(selectionID);
        resetComponents();
    }

    public static void RunGUI() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientGUI().setVisible(true);
            }
        });
    }

    ///////////////////////////////////////////////////////
    // setRowText() //
    // Moves labels down one by one using previousData variables //
    ///////////////////////////////////////////////////////
    public void setRowText(String Latitude, String Longitude, String Humidity, String Temperature, String WindSpeed,
            String WindDirection, String rainVol, String clouds) {

        String previousData = "";
        String nextData = "";

        map.setPos(Latitude, Longitude);

        previousData = Latitude01.getText();
        Latitude01.setText(Latitude);
        nextData = previousData;
        previousData = Latitude02.getText();
        Latitude02.setText(nextData);
        nextData = previousData;
        previousData = Latitude03.getText();
        Latitude03.setText(nextData);
        nextData = previousData;
        previousData = Latitude04.getText();
        Latitude04.setText(nextData);
        nextData = previousData;
        previousData = Latitude05.getText();
        Latitude05.setText(nextData);
        nextData = previousData;
        previousData = Latitude06.getText();
        Latitude06.setText(nextData);
        nextData = previousData;
        Latitude07.setText(nextData);

        previousData = Longitude01.getText();
        Longitude01.setText(Longitude);
        nextData = previousData;
        previousData = Longitude02.getText();
        Longitude02.setText(nextData);
        nextData = previousData;
        previousData = Longitude03.getText();
        Longitude03.setText(nextData);
        nextData = previousData;
        previousData = Longitude04.getText();
        Longitude04.setText(nextData);
        nextData = previousData;
        previousData = Longitude05.getText();
        Longitude05.setText(nextData);
        nextData = previousData;
        previousData = Longitude06.getText();
        Longitude06.setText(nextData);
        nextData = previousData;
        Longitude07.setText(nextData);

        previousData = Humidity01.getText();
        Humidity01.setText(Humidity);
        nextData = previousData;
        previousData = Humidity02.getText();
        Humidity02.setText(nextData);
        nextData = previousData;
        previousData = Humidity03.getText();
        Humidity03.setText(nextData);
        nextData = previousData;
        previousData = Humidity04.getText();
        Humidity04.setText(nextData);
        nextData = previousData;
        previousData = Humidity05.getText();
        Humidity05.setText(nextData);
        nextData = previousData;
        previousData = Humidity06.getText();
        Humidity06.setText(nextData);
        nextData = previousData;
        Humidity07.setText(nextData);

        previousData = Temperature01.getText();
        Temperature01.setText(Temperature);
        nextData = previousData;
        previousData = Temperature02.getText();
        Temperature02.setText(nextData);
        nextData = previousData;
        previousData = Temperature03.getText();
        Temperature03.setText(nextData);
        nextData = previousData;
        previousData = Temperature04.getText();
        Temperature04.setText(nextData);
        nextData = previousData;
        previousData = Temperature05.getText();
        Temperature05.setText(nextData);
        nextData = previousData;
        previousData = Temperature06.getText();
        Temperature06.setText(nextData);
        nextData = previousData;
        Temperature07.setText(nextData);

        previousData = WindSpeed01.getText();
        WindSpeed01.setText(WindSpeed);
        nextData = previousData;
        previousData = WindSpeed02.getText();
        WindSpeed02.setText(nextData);
        nextData = previousData;
        previousData = WindSpeed03.getText();
        WindSpeed03.setText(nextData);
        nextData = previousData;
        previousData = WindSpeed04.getText();
        WindSpeed04.setText(nextData);
        nextData = previousData;
        previousData = WindSpeed05.getText();
        WindSpeed05.setText(nextData);
        nextData = previousData;
        previousData = WindSpeed06.getText();
        WindSpeed06.setText(nextData);
        nextData = previousData;
        WindSpeed07.setText(nextData);

        previousData = WindDirection01.getText();
        WindDirection01.setText(WindDirection);
        nextData = previousData;
        previousData = WindDirection02.getText();
        WindDirection02.setText(nextData);
        nextData = previousData;
        previousData = WindDirection03.getText();
        WindDirection03.setText(nextData);
        nextData = previousData;
        previousData = WindDirection04.getText();
        WindDirection04.setText(nextData);
        nextData = previousData;
        previousData = WindDirection05.getText();
        WindDirection05.setText(nextData);
        nextData = previousData;
        previousData = WindDirection06.getText();
        WindDirection06.setText(nextData);
        nextData = previousData;
        WindDirection07.setText(nextData);

        previousData = rainVol01.getText();
        rainVol01.setText(rainVol);
        nextData = previousData;
        previousData = rainVol02.getText();
        rainVol02.setText(nextData);
        nextData = previousData;
        previousData = rainVol03.getText();
        rainVol03.setText(nextData);
        nextData = previousData;
        previousData = rainVol04.getText();
        rainVol04.setText(nextData);
        nextData = previousData;
        previousData = rainVol05.getText();
        rainVol05.setText(nextData);
        nextData = previousData;
        previousData = rainVol06.getText();
        rainVol06.setText(nextData);
        nextData = previousData;
        rainVol07.setText(nextData);

        previousData = clouds01.getText();
        clouds01.setText(clouds);
        nextData = previousData;
        previousData = clouds02.getText();
        clouds02.setText(nextData);
        nextData = previousData;
        previousData = clouds03.getText();
        clouds03.setText(nextData);
        nextData = previousData;
        previousData = clouds04.getText();
        clouds04.setText(nextData);
        nextData = previousData;
        previousData = clouds05.getText();
        clouds05.setText(nextData);
        nextData = previousData;
        previousData = clouds06.getText();
        clouds06.setText(nextData);
        nextData = previousData;
        clouds07.setText(nextData);
    }

    //////////////////////////////////////////////////////////
    // splitDISData()                                       //
    // Splits data that is received through the data stream //
    //////////////////////////////////////////////////////////

    public void splitDISData(String DISInput) {
        String[] encryptedWeatherList = DISInput.split("\\s*,\\s*");
        String decryptedString = PasswordUtils.decrypt(encryptedWeatherList[2], encryptedWeatherList[0], encryptedWeatherList[1]);
        decryptedString += "," + encryptedWeatherList[0] + "," + encryptedWeatherList[1]; 
        
        String[] weatherList = decryptedString.split("\\s*,\\s*");
        setRowText(weatherList[7], weatherList[6], weatherList[0], weatherList[1], weatherList[2], weatherList[3], weatherList[4], weatherList[5]);
    }

    public void splitDISDataWeatherClients(String DISInput)
    {
            weatherClients = DISInput.split("\\s*,\\s*");
            for (int i = 0; i < weatherClients.length; i++)
            {
                    weatherClients[i] = "Weather Client " + weatherClients[i];
            }
            jComboBoxWeather.setModel(new javax.swing.DefaultComboBoxModel<>(weatherClients));
    }

    public void sendDataToServer(String dataToSend)
    {
            try { dos.writeUTF(dataToSend); }
            catch(Exception e){}
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel rainVol01;
    private javax.swing.JLabel rainVol02;
    private javax.swing.JLabel rainVol03;
    private javax.swing.JLabel rainVol04;
    private javax.swing.JLabel rainVol05;
    private javax.swing.JLabel rainVol06;
    private javax.swing.JLabel rainVol07;
    private javax.swing.JLabel Humidity01;
    private javax.swing.JLabel Humidity02;
    private javax.swing.JLabel Humidity03;
    private javax.swing.JLabel Humidity04;
    private javax.swing.JLabel Humidity05;
    private javax.swing.JLabel Humidity06;
    private javax.swing.JLabel Humidity07;
    private javax.swing.JLabel Latitude01;
    private javax.swing.JLabel Latitude02;
    private javax.swing.JLabel Latitude03;
    private javax.swing.JLabel Latitude04;
    private javax.swing.JLabel Latitude05;
    private javax.swing.JLabel Latitude06;
    private javax.swing.JLabel Latitude07;
    private javax.swing.JLabel Longitude01;
    private javax.swing.JLabel Longitude02;
    private javax.swing.JLabel Longitude03;
    private javax.swing.JLabel Longitude04;
    private javax.swing.JLabel Longitude05;
    private javax.swing.JLabel Longitude06;
    private javax.swing.JLabel Longitude07;
    private javax.swing.JLabel Temperature01;
    private javax.swing.JLabel Temperature02;
    private javax.swing.JLabel Temperature03;
    private javax.swing.JLabel Temperature04;
    private javax.swing.JLabel Temperature05;
    private javax.swing.JLabel Temperature06;
    private javax.swing.JLabel Temperature07;
    private javax.swing.JLabel clouds01;
    private javax.swing.JLabel clouds02;
    private javax.swing.JLabel clouds03;
    private javax.swing.JLabel clouds04;
    private javax.swing.JLabel clouds05;
    private javax.swing.JLabel clouds06;
    private javax.swing.JLabel clouds07;
    private javax.swing.JLabel WindDirection01;
    private javax.swing.JLabel WindDirection02;
    private javax.swing.JLabel WindDirection03;
    private javax.swing.JLabel WindDirection04;
    private javax.swing.JLabel WindDirection05;
    private javax.swing.JLabel WindDirection06;
    private javax.swing.JLabel WindDirection07;
    private javax.swing.JLabel WindSpeed01;
    private javax.swing.JLabel WindSpeed02;
    private javax.swing.JLabel WindSpeed03;
    private javax.swing.JLabel WindSpeed04;
    private javax.swing.JLabel WindSpeed05;
    private javax.swing.JLabel WindSpeed06;
    private javax.swing.JLabel WindSpeed07;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
   // private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jComboBoxWeather;
    private javax.swing.JButton SaveToFileButton;
    private javax.swing.JButton LogOutButton;
    // End of variables declaration
}
