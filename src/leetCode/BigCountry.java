/**
 * 
 */
package leetCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Administrator
 *
 * 
 */
public class BigCountry {
	private static void execute(String sql){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/test?useSSL=false&" +
			        "user=root&password=root");
			stmt = conn.createStatement();
			stmt.executeUpdate("drop table if exists world;");
			int isCreated = stmt.executeUpdate("create table world(name varchar(20),continent varchar(20),area integer,population integer,gdp integer);");
			boolean isInserted;
			if (isCreated == 0) {
				isInserted = stmt.execute("insert into world values('Afghanistan','Asia',652230,25500100,20343000),"
						+ "('Africa','Europe',28748,2831741,12960000),"
						+ "('Algeria','Africa',2381741,37100000,188681000),"
						+ "('Andorra','Europe',468,78115,3712000),"
						+ "('Angola','Africa',1246700,20609294,100990000);"
						);
				}
		    rs = stmt.executeQuery("select * from world where population > 35000000 or area > 2000000 ;");
		    ResultSetMetaData metaData = rs.getMetaData();
		    int column =metaData.getColumnCount();
		    for(int i=1;i<column;i++){
		    	System.out.print(metaData.getColumnName(i) +"\t\t");
		    }
		    System.out.println();
		    while (rs.next()) {
		    	for(int i=1;i<column;i++){
		    		String value = rs.getString(i);		    		
		    		System.out.print(value + "\t\t");
		    	}
		    	System.out.println();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		execute("");
	}

}
