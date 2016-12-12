package fr.afcepf.al29.groupem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Address {
	
	private enum RoadType {Avenue,Boulevard,Chemin,Impasse,Rue,Voie,Place,Allee,};
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;
	
	private int number;
	
	private RoadType roadType;
	
	private String city;
	
	private String zipcode;
	
	private String country;
	
	private boolean isBilling;
	
	private int userId;
	
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;
	
	
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
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public RoadType getRoadType() {
		return roadType;
	}
	public void setRoadType(RoadType roadType) {
		this.roadType = roadType;
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
	public int getUser_id() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

}
