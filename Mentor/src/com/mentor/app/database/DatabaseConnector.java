package com.mentor.app.database;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class DatabaseConnector {
	
	private String ERROR_MASSAGE = "NOT FOUND 400";
	
	private ArrayList<String[]> data;
	
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
		data = new ArrayList<String[]>();
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
	
	public ArrayList<String[]> getUserData(String[] reqiredTables, String userName, String email, String password)throws SQLException {
			
			if(connection == null){
				connectToDataBase();
			}
			String tableName = findTableByName(reqiredTables);
			if(tableName.equals(ERROR_MASSAGE)){
				String [] tempo = new String [2];
				tempo[0] = "valid";
	        	tempo[1] = "false";
	        	data.add(tempo);
	        	return data;
			}
		    Statement stmt = null;
		    String query = 
		    		"SELECT DISTINCT * " +
		    		"FROM `users` JOIN `credintials` USING (`user_id`)" +
		    		"JOIN `tools` USING (`type_id`) JOIN `tools_data` USING (`tool_id`)" +
		    		" WHERE `users`.user_name =" + "\""+ userName + "\"" +
		    		"AND `users`.password =" + "\"" + password+ "\"" 
		    ;
		    try {
		    	try{
		    		stmt = connection.createStatement();
			        
		    	}
		    	catch(SQLException e){
		    		e.printStackTrace();
		    	}
		    	String[] tempo ;
		    	ResultSet rs = stmt.executeQuery(query);
		    	if(rs.first()){
		    		
		    		boolean fine = true;
		    		
		    		tempo = new String[2];
		    		tempo[0] = "valid";
	    			tempo[1] = "true";
	    			data.add(tempo);
		    		while(rs.next()){
		    			if(fine){
		    				fine = false;
		    				rs.first();
		    			}
		    			String temp = rs.getString("tool_name");
			           
		    			tempo = new String[2];
		    			tempo[0] = "tool_name";
		    			tempo[1] = temp;
		    			data.add(tempo);
		    		
		    		}	
		    		return data;
		    	}
		        else{
			        tempo = new String[2];
		        	tempo[0] = "valid";
		        	tempo[1] = "false";
		        	data.add(tempo);
		        	return data;
		        }
		    } 
		    catch (SQLException e ) {
		        System.out.println(e.getMessage());
		    	String[] tempo = new String[2];
				tempo[0] = "valid";
	        	tempo[1] = "false";
	        	data.add(tempo);
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

	private String findTableByName(String[] tabelName) {
		for(int i = 0 ; i < tablesData.length ; i++){
			if(tablesData[i] == tabelName[0] ){
				return tabelName[0];
			}
		}
		return ERROR_MASSAGE;
	}
}








/*
"SELECT DISTINCT `tool_id`, `tool_name`" +
"FROM `users` JOIN `credintials` USING (`user_id`)" +
"JOIN `tools` USING (`type_id`) JOIN `tools_data` USING (`tool_id`)" +
" WHERE `users`.user_name =" + "\""+  + "\"" +
"AND `users`.password =" + "\"" +       + "\"" 

*/

