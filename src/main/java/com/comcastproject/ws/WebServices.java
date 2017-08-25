package com.comcastproject.ws;

import com.comcastproject.contact.ContactInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

@Path("/DB")
public class WebServices {
	
	@GET
	@Path("/getContactInfoByName/{name}")
	@Produces("application/json")
	/**
	* gets a specified contact by searching for the name
	*
	* @param	name
	* @returns	contactInfo object for provided name
	*/
	public ContactInfo getContactInfoByName(@PathParam("name") String name) {
		
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
	
	@GET
	@Path("/getAllContacts")
	@Produces("application/json")
	/**
	* gets all contacts from contact_info table
	*
	* @returns	list<contactInfo> all contacts
	*/
	public List<ContactInfo> getAllContacts() {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null; 

		List<ContactInfo> contacts = new ArrayList<ContactInfo>();
		ContactInfo contactInfo = null;
		String query = "SELECT * FROM contact_info";
		try {           
			connection = DatabaseServices.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			while (rs.next()) {
				contactInfo = new ContactInfo();
				contactInfo.setName(rs.getString("name"));
				contactInfo.setPhone(rs.getString("phone"));
				contactInfo.setEmail(rs.getString("email"));
				contactInfo.setPosition(rs.getString("position"));
				contactInfo.setId(rs.getInt("id"));
				contacts.add(contactInfo);
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
		return contacts;
	}
	
	@POST
	@Path("/addContact")
	@Consumes("application/json")
	/**
	* add a contact to contact_info table
	*
	* @param	contactInfo to be added
	*/
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
	
	@POST
	@Path("/updateContact")
	@Consumes("application/json")
	/**
	* updates a contact entry in DB via the contact id
	*
	* @param	contact object to be updated
	*/
	public void updateContact(ContactInfo contact) {
		Connection connection = null;

		String query = "UPDATE contact_info SET name=?, phone=?, email=?, position=? where id=?";
		try {           
			connection = DatabaseServices.getConnection();
			PreparedStatement preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString (1, contact.getName());
		    preparedStmt.setString (2, contact.getPhone());
		    preparedStmt.setString (3, contact.getEmail());
		    preparedStmt.setString (4, contact.getPosition());
		    preparedStmt.setInt(5, contact.getId());
		    
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
	
	@POST
	@Path("/deleteContact")
	/**
	* deletes the contact associated with the provided id
	*
	* @param	id
	*/
	public void deleteContact(Integer id) {
		Connection connection = null;

		String query = "DELETE FROM contact_info WHERE id=?";
		try {           
			connection = DatabaseServices.getConnection();
			PreparedStatement preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setInt(1, id);
		    
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
	
}