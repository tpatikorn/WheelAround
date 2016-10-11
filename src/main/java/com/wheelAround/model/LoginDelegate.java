package com.wheelAround.model;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wheelAround.model.dao.RegistrationBean;

@Component
public class LoginDelegate
{

	@Autowired private UserService userService;

	public UserService getUserService()
	{
		return this.userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public boolean isValidUser(String username, String password) throws SQLException
	{
	    return userService.isValidUser(username, password);
	}
	
	public boolean registerNewUser(RegistrationBean regValues) throws SQLException
	{
	    return userService.registerNewUser(regValues);
	}
}
