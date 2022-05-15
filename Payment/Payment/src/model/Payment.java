package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/management", "root", "y1o2h3a4n5@#");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPay(String cusName, String cusAddress, String cusAccount, String time, String date) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into PAYMENTDATA(`ID`,`CUSNAME`,`CUSADDRESS`,`CUSACCOUNT`,`TIME`,`DATE`)" + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, cusName);
			preparedStmt.setString(3, cusAddress);
			preparedStmt.setString(4, cusAccount);
			preparedStmt.setString(5, time);
			preparedStmt.setString(6, date);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPay() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Customer Name</th><th>Customer Address</th><th>Customer Account No</th><th>Payment Time</th><th>Payment Date</th><th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from PAYMENTDATA";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String cusName = rs.getString("cusName");
				String cusAddress = rs.getString("cusAddress");
				String cusAccount = rs.getString("cusAccount");
				String time = rs.getString("time");
				String date = rs.getString("date");

				output += "<tr><td><input id='hidpayment_idUpdate' name='hidpayment_idUpdate' type='hidden' value='"+id+"'>"+cusName+"</td>"; 
				output += "<td>" + cusAddress + "</td>";
				output += "<td>" + cusAccount + "</td>";
				output += "<td>" + time + "</td>";
				output += "<td>" + date + "</td>";
				// buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-iD='" + id + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger' data-iD='" + id + "'></td></tr>";
			
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePay(String id, String cusName, String cusAddress, String cusAccount, String time, String date) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE PAYMENTDATA SET CUSNAME=?,CUSADDRESS=?,CUSACCOUNT=?,TIME=?,DATE=? WHERE ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, cusName);
			preparedStmt.setString(2, cusAddress);
			preparedStmt.setString(3, cusAccount);
			preparedStmt.setString(4, time);
			preparedStmt.setString(5, date);
			preparedStmt.setInt(6, Integer.parseInt(id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePay(String id) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from PAYMENTDATA where ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
