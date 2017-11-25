package lesson14;

import java.io.Serializable;

public class UserData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String lastName;
	private String username;
	
	public UserData () {
		this("", "", "");
	}
	
	public UserData (String firstName, String lastName, String username) {
		setFirstName (firstName);
		setLastName (lastName);
		setUsername	(username);
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String toString (){
		return firstName +" "+lastName;
	}
	
}
