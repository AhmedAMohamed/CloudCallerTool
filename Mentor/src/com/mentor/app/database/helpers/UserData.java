package com.mentor.app.database.helpers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

/*
 * This class is the data type that will be returned from the data base from each user account in the data base
 * By returning an object from this class all the data needed from the data base will be contained 
 */
public class UserData {

	private String userName;  			 // the user name of any client in case to be needed to show it in each page
	private String email;
	private String userId;
	private String typeId;   			 // the type of this client 
	private ArrayList<Tool> tools;		 //	an array of tools all the tools can be accessed by that user 
	private BigDecimal dueDate;			 //	the due date that the user can still access any tool
	private BigDecimal startDate;		 // the date that the user registered using this type of users
	private boolean valid;			 	 // checks the validity of the data returned to the main class in case that any error occurred this value will be false true in other cases
	
	
	public UserData() {
		tools = new ArrayList<>();
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public ArrayList<Tool> getTools() {
		return tools;
	}
	public void setTools(ArrayList<Tool> tools) {
		this.tools = tools;
	}
	public BigDecimal getDueDate() {
		return dueDate;
	}
	public void setDueDate(BigDecimal bigDecimal) {
		this.dueDate = bigDecimal;
	}
	public BigDecimal getStartDate() {
		return startDate;
	}
	public void setStartDate(BigDecimal bigDecimal) {
		this.startDate = bigDecimal;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	
	
}
