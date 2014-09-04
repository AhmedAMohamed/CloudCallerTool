package com.mentor.app.database.helpers;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

public class MyUserInfo implements UserInfo, UIKeyboardInteractive {

	private String passwd;
	 JTextField passwordField=(JTextField)new JPasswordField(20);
	 
	 final GridBagConstraints gbc = 
		      new GridBagConstraints(0,0,1,1,1,1,
		                             GridBagConstraints.NORTHWEST,
		                             GridBagConstraints.NONE,
		                             new Insets(0,0,0,0),0,0);
		    private Container panel;

	@Override
	public String getPassphrase() {
		return null;
	}

	@Override
	public String getPassword() {
		return passwd;
	}

	@Override
	public boolean promptPassphrase(String arg0) {
		return true;
	}

	@Override
	  public boolean promptPassword(String message){
	    	  passwd= "";
	    	  return true;
	      
	}

	@Override
	public boolean promptYesNo(String str){
	       return true;
	    }

	@Override
	public void showMessage(String message) {

	}

	@Override
  public String[] promptKeyboardInteractive(String destination,String name,String instruction, String[] prompt,boolean[] echo){
    	
    	panel = new JPanel();
    	panel.setLayout(new GridBagLayout());
 
    	gbc.weightx = 1.0;
    	gbc.gridwidth = GridBagConstraints.REMAINDER;
    	gbc.gridx = 0;
    	panel.add(new JLabel(instruction), gbc);
    	gbc.gridy++;
 
    	gbc.gridwidth = GridBagConstraints.RELATIVE;
 
    	JTextField[] texts=new JTextField[prompt.length];
    	for(int i=0; i<prompt.length; i++){
    		gbc.fill = GridBagConstraints.NONE;
    		gbc.gridx = 0;
    		gbc.weightx = 1;
    		panel.add(new JLabel(prompt[i]),gbc);
 
    		gbc.gridx = 1;
    		gbc.fill = GridBagConstraints.HORIZONTAL;
    		gbc.weighty = 1;
    		if(echo[i]){
    			texts[i]=new JTextField(20);
    		}	
    		else{
    			texts[i]=new JPasswordField(20);
    		}
    		panel.add(texts[i], gbc);
    		gbc.gridy++;
    	}
 
    	if(JOptionPane.showConfirmDialog(null, panel,destination+": "+name,JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.OK_OPTION){String[] response=new String[prompt.length];
        	for(int i=0; i<prompt.length; i++){
        		response[i]=texts[i].getText();
        	}
        	return response;
    	}
    	else{
    		return null;  // cancel
    	}
    }

}
