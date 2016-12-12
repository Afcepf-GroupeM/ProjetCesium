package fr.afcepf.al29.groupem.entities;

import java.util.Date;

public class User {
	
	private enum Civility {Mr,Mme};
	
	private int id;
	private Civility civility;
	private String lastName;
	private String firstName;
	private String email;
	private String phone;
	private Date birthDate;
	private String hashPassword;
	
	public User() {
	}
	

	public User(Civility civility, String lastName, String firstName, String email, String phone, Date birthDate, String hashPassword) {
		this.civility = civility;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phone = phone;
		this.birthDate = birthDate;
		this.hashPassword = hashPassword;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getHashPassword() {
		return hashPassword;
	}

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}


	public Civility getCivility() {
		return civility;
	}


	public void setCivility(Civility civility) {
		this.civility = civility;
	}
	
	
	
	
	
	

}
