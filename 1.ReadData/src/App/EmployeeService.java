package App;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class EmployeeService {

	Connection conn = null;
	Statement stmt = null;
	//ResultSet myRs = null;
	
	public EmployeeService(Connection conn, Statement stmt) {
		this.conn=conn;
		this.stmt=stmt;
	}
	
	public void ShowData() throws SQLException {
		ResultSet myRs = null;
		try {
			// 3. Execute SQL query
			myRs = stmt.executeQuery("select * from employees");

			// 4. Process the result set
			while (myRs.next()) {
				System.out.println(myRs.getString("id")+", "+ myRs.getString("last_name") + ", " + 
							myRs.getString("first_name"));
			}
						
		}catch(SQLException sqex) {
			System.out.println("Виникла проблема при підключені"+
						sqex.getMessage());
		}
		finally {
			if(myRs != null)
				myRs.close();
		}
		
		
	}

	public void InsertData(String lastname, String fistname,
			String email) throws SQLException {
		
		String sql = "insert into employees " + " (last_name, first_name, email)"
				+ " values ('"+lastname+"', '"+fistname+"', '"+email+"')";
		stmt.executeUpdate(sql);
	}
	
	public void InsertDataPrepare(String lastname, String fistname,
			String email) throws SQLException {
		
		String sql = "insert into employees " + " (last_name, first_name, email)"
				+ " values (?, ?, ?)";
		
		PreparedStatement myStmt = this.conn.prepareStatement(sql);
		
		myStmt.setString(1,lastname);
		myStmt.setString(2,fistname);
		myStmt.setString(3,email);
		
		myStmt.executeUpdate();
		//stmt.setString
		//stmt.executeUpdate(sql);
	}

	public void UpdateData(int id) throws SQLException {
		String sql = "update employees set email='demo@luv2code.com' where id="+id;
		int rowsAffected = stmt.executeUpdate(sql);
	}

	public void DeleteData() throws SQLException {
		String sql = "delete from employees where last_name='Brown'";
		int rowsAffected = stmt.executeUpdate(sql);
	}
	
	public int GetEmployeersByDepartment(String department) throws SQLException {
		String sql="{call get_count_for_department(?)}";
		
		PreparedStatement myStmt = this.conn.prepareCall(sql);
		
		myStmt.setString(1,department);
		
		myStmt.execute();
		
		//ResultSet myRs = myStmt.getResultSet();
		
		//if(myRs.next())
	//		return myRs.getInt("COUNT(*)");
		return 0;
	}
}
