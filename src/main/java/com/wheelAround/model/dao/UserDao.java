package com.wheelAround.model.dao;

import java.sql.SQLException;

public interface UserDao {
	
	public boolean isValidUser(String username, String password) throws SQLException;
	public boolean newCustomerRegistration(RegistrationBean regValues) throws SQLException;
	

}
