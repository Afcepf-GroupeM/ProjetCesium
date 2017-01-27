package fr.afcepf.groupem.entities;

import java.math.BigDecimal;
import java.util.Date;

public class OrderValidationRequest {
	
	private Date dateDemande;
	
//	Order
	private BigDecimal amount;
	private int nbOfItems;
	private int nbDaysMaxToDeliver;
	
//  Client
	private String lastname;
	private String firstname;
//	Carte bancaire
	private String cardNumber;
	private String monthValidity;
	private String yearValidity;
	private String cryptogram;
	
	
//	Adresse
	private int roadNumber;
	private String complement;
	private String roadType;
	private String roadName;
	private String city;
	private String zipcode;
	private String country;
	
	
	
	
	public Date getDateDemande() {
		return dateDemande;
	}
	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}
	public BigDecimal getMontant() {
		return amount;
	}
	public void setMontant(BigDecimal montant) {
		this.amount = montant;
	}
	public int getNbOfItems() {
		return nbOfItems;
	}
	public void setNbOfItems(int nbOfItems) {
		this.nbOfItems = nbOfItems;
	}
	public int getNbDaysMaxToDeliver() {
		return nbDaysMaxToDeliver;
	}
	public void setNbDaysMaxToDeliver(int nbDaysMaxToDeliver) {
		this.nbDaysMaxToDeliver = nbDaysMaxToDeliver;
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
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getMonthValidity() {
		return monthValidity;
	}
	public void setMonthValidity(String monthValidity) {
		this.monthValidity = monthValidity;
	}
	public String getYearValidity() {
		return yearValidity;
	}
	public void setYearValidity(String yearValidity) {
		this.yearValidity = yearValidity;
	}
	public String getCryptogram() {
		return cryptogram;
	}
	public void setCryptogram(String cryptogram) {
		this.cryptogram = cryptogram;
	}
	public int getRoadNumber() {
		return roadNumber;
	}
	public void setRoadNumber(int roadNumber) {
		this.roadNumber = roadNumber;
	}
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	public String getRoadType() {
		return roadType;
	}
	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
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
	
	
	
	

}
