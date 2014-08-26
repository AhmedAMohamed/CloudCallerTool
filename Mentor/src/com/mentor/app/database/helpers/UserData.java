package com.mentor.app.database.helpers;

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
	private BigInteger dueDate;			 //	the due date that the user can still access any tool
	private BigInteger startDate;		 // the date that the user registered using this type of users
	private boolean valid;			 	 // checks the validity of the data returned to the main class in case that any error occurred this value will be false true in other cases

	protected String getUserName() {
		return userName;
	}
	protected void setUserName(String userName) {
		this.userName = userName;
	}
	protected String getTypeId() {
		return typeId;
	}
	protected void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	protected ArrayList<Tool> getTools() {
		return tools;
	}
	protected void setTools(ArrayList<Tool> tools) {
		this.tools = tools;
	}
	protected BigInteger getDueDate() {
		return dueDate;
	}
	protected void setDueDate(BigInteger dueDate) {
		this.dueDate = dueDate;
	}
	protected BigInteger getStartDate() {
		return startDate;
	}
	protected void setStartDate(BigInteger startDate) {
		this.startDate = startDate;
	}
	protected boolean isValid() {
		return valid;
	}
	protected void setValid(boolean valid) {
		this.valid = valid;
	}
	protected String getEmail() {
		return email;
	}
	protected void setEmail(String email) {
		this.email = email;
	}
	protected String getUserId() {
		return userId;
	}
	protected void setUserId(String userId) {
		this.userId = userId;
	}
	
}
