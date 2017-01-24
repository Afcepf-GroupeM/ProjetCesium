package fr.afcepf.groupem.entities;

import java.util.Date;

public class Livraison {
	
	private int id;
	
	private String trackingCode;
	private int nbItems;
	
	private Date dateDemande;
	private Date datePriseEnCharge;
	private Date dateLivraison;
	
	private StatutLine statut;
	
	
	private String lastname;
	private String firstname;
	
	private int numero;
	private String complementAdresse;
	private String typeVoie;
	private String nomVoie;
	private String zipcode;
	private String city;
	private String country;
	
	
	
	
	public String getTrackingCode() {
		return trackingCode;
	}
	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}
	public int getNbItems() {
		return nbItems;
	}
	public void setNbItems(int nbItems) {
		this.nbItems = nbItems;
	}
	public Date getDateDemande() {
		return dateDemande;
	}
	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}
	public Date getDatePriseEnCharge() {
		return datePriseEnCharge;
	}
	public void setDatePriseEnCharge(Date datePriseEnCharge) {
		this.datePriseEnCharge = datePriseEnCharge;
	}
	public Date getDateLivraison() {
		return dateLivraison;
	}
	public void setDateLivraison(Date dateLivraison) {
		this.dateLivraison = dateLivraison;
	}
	public StatutLine getStatut() {
		return statut;
	}
	public void setStatut(StatutLine statut) {
		this.statut = statut;
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
	public String getComplementAdresse() {
		return complementAdresse;
	}
	public void setComplementAdresse(String complementAdresse) {
		this.complementAdresse = complementAdresse;
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
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	
	
	
	

}
