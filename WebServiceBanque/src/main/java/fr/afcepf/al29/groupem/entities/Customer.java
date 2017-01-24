package fr.afcepf.al29.groupem.entities;

import java.util.Date;

public class Customer {

    private Integer id;

    private Enum civilite;
  
    private String lastName;

    private String firstName;

    private Date birthday;

    private String email;

    private String telephone;

  
    private Integer noStreet;

    private String street;

    private String city;

    private String zipCode;

    public Customer() {
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Enum getCivilite() {
		return civilite;
	}

	public void setCivilite(Enum civilite) {
		this.civilite = civilite;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public Integer getNoStreet() {
		return noStreet;
	}

	public void setNoStreet(Integer noStreet) {
		this.noStreet = noStreet;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
    

}