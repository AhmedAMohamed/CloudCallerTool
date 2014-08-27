package com.mentor.app.database;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import com.mentor.app.database.helpers.Tool;
import com.mentor.app.database.helpers.UserData;

public class DatabaseConnector {
	
	private String ERROR_MASSAGE = "NOT FOUND 400";
	
	private UserData data;
	
	protected ArrayList<String> adminData;
	protected static String [] tablesData;
	protected static String dataBaseName;
	protected ArrayList<String> userData;
	protected ArrayList<String> configurationData;
	
	private Connection connection;
	
	
	public DatabaseConnector(ArrayList<String> configurationData, ArrayList<String> adminData, String [] tablesData , String databaseName, ArrayList<String> userData) {
		this.adminData = adminData;
		DatabaseConnector.tablesData = tablesData;
		DatabaseConnector.dataBaseName = databaseName;
		this.userData = userData;
		this.configurationData = configurationData;
		data = new UserData();
	}
	
	public void connectToDataBase(){
		connection = null;
		try {
				// first element in  configuration => com.mysql.jdbc.Driver
				// second element in the configuration => jdbc:mysql://localhost:3306/TABLE_NAME
			
	           Class.forName(configurationData.get(0));
	           String url1 = configurationData.get(1);
	           String user = adminData.get(0);
	           String password = adminData.get(1);

	           connection = DriverManager.getConnection(url1, user, password);
	           if (connection != null) {
	        	   return ;
	           }
	           
	     } catch (SQLException ex) {
	            ex.printStackTrace();
	     } catch (ClassNotFoundException e) {
	    	 e.printStackTrace();
	     }

	}
	
	public UserData getUserData(String[] reqiredTables, String userName, String email, String password)throws SQLException {

			if(connection == null){
				connectToDataBase();
			}
			Statement stmt = null;
		    String query = 
		    		"SELECT DISTINCT * " +
		    		"FROM `users` JOIN `credintials` USING (`user_id`)" +
		    		"JOIN `tools` USING (`type_id`) JOIN `tools_data` USING (`tool_id`)" +
		    		" WHERE `users`.user_name =" + "\""+ userName + "\"" +
		    		" AND `users`.password =" + "\"" + password+ "\"" 
		    ;
		    try {
		    	try{
		    		stmt = connection.createStatement();
			        
		    	}
		    	catch(SQLException e){
		    		e.printStackTrace();
		    	}
		    	System.out.println(query);
		    	ArrayList<Tool> tools = new ArrayList<Tool>();
		    	ResultSet rs = stmt.executeQuery(query);
		    	if(rs.first()){
		    		
		    		boolean fine = true;
		    		
		    		data.setUserId(rs.getString("user_id"));
		    		System.out.println(data.getUserId());
		    		System.out.println("jhlashdks");
		    		data.setUserName(rs.getString("user_name"));
		    		data.setEmail(rs.getString("email"));
		    		System.out.println(data.getEmail());
		    		data.setDueDate(rs.getBigDecimal("due_date"));
		    		System.out.println(data.getDueDate());
		    		data.setStartDate(rs.getBigDecimal("start_date"));
		    		System.out.println(data.getStartDate());
		    		data.setTypeId(rs.getString("type_id"));
		    		System.out.println(data.getTypeId());
		    		data.setValid(true);
		    		int i = 0 ;
		    		while(rs.next()){
		    			if(fine){
		    				fine = false;
		    				rs.first();
		    			}
		    			Tool temp = new Tool();
		    			temp.setTool_calling_name(rs.getString("calling_name"));
		    			
		    			temp.setTool_name(rs.getString("tool_name"));
		    			temp.setTool_id(rs.getString("tool_id"));
		    			tools.add(temp);	
		    			System.out.println(tools.get(i).getTool_name());
		    		}	
		    		data.setTools(tools);
		    		return data;
		    	}
		        else{
		        	data.setValid(false);
		        	return data;
		        }
		    } 
		    catch (SQLException e ) {
		        data.setValid(false);
		    } finally {
		        if (stmt != null) {
		        	stmt.close();
		        }
		    }
        	return data;
		}

	@SuppressWarnings({ "unused", "deprecation" })
	private void createErrorMassage() {
		final JDialog error = new JDialog();
		error.setAlwaysOnTop(true);
		error.setBackground(Color.white);
		error.setSize(250, 500);
		error.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		error.setTitle("Error");
		error.show();
		JLabel massage = new JLabel("NO such tabel in the data base");
		massage.setBounds(250, 300, 100, 100);
		massage.show();
		JButton okButton = new JButton("OK");
		okButton.setBounds(50, 50, 200, 100);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				error.setVisible(false);
			}
		});
		okButton.show();
		error.add(okButton);
		error.add(massage);	
		error.show();
	}

	
}