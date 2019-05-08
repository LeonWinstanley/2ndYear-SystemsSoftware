import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.regex.Pattern;

class listenToServer implements Runnable {

    DataInputStream dis;
    ClientGUI client;

    public listenToServer(DataInputStream dis, ClientGUI client) {
        this.dis = dis;
        this.client = client;
    }

    @Override
    public void run(){
        while(true)
        {
        try {
                String received = dis.readUTF();
                client.splitDISData(received);                
        } catch (Exception e) {
                //TODO: handle exception
        }
        }       
    }
}

public class ClientGUI extends JFrame {

    final static int ServerPort = 50001;
    DataInputStream dis; // = new DataInputStream(s.getInputStream());
    DataOutputStream dos; // = new DataOutputStream(s.getOutputStream());

    public ClientGUI() {
        initComponents();
        this.setVisible(true);
        try {
            ConnectToServer();

        } catch (Exception e) {
            System.out.println("ClientGUI.java :: ClientGUI() " + e);
        }

    }

    private void ConnectToServer() throws UnknownHostException, IOException {

        Scanner scn = new Scanner(System.in);

        // getting localhost ip
        InetAddress ip = InetAddress.getByName("localhost");

        // establish the connection
        Socket s = new Socket(ip, ServerPort);

        dis = new DataInputStream(s.getInputStream());

        listenToServer lis = new listenToServer(dis, this);
        Thread listenThread = new Thread(lis);
        listenThread.start();
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
        jScrollPane1 = new javax.swing.JScrollPane();
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
        UVIndex01 = new javax.swing.JLabel();
        ChanceOfRain03 = new javax.swing.JLabel();
        ChanceOfRain01 = new javax.swing.JLabel();
        ChanceOfRain02 = new javax.swing.JLabel();
        ChanceOfRain04 = new javax.swing.JLabel();
        ChanceOfRain06 = new javax.swing.JLabel();
        ChanceOfRain05 = new javax.swing.JLabel();
        ChanceOfRain07 = new javax.swing.JLabel();
        UVIndex02 = new javax.swing.JLabel();
        UVIndex03 = new javax.swing.JLabel();
        UVIndex04 = new javax.swing.JLabel();
        UVIndex05 = new javax.swing.JLabel();
        UVIndex06 = new javax.swing.JLabel();
        UVIndex07 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Latitude");

        jLabel2.setText("Longitude");

        jLabel3.setText("Temperature");

        jLabel4.setText("WindSpeed");

        jLabel5.setText("Humidity");

        jLabel6.setText("Chance of Rain");

        jLabel7.setText("WindDirection");

        jLabel8.setText("UV Index");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(null));

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

        Latitude01.setText("0");
        Latitude02.setText("0");
        Latitude03.setText("0");
        Latitude04.setText("0");
        Latitude05.setText("0");
        Latitude06.setText("0");
        Latitude07.setText("0");

        Longitude01.setText("0");
        Longitude02.setText("0");
        Longitude03.setText("0");
        Longitude04.setText("0");
        Longitude05.setText("0");
        Longitude06.setText("0");
        Longitude07.setText("0");

        Humidity01.setText("0");
        Humidity02.setText("0");
        Humidity03.setText("0");
        Humidity05.setText("0");
        Humidity04.setText("0");
        Humidity06.setText("0");
        Humidity07.setText("0");

        Temperature01.setText("0");
        Temperature02.setText("0");
        Temperature03.setText("0");
        Temperature04.setText("0");
        Temperature05.setText("0");
        Temperature06.setText("0");
        Temperature07.setText("0");

        WindSpeed01.setText("0");
        WindSpeed02.setText("0");
        WindSpeed03.setText("0");
        WindSpeed04.setText("0");
        WindSpeed05.setText("0");
        WindSpeed06.setText("0");
        WindSpeed07.setText("0");

        WindDirection01.setText("0");
        WindDirection02.setText("0");
        WindDirection03.setText("0");
        WindDirection04.setText("0");
        WindDirection05.setText("0");
        WindDirection06.setText("0");
        WindDirection07.setText("0");

        ChanceOfRain01.setText("0");
        ChanceOfRain02.setText("0");
        ChanceOfRain03.setText("0");
        ChanceOfRain04.setText("0");
        ChanceOfRain05.setText("0");
        ChanceOfRain06.setText("0");
        ChanceOfRain07.setText("0");

