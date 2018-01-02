package Jdbcdemo;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UConnMBBDAO{

	public static void main(String[] args) throws Exception{
		UConnMBBDAO testConn = new UConnMBBDAO();
		while (true){
			Scanner scanner = new Scanner(System.in);
			System.out.print( "Choose A Table: " );
			String table = scanner.nextLine();
			System.out.print( "Choose A Column: " );
			String column = scanner.nextLine();
			System.out.println(testConn.query(table, column));
		}
	}


	private Connection myConn;

	//initialize connection to database
	public UConnMBBDAO() throws Exception{
		String dburl = "jdbc:mysql://localhost/uconnmbb";
		String user = "root";
		String password = "";
				
		myConn = DriverManager.getConnection(dburl, user, password);
		
		System.out.println("DB connection successful to: " + dburl);
	}
	
	//basic querying method
	public List<String> query(String table, String attribute) throws Exception {
		List<String> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from " + table);
			
			while (myRs.next()) {
				String temp = myRs.getString(attribute);
				list.add(temp);
			}
		}finally {return list;}
	}
}

