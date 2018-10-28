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
import java.util.Properties;
/**
 * 
 */
public class DBUtil {
	private static volatile Connection instance;

	private DBUtil() {
	}

	public static Connection getInstance() throws IOException, SQLException {
		if (instance == null) {
			synchronized (DBUtil.class) {
				if (instance == null) {
					Properties properties = new Properties();
					FileInputStream fileInputStream = new FileInputStream(new File("env.properties"));
					properties.load(fileInputStream);
					String useSSL = properties.getProperty("database.useSSL");
					String dburl = properties.getProperty("database.url") + "&useSSL=" + useSSL;
					String dbuser = properties.getProperty("database.username");
					String dbpassword = properties.getProperty("database.password");
					Connection instance = DriverManager.getConnection(dburl + "&dbuser" + "&dbpassword");
				}
			}
		}
		return instance;
	}
}
