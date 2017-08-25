package com.comcastproject.main;

import com.comcastproject.contact.ContactInfo;
import com.comcastproject.ws.DatabaseServices;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactInfoDemo {
	public static void main(String[] args) {        
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		System.out.println("Enter the Name:");
//
//		String name;
		try {
//			name = br.readLine();
			ContactInfoDemo demo = new ContactInfoDemo();
//			ContactInfo contactInfo = demo.getContactInfoByName(name);
			ContactInfo contactInfo = new ContactInfo();
			contactInfo.setName("test");
			contactInfo.setPhone("12341234");
			contactInfo.setEmail("asdf@test");
			contactInfo.setPosition("Catcher");
			demo.addContact(contactInfo);
			System.out.println(contactInfo);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	public void addContact(ContactInfo contact) {
		Connection connection = null;

		String query = "INSERT INTO contact_info (name, phone, email, position) VALUES (?, ?, ?, ?)";
		try {           
			
			
			connection = DatabaseServices.getConnection();
			PreparedStatement preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString (1, contact.getName());
		    preparedStmt.setString (2, contact.getPhone());
		    preparedStmt.setString (3, contact.getEmail());
		    preparedStmt.setString (4, contact.getPosition());
		    
		    preparedStmt.execute();
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public ContactInfo getContactInfoByName(String name) {
		
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null; 

		ContactInfo contactInfo = null;
		String query = "SELECT * FROM contact_info WHERE name='" + name + "'";
		try {           
			connection = DatabaseServices.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			if (rs.next()) {
				contactInfo = new ContactInfo();
				contactInfo.setName(rs.getString("name"));
				contactInfo.setPhone(rs.getString("phone"));
				contactInfo.setEmail(rs.getString("email"));
				contactInfo.setPosition(rs.getString("position"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return contactInfo;
		
	}

}
