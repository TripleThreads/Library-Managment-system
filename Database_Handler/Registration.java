package project.Database_Handler;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Registration {
	static private String[] credent;
	public Registration(String credential[]) {
		Connection con = Connect_Database.INISTANCE.Connect();
		credent = credential;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String sql = "insert into customer_list " + "(Fname, Lname, email , password, Security_Q, Security_A , department)"
					+ "values(?,?,?,?,?,?,?)";
			
			PreparedStatement ps = con.prepareStatement(sql);
			for (int i = 1; i < 8; i++) {
				ps.setString(i, credent[i - 1]);
			}
			ps.execute();
			JOptionPane.showMessageDialog(null, "Congratulations!!! You have joined our library");

			String a = "select customer_id , password from customer_list where Fname = ? and Lname = ?";
			PreparedStatement pss = con.prepareStatement(a);
			pss.setString(1, credent[0]);
			pss.setString(2, credent[1]);
			ResultSet rs3 = pss.executeQuery();
			if (rs3.next()) {
				int cid = rs3.getInt("customer_id");
				setSession(cid);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Something went wrong");
		}
	}
	public void setSession(int cid) {
		try {
			FileWriter file = new FileWriter("Session.txt");
			file.write("ID:"+cid);
			file.close();
		} catch (Exception e) {
			System.out.println("Error while writing ");
		}
	}
}
