package fr.afcepf.al29.groupem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="address")
public class Address {
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;
	
	@Column(name="roadnumber")
	private int number;
	
	@Column(name="complement")
	@Enumerated(EnumType.STRING)
	private ComplementAddress complement;
	
	@Column(name="roadtype")
	@Enumerated(EnumType.STRING)
	private RoadType roadType;
	
	@Column(name="roadname")
	private String roadName;
	
	private String city;
	
	private String zipcode;
	
	private String country;
	
	@Column(name="isbilling")
	private boolean isBilling;
	
	@Column(name="isvalid")
	private boolean isValide;
	
	
	
	
	@ManyToOne
	@JoinColumn(name = "userid")
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
	public ComplementAddress getComplement() {
		return complement;
	}
	public void setComplement(ComplementAddress complement) {
		this.complement = complement;
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
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public boolean isValide() {
		return isValide;
	}
	public void setValide(boolean isValide) {
		this.isValide = isValide;
	}
	
	public Address() {
		
	}
	public Address(int id, String name, int number, ComplementAddress complement, RoadType roadType, String roadName, String city,
			String zipcode, String country, boolean isBilling, boolean isValide, User user) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.complement = complement;
		this.roadType = roadType;
		this.roadName = roadName;
		this.city = city;
		this.zipcode = zipcode;
		this.country = country;
		this.isBilling = isBilling;
		this.isValide = isValide;
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", name=" + name + ", number=" + number + ", complement=" + complement
				+ ", roadType=" + roadType + ", roadName=" + roadName + ", city=" + city + ", zipcode=" + zipcode
				+ ", country=" + country + ", isBilling=" + isBilling + ", isValide=" + isValide + ", user=" + user
				+ "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((complement == null) ? 0 : complement.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + id;
		result = prime * result + (isBilling ? 1231 : 1237);
		result = prime * result + (isValide ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + number;
		result = prime * result + ((roadName == null) ? 0 : roadName.hashCode());
		result = prime * result + ((roadType == null) ? 0 : roadType.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
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
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (complement != other.complement)
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (id != other.id)
			return false;
		if (isBilling != other.isBilling)
			return false;
		if (isValide != other.isValide)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number != other.number)
			return false;
		if (roadName == null) {
			if (other.roadName != null)
				return false;
		} else if (!roadName.equals(other.roadName))
			return false;
		if (roadType != other.roadType)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals(other.zipcode))
			return false;
		return true;
	}
}
