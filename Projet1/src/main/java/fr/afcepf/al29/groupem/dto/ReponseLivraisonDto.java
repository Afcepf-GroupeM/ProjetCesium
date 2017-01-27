package fr.afcepf.al29.groupem.dto;

import java.util.Date;

public class ReponseLivraisonDto {

	private String trackingCode;
	private int nbItems;
	private Date dateDemande;
	private Date datePriseEnCharge;
	private Date dateLivraison;
	
	private String lastName;
	private String firstName;
	private int numero;
	private String complement;
	private String typeVoie;
	private String nomVoie;
	private String city;
	private String zipcode;
	private String country;
	
	private String transporteurName;
	private String url;
	
	
	
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
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
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
	public String getTransporteurName() {
		return transporteurName;
	}
	public void setTransporteurName(String transporteurName) {
		this.transporteurName = transporteurName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
