/**
 * 
 */
package leetCode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 编写一个 SQL 查询，查找 Person 表中所有重复的电子邮箱。
示例：
+----+---------+
| Id | Email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+
根据以上输入，你的查询应返回以下结果：
+---------+
| Email   |
+---------+
| a@b.com |
+---------+
 *
 * 
 */
public class DuplicateEmail {
	public static List<String> duplicateEmails(){
		Connection connection = DBUtil.getInstance();
		Statement stmt = null;
		List<String> list = new ArrayList<>();
		try {
			stmt = connection.createStatement();
			stmt.execute("create table if not exists Persion(id integer,email varchar(255));");
			stmt.execute("insert into Persion values(1,'a@b.com'),"
					+ "(2,'c@d.com'),"
					+ "(3,'a@b.com')");
			ResultSet rs = stmt.executeQuery("select email from Persion group by email having count(*) > 1");
			while (rs.next()) {
				String email = rs.getString(1);
				list.add(email);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
	public static void main(String[] args) {
		List<String> list = duplicateEmails();
		if (list != null) {
			for(String email:list){
				System.out.println(email);
			}
			
		}

	}

}
