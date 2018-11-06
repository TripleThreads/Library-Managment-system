package project;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import project.Database_Handler.ReturnItems;
import project.Database_Handler.View;

public class Library {
	JMenuItem currentMenu = null;
	JFrame frmLibraryManagmentSystem;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private String Item = "Recommended Items";
	private Object[] items;
	private String usr = "";
	public TextArea textArea;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CheckFiles();
					Library window = new Library();
					window.new Client().start();
					window.frmLibraryManagmentSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Library() {
		initialize();
	}

	private void initialize() {
		frmLibraryManagmentSystem = new JFrame();
		frmLibraryManagmentSystem.setType(Type.UTILITY);
		frmLibraryManagmentSystem.setTitle("Library Managment system");
		frmLibraryManagmentSystem.setResizable(false);
		frmLibraryManagmentSystem.getContentPane().setFont(new Font("Apple Casual", Font.PLAIN, 11));
		frmLibraryManagmentSystem.getContentPane().setBackground(new Color(135, 206, 235));
		frmLibraryManagmentSystem.setBounds(100, 100, 636, 400);
		frmLibraryManagmentSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLibraryManagmentSystem.getContentPane().setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(171, -1, 455, 372);
		frmLibraryManagmentSystem.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		List list = new List();
		list.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 16));
		list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Description.getDescription(list.getItem(list.getSelectedIndex()));
			}
		});
		list.setBounds(10, 105, 157, 240);
		list.setBackground(new Color(240, 240, 240));

		Panel panel_4 = new Panel();
		panel_4.setBounds(299, 265, 146, 97);

		panel_4.setLayout(null);

		List list_3 = new List();
		list_3.setBounds(0, 35, 142, 60);
		list_3.setBackground(new Color(240, 240, 240));
		ArrayList<String> array = ReadText.suggest();
		for(String title : array) {
			list_3.add(title);
		}
		panel_4.add(list_3);

		JLabel lblRecommendedBooks = new JLabel(Item);
		lblRecommendedBooks.setBounds(2, 16, 140, 14);
		panel_4.add(lblRecommendedBooks);
		lblRecommendedBooks.setFont(new Font("Trebuchet MS", Font.BOLD, 12));

		Choice choice = new Choice();
		choice.setFont(new Font("Eras Demi ITC", Font.PLAIN, 15));
		choice.add("Books");
		choice.add("Digital Media");
		choice.add("Article");
		choice.select(0);

		Item = choice.getItem(choice.getSelectedIndex());
		lblRecommendedBooks.setText("Recommended " + Item);
		try {
			ArrayList<String> arr = View.catagory(Item);
			for (String title : arr) {
				list.add(title);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		choice.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				Item = choice.getItem(choice.getSelectedIndex());
				lblRecommendedBooks.setText("Recommended " + Item);
				list.removeAll();
				try {
					ArrayList<String> arr = View.catagory(Item);
					for (String title : arr) {
						list.add(title);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Sorry something went wrong");
				}
			}
		});
		choice.setBounds(10, 74, 157, 271);

		List list_1 = new List();
		list_1.setBackground(new Color(220, 220, 220));
		list_1.setBounds(293, 122, 149, 132);
		ArrayList<String> top = View.Top_Rated();
		for (String t : top) {
			list_1.add(t);
		}
		list_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String title = list_1.getSelectedItem().split(":")[0].trim();
				Description.getDescription(title);
			}
		});

		Label label_1 = new Label("Top Rated Items");
		label_1.setFont(new Font("Lucida Fax", Font.BOLD, 15));
		label_1.setAlignment(Label.CENTER);
		label_1.setBounds(294, 82, 146, 20);

		TextField textField = new TextField();
		textField.setBounds(201, 35, 154, 22);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(255, 250, 250));
		panel_2.setBorder(null);
		panel_2.setBackground(new Color(255, 140, 0));
		panel_2.setBounds(0, 0, 64, 23);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblKenedy = new JLabel("Kenedy");
		lblKenedy.setForeground(new Color(255, 250, 250));
		lblKenedy.setFont(new Font("Perpetua Titling MT", Font.BOLD, 12));
		lblKenedy.setBounds(4, 6, 60, 14);
		panel_2.add(lblKenedy);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(null);
		panel_3.setBackground(new Color(204, 204, 255));
		panel_3.setBounds(103, 79, 243, 203);
		panel_1.add(panel_3);

		JLabel label_2 = new JLabel("Name");
		label_2.setForeground(new Color(51, 0, 51));
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(10, 32, 57, 26);
		panel_3.add(label_2);

		JLabel label_3 = new JLabel("Password");
		label_3.setForeground(new Color(0, 0, 51));
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(10, 69, 75, 26);
		panel_3.add(label_3);

		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent k) {
				if (usr.length() < 3) {
					usr += k.getKeyChar();
				} else {
					AutoComplete(usr);
				}
			}
		});
		textField_1.setColumns(10);
		textField_1.setBounds(97, 32, 107, 20);
		panel_3.add(textField_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(95, 74, 109, 20);
		panel_3.add(passwordField);

		JLabel label_4 = new JLabel("\r\n");
		label_4.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				String name = textField_1.getText();
				String pwd = passwordField.getText();
				setUser(name + ":" + pwd);
			}
		});
		label_4.setFont(new Font("Apple Casual", Font.PLAIN, 16));
		label_4.setBackground(new Color(34, 167, 240));
		label_4.setBounds(70, 116, 94, 39);
		Image im = new ImageIcon(this.getClass().getResource("/login-btn.png")).getImage();
		label_4.setIcon(new ImageIcon(im.getScaledInstance(90, 30, 20)));
		panel_3.add(label_4);

		JLabel label_5 = new JLabel("Create new account");
		label_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Register.getInit();
			}
		});
		label_5.setForeground(new Color(102, 0, 102));
		label_5.setBounds(64, 166, 131, 14);
		panel_3.add(label_5);

		Checkbox checkbox = new Checkbox("Rate Item");
		checkbox.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 17));
		checkbox.setBounds(133, 204, 95, 22);

		Choice choice_1 = new Choice();
		choice_1.setBounds(239, 204, 91, 20);
		for (int i = 0; i <= 10; i++) {
			choice_1.add(i + "");
		}

		Label label = new Label("You have borrowed these items");
		label.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		label.setBounds(122, 96, 208, 22);

		List list_2 = new List();
		list_2.setMultipleSelections(true);
		list_2.setMultipleMode(true);
		list_2.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		list_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> ReturnItems = new ArrayList<>();
				for (int i : list_2.getSelectedIndexes()) {
					String item = list_2.getItem(i);
					ReturnItems.add(item);
				}
				items = ReturnItems.toArray();
			}
		});
		ArrayList<String> borrowed_items = ReturnItems.Borrowed();
		for (String item : borrowed_items) {
			list_2.add(item);
		}

		Button button_1 = new Button("Return");
		button_1.setForeground(new Color(255, 255, 255));
		button_1.setFont(new Font("Yu Gothic", Font.BOLD, 13));
		button_1.setBackground(new Color(100, 149, 237));
		button_1.setBounds(173, 272, 99, 30);
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					for (String lists : list_2.getSelectedItems()) {
						ReturnItems.Return(lists);
						if (checkbox.getState()) {
							int r = Integer.parseInt(choice_1.getSelectedItem());
							ReturnItems.Rate(lists, r);
						}
					}
					for (int list : list_2.getSelectedIndexes()) {
						list_2.remove(0);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		list_2.setBounds(122, 120, 208, 54);
		
		textArea = new TextArea();
		textArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		textArea.setEditable(false);
		textArea.setBounds(35, 53, 380, 192);

		TextArea textArea_1 = new TextArea();
		textArea_1.setBounds(35, 274, 263, 68);

		Button button = new Button("Send");
		button.setForeground(new Color(255, 255, 255));
		button.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN, 20));
		button.setBackground(new Color(100, 149, 237));
		button.setBounds(336, 286, 70, 37);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String text = textArea_1.getText();
				Library.Client l = new Library().new Client();
				l.listenForInput("CH:"+text);
			}
		});

		Button button_2 = new Button("Search");
		button_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		button_2.setBounds(370, 36, 70, 22);
		button_2.setBackground(new Color(100, 149, 237));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String search = textField.getText();
				list.removeAll();
				try {
					if (search.length() >= 2) {
						ArrayList<String> arr = View.Items_Search(search);
						if (arr.isEmpty()) {
							JOptionPane.setRootFrame(frmLibraryManagmentSystem);
							JOptionPane.showMessageDialog(list, "No item found");
						}
						for (String title : arr) {
							list.add(title);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		JPanel panel = new JPanel();
		panel.setBackground(new Color(92, 165, 195));
		panel.setBounds(0, -1, 170, 374);
		frmLibraryManagmentSystem.getContentPane().add(panel);

		// --------------------------- Menu ------------------------------------------//

		JMenuItem lblHome = new JMenuItem("Home");
		lblHome.setSelected(true);
		currentMenu = currentMenu == null ? lblHome : currentMenu;
		lblHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentMenu != lblHome) {
					panel_1.removeAll();
					panel_1.add(panel_2);
					panel_1.add(panel_3);
					currentMenu.setBackground(new Color(92, 165, 195));
					currentMenu = lblHome;
					currentMenu.setBackground(new Color(192, 165, 255));
				}
			}
		});
		lblHome.setBounds(10, 35, 160, 30);
		lblHome.setHorizontalAlignment(SwingConstants.LEFT);
		lblHome.setForeground(new Color(255, 255, 255));
		lblHome.setFont(new Font("Ebrima", Font.BOLD, 19));
		Image img = new ImageIcon(this.getClass().getResource("/home-icon.png")).getImage();
		panel.setLayout(null);
		lblHome.setIcon(new ImageIcon(img.getScaledInstance(30, 25, 5)));
		lblHome.setBackground(new Color(192, 165, 255));
		panel.add(lblHome);

		JMenuItem mntBrowse = new JMenuItem("Browse");
		mntBrowse.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
			}
		});

		mntBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentMenu != mntBrowse) {
					currentMenu.setBackground(new Color(92, 165, 195));
					currentMenu = mntBrowse;
					currentMenu.setBackground(new Color(192, 165, 255));
					panel_1.removeAll();
					panel_1.add(panel_2);
					panel_1.add(button_2);
					panel_1.add(textField);
					panel_1.add(choice);
					panel_1.add(list);
					panel_1.add(label_1);
					panel_1.add(list_1);
					panel_1.add(panel_4);
				}
			}
		});

		mntBrowse.setBounds(10, 143, 160, 30);
		mntBrowse.setForeground(Color.WHITE);
		mntBrowse.setFont(new Font("Ebrima", Font.BOLD, 19));
		mntBrowse.setBackground(new Color(92, 165, 195));
		Image img_4 = new ImageIcon(this.getClass().getResource("/browse.png")).getImage();
		panel.setLayout(null);
		mntBrowse.setIcon(new ImageIcon(img_4.getScaledInstance(28, 25, 5)));
		panel.add(mntBrowse);
		panel.setLayout(null);

		JMenuItem mntmChats = new JMenuItem("Chat Room");
		mntmChats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentMenu != mntmChats) {
					currentMenu.setBackground(new Color(92, 165, 195));
					currentMenu = mntmChats;
					panel_1.removeAll();
					panel_1.add(button);
					panel_1.add(textArea);
					panel_1.add(textArea_1);
					currentMenu.setBackground(new Color(192, 165, 255));
				}
			}
		});
		
		mntmChats.setBounds(10, 85, 160, 30);
		mntmChats.setForeground(new Color(255, 255, 255));
		mntmChats.setBackground(new Color(92, 165, 195));
		mntmChats.setFont(new Font("Ebrima", Font.BOLD, 19));
		Image img_1 = new ImageIcon(this.getClass().getResource("/chat-btn.png")).getImage();
		panel.setLayout(null);
		mntmChats.setIcon(new ImageIcon(img_1.getScaledInstance(30, 28, 5)));
		panel.add(mntmChats);

		JMenuItem mntmReturnItem = new JMenuItem("Return Item");
		mntmReturnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentMenu != mntmReturnItem) {
					panel_1.removeAll();
					currentMenu.setBackground(new Color(92, 165, 195));
					currentMenu = mntmReturnItem;
					currentMenu.setBackground(new Color(192, 165, 255));
					panel_1.add(checkbox);
					panel_1.add(choice_1);
					panel_1.add(list_2);
					panel_1.add(label);
					panel_1.add(button_1);

				}
			}
		});

		mntmReturnItem.setBounds(10, 195, 160, 30);
		mntmReturnItem.setForeground(Color.WHITE);
		mntmReturnItem.setFont(new Font("Ebrima", Font.BOLD, 19));
		mntmReturnItem.setBackground(new Color(92, 165, 195));
		Image img_2 = new ImageIcon(this.getClass().getResource("/return ico.png")).getImage();
		panel.setLayout(null);
		mntmReturnItem.setIcon(new ImageIcon(img_2.getScaledInstance(25, 25, 5)));
		panel.add(mntmReturnItem);

		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentMenu != mntmHelp) {
					panel_1.removeAll();
					currentMenu.setBackground(new Color(92, 165, 195));
					currentMenu = mntmHelp;
					currentMenu.setBackground(new Color(192, 165, 255));
				}
			}
		});

		mntmHelp.setBounds(10, 246, 160, 30);
		mntmHelp.setForeground(Color.WHITE);
		mntmHelp.setFont(new Font("Ebrima", Font.BOLD, 19));
		mntmHelp.setBackground(new Color(92, 165, 195));
		Image img_5 = new ImageIcon(this.getClass().getResource("/Helps.png")).getImage();
		panel.setLayout(null);
		mntmHelp.setIcon(new ImageIcon(img_5.getScaledInstance(28, 25, 5)));
		panel.add(mntmHelp);

	}

	public void setUser(String name) {
		try {
			FileWriter file = new FileWriter("user.txt");
			file.write(name + "\n");
			file.write(name);
			file.close();
		} catch (Exception e) {
			System.out.println("Error while writing ");
		}
	}

	public void AutoComplete(String c) {
		String[] i;
		try {
			i = ReadText.whoIsUser(c);
			if (i != null) {
				textField_1.setText(i[0]);
				passwordField.setText(i[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	class ClientConnection extends Thread {
		Socket socket;
		DataInputStream din;
		DataOutputStream dout;

		public ClientConnection(Socket socket, Client client) {
			this.socket = socket;
		}

		public void sendStringtoServer(String text) {
			try {
				dout = new DataOutputStream(socket.getOutputStream());
				dout.writeUTF(text);
				dout.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void run() {
			try {
				din = new DataInputStream(socket.getInputStream());
				dout = new DataOutputStream(socket.getOutputStream());
				while (true) {
					try {
						while (din.available() == 0) {
							Thread.sleep(1);
						}
						String reply = din.readUTF();
						textArea.append(reply+"\n");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void close() {
			try {
				socket.close();
				din.close();
				dout.close();
			} catch (Exception e) {

			}
		}
	}

	public class Client extends Thread{
		Socket s;
		ClientConnection cc;

		public Client() {
			try {
				Socket s = new Socket("localhost", 3333);
				cc = new ClientConnection(s, this);
				cc.start();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "The server is closed. So some funcitionality wouldn't work");
			}
		}
		public void listenForInput(String input) {
			cc.sendStringtoServer(input);
		}
	}

}
