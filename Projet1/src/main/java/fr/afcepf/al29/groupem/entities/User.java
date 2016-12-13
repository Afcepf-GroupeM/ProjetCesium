package fr.afcepf.al29.groupem.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="user")
public class User {
	
	
	
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
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Column(name="hashpassword")
	private String passwordHash;
	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="user")
	private List<Address>addresses;
	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="user")
	private List<Order> orders;
	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="user")
	private List<Review> reviews;
	
	
	
	
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

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String name) {
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

	public String getphone() {
		return phone;
	}

	public void setphone(String phone) {
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


	public List<Address> getAddresses() {
		return addresses;
	}


	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addresses == null) ? 0 : addresses.hashCode());
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((civilite == null) ? 0 : civilite.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + ((passwordHash == null) ? 0 : passwordHash.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((reviews == null) ? 0 : reviews.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (addresses == null) {
			if (other.addresses != null)
				return false;
		} else if (!addresses.equals(other.addresses))
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (civilite != other.civilite)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (passwordHash == null) {
			if (other.passwordHash != null)
				return false;
		} else if (!passwordHash.equals(other.passwordHash))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (reviews == null) {
			if (other.reviews != null)
				return false;
		} else if (!reviews.equals(other.reviews))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", lastName=" + lastName + ", civilite=" + civilite + ", firstName=" + firstName
				+ ", email=" + email + ", phone=" + phone + ", birthDate=" + birthDate + ", passwordHash="
				+ passwordHash + "]";
	}
	
	
	
	
	
	

}
