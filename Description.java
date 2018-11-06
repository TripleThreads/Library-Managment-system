package project;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window.Type;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import project.Database_Handler.Borrowings;
import project.Database_Handler.View;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Description {
	static String title = null;
	static private JFrame frmDescription;
	static private Description INSTANCE = null;

	static public Description getDescription(String titl) {
		title = titl;
		if (INSTANCE == null) {
			INSTANCE = new Description(title);
		} else {
			frmDescription.dispose();
			INSTANCE = new Description(title);
		}
		return INSTANCE;
	}

	private Description(String title) {
		ArrayList<String> des = View.Description(title);
		String author = des.get(0);
		String published = des.get(1);
		initialize(title, author, published);
	}

	private void initialize(String title, String author, String published) {
		frmDescription = new JFrame();
		frmDescription.setTitle("Description : " + title);
		frmDescription.setType(Type.UTILITY);
		frmDescription.setBounds(100, 100, 222, 162);
		frmDescription.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDescription.getContentPane().setLayout(null);

		JLabel lblTitle = new JLabel("Title : " + title);
		lblTitle.setFont(new Font("Lucida Sans", Font.PLAIN, 13));
		lblTitle.setBounds(6, 27, 212, 22);
		frmDescription.getContentPane().add(lblTitle);

		JLabel lblAuthor = new JLabel("Author : " + author);
		lblAuthor.setFont(new Font("Lucida Sans", Font.PLAIN, 13));
		lblAuthor.setBounds(6, 50, 212, 22);
		frmDescription.getContentPane().add(lblAuthor);

		JLabel lblPublishedDate = new JLabel("Published Date : " + published);
		lblPublishedDate.setFont(new Font("Lucida Sans", Font.PLAIN, 13));
		lblPublishedDate.setBounds(6, 75, 212, 14);
		frmDescription.getContentPane().add(lblPublishedDate);

		Button button = new Button("Borrow it!");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (ReadText.ReadSession() != null) {
						Borrowings.Borrow(title);
					} else {
						JOptionPane.showMessageDialog(null, "Please login");
						new Login();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				frmDescription.dispose();
			}
		});
		button.setForeground(new Color(255, 255, 255));
		button.setFont(new Font("Dialog", Font.BOLD, 12));
		button.setBackground(new Color(100, 149, 237));
		button.setBounds(49, 95, 70, 22);
		frmDescription.getContentPane().add(button);

		frmDescription.setVisible(true);
		frmDescription.setLocationRelativeTo(null);
	}
}
