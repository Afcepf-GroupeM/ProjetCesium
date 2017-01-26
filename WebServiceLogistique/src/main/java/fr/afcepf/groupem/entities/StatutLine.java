package fr.afcepf.groupem.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="statutline")
public class StatutLine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="dateupdate")
	@Temporal(TemporalType.DATE)
	private Date dateUpdate;
	
	@Column(name="locationupdate")
	private String locationUpdate;
	
	@Column(name="detailsupdate")
	private String detailsUpdate;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "idstatut")
	private Statut statut;
	
	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	
	
	
	
	

}
