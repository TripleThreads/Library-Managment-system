package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import project.Database_Handler.Connect_Database;

public class ReadText {
	static public String[] whoIsUser(String c) throws IOException {
		String name = null;
		String[] inputs = null;
		try {
			File file = new File("user.txt");
			FileReader fr = new FileReader(file);
			Scanner scan = new Scanner(fr);
			while (scan.hasNext()) {
				name = scan.next();
				if (name == null) {
					return null;
				}
				if (name.contains(c)) {
					inputs = name.split(":");
				}
			}
			scan.close();
		} catch (Exception e) {
			FileWriter file = new FileWriter("user.txt");
			file.close();
		}
		return inputs;
	}

	static public String ReadSession() throws IOException {
		File file = new File("Session.txt");
		Scanner scan;
		String ID = null;
		try {
			scan = new Scanner(file);
			if (scan.hasNext()) {
				String[] id = scan.next().split(":");
				ID = id[1];
				scan.close();
			} else {
				ID = null;
			}
		} catch (FileNotFoundException e) {
			FileWriter f = new FileWriter("Session.txt");
			f.close();
			JOptionPane.showMessageDialog(null, "Please login and try again");
		}
		return ID;
	}

	public static ArrayList<String> suggest() {
		Connection con = Connect_Database.INISTANCE.Connect();
		ArrayList<String> arr = new ArrayList<>();
		try {
			String customer_id = ReadText.ReadSession();
			String Department = null;
			// --------let us check department of our user using its its id ----//
			String s = "select * from customer_list where customer_id = ?";
			PreparedStatement p = con.prepareStatement(s);
			p.setString(1, customer_id);
			ResultSet r = p.executeQuery();
			if (r.next()) {
				Department = r.getString("department");
			}

			// then let us look for similar departments on our text file
			FileReader file = new FileReader("dep.txt");
			BufferedReader br  = new BufferedReader(file);
			String[] dept = null;
			while (file.ready()) {
				String d = br.readLine().toLowerCase();
				if (d.contains(Department.toLowerCase())) {
					dept = d.split(":");
					break;
				}
			}
			br.close();
			String sql = "select title from items_list where Suggestion = ?";
			PreparedStatement pr = con.prepareStatement(sql);
			for (String d : dept) {
				pr.setString(1, d);
				ResultSet res = pr.executeQuery();
				while (res.next()) {
					arr.add(res.getString("Title"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
}