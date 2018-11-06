package project.Database_Handler;

import java.sql.Connection;
import java.sql.Statement;

public class CreateDatabaseTables {
	public CreateDatabaseTables() {
		try{
			Connection con = Connect_Database.INISTANCE.Connect();
			Statement st = con.createStatement();
			
			String sql1 = "create table if not exists Customer_list("
					  +"customer_id int(6) auto_increment primary key,"
					  +"Fname varchar(20) not null,"
					  +"Lname varchar(20) not null,"
					  +"Email varchar(255) not null,"
					  +"password varchar(8) not null,"
					  +"Security_Q varchar(20) not null,"
					  +"Security_A varchar(20) not null,"
					  +"department varchar(255) not null);";
			st.execute(sql1);
			
			String sql2 = "create table if not exists Items_list("
					  +"Item_id int(6) auto_increment primary key,"
					  +"Catagory varchar(20) not null,"
					  +"Title varchar(30) not null,"
					  +"Suggestion varchar(255) default 'all',"
					  +"Author Varchar(255),"
					  +"published_date date not null,"
					  +"copies int(4) default '0',"
					  +"rate decimal(2,1) default '0.00',"
					  +"image LONGBLOB);";
					  
			st.execute(sql2);
			
			String sql3 = "create table if not exists Borrowed_items("
					  +"Fname varchar(20) not null,"
					  +"Lname varchar(20) not null,"
					  +"Item_title varchar(255) not null,"
					  +"Item_id int(10),"
					  +"customer_id int(10) not null,"
					  +"Borrowing_date date not null,"
					  +"Returning_date date not null,"
					  +"fee int(4) default '0.00');";
			st.execute(sql3);
			}
			catch(Exception e){
				System.out.println(e);
			}
	}
}