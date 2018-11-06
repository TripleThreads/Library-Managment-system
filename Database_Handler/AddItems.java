package project.Database_Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class AddItems {
	static public void Add(String added_item[]) throws Exception {
		try {
			Connection con = Connect_Database.INISTANCE.Connect();
			String sql = "insert into items_list " + "(catagory, Title, suggestion, Author, published_date, copies)"
					+ "values(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			for (int i = 1; i < 7; i++) {
				ps.setString(i, added_item[i - 1]);
			}
			ps.execute();
			JOptionPane.showMessageDialog(null, added_item[1]+" is succesfully added");
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}