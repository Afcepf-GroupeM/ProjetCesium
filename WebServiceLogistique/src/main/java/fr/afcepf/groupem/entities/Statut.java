package fr.afcepf.groupem.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="statut")
public class Statut {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="statut")
	private List<StatutLine> statutLines;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="idlivraison")
	private Livraison livraison;
	
	
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
	public Livraison getLivraison() {
		return livraison;
	}
	public void setLivraison(Livraison livraison) {
		this.livraison = livraison;
	}
	
	
	

}
