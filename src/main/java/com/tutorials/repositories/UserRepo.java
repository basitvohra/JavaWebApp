package com.tutorials.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.tutorials.models.User;
import com.tutorials.utils.DbUtil;

public class UserRepo {

	private static final Logger LOGGER = Logger.getLogger(UserRepo.class);
	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet result = null;

	public User retrieveUserDetails(String userId) {
		User user = null;
		try {
			Connection connection = DbUtil.getConnection();
			statement = connection.prepareStatement("select * from user where user_id = ?");
			statement.setString(1, userId);
			result = statement.executeQuery();
			if (result != null) {
				while (result.next()) {
					user = new User();
					user.setUserId(result.getString("user_id"));
					user.setUserName(result.getString("user_name"));
					user.setUserRole(result.getString("user_role"));
					user.setUserEmail(result.getString("user_email"));
					user.setUserContact(result.getString("user_contact"));
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return user;
	}
}
