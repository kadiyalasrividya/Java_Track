package com.yash.cms.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yash.cms.dao.UserDao;
import com.yash.cms.model.User;
import com.yash.cms.util.JDBCUtil;

public class UserDaoImpl extends JDBCUtil implements UserDao {
	 private static final Logger logger=
				LoggerFactory.getLogger(UserDaoImpl.class);

	/**
	 * this will insert the user in DB.
	 */
	@Override
	public void insertUser(User user) {
		PreparedStatement pstmt=super.createPreparedStatement("insert into user(name,phone,email,address,loginName,password) values (?,?,?,?,?,?)");
		try {
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPhone());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());
			pstmt.setString(5, user.getLoginName());
			pstmt.setString(6, user.getPassword());
			pstmt.executeUpdate();
			logger.info("Success :  User Inserted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			super.closePreparedStatement(pstmt);
			super.closeConnection();			
		}
	}

	@Override
	public void deleteUser(int userid) {
		PreparedStatement pstmt=super.createPreparedStatement("delete from user where userid=?");
		try {
			pstmt.setInt(1, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			super.closePreparedStatement(pstmt);
			super.closeConnection();
		}
	}

	@Override
	public void editUser(User user) {
		PreparedStatement pstmt=super.createPreparedStatement("update user set name=?, phone=?, email=?, address=?, password=? where userId=?");
		try {
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPhone());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());
			pstmt.setString(5, user.getPassword());
			pstmt.setInt(6, user.getUserid());
			pstmt.executeUpdate();
			logger.info("Success : record updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			super.closePreparedStatement(pstmt);
			super.closeConnection();
		}
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUserIdPassword(String userid, String password) {
		PreparedStatement pstmt=super.createPreparedStatement("select * from user where loginName=? and password=?");
		User user=null;
		ResultSet rs=null;
		try{
			pstmt.setString(1, userid);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()){
				user=new User();
				user.setName(rs.getString("name"));
				user.setUserid(rs.getInt("userId"));
				user.setEmail(rs.getString("email"));
				user.setLoginName(rs.getString("loginName"));
				user.setPassword(rs.getString("password"));
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
		return user;
	}

}
