package fr.afcepf.al29.groupem.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	private enum Civility {Mr,Mme};
	
	
	@Id
	@Column(name="id") 
	@GeneratedValue(strategy=GenerationType.AUTO) // Auto increment
	private int id;
	
	@Column(name="lastname")
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="civilite")
	private Civilite civilite;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="birthdate")
	private Date birthDate;
	
	@Column(name="passwordhash")
	private String passwordHash;
	
	
	@OneToMany(mappedBy="user")
	private List<Address>adresses;
	
	
	
	public User() {
	}
	

	public User(Civilite civilite, String lastName, String firstName, String email, String phone, Date birthDate,
			String passwordHash) {
		super();
		this.civilite = civilite;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phone = phone;
		this.birthDate = birthDate;
		this.passwordHash = passwordHash;
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

	public String getpasswordHash() {
		return passwordHash;
	}

	public void setpasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}


	public Civilite getCivilite() {
		return civilite;
	}


	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}


	public List<Address> getAdresses() {
		return adresses;
	}


	public void setAdresses(List<Address> adresses) {
		this.adresses = adresses;
	}
	
	
	
	
	
	

}
