package com.yash.cms.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yash.cms.dao.ContactDao;
import com.yash.cms.model.Contact;
import com.yash.cms.model.User;
import com.yash.cms.util.JDBCUtil;

public class ContactDaoImpl extends JDBCUtil implements ContactDao {

	@Override
	public void insertContact(Contact contact) {
		
		PreparedStatement pstmt = super.createPreparedStatement(
				"insert into contact(userId, name, email, phone, address, category) values(?,?,?,?,?,?)");
		try {
			pstmt.setInt(1, contact.getUserid());
			pstmt.setString(2, contact.getName());
			pstmt.setString(3, contact.getEmail());
			pstmt.setString(4, contact.getPhone());
			pstmt.setString(5, contact.getAddress());
			pstmt.setString(6, contact.getCategory());
			pstmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.closePreparedStatement(pstmt);
			super.closeConnection();
		}

	}

	@Override
	public void deleteContact(int contactId) {
		PreparedStatement pstmt = super.createPreparedStatement("delete from user where contactId=?");
		try {
			pstmt.setInt(1, contactId);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.closePreparedStatement(pstmt);
			super.closeConnection();
		}
	}

	@Override
	public void updateContact(Contact contact) {
		PreparedStatement pstmt = super.createPreparedStatement(
				"update user set name=?,email=?, phone=?, address=?, category=? where contactId=?");
		
		try{
			pstmt.setString(1, contact.getName());
			pstmt.setString(2, contact.getEmail());
			pstmt.setString(3, contact.getPhone());
			pstmt.setString(4, contact.getAddress());
			pstmt.setString(5, contact.getCategory());
			pstmt.setInt(6, contact.getContactid());
			
			pstmt.executeUpdate();
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}

	}

	@Override
	public List<Contact> getAllContactsByUserId(int userId) {
		List<Contact> contacts=new ArrayList<>();
		ResultSet rs=null;
		PreparedStatement pstmt = super.createPreparedStatement(
				"select * from contact where userId="+userId);
		try{
			
		rs=pstmt.executeQuery();
		while(rs.next()){
			Contact contact=new Contact();
			contact.setContactid(rs.getInt("contactId"));
			contact.setUserid(rs.getInt("userId"));
			contact.setName(rs.getString("name"));
			contact.setEmail(rs.getString("email"));
			contact.setPhone(rs.getString("phone"));
			contact.setAddress(rs.getString("address"));
			contact.setCategory(rs.getString("category"));
			contacts.add(contact);
			
		}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		finally{
			super.closeResultSet(rs);
			super.closePreparedStatement(pstmt);
			super.closeConnection();
		}
		return contacts;
	}

}