        UVIndex01.setText("0");
        UVIndex02.setText("0");
        UVIndex03.setText("0");
        UVIndex04.setText("0");
        UVIndex05.setText("0");
        UVIndex06.setText("0");
        UVIndex07.setText("0");

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
                                .addComponent(ChanceOfRain06, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ChanceOfRain05, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ChanceOfRain04, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ChanceOfRain03, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ChanceOfRain07, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ChanceOfRain02, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ChanceOfRain01, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(76, 76, 76)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(UVIndex02, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(UVIndex01, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(UVIndex07, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(UVIndex03, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(UVIndex04, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(UVIndex05, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(UVIndex06, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Latitude01).addComponent(Longitude01).addComponent(Humidity01)
                                .addComponent(Temperature01).addComponent(WindSpeed01).addComponent(WindDirection01)
                                .addComponent(ChanceOfRain01).addComponent(UVIndex01))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Latitude02).addComponent(Longitude02).addComponent(Humidity02)
                                .addComponent(Temperature02).addComponent(WindSpeed02).addComponent(WindDirection02)
                                .addComponent(ChanceOfRain02).addComponent(UVIndex02))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Latitude03).addComponent(Longitude03).addComponent(Humidity03)
                                .addComponent(Temperature03).addComponent(WindSpeed03).addComponent(WindDirection03)
                                .addComponent(ChanceOfRain03).addComponent(UVIndex03))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Latitude04).addComponent(Longitude04).addComponent(Humidity04)
                                .addComponent(Temperature04).addComponent(WindSpeed04).addComponent(WindDirection04)
                                .addComponent(ChanceOfRain04).addComponent(UVIndex04))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Latitude05).addComponent(Longitude05).addComponent(Humidity05)
                                .addComponent(Temperature05).addComponent(WindSpeed05).addComponent(WindDirection05)
                                .addComponent(ChanceOfRain05).addComponent(UVIndex05))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Latitude06).addComponent(Longitude06).addComponent(Humidity06)
                                .addComponent(Temperature06).addComponent(WindSpeed06).addComponent(WindDirection06)
                                .addComponent(ChanceOfRain06).addComponent(UVIndex06))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Latitude07).addComponent(Longitude07).addComponent(Humidity07)
                                .addComponent(Temperature07).addComponent(WindSpeed07).addComponent(WindDirection07)
                                .addComponent(ChanceOfRain07).addComponent(UVIndex07))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 24, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1))
                        .addGap(120, 120, 120)
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
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(72, 72, 72))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                        jPanel1Layout.createSequentialGroup()
                                                .addComponent(LogOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(169, 169, 169)))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }

    private void SaveToFileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

    }

    private void LogOutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        Login login = new Login();
        login.setVisible(true);
        dispose();
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
            String WindDirection, String ChanceOfRain, String UVIndex) {

        String previousData = "";
        String nextData = "";

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
        nextData = previousData;
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

        previousData = ChanceOfRain01.getText();
        ChanceOfRain01.setText(ChanceOfRain);
        nextData = previousData;
        previousData = ChanceOfRain02.getText();
        ChanceOfRain02.setText(nextData);
        nextData = previousData;
        previousData = ChanceOfRain03.getText();
        ChanceOfRain03.setText(nextData);
        nextData = previousData;
        previousData = ChanceOfRain04.getText();
        ChanceOfRain04.setText(nextData);
        nextData = previousData;
        previousData = ChanceOfRain05.getText();
        ChanceOfRain05.setText(nextData);
        nextData = previousData;
        previousData = ChanceOfRain06.getText();
        ChanceOfRain06.setText(nextData);
        nextData = previousData;
        ChanceOfRain07.setText(nextData);

        previousData = UVIndex01.getText();
        UVIndex01.setText(UVIndex);
        nextData = previousData;
        previousData = UVIndex02.getText();
        UVIndex02.setText(nextData);
        nextData = previousData;
        previousData = UVIndex03.getText();
        UVIndex03.setText(nextData);
        nextData = previousData;
        previousData = UVIndex04.getText();
        UVIndex04.setText(nextData);
        nextData = previousData;
        previousData = UVIndex05.getText();
        UVIndex05.setText(nextData);
        nextData = previousData;
        previousData = UVIndex06.getText();
        UVIndex06.setText(nextData);
        nextData = previousData;
        UVIndex07.setText(nextData);
    }

    //////////////////////////////////////////////////////////
    // splitDISData() //
    // Splits data that is received through the data stream //
    //////////////////////////////////////////////////////////

    public void splitDISData(String DISInput) {

        String[] weatherList = DISInput.split("\\s*,\\s*");
        setRowText(weatherList[0], weatherList[1], weatherList[2], weatherList[3], weatherList[4], weatherList[5],
                weatherList[6], weatherList[7]);

    }

    // Variables declaration - do not modify
    private javax.swing.JLabel ChanceOfRain01;
    private javax.swing.JLabel ChanceOfRain02;
    private javax.swing.JLabel ChanceOfRain03;
    private javax.swing.JLabel ChanceOfRain04;
    private javax.swing.JLabel ChanceOfRain05;
    private javax.swing.JLabel ChanceOfRain06;
    private javax.swing.JLabel ChanceOfRain07;
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
    private javax.swing.JLabel UVIndex01;
    private javax.swing.JLabel UVIndex02;
    private javax.swing.JLabel UVIndex03;
    private javax.swing.JLabel UVIndex04;
    private javax.swing.JLabel UVIndex05;
    private javax.swing.JLabel UVIndex06;
    private javax.swing.JLabel UVIndex07;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton SaveToFileButton;
    private javax.swing.JButton LogOutButton;
    // End of variables declaration

}
