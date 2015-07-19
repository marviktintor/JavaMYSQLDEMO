package com.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.jdbc.ResultSet;

public class MainClass {

	public static void main(String[] args) {
		
		try {
			insertData();
			queryData();
			updateData();
			queryData();
			deleteData() ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.print(e.toString());
		}
	}
	
	private static Connection createConnection() throws Exception{
		/**
		 * SQL STATEMENTS 
		 * 
		 * create database test_db;
		 * 
		 * create table tbl_test(_id int primary key auto_increment,fname text not null,lname text not null);
		 * 
		 */
		Class.forName("com.mysql.jdbc.Driver");
		String DATABASE_NAME = "test_db";
		return DriverManager.getConnection("jdbc:mysql://localhost/"+DATABASE_NAME, "root", "");
	}
	
	private static void insertData() throws Exception{
		Statement statement = createConnection().createStatement();
		String SQL = "insert into `tbl_test` (`fname`,`lname`) values ('Victor','Mwenda') ";
		boolean success = statement.execute(SQL);
		
		String executionResults = "";
		if(!success){
			executionResults = "Data inserted";
		}else{
			executionResults = "Data not inserted";
		}
		
		JOptionPane.showMessageDialog(null, executionResults);
	}
	private static void queryData() throws Exception{
		Statement statement = createConnection().createStatement();
		String SQL = "select * from `tbl_test`";
		java.sql.ResultSet results = statement.executeQuery(SQL);
		
		int i = 0;
		for(results.first();!results.isAfterLast();results.next()){ //Iterate through the results
			String firstname = results.getString("fname");//fname is the column name . . .this is the array index
			String lastname = results.getString("lname");//lname is the column name . . .this is the array index
			i++;
			String executionResults = i+" Firstname : "+firstname +" Lastname : "+lastname;
			JOptionPane.showMessageDialog(null, executionResults);
			
		}
		
	}
	private static void updateData() throws Exception{
		Statement statement = createConnection().createStatement();
		String SQL = "update `tbl_test` set `fname`='Mwenda',`lname`='Victor' where `fname`='Victor' and `lname`='Mwenda' ";
		boolean success = statement.execute(SQL);
		
		String executionResults = "";
		if(!success){
			executionResults = "Data updated";
		}else{
			executionResults = "Data not updated";
		}
		
		JOptionPane.showMessageDialog(null, executionResults);
	}
	private static void deleteData() throws Exception{
		Statement statement = createConnection().createStatement();
		String SQL = "delete from `tbl_test` where fname='Victor' and lname='Mwenda' ";
		boolean success = statement.execute(SQL);
		
		String executionResults = "";
		if(!success){
			executionResults = "Data deleted";
		}else{
			executionResults = "Data not deleted";
		}
		
		JOptionPane.showMessageDialog(null, executionResults);
	}
}
