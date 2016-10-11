package com.wheelAround.model.dao.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wheelAround.model.dao.RegistrationBean;
import com.wheelAround.model.dao.UserDao;



@Component
public class UserDaoImpl implements UserDao {
	
	@Autowired private DataSource dataSource ;
	
	
	CallableStatement callStmt;

	public DataSource getDataSource()
	{
		return this.dataSource;
	}

	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	public boolean newCustomerRegistration(RegistrationBean regBean) throws SQLException{
		
		callStmt = dataSource.getConnection().prepareCall("{call REGISTER_NEW_CUSTOMER (?,?,?,?,?,?,?)}");
		callStmt.setString(1, regBean.getName());
		callStmt.setString(2, regBean.getEmail());
		callStmt.setString(3, regBean.getDob());
		callStmt.setString(4, regBean.getUserName());
		callStmt.setString(5, regBean.getLicencse());
		
		try {
			callStmt.setString(6, hashGenerateCIDAndPasswordForUser(regBean.getPassword()));
			callStmt.setString(7, hashGenerateCIDAndPasswordForUser(regBean.getLicencse()));
		} catch (Exception e) {
			System.out.println("Error while hashing the fields");
		}

		callStmt.execute();
		return true;
		
		
	}

	
	private String hashGenerateCIDAndPasswordForUser(String data) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(data.getBytes());
		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
		return sb.toString();
	}

	public boolean isValidUser(String username, String password) throws SQLException
	{
		callStmt = dataSource.getConnection().prepareCall("{?=call VALIDATECUSTOMER (?,?)}");
		callStmt.setString(2, username);
		callStmt.setString(3, password);
		callStmt.registerOutParameter(1, Types.VARCHAR);
		callStmt.execute();
		
		String results = callStmt.getString(1);
		
		if(results.equals("Login successful!"))
			return true;
		else
			return false;
					
	
	}
}