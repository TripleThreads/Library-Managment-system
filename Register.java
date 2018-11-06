package project;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

// ---- collected data will be stored here 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import project.Database_Handler.Registration;

public class Register {
	private static Register INSTANCE = null;

	static private JFrame frmRegister;
	private JTextField fname;
	private JPasswordField passwordField;
	private JTextField emailField;
	private JPasswordField rePassword;
	private JTextField lname;
	private JTextField ans;

	///// THE COLLECTED CREDENTIALS WILL BE STORED OVER HERE
	private String FName;
	private String LName;
	private String pwd;
	private String re_pwd;
	private String Depts;
	private String email;
	private String Question;
	private String answer;

	static Register getInit() {
		if (INSTANCE == null) {
			INSTANCE = new Register();
		} else {
			frmRegister.dispose();
			INSTANCE = new Register();
		}
		return INSTANCE;
	}

	private Register() {
		initialize();
	}

	private void initialize() {
		frmRegister = new JFrame();
		frmRegister.setType(Type.UTILITY);
		frmRegister.setTitle("Register");
		frmRegister.setResizable(false);
		frmRegister.setBounds(100, 100, 295, 373);
		frmRegister.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRegister.getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 289, 344);
		frmRegister.getContentPane().add(panel_1);
		panel_1.setForeground(new Color(102, 0, 102));
		panel_1.setLayout(null);
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(204, 204, 255));

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(new Color(102, 0, 102));
		lblFirstName.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 17));
		lblFirstName.setBounds(10, 47, 83, 26);
		panel_1.add(lblFirstName);

		lname = new JTextField();
		lname.setColumns(10);
		lname.setBounds(135, 80, 130, 20);
		panel_1.add(lname);

		JLabel lblLName = new JLabel("Last Name");
		lblLName.setForeground(new Color(102, 0, 102));
		lblLName.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 17));
		lblLName.setBounds(10, 79, 75, 26);
		panel_1.add(lblLName);
		frmRegister.setVisible(true);
		frmRegister.setLocationRelativeTo(null);

		JLabel label_1 = new JLabel("Password");
		label_1.setForeground(new Color(102, 0, 102));
		label_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 17));
		label_1.setBounds(10, 109, 75, 26);
		panel_1.add(label_1);

		fname = new JTextField();
		fname.setColumns(10);
		fname.setBounds(135, 47, 130, 20);
		panel_1.add(fname);

		passwordField = new JPasswordField();
		passwordField.setBounds(134, 112, 132, 20);
		panel_1.add(passwordField);


		ans = new JTextField();
		ans.setBounds(136, 235, 127, 20);

		ans.setColumns(10);

		Button button_1 = new Button("Reset");
		button_1.setForeground(new Color(255, 255, 255));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				passwordField.setText(null);
				fname.setText(null);
				rePassword.setText(null);
				lname.setText(null);
				emailField.setText(null);
				ans.setText(null);
			}
		});
		button_1.setFont(new Font("Apple Casual", Font.PLAIN, 18));
		button_1.setBackground(new Color(192, 57, 43));
		button_1.setBounds(61, 302, 70, 22);
		panel_1.add(button_1);

		JLabel lblPhone = new JLabel("Re type");
		lblPhone.setForeground(new Color(102, 0, 102));
		lblPhone.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 17));
		lblPhone.setBounds(10, 142, 57, 26);
		panel_1.add(lblPhone);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(102, 0, 102));
		lblEmail.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 17));
		lblEmail.setBounds(10, 180, 57, 26);
		panel_1.add(lblEmail);

		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(135, 180, 130, 20);
		panel_1.add(emailField);

		Label label_2 = new Label("SignUp");
		label_2.setForeground(new Color(102, 0, 102));
		label_2.setFont(new Font("Broadway", Font.PLAIN, 18));
		label_2.setAlignment(Label.CENTER);
		label_2.setBounds(46, 9, 167, 22);
		panel_1.add(label_2);

		rePassword = new JPasswordField();
		rePassword.setBounds(135, 147, 130, 20);
		panel_1.add(rePassword);

		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setForeground(new Color(102, 0, 102));
		lblDepartment.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 17));
		lblDepartment.setBounds(10, 248, 83, 26);
		panel_1.add(lblDepartment);

		Choice choice_1 = new Choice();
		choice_1.setBounds(182, 255, 83, 20);
		panel_1.add(choice_1);
		choice_1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				Depts = choice_1.getItem(choice_1.getSelectedIndex());
			}
		});

		for (String d : Depts("Engineering")) {
			choice_1.add(d);
		}

		Choice choice = new Choice();
		choice.setBounds(101, 255, 75, 20);
		choice.add("Engineering");
		choice.add("Medical");
		choice.add("Social");

		choice.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				choice_1.removeAll();
				for (String d : Depts(choice.getItem(choice.getSelectedIndex()))) {
					choice_1.add(d);
				}
			}
		});
		panel_1.add(choice);

		JLabel lblSecurityQuestion = new JLabel("Security Question");
		lblSecurityQuestion.setForeground(new Color(102, 0, 102));
		lblSecurityQuestion.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 17));
		lblSecurityQuestion.setBounds(10, 212, 109, 26);
		panel_1.add(lblSecurityQuestion);

		Choice choice_2 = new Choice();
		choice_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Question = choice_2.getItem(choice_2.getSelectedIndex());
				answer = JOptionPane.showInputDialog("Don't share the answer");
			}
		});
		choice_2.setBounds(135, 218, 131, 20);
		choice_2.add("Nick name?");
		choice_2.add("Pet Name");
		choice_2.add("Birth Place");
		panel_1.add(choice_2);
		
		Button button = new Button("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FName = fname.getText();
				LName = lname.getText();
				pwd = passwordField.getText();
				re_pwd = rePassword.getText();
				email = emailField.getText();
				String[] credential = new String[] {FName,LName,email,pwd, Question,answer,Depts};
				new Registration(credential);
				frmRegister.dispose();
				try {
					new AdminPage();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		button.setForeground(new Color(255, 255, 255));
		button.setFont(new Font("Apple Casual", Font.PLAIN, 16));
		button.setBackground(new Color(34, 167, 240));
		button.setBounds(148, 302, 70, 22);
		panel_1.add(button);
	}

	public ArrayList<String> Depts(String department) {
		ArrayList<String> dep = new ArrayList<>();
		try {
			File file = new File("dep.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String[] sc = scan.nextLine().split(":");
				if (sc[0].equals(department)) {
					dep.add(sc[1]);
				}
			}
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dep;
	}
}
