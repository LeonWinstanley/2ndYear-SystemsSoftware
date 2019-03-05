import java.io.*;
import java.net.*;
import java.security.*;
import java.security.spec.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;

// below are Jframe imports
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

// PBKDF2 hashing used

public class Login {
	JLabel l1, l2, l3;
	JTextField tf1;
	JButton btn1;
	JPasswordField p1;
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	String password;

	Login() {
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		int widthMid = width / 2;
		int heightMid = height / 2;
		JFrame frame = new JFrame("Login Screen");
		l1 = new JLabel("Login Form");
		l1.setForeground(Color.blue);

		l2 = new JLabel("Username :");
		l3 = new JLabel("Password :");
		tf1 = new JTextField();
		p1 = new JPasswordField();
		btn1 = new JButton("Login");

		l1.setBounds(100, 30, 400, 30);
		l2.setBounds(80, 70, 200, 30);
		l3.setBounds(80, 110, 200, 30);
		tf1.setBounds(300, 70, 200, 30);
		p1.setBounds(300, 110, 200, 30);
		btn1.setBounds(150, 160, 100, 30);

		frame.add(l1);
		frame.add(l2);
		frame.add(tf1);
		frame.add(l3);
		frame.add(p1);
		frame.add(btn1);

		// leave at bottom
		frame.setSize(widthMid - 400, heightMid - 200);
		frame.setLocation(widthMid - 400, heightMid - 200);
		frame.setLayout(null);
		frame.setResizable(false);
		// frame layouts must be above
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// start hashing. password variable is all that needs
		// to change to inputted password

	}

	public void ReadingFromFile() {
		FileReader fr = new FileReader("data\users.txt")
	}

	public void actionPerformed(ActionEvent ae) {
		String inputtedUserName = tf1.getText();
		char[] inputtedPassword = p1.getPassword();

		try {
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			random.nextBytes(salt);
			KeySpec spec = new PBEKeySpec(inputtedPassword, salt, 65536, 128);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = factory.generateSecret(spec).getEncoded();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void main(String[] args) {
		new Login();
	}
}
