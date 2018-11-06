package project.Database_Handler;

import java.sql.*;
import java.util.Scanner;

public class DeleteItems {
	public static void Items(String ID) {
		try {
			Connection con = Connect_Database.INISTANCE.Connect();
			String sql2 = "delete from items_list where Item_id = ?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, ID);
			ps2.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void Users(String ID) {
		try {
			Connection con = Connect_Database.INISTANCE.Connect();
			String sql2 = "delete from customer_list where customer_id = ?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, ID);
			ps2.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
