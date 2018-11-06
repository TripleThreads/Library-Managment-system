package project.Database_Handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class View {
	static Connection con = Connect_Database.INISTANCE.Connect();

	public static void Users() throws SQLException, Exception {
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("select Fname, Lname from customer_list");
		while (rs.next()) {
			System.out.println(rs.getString("Fname") + " " + rs.getString("Lname"));
		}

	}

	public static ArrayList<String> Items_Search(String search) throws Exception {
		ArrayList<String> it = new ArrayList<>();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("select Title from items_list");
		search.trim().toLowerCase();
		String[] S = search.contains(" ") ? search.split(" ") : new String[] { search };
		while (rs.next()) {
			if(rs.getString("Title").toLowerCase().equals(search.toLowerCase())) {
				it.clear();
				it.add(rs.getString("Title"));
				return it;
			}
			for (String title : S) {
				title.trim();
				if (rs.getString("Title").toLowerCase().contains(title)) {
					if (it.contains(rs.getString("Title"))) {
						continue;
					}
					it.add(rs.getString("Title"));
				}
				else if (title.length() >= 3 && rs.getString("Title").toLowerCase().contains(title.toLowerCase().substring(0, title.length() - 1))) {
					it.add(rs.getString("Title"));
					}
			}
		}
		return it;
	}

	public static ArrayList<String> catagory(String catagory) {
		ArrayList<String> it = new ArrayList<>();
		try {
			String sql2 = "select Title from items_list where catagory = ?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, catagory);
			ResultSet rs = ps2.executeQuery();
			while (rs.next()) {
				it.add(rs.getString("Title"));
			}
			ps2.execute();

		} catch (Exception e) {
			System.out.println(e);
		}
		return it;
	}

	public static ArrayList<String> Description(String title) {
		ArrayList<String> it = new ArrayList<>();
		try {
			String sql2 = "select * from items_list where Title = ?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, title);
			ResultSet rs = ps2.executeQuery();
			while (rs.next()) {
				it.add(rs.getString("Author"));
				it.add(rs.getString("published_date"));
			}
			ps2.execute();

		} catch (Exception e) {
			System.out.println(e);
		}
		return it;
	}

	static public ArrayList<String> CustomerTables() {
		ArrayList<String> arr = new ArrayList<>();
		Statement s;
		try {
			s = con.createStatement();
			ResultSet rs = s.executeQuery("select * from customer_list");
			while (rs.next()) {
				arr.add(rs.getString("customer_id") + ":" + rs.getString("Fname") + ":" + rs.getString("Lname") + ":"
						+ rs.getString("Email") + ":" + rs.getString("department"));
			}
		} catch (SQLException e) {
			new CreateDatabaseTables();
			JOptionPane.showMessageDialog(null, "Something went wrong try Again");
		}
		return arr;
	}

	static public ArrayList<String> ItemsTables() {
		ArrayList<String> arr = new ArrayList<>();
		Statement s;
		try {
			s = con.createStatement();
			ResultSet rs1 = s.executeQuery("select * from items_list");
			while (rs1.next()) {
				arr.add(rs1.getString("Item_id") + ":" + rs1.getString("catagory") + ":" + rs1.getString("Title") + ":"
						+ rs1.getString("Suggestion") + ":" + rs1.getString("Author") + ":"
						+ rs1.getString("Published_date") + ":" + rs1.getString("copies") + ":"
						+ rs1.getString("rate"));
			}
		} catch (SQLException e) {
			new CreateDatabaseTables();
			JOptionPane.showMessageDialog(null, "Something went wrong try Again");
		}
		return arr;
	}

	static public ArrayList<String> Top_Rated() {
		ArrayList<String> title = new ArrayList<>();
		Statement s;
		try {
			s = con.createStatement();
			ResultSet rs1 = s.executeQuery("select * from items_list order by rate desc");
			int i = 0;
			while (rs1.next() && i <=5) {
				title.add(rs1.getString("Title")+" : "+rs1.getDouble("rate"));
				i++;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error occured while browsing top rated");
		}
		return title;
	}
}