

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class TestConnection {
public static void main(String[] args) throws IOException, SQLException {
	Properties p = new Properties();
	 BufferedInputStream in = new BufferedInputStream(TestConnection.class.getClassLoader()
			.getResourceAsStream("jdbc.properties"));
	p.load(in);
    String user = p.getProperty("username");
    String password = p.getProperty("password");
    String url = p.getProperty("url");
   Connection conn = (Connection) DriverManager
			.getConnection(url, user, password);
   Statement statement = conn.createStatement();
   boolean f = statement.execute("select * from seckill");
   ResultSet rs = statement.executeQuery("select * from seckill");
   while(rs.next()){
	   String name = rs.getString("name");
	   System.out.println(name);
   }

}
}
