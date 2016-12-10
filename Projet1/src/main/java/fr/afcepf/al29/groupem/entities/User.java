package fr.afcepf.al29.groupem.entities;

import java.util.Date;

public class User {
	
	private enum Civilite {Mr,Mme};
	
	private int id;
	private Civilite civilite;
	private String lastName;
	private String firstName;
	private String email;
	private String telephone;
	private Date birthDate;
	private String hashPassword;
	
	public User() {
	}
	

	public User(Civilite civilite, String lastName, String firstName, String email, String telephone, Date birthDate,
			String hashPassword) {
		super();
		this.civilite = civilite;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.telephone = telephone;
		this.birthDate = birthDate;
		this.hashPassword = hashPassword;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getname() {
		return lastName;
	}

	public void setname(String name) {
		this.lastName = name;
	}

	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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


	public Civilite getCivilite() {
		return civilite;
	}


	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}
	
	
	
	
	
	

}
