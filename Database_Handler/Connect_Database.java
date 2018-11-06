package project.Database_Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public enum Connect_Database {
	INISTANCE;
	public Connection Connect() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:6345/Library?autoReconnect=true&useSSL=false",
					"root", "root@mysql");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Cannot Create connection with host");
		}
		return con;
	}

}