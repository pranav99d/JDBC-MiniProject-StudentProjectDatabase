package studentProjectDatabase;
import java.sql.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

public class StudentProjectDatabase {
	
	String dbURL = "jdbc:mysql://localhost/student_project_database";
	String username = "root";
	String password = "Pranav@123";
	
	public void studentList() throws ClassNotFoundException, SQLException {
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(dbURL, username, password);
			
			Statement stmt = conn.createStatement();
			
			String sql = "Select student.st_name From student ";
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				
					System.out.println("Student Name:"+rs.getString(1));
				
			}
			
			conn.close();
		
	}
		public void projectList() throws ClassNotFoundException, SQLException {
			
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(dbURL, username, password);
				
				Statement stmt = conn.createStatement();
				
				String sql = "Select project.prj_name From project ";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					
					System.out.println("Project Name : " + rs.getString(1));
				}
				
				conn.close();
			
	}
		public void countProject() throws ClassNotFoundException, SQLException {
			
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(dbURL, username, password);
				
				Statement stmt = conn.createStatement();
				
				String sql = "Select count(studentproject.st_no) from studentproject where studentproject.prj_no = 'P01' ";

	     		ResultSet rs = stmt.executeQuery(sql);
			
	     	    rs.next();
				
				System.out.println("The number of students who are working on project “P01” is : " + rs.getInt(1));
				
				conn.close();
			
		}
		
		public void moreThanOneProject() throws ClassNotFoundException, SQLException {
			
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(dbURL, username, password);
				
				Statement stmt = conn.createStatement();
				
				String sql = "Select count(*) from studentproject group by studentproject.st_no having count(studentproject.prj_no) > 1 ";

	     		ResultSet rs = stmt.executeQuery(sql);
			
	     	    rs.next();
				
				System.out.println("The number of students who are working on more than one project is : " + rs.getInt(1));
				
				conn.close();
			
		}
		
		public void participatedInZeroProject() throws ClassNotFoundException, SQLException {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(dbURL, username, password);
				
				Statement stmt = conn.createStatement();
				
				String sql = "Select count(*) st_no from student  "
						+ "where st_no not in (select st_no from studentproject)";

	     		ResultSet rs = stmt.executeQuery(sql);
			
	     	    rs.next();
				
				System.out.println("number of students who did not participate in any project is : " + rs.getInt(1));
				
				conn.close();
		}
		
		public void infoStudProjectJava() throws ClassNotFoundException, SQLException {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(dbURL, username, password);
				
				Statement stmt = conn.createStatement();
				
				String sql = "Select student.st_no,student.st_name, DATE_FORMAT(FROM_DAYS(DATEDIFF(now(),student.st_dob)), '%Y')+0 as age From student, "
						+ "studentproject where student.st_no = studentproject.st_no and studentproject.prj_no = 'P02' ";
				
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					
						System.out.println("Student Number: "+rs.getString(1) + " Student Name: " + rs.getString(2) + " Student Age: "
								+ rs.getInt(3));
					
				}
				
				conn.close();
		}
		
		public void studInfoProgrammer() throws ClassNotFoundException, SQLException {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(dbURL, username, password);
				
				Statement stmt = conn.createStatement();
				
				String sql = "Select * From student, "
						+ "studentproject where student.st_no = studentproject.st_no and studentproject.designation = 'PROGRAMMER' ";
				
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					
						System.out.println("Student Number: "+rs.getString(1) + " Student Name: " + rs.getString(2) + " Student DOB: "
								+ rs.getDate(3) + " Student DOJ: " + rs.getDate(4));
				}
			
				conn.close();
		}
		
		public void maxDesignation() throws ClassNotFoundException, SQLException {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(dbURL, username, password);
				
				Statement stmt = conn.createStatement();
				
				String sql = "select st_no, COUNT(designation) from studentproject group by st_no,prj_no; ";
				
	     		ResultSet rs = stmt.executeQuery(sql);
	     		
	     		HashMap<String, Integer> hm = new HashMap<String, Integer>();
	     		
	     		int flag=1;
	    		String a = null;
	    		int b = 0;
	    		while(rs.next())
	    		{
	    			if(flag==1)
	    			{
	    				 a= rs.getString(1);
	    				 b = rs.getInt(2);
	    				 hm.put(a, b);
	    				flag=0;
	    			}
	    			else if(a.equalsIgnoreCase(rs.getString(1)) && flag==0)
	    			{
	    				if(b<rs.getInt(2))
	    				{
	    					b=rs.getInt(2);
	    					hm.replace(a, b);
	    				}
	    				flag=1;
	    				
	    			}
	    			else
	    			{
	    				hm.put(rs.getString(1),rs.getInt(2));
	    			}
	    				
	    		}
	     	    int maxDesignation = (Collections.max(hm.values()));
	     	   for (Entry<String, Integer> entry : hm.entrySet()) { 
	               if (entry.getValue() == maxDesignation) {
	                   System.out.println(entry.getKey()+" "+entry.getValue());    
	               }
	     	   }
				conn.close();
			
		}
		
		public void youngestStudent() throws ClassNotFoundException, SQLException {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(dbURL, username, password);
				
				Statement stmt = conn.createStatement();
				
				String sql = "Select * From student where student.st_dob = (select MAX(student.st_dob) from student); ";
				
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					
						System.out.println("Student Number: "+rs.getString(1) + " Student Name: " + rs.getString(2) +
							 " Student DOB:  "+ rs.getDate(3) + " Student DOJ: "+ rs.getDate(4));
					
				}
				
				conn.close();
		}
		
		public void totalThreeStudProject() throws ClassNotFoundException, SQLException {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(dbURL, username, password);
			
			Statement stmt = conn.createStatement();
			
			String sql = "select * from student where st_no in (\r\n"
					+ "select st_no from studentproject where prj_no in (\r\n"
					+ "select prj_no from studentproject group by prj_no having COUNT(Distinct(st_no))=3\r\n"
					+ ")\r\n"
					+ ");";
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				
					System.out.println("Student Number: "+rs.getString(1) + " Student Name: " + rs.getString(2) + " Student DOB: "
							+ rs.getDate(3) + " Student DOJ: " + rs.getDate(4));
			}
			conn.close();
		}

}
