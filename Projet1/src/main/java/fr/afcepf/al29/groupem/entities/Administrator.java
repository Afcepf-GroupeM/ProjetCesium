package fr.afcepf.al29.groupem.entities;

import java.util.Date;


public class Administrator {
	
	private int id;
	private String lastName;
	private String firstName;
	private String email;
	private String phone;
	private String hashPassword;
	private Date creationDate;
	
	public Administrator() {
		
	}
	
	public Administrator(int id, String lastName, String firstName, String email, String phone, String hashPassword, Date creationDate) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phone = phone;
		this.hashPassword = hashPassword;
		this.creationDate = creationDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHashPassword() {
		return hashPassword;
	}
	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
	

}
