package project.Database_Handler;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import project.AdminPage;
import project.Library;

public class LoginExcute {
	public LoginExcute(String name, String pwd) {
		Connection con = Connect_Database.INISTANCE.Connect();
		try {
			String s = "select * from customer_list where Fname = ? and password = ?";
			PreparedStatement p = con.prepareStatement(s);
			p.setString(1, name);
			p.setString(2, pwd);
			ResultSet r = p.executeQuery();
			if (r.next()) {
				setSession(r.getInt("customer_id"));
				if(r.getString("Fname").equals("admin")) {
					new AdminPage();
				}else {
					new Library();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Wrong Input");
			}
		} catch (Exception e) {

		}
	}

	public void setSession(int cid) {
		try {
			FileWriter file = new FileWriter("Session.txt");
			file.write("ID:" + cid);
			file.close();
		} catch (Exception e) {
			System.out.println("Error while writing ");
		}
	}
}
