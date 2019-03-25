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

public class ClientGUI extends JFrame {

    final static int ServerPort = 50001;

    public ClientGUI() {
        initComponents();
        try {
            ConnectToServer();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void ConnectToServer() throws UnknownHostException, IOException {

        Scanner scn = new Scanner(System.in);

        // getting localhost ip
        InetAddress ip = InetAddress.getByName("localhost");

        // establish the connection
        Socket s = new Socket(ip, ServerPort);

        // obtaining input and out streams
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

    }

    private void initComponents() {

        jPanel1 = new JPanel();
        btnLogOut = new JButton();
        btnSave = new JButton();
        scrollPane1 = new ScrollPane();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new Color(193, 193, 193));

        btnLogOut.setFont(new Font("URW Gothic L", 0, 16)); // NOI18N
        btnLogOut.setText("Log Out");
        btnLogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        btnSave.setFont(new Font("URW Gothic L", 0, 16)); // NOI18N
        btnSave.setText("Save To File");

        jTable1.setBackground(new Color(236, 236, 236));
        jTable1.setFont(new Font("URW Gothic L", 0, 15)); // NOI18N
        jTable1.setModel(new DefaultTableModel(new Object[][] { null, null, null, null, null, null, null, null, null },
                new String[] { "Latitude", "Long", "Humid", "Temp", "WindSpeed", "WindDirection", "Pressure",
                        "Chance of Rain", "UV Index" }));
        jScrollPane1.setViewportView(jTable1);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                GroupLayout.Alignment.TRAILING,
                jPanel1Layout.createSequentialGroup().addGap(10, 10, 10)
                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 905, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 156,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnLogOut, GroupLayout.PREFERRED_SIZE, 156,
                                                GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout
                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnLogOut, GroupLayout.PREFERRED_SIZE, 79,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 79,
                                                GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap()));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap()
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap()
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap()));

        pack();
    }

    private void btnLogOutActionPerformed(ActionEvent evt) {

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
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientGUI().setVisible(true);
            }
        });
    }

    private JButton btnLogOut;
    private JButton btnSave;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private ScrollPane scrollPane1;

}
