package model;
import java.sql.*;
public class Project { 		
	//A common method to connect to the DB
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
	
	public String insertItem(String name, String type, String price, String desc)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement
			String query = " insert into project (`projectID`,`projectName`,`projectType`,`projectPrice`,`projectDescription`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, type);
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
			 } 
	public String readItems() 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for reading."; } 
			 // Prepare the HTML table to be displayed
			 output = "<table border='1'><tr><th>Project Name</th><th>Project Type</th>" +
			 "<th>Project Price</th>" + 
			 "<th>Project Description</th></tr>"; 
			 
			 String query = "select * from project"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
			 String itemID = Integer.toString(rs.getInt("projectID")); 
			 String itemCode = rs.getString("projectName"); 
			 String itemName = rs.getString("projectType"); 
			 String itemPrice = Double.toString(rs.getDouble("projectPrice")); 
			 String itemDesc = rs.getString("projectDescription"); 
			 // Add into the HTML table
			 output += "<tr><td>" + itemCode + "</td>"; 
			 output += "<td>" + itemName + "</td>"; 
			 output += "<td>" + itemPrice + "</td>"; 
			 output += "<td>" + itemDesc + "</td>"; 
			
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
			
	public String updateItem(String ID, String name, String type, String price, String desc)
			 { 
				
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; } 
			
			 // create a prepared statement
			 String query = "UPDATE project SET projectName=?,projectType=?,projectPrice=?,projectDescription=? WHERE projectID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, name); 
			 preparedStmt.setString(2, type); 
			 preparedStmt.setDouble(3, Double.parseDouble(price)); 
			 preparedStmt.setString(4, desc); 
			 preparedStmt.setInt(5, Integer.parseInt(ID)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Updated successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while updating the item."; 
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
			 String query = "delete from project where projectID=?"; 
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
			 output = "Error while deleting the item."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 
}
