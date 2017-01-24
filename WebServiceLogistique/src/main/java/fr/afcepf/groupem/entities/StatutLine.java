package fr.afcepf.groupem.entities;

import java.util.Date;

public class StatutLine {
	
	private int id;
	private Date dateUpdate;
	private String locationUpdate;
	private String detailsUpdate;
	private int idstatut;
	
	
	public Date getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	public String getLocationUpdate() {
		return locationUpdate;
	}
	public void setLocationUpdate(String locationUpdate) {
		this.locationUpdate = locationUpdate;
	}
	public String getDetailsUpdate() {
		return detailsUpdate;
	}
	public void setDetailsUpdate(String detailsUpdate) {
		this.detailsUpdate = detailsUpdate;
	}
	
	

}
