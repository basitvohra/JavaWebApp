package com.tutorials.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.tutorials.utils.DbUtil;

public class AuthRepo {

	private static final Logger LOGGER = Logger.getLogger(AuthRepo.class);
	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet result = null;

	public boolean validateCredentials(String userId, String password) {
		boolean isValid = false;
		try {
			connection = DbUtil.getConnection();
			statement = connection.prepareStatement("select active from auth where userId = ? and password = ?");
			statement.setString(1, userId);
			statement.setString(2, password);
			result = statement.executeQuery();
			if (result != null) {
				while (result.next()) {
					isValid = result.getBoolean("active");
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
		return isValid;
	}
}
