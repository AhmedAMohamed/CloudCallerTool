package com.mentor.app.database.helpers;

public class Tool {

	private String toool_id;
	private String tool_name;
	private String tool_calling_name;


	protected String getToool_id() {
		return toool_id;
	}
	protected void setToool_id(String toool_id) {
		this.toool_id = toool_id;
	}
	protected String getTool_name() {
		return tool_name;
	}
	protected void setTool_name(String tool_name) {
		this.tool_name = tool_name;
	}
	protected String getTool_calling_name() {
		return tool_calling_name;
	}
	protected void setTool_calling_name(String tool_calling_name) {
		this.tool_calling_name = tool_calling_name;
	}
	
	
}
