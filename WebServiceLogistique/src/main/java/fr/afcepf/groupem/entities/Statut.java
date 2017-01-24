package fr.afcepf.groupem.entities;

import java.util.List;

public class Statut {
	
	private int id;
	private List<StatutLine> statutLines;
	private int idlivraison;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<StatutLine> getStatutLines() {
		return statutLines;
	}
	public void setStatutLines(List<StatutLine> statutLines) {
		this.statutLines = statutLines;
	}
	public int getIdlivraison() {
		return idlivraison;
	}
	public void setIdlivraison(int idlivraison) {
		this.idlivraison = idlivraison;
	}
	
	

}
