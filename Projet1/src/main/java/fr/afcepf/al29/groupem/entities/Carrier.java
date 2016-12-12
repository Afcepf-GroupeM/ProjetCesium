package fr.afcepf.al29.groupem.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Carrier {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;
	
	private String trackingUrl;
	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="carrier")
	private List<Order> orders;
	
	
	public Carrier() {
		
	}
	
	public Carrier(int id, String name, String trackingUrl) {
		this.id = id;
		this.name = name;
		this.trackingUrl = trackingUrl;
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
	public String getTrackingUrl() {
		return trackingUrl;
	}
	public void setTrackingUrl(String trackingUrl) {
		this.trackingUrl = trackingUrl;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public Carrier(int id, String name, String trackingUrl, List<Order> orders) {
		super();
		this.id = id;
		this.name = name;
		this.trackingUrl = trackingUrl;
		this.orders = orders;
	}
	
	public Carrier() {
	
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + ((trackingUrl == null) ? 0 : trackingUrl.hashCode());
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
		Carrier other = (Carrier) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (trackingUrl == null) {
			if (other.trackingUrl != null)
				return false;
		} else if (!trackingUrl.equals(other.trackingUrl))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Carrier [id=" + id + ", name=" + name + ", trackingUrl=" + trackingUrl + ", orders=" + orders + "]";
	}
	
	

}
