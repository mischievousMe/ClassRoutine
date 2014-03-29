package class_routine.model;

import java.io.Serializable;


public class Admin {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String password;
	protected String userID;
	public Admin(String newUserID, String newPassword) {
//		sessionID = newSessionID;
		password = newPassword;
		userID = newUserID;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String newUserID) {
		 userID = newUserID;
	}
	
	public String getPassword() {
		return password;
	}

	public boolean check_password(String enteredPass) {
		return (enteredPass.equals(password));
	}
	public boolean check_user_id(String newUserID) {
		return (newUserID.equals(userID));
	}
	public void setPassWord(String newPassword) {
		password = newPassword;
	}
}
