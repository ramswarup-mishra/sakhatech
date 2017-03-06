package org.college.students;

public class Registration {
	
	private String user_Name;
	private String college_Name;
	private String college_Place;
	private String email;
	private String pincode;
	private String password;
	private String userId;
	public Registration(String user_Name, String college_Name, String college_Place, String email, String pincode,
			String password, String userId) {
		super();
		this.user_Name = user_Name;
		this.college_Name = college_Name;
		this.college_Place = college_Place;
		this.email = email;
		this.pincode = pincode;
		this.password = password;
		this.userId = userId;
	}
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	public String getCollege_Name() {
		return college_Name;
	}
	public void setCollege_Name(String college_Name) {
		this.college_Name = college_Name;
	}
	public String getCollege_Place() {
		return college_Place;
	}
	public void setCollege_Place(String college_Place) {
		this.college_Place = college_Place;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
