import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.security.*;
import java.security.spec.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginTest extends JFrame {

    public LoginTest() {
        initComponents();
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jTextField1 = new JTextField();
        jLabel2 = new JLabel();
        jPasswordField1 = new JPasswordField();
        jLabel3 = new JLabel();
        jButton2 = new JButton();
        jButton3 = new JButton();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(new Color(61, 158, 226));
        setResizable(false);

        jPanel1.setBackground(new Color(162, 162, 162));
        jPanel1.setForeground(new Color(195, 195, 195));
        jPanel1.setToolTipText("");

        jLabel1.setFont(new Font("URW Gothic L", 1, 18));
        jLabel1.setForeground(new Color(254, 254, 254));
        jLabel1.setText("Username :");

        jLabel2.setFont(new Font("URW Gothic L", 1, 18));
        jLabel2.setForeground(new Color(254, 254, 254));
        jLabel2.setText("Password :");

        jPasswordField1.setFont(new Font("URW Gothic L", 0, 18));

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout
                .setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
                                jPanel1Layout.createSequentialGroup().addContainerGap()
                                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
                                                .addComponent(jPasswordField1).addComponent(jTextField1)
                                                .addGroup(Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                                                                .addComponent(jLabel1).addComponent(jLabel2))
                                                        .addGap(0, 350, Short.MAX_VALUE)))
                                        .addContainerGap()));
        jPanel1Layout
                .setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
                        jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18).addComponent(jLabel2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jPasswordField1,
                                        GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(30, Short.MAX_VALUE)));

        jLabel3.setFont(new Font("URW Gothic L", 0, 48));
        jLabel3.setForeground(new Color(254, 254, 254));
        jLabel3.setText("Log in to your account");

        jButton2.setBackground(new Color(148, 175, 172));
        jButton2.setFont(new Font("URW Gothic L", 0, 24));
        jButton2.setForeground(new Color(144, 144, 144));
        jButton2.setText("Log In");
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new Color(148, 175, 172));
        jButton3.setFont(new Font("URW Gothic L", 0, 24));
        jButton3.setForeground(new Color(144, 144, 144));
        jButton3.setText("Sign Up");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING,
                                layout.createSequentialGroup().addComponent(jLabel3).addGap(53, 53, 53))
                        .addGroup(Alignment.TRAILING,
                                layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 197,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 197,
                                                GroupLayout.PREFERRED_SIZE))
                                        .addGap(217, 217, 217))
                        .addGroup(
                                Alignment.TRAILING, layout
                                        .createSequentialGroup().addComponent(jPanel1, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(82, 82, 82)))));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34).addComponent(jLabel3).addGap(38, 38, 38)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18).addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE)));

        pack();
    }

    private void jButton2ActionPerformed(ActionEvent evt) {

        String inputtedUserName = jTextField1.getText();
        char[] inputtedPassword = jPasswordField1.getPassword();
        String testString = "";
        String afterHash = "";
        File userFile = new File("../data/users.txt");

        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            KeySpec spec = new PBEKeySpec(inputtedPassword, salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            System.out.println(hash);
            afterHash = new String(hash);
        } catch (Exception e) {
            System.out.println(e);
        }

        boolean isErrorU = true;

        try {
            BufferedReader br = new BufferedReader(new FileReader(userFile));
            while ((testString = br.readLine()) != null) {
                String fileUserName;
                String filePassword;

                String findComma = ",";
                Pattern word = Pattern.compile(findComma);
                Matcher match = word.matcher(testString);
                int commaLocation = 0;

                while (match.find()) {
                    commaLocation = match.start();
                }

                fileUserName = testString.substring(0, commaLocation);
                filePassword = testString.substring(commaLocation + 1, testString.length());

                System.out.println(fileUserName);
                System.out.println(filePassword);
                // comma seperates the username and password

                if (inputtedUserName.equals(fileUserName) && afterHash.equals(filePassword)) {
                    isErrorU = false;
                }
            }

            if (isErrorU == true) {
                JOptionPane.showMessageDialog(this, "Incorrect username or password", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginTest().setVisible(true);
            }
        });
    }

    private JButton jButton2;
    private JButton jButton3;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPanel jPanel1;
    private JPasswordField jPasswordField1;
    private JTextField jTextField1;

}
