package application;

import java.util.HashMap;
import java.util.Map;

public class User {
	
	private String username;
	private String password;
	protected static Map<String, String> userMap = new HashMap<>();
	
	public User(String username, String password) {
		
		this.username = username;
		this.password = password;
		userMap.put(username, password);
	}
	
	public String getUsername() {
		
		return username;
	}
	public String getpassword() {
		
		return password;
	}

	public void setPassword(String newPassword) {
		
		this.password = newPassword;
	}
}
