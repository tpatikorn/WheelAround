package com.wheelAround.model;

import java.sql.SQLException;

import com.wheelAround.model.dao.RegistrationBean;

/**
 * @author CENTAUR
 *
 */
public interface UserService
{
	public boolean isValidUser(String username, String password) throws SQLException;
	
	public boolean registerNewUser(RegistrationBean regValues) throws SQLException;
}
