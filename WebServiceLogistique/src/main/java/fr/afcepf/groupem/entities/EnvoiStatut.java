package fr.afcepf.groupem.entities;

public class EnvoiStatut {

	private Livraison livraison;
	private StatutLine statutLine;
	private Transporteur transporteur;
	
	public Livraison getLivraison() {
		return livraison;
	}
	
	public void setLivraison(Livraison livraison) {
		this.livraison = livraison;
	}
	
	public StatutLine getStatutLine() {
		return statutLine;
	}
	
	public void setStatutLine(StatutLine statutLine) {
		this.statutLine = statutLine;
	}

	public Transporteur getTransporteur() {
		return transporteur;
	}

	public void setTransporteur(Transporteur transporteur) {
		this.transporteur = transporteur;
	}
	

}
