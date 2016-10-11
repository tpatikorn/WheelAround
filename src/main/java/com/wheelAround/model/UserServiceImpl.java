package com.wheelAround.model;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wheelAround.model.dao.RegistrationBean;
import com.wheelAround.model.dao.UserDao;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired private UserDao userDao;

	public UserDao getUserDao()
	{
		return this.userDao;
	}

	public void setUserDao(UserDao userDao)
	{
		this.userDao = userDao;
	}

	
	public boolean isValidUser(String username, String password) throws SQLException
	{
		return userDao.isValidUser(username, password);
	}

	@Override
	public boolean registerNewUser(RegistrationBean regValues) throws SQLException {
		
		return userDao.newCustomerRegistration(regValues);
	}
}
