package fr.afcepf.groupem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transporteur")
public class Transporteur {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String url;
	
	@Column(name="trackingprefix")
	private String trackingPrefix;
	
	@Column(name="delailivraisongaranti")
	private int delaiLivraisonGaranti;
	
	
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTrackingPrefix() {
		return trackingPrefix;
	}
	public void setTrackingPrefix(String trackingPrefix) {
		this.trackingPrefix = trackingPrefix;
	}
	public int getDelaiLivraisonGaranti() {
		return delaiLivraisonGaranti;
	}
	public void setDelaiLivraisonGaranti(int delaiLivraisonGaranti) {
		this.delaiLivraisonGaranti = delaiLivraisonGaranti;
	}
	
	
	
	
	
	

}
