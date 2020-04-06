package com.tutorials.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DbUtil {

	private static final Logger LOGGER = Logger.getLogger(DbUtil.class);

	public static Connection getConnection() {
		Connection dbConnection = null;
		try {
			InputStream inputStream = DbUtil.class.getClassLoader().getResourceAsStream("db.properties");
			Properties properties = new Properties();
			if (inputStream != null) {
				properties.load(inputStream);
				String dbDriver = properties.getProperty("mysql.dbDriver");
				String connectionUrl = properties.getProperty("mysql.connectionUrl");
				String userName = properties.getProperty("mysql.userName");
				String password = properties.getProperty("mysql.password");
				Class.forName(dbDriver).newInstance();
				dbConnection = DriverManager.getConnection(connectionUrl, userName, password);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return dbConnection;
	}

}