package project;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.SystemColor;
import java.awt.Window.Type;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import project.Database_Handler.LoginExcute;

public class Login {

	private JFrame frmLogin;
	private JTextField nameField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JPasswordField passwordField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private String usr = "";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		initialize();
	}

	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setType(Type.UTILITY);
		frmLogin.setResizable(false);
		frmLogin.setBackground(new Color(0, 255, 255));
		frmLogin.getContentPane().setBackground(new Color(244, 164, 96));
		frmLogin.setBounds(100, 100, 249, 295);
		frmLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		frmLogin.setVisible(true);
		
		JLabel lblLoginForm = new JLabel("Login Form");
		lblLoginForm.setForeground(new Color(255, 255, 255));
		lblLoginForm.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 25));
		lblLoginForm.setBounds(68, 13, 110, 29);
		frmLogin.getContentPane().add(lblLoginForm);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		panel.setBounds(0, 41, 243, 224);
		panel.setBorder(null);
		frmLogin.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBounds(10, 32, 57, 26);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblName);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setBounds(10, 69, 75, 26);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblPassword);

		nameField = new JTextField();
		nameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent k) {
				if (usr.length() < 3) {
					usr += k.getKeyChar();
				}else{
					AutoComplete(usr);
				}
			}
		});
		nameField.setBounds(97, 32, 107, 20);
		panel.add(nameField);
		nameField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(95, 74, 109, 20);
		panel.add(passwordField);

		JLabel lblCreateNewAccount = new JLabel("Create new account");
		lblCreateNewAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frmLogin.dispose();
				Register.getInit();
			}
		});
		lblCreateNewAccount.setForeground(SystemColor.window);
		lblCreateNewAccount.setBounds(64, 172, 128, 14);
		panel.add(lblCreateNewAccount);

		JCheckBox chckbxRM = new JCheckBox("Remember me");
		chckbxRM.setForeground(new Color(255, 255, 255));
		chckbxRM.setBackground(new Color(100, 149, 237));
		chckbxRM.setBounds(60, 102, 134, 23);
		panel.add(chckbxRM);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(234, 41, 240, 220);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmLogin.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel label = new JLabel("Name");
		label.setBounds(10, 34, 57, 26);
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(label);

		textField_1 = new JTextField();
		textField_1.setBounds(97, 33, 107, 20);
		textField_1.setColumns(10);
		panel_1.add(textField_1);

		JLabel label_1 = new JLabel("Password");
		label_1.setBounds(9, 69, 75, 26);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(label_1);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(95, 72, 109, 20);
		panel_1.add(passwordField_1);

		textField_2 = new JTextField();
		textField_2.setBounds(96, 113, 107, 20);
		textField_2.setColumns(10);
		panel_1.add(textField_2);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(9, 111, 57, 26);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(lblEmail);

		textField_3 = new JTextField();
		textField_3.setBounds(97, 151, 107, 20);
		textField_3.setColumns(10);
		panel_1.add(textField_3);

		JLabel lblPhoneNo = new JLabel("Phone No");
		lblPhoneNo.setBounds(10, 150, 77, 26);
		lblPhoneNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(lblPhoneNo);

		Button signup = new Button("Sign up\r\n");
		signup.setBounds(79, 190, 91, 22);
		signup.setFont(new Font("Apple Casual", Font.PLAIN, 16));
		signup.setBackground(new Color(0, 128, 0));
		panel_1.add(signup);
		JLabel label1 = new JLabel("\r\n");

		label1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String N = nameField.getText();
				String P = passwordField.getText();
				if (chckbxRM.isSelected()) {
					String name = nameField.getText();
					String pwd = passwordField.getText();
					setUser(name + ":" + pwd);
				}
				new LoginExcute(N, P);
			}
		});
		label1.setBounds(70, 126, 97, 39);
		label1.setBackground(new Color(34, 167, 240));
		label1.setFont(new Font("Apple Casual", Font.PLAIN, 16));
		Image img = new ImageIcon(this.getClass().getResource("/login-btn.png")).getImage();
		label1.setIcon(new ImageIcon(img.getScaledInstance(90, 30, 20)));
		panel.add(label1);
	}

	public void setUser(String name) {
		try {
			FileReader read = new FileReader("user.txt");
			Scanner scan = new Scanner(read);
			FileWriter file = new FileWriter("user.txt");
			if (scan != null) {
				while (scan.hasNext()) {
					System.out.println(scan.nextLine());
					file.write(scan.next());
				}
			}
			file.write(name + "\n");
			scan.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void AutoComplete(String c) {
		String[] i;
		try {
			i = ReadText.whoIsUser(c);
			if (i != null) {
				nameField.setText(i[0]);
				passwordField.setText(i[1]);
				nameField.add(new PopupMenu());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}