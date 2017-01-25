package fr.afcepf.groupem.entities;

public class DemandeLivraison {
	
	private Adresse adresse;
	private int nbItem;
	private int delaiMax; //nb jours max pour livrer
	
	
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
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
