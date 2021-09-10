package com.lti.triplnr20.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.triplnr20.daos.UserRepository;
import com.lti.triplnr20.exceptions.AuthenticationException;
import com.lti.triplnr20.models.User;

@Service
public class AuthServiceImpl implements AuthService {

	private UserRepository ur;
	private UserService us;

	@Autowired
	public AuthServiceImpl(UserRepository ur, UserService us) {
		super();
		this.us = us;
		this.ur = ur;
	}

	//
	public static String getUserFromToken(String token) {
		String[] split = token.split(":");
		return split[1];
	}
	
	public static int getIdFromToken(String token) {
		String[] split = token.split(":");
		return Integer.valueOf(split[0]);
	}
	
	//creates the authorization token needed for validation and authorization 
	// public String createAuthToken(User user) {
	// 	String token = null;
	// 	token = user.getUserId() + ":" + user.getUsername();
	// 	return token;
	// }

	// //checks if user credentials are valid and sets user authorization token, if invalid throws AuthenticationException 
	// @Override
	// public String login(User user) {
	// 	String token = null;
	// 	if (ur.findUserByUsername(user.getUsername()) != null) {
	// 		user.setUserId(ur.findUserByUsername(user.getUsername()).getUserId());
	// 		token = createAuthToken(user);
	// 		return token;
	// 	}else {
	// 		throw new AuthenticationException();
	// 	}

	// }

	// @Override
	// public String register(User user) {
	// 	return createAuthToken(us.createUser(user));
	// }

}
