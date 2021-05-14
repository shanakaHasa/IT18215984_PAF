package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer {
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	}
	public String insertCustomer(String C_name, String C_type, String Added_date, String email, String password)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement
			
			String query = " insert into customers(`customerID`,`customerName`,`customerType`,`addedDate`,`email`,`password`)" + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, C_name);
			preparedStmt.setString(3, C_type);
			preparedStmt.setString(4, Added_date);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, password);
			
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String displayCustomerDetails()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Customer ID</th><th>Customer Name</th>" +
					"<th>Customer Type</th>" +
					"<th>Added Date</th>" +
					"<th>Customer Email</th>" +
					"<th>Update</th><th>Remove</th></tr>";
			
			
			
			String query = "select * from customers";
			Statement stmt = con.createStatement();
			ResultSet r = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (r.next())
			{
				
				String customerID = Integer.toString(r.getInt("customerID"));
				String customerName = r.getString("customerName");
				String customerType = r.getString("customerType");
				String addedDate = r.getString("addedDate");
				String email = r.getString("email");
				// Add into the html table
				output += "<tr><td>" + customerName + "</td>";
				output += "<td>" + customerType + "</td>";
				output += "<td>" + addedDate + "</td>";
				output += "<td>" + email + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
								+ "<td><form method='post' action='customers.jsp'>"
								+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
										+ "<input name='customerID' type='hidden' value='" + customerID
										+ "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the customers.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String updateCustomer(String ID, String name, String type, String date, String email, String password)

	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			// create a prepared statement
			String query = "UPDATE customers SET customerName=?,customerType=?,addedDate=?,email=?, password=? WHERE customerID=?";
							PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, type);
			preparedStmt.setString(3, date);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, password);
			preparedStmt.setInt(6, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String deleteCustomer(String customerId)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			// create a prepared statement
			String query = "delete from customers where customerId=?";
			PreparedStatement preparedSt = con.prepareStatement(query);
			// binding values
			preparedSt.setInt(1, Integer.parseInt(customerId));
			// execute the statement
			preparedSt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
}


