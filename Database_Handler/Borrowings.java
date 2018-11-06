package project.Database_Handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import project.ReadText;

public class Borrowings {
	static Connection con = Connect_Database.INISTANCE.Connect();

	public static void Borrow(String Title) throws Exception {

		try {
			String customer_id = ReadText.ReadSession();
			String Item_id = null;
			int copy = 0;
			
			//--------let us find the id of item using its name ----//
			String s = "select * from items_list where Title = ?";
			PreparedStatement p = con.prepareStatement(s);
			p.setString(1, Title);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				Item_id = r.getString("Item_id");
				copy = Integer.parseInt(r.getString("copies"));
			}
			
			//-----------------------------------------------------//
			String sql = "insert into borrowed_items "
					+ "(Fname, Lname, Item_title, Item_id, customer_id, Borrowing_date, Returning_date)"
					+ "values(?,?,?,?,?,Now(),DATE_ADD(NOW(), INTERVAL 10 DAY))";

			PreparedStatement ps = con.prepareStatement(sql);
			String sql2 = "select Fname,Lname from customer_list where customer_id = ?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, customer_id);
			ResultSet rs4 = null;
			rs4 = ps2.executeQuery();
			if (rs4.next()) {
				String Fname = rs4.getString("Fname");
				String Lname = rs4.getString("Lname");
				ps.setString(1, Fname);
				ps.setString(2, Lname);
			}
			ps.setString(3, Title);
			ps.setString(4, Item_id);
			ps.setString(5, customer_id);
			if (copy > 0) {
				if (!isBorrowed(customer_id,Item_id)) {
					ps.execute();
				}else {
					JOptionPane.showMessageDialog(null, "You have already borrowed this item");
				}
			} else {
				JOptionPane.showMessageDialog(null, "No Book left on the shelf");
			}
			// LET US DECRMENT FROM ITEM LIST

			String sql6 = "select copies from items_list where Title = ?";
			PreparedStatement ps5 = con.prepareStatement(sql6);
			ps5.setString(1, Title);
			ResultSet rs3 = ps5.executeQuery();
			if (rs3.next()) {
				String str3 = rs3.getString("copies");
				if (rs3.getInt("copies") > 0) {
					int a = Integer.parseInt(str3) - 1;
					String sql1 = "update items_list set copies = ? where Item_id = ?";
					PreparedStatement ps1 = con.prepareStatement(sql1);
					ps1.setInt(1, a);
					ps1.setString(2, Item_id);
					ps1.executeUpdate();

					JOptionPane.showMessageDialog(null, "Thank you for using our service");
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	static public boolean isBorrowed(String customer_id, String Item_id) {
		String s = "select * from borrowed_items where customer_id =  ? and Item_id = ?";
		PreparedStatement p;
		boolean hasBorrowed = false;
		try {
			p = con.prepareStatement(s);
			p.setString(1, customer_id);
			p.setString(2, Item_id);
			ResultSet r = p.executeQuery();
			if (r.next()) {
				hasBorrowed = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hasBorrowed;
	}
}