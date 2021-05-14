package common;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	static Connection con = null;

	public static  Connection getConnection(){

		if(con==null){
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");

				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
			}
			catch (Exception e)
			{e.printStackTrace();}
		}

		return con;
	}
}
