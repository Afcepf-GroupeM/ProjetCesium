package fr.afcepf.al29.groupem.entities;

public class Carrier {
	
	private int id;
	private String name;
	private String trackingUrl;
	
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

	

}
