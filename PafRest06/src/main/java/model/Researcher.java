package model;
import java.sql.*;

public class Researcher
{ 		//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	}
	/*
	public String insertItem(String code, String name, String price, String desc)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement
			String query = " insert into items (`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setDouble(4, Double.parseDouble(price));
			preparedStmt.setString(5, desc);
			
			
			// execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Inserted successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while inserting the item."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } */
	
			public String readItems() 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for reading."; } 
			 // Prepare the HTML table to be displayed
			 output = "<table border='1'><tr><th>Researcher Code</th><th>Researcher Name</th>" +
			 "<th>Project Price</th>" + 
			 "<th>Project Description</th>" +
			 "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select userName,userPassword,userCode,userEmail,userPhone from user"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
			 String itemID = Integer.toString(rs.getInt("userID")); 
			 String itemName = rs.getString("userName"); 
			 String itemPw = rs.getString("userPassword"); 
			 String itemCode = rs.getString("userCode"); 
			 String itemEmail = rs.getString("userEmail"); 
			 String itemPh = rs.getString("userPhone"); 
			 // Add into the HTML table
			 output += "<tr><td>" + itemName + "</td>"; 
			 output += "<td>" + itemPw + "</td>"; 
			 output += "<td>" + itemCode + "</td>"; 
			 output += "<td>" + itemEmail + "</td>"; 
			 output += "<td>" + itemPh + "</td>"; 
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='user.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
			 + "<input name='itemID' type='hidden' value='" + itemID 
			 + "'>" + "</form></td></tr>"; 
			 } 
			 con.close(); 
			 // Complete the HTML table
			 output += "</table>"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while reading the items."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 
			
			public String updateItem(String ID, String name, String pw, String email, String phone)
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; } 
			
			 // create a prepared statement
			 String query = "UPDATE user SET userName=?,userPassword=?,userEmail=?,userPhone=? WHERE userID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setString(1, name); 
			 preparedStmt.setString(2, pw); 
			 preparedStmt.setString(3, email); 
			 preparedStmt.setString(4, phone); 
			 preparedStmt.setInt(5, Integer.parseInt(ID)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Updated successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while updating the user details."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 
			
			public String deleteItem(String itemID) 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for deleting."; } 
			 // create a prepared statement
			 String query = "delete from user where userID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(itemID)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Deleted successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while deleting the user account."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 
			} 