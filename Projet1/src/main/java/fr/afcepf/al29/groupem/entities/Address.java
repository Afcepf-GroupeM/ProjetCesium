package fr.afcepf.al29.groupem.entities;

public class Address {
	
	private enum RoadType {Avenue,Boulevard,Chemin,Impasse,Rue,Voie,Place,Allee,};
	
	private int id;
	private String name;
	private int roadNumber;
	private RoadType roadType;
	private String roadName;
	private String city;
	private String zipcode;
	private String country;
	private boolean isBilling;
	private boolean isValid;
	private int userId;
	
	public Address() {
		
	}
	
	public Address(int id, String name, int roadNumber, RoadType roadType, String roadName, String city, String zipcode, String country, boolean isBilling, boolean isValid, int userId) {
		this.id = id;
		this.name = name;
		this.roadNumber = roadNumber;
		this.roadType = roadType;
		this.roadName = roadName;
		this.city = city;
		this.zipcode = zipcode;
		this.country = country;
		this.isBilling = isBilling;
		this.isValid = isValid;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRoadNumber() {
		return roadNumber;
	}
	public void setRoadNumber(int roadNumber) {
		this.roadNumber = roadNumber;
	}
	public RoadType getRoadType() {
		return roadType;
	}
	public void setRoadType(RoadType roadType) {
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
	public boolean isBilling() {
		return isBilling;
	}
	public void setBilling(boolean isBilling) {
		this.isBilling = isBilling;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	

}
