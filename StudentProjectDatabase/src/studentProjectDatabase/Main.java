package studentProjectDatabase;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		StudentProjectDatabase student = new StudentProjectDatabase();
		System.out.println("1.Display list of all student .\r\n"
				+ "2.Display list of all projects.\r\n"
				+ "3.Display the number of students who are working on project “P01”.\r\n"
				+ "4.Display number of students who participated in more than one project.\r\n"
				+ "5.Find number of students who did not participate in any project.\r\n"
				+ "6.Display the information (no,name,age) of student  who made the project in java.\r\n"
				+ "7.Display the student information who is a programmer.\r\n"
				+ "8.Display the student who played the max designation(e.g. manager,programmer) in the same project.\r\n"
				+ "9.Display details of the youngest student.\r\n"
				+ "10.Display the info of the student who participated in the project where total no of the student should be exact three.\r\n");
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number: ");
		int i = sc.nextInt();
		
		switch(i) {
		
		case 1:
			student.studentList();
			break;
			
		case 2:
			student.projectList();
			break;
		
		case 3:
			student.countProject();
			break;
			
		case 4:
			student.moreThanOneProject();
			break;
			
		case 5:
			student.participatedInZeroProject();
			break;
			
		case 6:
			student.infoStudProjectJava();
			break;
			
		case 7:
			student.studInfoProgrammer();
			break;
			
		case 8:
			student.maxDesignation();
			break;
			
		case 9:
			student.youngestStudent();
			break;
			
		case 10:
			student.totalThreeStudProject();
			break;
		default:
			System.out.println("\nInvalid Key");
		}
	}

}
