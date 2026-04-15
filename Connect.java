package company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static Connection con;
	private static String url="jdbc:mysql://localhost:3306/r";
	private static String name="root";
	private static String password="root";

public static Connection getConnection() {
		try {
			if(con==null || con.isClosed()) {
Class.forName("com.mysql.cj.jdbc.Driver");
con = DriverManager.getConnection(url,name,password);	
System.out.println("pass");
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			System.out.println("fail");
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("fail");
		}

	
return con;
}


    
}
