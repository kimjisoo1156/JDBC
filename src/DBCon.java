

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
	static Connection con;
	public DBCon() {
	   String Driver="";
        String 
   url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul"; 
        String userid="madang";
        String pwd="madang";

        try {
          Class.forName("com.mysql.cj.jdbc.Driver");   
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
         }
      
        try { 
          con=DriverManager.getConnection(url, userid, pwd);
        } catch(SQLException e) {
            e.printStackTrace();
      }
	}
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return con;
	}
}