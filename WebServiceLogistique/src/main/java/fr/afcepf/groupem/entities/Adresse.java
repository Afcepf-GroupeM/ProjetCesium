package fr.afcepf.groupem.entities;

public class Adresse {
	
	private int id;
	private String lastname;
	private String firstname;
	private int numero;
	private String completement;
	private String typeVoie;
	private String nomVoie;
	private String city;
	private String zipcode;
	private String country;
	private int idlivraison;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getCompletement() {
		return completement;
	}
	public void setCompletement(String completement) {
		this.completement = completement;
	}
	public String getTypeVoie() {
		return typeVoie;
	}
	public void setTypeVoie(String typeVoie) {
		this.typeVoie = typeVoie;
	}
	public String getNomVoie() {
		return nomVoie;
	}
	public void setNomVoie(String nomVoie) {
		this.nomVoie = nomVoie;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getIdlivraison() {
		return idlivraison;
	}
	public void setIdlivraison(int idlivraison) {
		this.idlivraison = idlivraison;
	}
	
	

}
