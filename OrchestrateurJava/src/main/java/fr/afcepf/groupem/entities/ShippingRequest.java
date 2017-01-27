package fr.afcepf.groupem.entities;

public class ShippingRequest {
	
	private String lastname;
	private String firstname;
	
	private int roadNumber;
	private String complement;
	private String roadType;
	private String roadName;
	private String city;
	private String zipcode;
	private String country;
	
	private int nbItem;
	private int delaiMax; //nb jours max pour livrer
	
	
	
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
	public int getNbItem() {
		return nbItem;
	}
	public void setNbItem(int nbItem) {
		this.nbItem = nbItem;
	}
	public int getDelaiMax() {
		return delaiMax;
	}
	public void setDelaiMax(int delaiMax) {
		this.delaiMax = delaiMax;
	}
	
	
	

}
