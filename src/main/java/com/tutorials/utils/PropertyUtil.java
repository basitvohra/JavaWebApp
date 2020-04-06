package com.tutorials.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyUtil implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -719409886279534415L;
	private static final Logger LOGGER = Logger.getLogger(PropertyUtil.class);
	private static volatile PropertyUtil INSTANCE = null;
	private Properties properties = null;

	private PropertyUtil() {
		properties = new Properties();
		try {
			InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");
			if (input == null) {
				LOGGER.error("Unable to find application.properties");
			} else {
				properties.load(input);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static PropertyUtil getInstance() {
		if (INSTANCE == null) {
			synchronized (PropertyUtil.class) {
				if (INSTANCE == null) {
					INSTANCE = new PropertyUtil();
				}
			}
		}
		return INSTANCE;
	}

	public Object getProperty(String key) {
		return properties.get(key);
	}

	@Override
	protected Object clone() {
		return INSTANCE;
	}

	protected Object readResolve() {
		return INSTANCE;
	}

}
