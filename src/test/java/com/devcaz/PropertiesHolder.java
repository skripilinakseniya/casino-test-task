package com.devcaz;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.Properties;


public class PropertiesHolder {
	private final Properties properties;

	@SneakyThrows
	public PropertiesHolder() {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("application.properties");
		properties = new Properties();
		properties.load(is);
	}

	public String getProperty(String propertyName) {
		return properties.getProperty(propertyName);
	}
}
