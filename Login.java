import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.*;
// below are Jframe imports
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
	JLabel l1, l2, l3;
	JTextField tf1;
	JButton btn1;
	JPasswordField p1;

	Login() {
		JFrame frame = new JFrame("Login Screen");
		l1 = new JLabel("Login Form");
		l1.setForeground(Color.blue);

		l2 = new JLabel("Username");
		l3 = new JLabel("Password");
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
		frame.setSize(700, 500);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// public void actionPerformed(ActionEvent ae) {
	// String uName = tf1.getText();
	// String pass = p1.gettext();

	// }

	public static void main(String[] args) {
		new Login();
	}
}
