package project.Database_Handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import project.ReadText;

public class ReturnItems {
	static Connection con = Connect_Database.INISTANCE.Connect();

	static public ArrayList<String> Borrowed() {
		ArrayList<String> items = new ArrayList<>();
		String customer_id;
		try {
			customer_id = ReadText.ReadSession();
			String s = "select * from borrowed_items where customer_id = ?";
			PreparedStatement p = con.prepareStatement(s);
			p.setString(1, customer_id);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				items.add(r.getString("Item_title"));
			}
		} catch (Exception e) {

		}
		return items;
	}

	public static void Return(String Title) throws Exception {
		try {
			String customer_id = ReadText.ReadSession();
			String Item_id = null;
			// ...........................................................................................................................

			// --------let us find the id of item using its name ----//
			String s = "select * from items_list where Title = ?";
			PreparedStatement p = con.prepareStatement(s);
			p.setString(1, Title);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				Item_id = r.getString("Item_id");
			}

			String ss = "select Item_title from Borrowed_items where Item_id = ? ";
			PreparedStatement pp = con.prepareStatement(ss);
			pp.setString(1, Item_id);
			ResultSet rr = pp.executeQuery();

			String sql6 = "select copies from items_list where Item_id = ? ";
			PreparedStatement ps5 = con.prepareStatement(sql6);
			ps5.setString(1, Item_id);

			ResultSet rs3 = ps5.executeQuery();
			if (rs3.next() && rr.next()) {
				String str3 = rs3.getString("copies");
				int a = Integer.parseInt(str3) + 1;

				String sql1 = "update items_list set copies = ? where Item_id = ?";
				PreparedStatement ps1 = con.prepareStatement(sql1);
				ps1.setInt(1, a);
				ps1.setString(2, Item_id);
				ps1.executeUpdate();

				String sql2 = "delete from Borrowed_items  where customer_id = ? and Item_id = ?";
				PreparedStatement ps2 = con.prepareStatement(sql2);
				ps2.setString(1, customer_id);
				ps2.setString(2, Item_id);
				ps2.executeUpdate();
			} else {
				JOptionPane.showMessageDialog(null,"You have returned all books with this name");
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	static public void Rate(String Title, int rate) {
		// .............................................................................................................................
		try {
			String sql7 = "select rate from items_list where Title = ? ";
			PreparedStatement ps6 = con.prepareStatement(sql7);
			ps6.setString(1, Title);
			ResultSet rs4 = ps6.executeQuery();
			if (rs4.next()&& rs4.getDouble("rate") != 0) {
				String str4 = rs4.getString("rate");
				double r1 = (Integer.parseInt(str4) + rate) / 2;
				String sql01 = "update items_list set rate = ? where Title = ?";
				PreparedStatement ps7 = con.prepareStatement(sql01);
				ps7.setDouble(1, r1);
				ps7.setString(2, Title);
				ps7.executeUpdate();
				JOptionPane.showMessageDialog(null,"Thank you for your support.");
			}else {
				String sql01 = "update items_list set rate = ? where Title = ?";
				PreparedStatement ps7 = con.prepareStatement(sql01);
				ps7.setDouble(1, rate);
				ps7.setString(2, Title);
				ps7.executeUpdate();
			}
			ps6.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error occured while rating "+Title);
		}
	}
}
