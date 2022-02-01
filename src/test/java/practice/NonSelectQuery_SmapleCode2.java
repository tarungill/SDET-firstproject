package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class NonSelectQuery_SmapleCode2 {

	public static void main(String[] args) throws SQLException {
		Connection conn=null;
		int result=0;
		try {
			Driver driverRef=new Driver();
			//step1:load/register mysql the database
			DriverManager.registerDriver(driverRef);
			
			//step2:connection to database
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
			System.out.println("connection is done");
			
			//step3:create query statement
			Statement stat = conn.createStatement();
			String query = "insert into project values('Ty_Proj_001','10','Tarun','OnGoing','12/01/2022')";
			
			//step4:execute the query
			result = stat.executeUpdate(query);
		}
			
			catch(Exception e) {
				
			}
		finally {
			if(result==1)
			{
				System.out.println("project inserted successfully");
			}
			else
			{
				System.err.println("project is not inserted---!");
			}
			//step5:close the connection
			conn.close();
			System.out.println("==========close db connection==========");
			}
	}
}


	


