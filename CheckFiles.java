package project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import project.Database_Handler.CreateDatabaseTables;

public class CheckFiles {
	public CheckFiles() throws IOException {
		new CreateDatabaseTables();
		File sessionDir = new File("Session.txt");
		File usersDir = new File("user.txt");
		boolean user_exists = usersDir.exists();
		boolean Session_exists = sessionDir.exists();
		if (!Session_exists) {
			FileWriter writeSession = new FileWriter("Session.txt");
			writeSession.close();
		}
		if (!user_exists) {
			FileWriter writeSession = new FileWriter("user.txt");
			writeSession.close();
		}
	}
}
