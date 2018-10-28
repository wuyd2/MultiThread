/**
 * 
 */
package leetCode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 
 */
public class DBUtil {
	private static volatile Connection instance;

	private DBUtil() {
	}

	public static Connection getInstance() {
		try {
			if (instance == null) {
				synchronized (DBUtil.class) {
					if (instance == null) {
						Properties properties = new Properties();
						FileInputStream fileInputStream = new FileInputStream(new File("env.properties"));
						properties.load(fileInputStream);
						String useSSL = properties.getProperty("database.useSSL");
						String dburl = properties.getProperty("database.url") + "?useSSL=" + useSSL;
						String dbuser = properties.getProperty("database.username");
						String dbpassword = properties.getProperty("database.password");
						instance = DriverManager.getConnection(dburl + "&user=" + dbuser + "&password="+dbpassword);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}
	public static void createTable(String sql){
		try {
			Statement stmt = getInstance().createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void close(){
		
	}
}
