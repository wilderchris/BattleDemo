package com.wilder.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

	
	
		private static ConnectionUtil connUtil;
		private static Properties dbProperties;
		
		
		private ConnectionUtil() {
			dbProperties = new Properties();
			
			try {
				InputStream propFileStream = ConnectionUtil.class.
					getClassLoader().getResourceAsStream("database.properties");
					dbProperties.load(propFileStream);
			}catch (IOException ie){
				ie.printStackTrace();
			}
			
		}
		
		public static synchronized ConnectionUtil getConnectionUtil() {
			if(connUtil == null)
				connUtil = new ConnectionUtil();
			return connUtil;
		}
		
		
		
		
		public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(dbProperties.getProperty("drv"));
			conn = DriverManager.getConnection(
					dbProperties.getProperty("db"),
					dbProperties.getProperty("username"),
					dbProperties.getProperty("password"));
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
			
			return conn;
			
			
		}
}
