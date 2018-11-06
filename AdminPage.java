package project;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import project.Database_Handler.AddItems;
import project.Database_Handler.DeleteItems;
import project.Database_Handler.View;

public class AdminPage {
	JMenuItem currentMenu = null;
	private JFrame frmLibraryManagmentSystem;
	private JTable table;
	private JTable table_1;
	private String usr;
	public TextArea textArea;
	static AdminPage window;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						
						new CheckFiles();
						if (ReadText.ReadSession() == null) {
							new Login();
						}
						if (ReadText.ReadSession() != null) {
							window = new AdminPage();	
							window.frmLibraryManagmentSystem.setVisible(true);
						}
					} catch (FileNotFoundException e) {
						FileWriter file = new FileWriter("Session.txt");
						file.close();
						JOptionPane.showMessageDialog(null, "Please Login first");
						new Login();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				window.new Server().start();
			}
		});
		
	}

	public AdminPage() throws Exception {
		initialize();
	}

	private void initialize() {
		frmLibraryManagmentSystem = new JFrame();
		frmLibraryManagmentSystem.setType(Type.UTILITY);
		frmLibraryManagmentSystem.setTitle("Library Managment system - ADMIN");
		frmLibraryManagmentSystem.setResizable(false);
		frmLibraryManagmentSystem.getContentPane().setFont(new Font("Apple Casual", Font.PLAIN, 11));
		frmLibraryManagmentSystem.getContentPane().setBackground(new Color(135, 206, 235));
		frmLibraryManagmentSystem.setBounds(100, 100, 746, 400);
		frmLibraryManagmentSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLibraryManagmentSystem.getContentPane().setLayout(null);
		frmLibraryManagmentSystem.setVisible(true);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(171, -1, 569, 372);
		frmLibraryManagmentSystem.getContentPane().add(panel_1);

		Choice itemType = new Choice();
		itemType.setFont(new Font("Eras Demi ITC", Font.PLAIN, 15));
		itemType.add("Books");
		itemType.add("Digital Media");
		itemType.add("Article");
		itemType.select(0);
		itemType.setBounds(250, 54, 145, 24);
		panel_1.setLayout(null);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 64, 23);
		panel_2.setForeground(new Color(255, 250, 250));
		panel_2.setBorder(null);
		panel_2.setBackground(new Color(255, 140, 0));
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblKenedy = new JLabel("Kenedy");
		lblKenedy.setForeground(new Color(255, 250, 250));
		lblKenedy.setFont(new Font("Perpetua Titling MT", Font.BOLD, 12));
		lblKenedy.setBounds(4, 6, 60, 14);
		panel_2.add(lblKenedy);
		
		Button logout = new Button("Logout");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					FileWriter fw = new FileWriter("Session.txt");
					fw.close();
					frmLibraryManagmentSystem.dispose();
					new Login();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		logout.setForeground(new Color(255, 255, 255));
		logout.setBackground(new Color(255, 0, 0));
		logout.setFont(new Font("Apple Casual", Font.PLAIN, 12));
		logout.setBounds(490, 0, 70, 22);
		panel_1.add(logout);
				Panel newPanel2 = new Panel();
		newPanel2.setBounds(0, 44, 573, 328);
		newPanel2.setLayout(null);

		
		textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setBounds(35, 53, 380, 192);

		TextArea textArea_1 = new TextArea();
		textArea_1.setBounds(35, 274, 263, 68);

		Button button = new Button("Send");
		button.setForeground(new Color(255, 255, 255));
		button.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN, 20));
		button.setBackground(new Color(100, 149, 237));
		button.setBounds(336, 286, 70, 37);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(92, 165, 195));
		panel.setBounds(0, -1, 170, 374);
		frmLibraryManagmentSystem.getContentPane().add(panel);

		// --- add items

		JLabel lblItemType = new JLabel("Item Type");
		lblItemType.setForeground(new Color(139, 0, 139));
		lblItemType.setFont(new Font("Tw Cen MT", Font.BOLD, 17));
		lblItemType.setBounds(119, 55, 82, 24);

		JLabel lblTitleOfItem = new JLabel("Title of Item");
		lblTitleOfItem.setForeground(new Color(139, 0, 139));
		lblTitleOfItem.setFont(new Font("Tw Cen MT", Font.BOLD, 17));
		lblTitleOfItem.setBounds(119, 93, 82, 24);

		TextField titleItem = new TextField();
		titleItem.setBounds(249, 93, 146, 22);

		TextField forWhom = new TextField();
		forWhom.setBounds(250, 128, 145, 22);

		JLabel lblWhoCanUse = new JLabel("For whom");
		lblWhoCanUse.setForeground(new Color(139, 0, 139));
		lblWhoCanUse.setFont(new Font("Tw Cen MT", Font.BOLD, 17));
		lblWhoCanUse.setBounds(119, 128, 82, 24);

		TextField authorName = new TextField();
		authorName.setBounds(250, 196, 145, 22);

		JLabel lblAuthorName = new JLabel("Author Name");
		lblAuthorName.setForeground(new Color(139, 0, 139));
		lblAuthorName.setFont(new Font("Tw Cen MT", Font.BOLD, 17));
		lblAuthorName.setBounds(119, 196, 95, 24);

		JLabel copies = new JLabel("No. Copies");
		copies.setForeground(new Color(139, 0, 139));
		copies.setFont(new Font("Tw Cen MT", Font.BOLD, 17));
		copies.setBounds(118, 231, 95, 24);

		Choice noCopies = new Choice();
		noCopies.setBounds(250, 235, 37, 20);
		for (int i = 1; i <= 15; i++) {
			noCopies.add(i + "");
		}
		Choice Year = new Choice();
		Year.setBounds(250, 165, 52, 20);
		for (int i = 2018; i > 1980; i--) {
			Year.add(i + "");
		}

		Choice Month = new Choice();
		Month.setBounds(312, 165, 37, 20);
		for (int i = 1; i <= 12; i++) {
			Month.add(i + "");
		}

		Choice Day = new Choice();
		Day.setBounds(356, 165, 39, 20);
		for (int i = 1; i <= 30; i++) {
			Day.add(i + "");
		}

		JLabel lblPublishedDate = new JLabel("Published Date");
		lblPublishedDate.setForeground(new Color(139, 0, 139));
		lblPublishedDate.setFont(new Font("Tw Cen MT", Font.BOLD, 17));
		lblPublishedDate.setBounds(119, 162, 110, 24);

		Button button_2 = new Button("Submit");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String type = itemType.getSelectedItem();
				String title = titleItem.getText();
				String fw = forWhom.getText();
				String Date = Year.getSelectedItem() + "-" + Month.getSelectedItem() + "-" + Day.getSelectedItem();
				String auth = authorName.getText();
				String cp = noCopies.getSelectedItem();
				String[] add = new String[] { type, title, fw, auth, Date, cp };
				try {
					AddItems.Add(add);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		button_2.setForeground(Color.WHITE);
		button_2.setBackground(new Color(0, 191, 255));
		button_2.setFont(new Font("Century Gothic", Font.BOLD, 15));
		button_2.setBounds(195, 276, 85, 38);


		// --------------------------- Menu ------------------------------------------//
		JMenuItem lblHome = new JMenuItem("Home");
		lblHome.setSelected(true);
		currentMenu = currentMenu == null ? lblHome : currentMenu;
		lblHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentMenu != lblHome) {
					panel_1.removeAll();
					panel_1.add(panel_2);
					currentMenu.setBackground(new Color(92, 165, 195));
					currentMenu = lblHome;
					currentMenu.setBackground(new Color(192, 165, 255));
					panel_1.add(logout);
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

		JMenuItem mntBrowse = new JMenuItem("Add Items");
		mntBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentMenu != mntBrowse) {
					panel_1.removeAll();
					currentMenu.setBackground(new Color(92, 165, 195));
					currentMenu = mntBrowse;
					currentMenu.setBackground(new Color(192, 165, 255));
					panel_1.add(panel_2);
					panel_1.add(itemType);
					panel_1.add(lblItemType);
					panel_1.add(lblTitleOfItem);
					panel_1.add(lblWhoCanUse);
					panel_1.add(titleItem);
					panel_1.add(forWhom);
					panel_1.add(copies);
					panel_1.add(authorName);
					panel_1.add(lblAuthorName);
					panel_1.add(noCopies);
					panel_1.add(lblPublishedDate);
					panel_1.add(button_2);
					panel_1.add(Year);
					panel_1.add(Month);
					panel_1.add(Day);
				}
			}
		});

		mntBrowse.setBounds(10, 135, 160, 30);
		mntBrowse.setForeground(Color.WHITE);
		mntBrowse.setFont(new Font("Ebrima", Font.BOLD, 19));
		mntBrowse.setBackground(new Color(92, 165, 195));
		Image img_4 = new ImageIcon(this.getClass().getResource("/plus.png")).getImage();
		panel.setLayout(null);
		mntBrowse.setIcon(new ImageIcon(img_4.getScaledInstance(27, 25, 5)));
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
		Image img_1 = new ImageIcon(this.getClass().getResource("/chats.png")).getImage();
		panel.setLayout(null);
		mntmChats.setIcon(new ImageIcon(img_1.getScaledInstance(30, 28, 5)));
		panel.add(mntmChats);

		JMenuItem mntmReturnItem = new JMenuItem("List of Items");
		mntmReturnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentMenu != mntmReturnItem) {
					panel_1.removeAll();
					frmLibraryManagmentSystem.getContentPane().add(panel_1);
					currentMenu.setBackground(new Color(92, 165, 195));
					currentMenu = mntmReturnItem;
					currentMenu.setBackground(new Color(192, 165, 255));
					
					table_1 = new JTable();
					table_1.setShowGrid(false);
					table_1.setShowVerticalLines(false);
					table_1.setShowHorizontalLines(false);
					table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table_1.setBounds(2, 0, 563, 267);
					table_1.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null }, },
							new String[] { "ITEM ID", "CATAGORY", "TITLE", "SUGGESTION", "AUTHOR", "PUBLISHED", "COPIES",
									"RATE" }));
					newPanel2.add(table_1);
					ArrayList<String> itemTable = View.ItemsTables();
					while (table_1.getRowCount() > 0) {
						((DefaultTableModel) table_1.getModel()).removeRow(0);
					}
					Object[] title = { "ITEM ID", "CATAGORY", "TITLE", "SUGGESTION", "AUTHOR", "PUBLISHED", "COPIES", "RATE" };
					((DefaultTableModel) table_1.getModel()).insertRow(0, title);
					for (String s : itemTable) {
						String[] word = s.split(":");
						int length = word.length;
						Object[] rows = new Object[length];
						for (int i = 1; i <= length; i++) {

							rows[i - 1] = word[i - 1];
						}
						((DefaultTableModel) table_1.getModel()).insertRow(1, rows);
					}

					panel_1.add(newPanel2);
				}
			}
		});
		
		mntmReturnItem.setBounds(10, 181, 160, 30);
		mntmReturnItem.setForeground(Color.WHITE);
		mntmReturnItem.setFont(new Font("Ebrima", Font.BOLD, 19));
		mntmReturnItem.setBackground(new Color(92, 165, 195));
		Image img_2 = new ImageIcon(this.getClass().getResource("/home-icon.png")).getImage();
		panel.setLayout(null);
		mntmReturnItem.setIcon(new ImageIcon(img_2.getScaledInstance(30, 25, 5)));
		panel.add(mntmReturnItem);

		Button button_1 = new Button("Remove");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table_1.getSelectedRow() != -1) {
					String Selected_ID = table_1.getValueAt(table_1.getSelectedRow(), 0).toString();
					String item = table_1.getValueAt(table_1.getSelectedRow(), 2).toString();
					((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
					DeleteItems.Items(Selected_ID);
					JOptionPane.showMessageDialog(null,
							"You have succesfully removed " + item);
				} else {
					JOptionPane.showMessageDialog(null, "Please select atleast 1 item");
				}
			}
		});
		button_1.setBackground(new Color(100, 149, 237));
		button_1.setForeground(new Color(255, 255, 255));
		button_1.setFont(new Font("Felix Titling", Font.BOLD, 15));
		button_1.setBounds(223, 282, 97, 22);
		newPanel2.add(button_1);
		

		JMenuItem mntmHelp = new JMenuItem("List of Users");
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentMenu != mntmHelp) {
					panel_1.removeAll();
					currentMenu.setBackground(new Color(92, 165, 195));
					currentMenu = mntmHelp;
					currentMenu.setBackground(new Color(192, 165, 255));
					Panel newPanel = new Panel();
					newPanel.setBounds(0, 42, 481, 330);
					newPanel.setLayout(null);
					table = new JTable();
					table.setShowVerticalLines(false);
					table.setShowHorizontalLines(false);
					table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
							new String[] { "User ID", "User Name", "New column", "New column", "New column" }));
					newPanel.add(table);
					ArrayList<String> arr = View.CustomerTables();
					
					table.setBounds(54, 0, 417, 266);
					while (table.getRowCount() > 0) {
						((DefaultTableModel) table.getModel()).removeRow(0);
					}
					Object[] r = { "Customer Id", "First Name", "Last Name", "Email", "Department" };
					((DefaultTableModel) table.getModel()).insertRow(0, r);
					for (String s : arr) {
						String[] word = s.split(":");
						int length = word.length;
						Object[] rows = new Object[length];
						for (int i = 1; i <= length; i++) {
							rows[i - 1] = word[i - 1];
						}
						((DefaultTableModel) table.getModel()).insertRow(1, rows);
					}

					Button button_3 = new Button("Remove");
					button_3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if (table.getSelectedRow() != -1) {
								String Selected_ID = table.getValueAt(table.getSelectedRow(), 0).toString();
								String item = table.getValueAt(table.getSelectedRow(), 1).toString();
								((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
								DeleteItems.Users(Selected_ID);
								JOptionPane.showMessageDialog(null,
										"You have succesfully removed " + item);
							} else {
								JOptionPane.showMessageDialog(null, "Please select atleast 1 item");
							}
						}
					});
					button_3.setFont(new Font("Felix Titling", Font.BOLD, 15));
					button_3.setForeground(new Color(255, 255, 255));
					button_3.setBackground(new Color(100, 149, 237));
					button_3.setBounds(241, 284, 87, 26);
					newPanel.add(button_3);
					panel_1.add(newPanel);
				}
			}
		});


		mntmHelp.setBounds(10, 227, 160, 30);
		mntmHelp.setForeground(Color.WHITE);
		mntmHelp.setFont(new Font("Ebrima", Font.BOLD, 19));
		mntmHelp.setBackground(new Color(92, 165, 195));
		Image img_5 = new ImageIcon(this.getClass().getResource("/home-icon.png")).getImage();
		panel.setLayout(null);
		mntmHelp.setIcon(new ImageIcon(img_5.getScaledInstance(30, 25, 5)));
		panel.add(mntmHelp);

		JMenuItem menuItem = new JMenuItem("Help");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentMenu != menuItem) {
					panel_1.removeAll();
					currentMenu.setBackground(new Color(92, 165, 195));
					currentMenu = menuItem;
					currentMenu.setBackground(new Color(192, 165, 255));
				}
			}
		});
		menuItem.setForeground(Color.WHITE);
		menuItem.setFont(new Font("Ebrima", Font.BOLD, 19));
		menuItem.setBackground(new Color(92, 165, 195));
		Image img_6 = new ImageIcon(this.getClass().getResource("/home-icon.png")).getImage();
		panel.setLayout(null);
		menuItem.setIcon(new ImageIcon(img_6.getScaledInstance(30, 25, 5)));
		menuItem.setBounds(10, 273, 160, 30);
		panel.add(menuItem);

	}
	
	/// -------------------------------------For chat room for server---------------------------- ///
	class ServerConnection extends Thread {
		Server server;
		Socket socket;
		DataInputStream din;
		DataOutputStream dout;

		public ServerConnection(Socket socket, Server server) {
			super("serverConnectionThread");
			this.socket = socket;
			this.server = server;
		}

		public void SendStringToClient(String text) {
			try {
				dout.writeUTF(text);
				dout.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void SendStringToAllClient(String text) {
			for(ServerConnection sc : server.connections) {
				sc.SendStringToClient(text);
			}
		}

		public void run() {
			try {
				din = new DataInputStream(socket.getInputStream());
				dout = new DataOutputStream(socket.getOutputStream());
				while (true) {
					while (din.available() == 0) {
						Thread.sleep(1);
					}
					String textIn = din.readUTF();
					textArea.append("message arrived ::"+textIn+" \n");
					SendStringToAllClient(textIn);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public void CheckCommand(String c) {
			String[] comm = c.split(":");
			if (comm[0].equals("CH")) {

			}if (comm[0].equals("BI")) {
				
			}if (comm[0].equals("CH")) {
				
			}if (comm[0].equals("CH")) {
				
			}if (comm[0].equals("CH")) {
				
			}if (comm[0].equals("CH")) {
				
			}if (comm[0].equals("CH")) {
				
			}
		}
	}

	public class Server extends Thread{
		ArrayList<ServerConnection> connections = new ArrayList<>();
		public synchronized void run() {
		ServerSocket ss;
			try {
				ss = new ServerSocket(3333);
				while (true) {
					Socket s = ss.accept();
					ServerConnection sc = new ServerConnection(s, this);
					sc.start();
					if(!connections.contains(sc)) {
						connections.add(sc) ;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//EXPERIMENTAL 

}
//let me try to dynamically assign the ip address of our server 
//how can I do this I will scan the entire network with specific port and get the ip address with specific port
//are you mad? it needs huge time to do this
//we will decrease the time by scanning ip address by guessing from the our ipaddress