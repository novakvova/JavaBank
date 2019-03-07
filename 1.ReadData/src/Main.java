import java.sql.*;

import App.EmployeeService;
public class Main {

	public static void main(String[] args) throws SQLException  {
		Connection myConn = null;
		Statement myStmt = null;
		// 1. Get a connection to database
		myConn = DriverManager.getConnection("jdbc:mysql://DESKTOP-SSQ41VA:3306/demo", 
				"student" , "student");
		myStmt = myConn.createStatement();
		
		EmployeeService service = new EmployeeService(myConn, myStmt);
		
		//service.InsertData("Valera","Semen","smoder@gmail.com");
		//service.UpdateData(9);
		//service.InsertDataPrepare("Vlad","DDD","sdfsdf@sdfsdf.vvv");
		int count=service.GetEmployeersByDepartment("asdfasdf");
		System.out.println("Count = "+ count);
		//service.ShowData();
		
		if (myStmt != null) {
			myStmt.close();
		}

		if (myConn != null) {
			myConn.close();
		}
	}

}
