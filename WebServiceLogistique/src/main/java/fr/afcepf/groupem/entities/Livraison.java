package fr.afcepf.groupem.entities;

import java.util.Date;

public class Livraison {
	
	private int id;
	
	private String trackingCode;
	private int nbItems;
	
	private Date dateDemande;
	private Date datePriseEnCharge;
	private Date dateLivraison;
	
	
	private Statut statut;
	
	
	private Adresse adresse;

	
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
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	
	
	
	
	
	

}
