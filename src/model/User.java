package model;

import java.sql.*;

//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;

public class User {
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
			{
				Class.forName("com.mysql.jdbc.Driver");
				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", "");
			}
		catch (Exception e)
			{
				e.printStackTrace();
			}
			return con;
	}
	
	public String insertUser(String code, String name, String pNumber, String gmail)
	{
	   String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
		{
			
			return "Error while connecting to the database for inserting."; 
		}
	
		  // create a prepared statement
			String query = " insert into users(`ID`,`userCode`,`userName`,`userPnumber`,`userGmail`)"+ " values (?, ?, ?, ?, ?)";
	        PreparedStatement preparedStmt = con.prepareStatement(query);
	       
	      // binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, pNumber);
			preparedStmt.setString(5, gmail);
	
		  // execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the user.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readUsers()
	{
		String output = "";
		  try
		  {
			  Connection con = connect();
			  if (con == null)
		  {	
			  return "Error while connecting to the database for reading."; 
	      }
			  
		  	// Prepare the html table to be displayed
			  output = "<table border='1'><tr><th>User Code</th><th>User Name</th>" +
					  "<th>User Phone number</th>" +
					  "<th>User Gmail</th>" +
					  "<th>Update</th><th>Remove</th></tr>";
			  String query = "select * from users";
			  Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery(query);
		
		   // iterate through the rows in the result set
		  
		  while (rs.next())
		  {
			  String ID = Integer.toString(rs.getInt("ID"));
				String userCode = rs.getString("userCode");
				String userName = rs.getString("userName");
				String userPnumber = rs.getString("userPnumber");
				String userGmail = rs.getString("userGmail");
				// Add into the html table
				output += "<tr><td>" + userCode + "</td>";
				output += "<td>" + userName + "</td>";
				output += "<td>" + userPnumber + "</td>";
				output += "<td>" + userGmail + "</td>";
				// buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
						 + "class='btnUpdate btn btn-secondary' data-id='" + ID + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' "
						 + "class='btnRemove btn btn-danger' data-id='" + ID + "'></td></tr>";
		  }
				con.close();
				// Complete the html table
				output += "</table>";
		  }
		  catch (Exception e)
		  {
				output = "Error while reading the users.";
				System.err.println(e.getMessage());
		  }
		return output;
	}
	
	public String updateUser(String ID, String code, String name, String Pnumber, String gmail)
	{
		String output = "";
		  try
		  {
			  Connection con = connect();
			  if (con == null)
		  {
			  return "Error while connecting to the database for updating."; 
		  }
			  
			  // create a prepared statement
				  String query = "UPDATE users SET userCode=?,userName=?,userPnumber=?,userGmail=? WHERE ID=?";
				  PreparedStatement preparedStmt = con.prepareStatement(query);
			  
			  // binding values
				preparedStmt.setString(1, code);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, Pnumber);
				preparedStmt.setString(4, gmail);
				preparedStmt.setInt(5, Integer.parseInt(ID));
			 
			  // execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
		  }
		  catch (Exception e)
		  {
				output = "Error while updating the user.";
				System.err.println(e.getMessage());
		  }
		return output;
	}
			
	public String deleteUser(String ID)
	{
		String output = "";
		  try
		  {
			  	Connection con = connect();
			  	if (con == null)
		  {
				return "Error while connecting to the database for deleting."; 
		  }
			  	
			// create a prepared statement
			  	String query = "delete from users where ID=?";
			  	PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			  	preparedStmt.setInt(1, Integer.parseInt(ID));
			
			// execute the statement
			  	preparedStmt.execute();
			  	con.close();
			  	output = "Deleted successfully";
		  }
		  catch (Exception e)
		  {
			  	output = "Error while deleting the user.";
			  	System.err.println(e.getMessage());
		  }
		  return output;
			}
	}
