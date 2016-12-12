package fr.afcepf.al29.groupem.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Category {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;
	
	
	
	@ManyToOne
	@JoinColumn(name="id_metaCategory")
	private MetaCategory metaCategory;
	
	@OneToMany(mappedBy="category")
	private List<Item> item;
	
	@OneToMany(mappedBy="categories")
	private List<Coupon> coupon;
	
	
	
	
	public Category() {
	}
	
	
<<<<<<< HEAD
	public Category(int id, String name, int metaCategoryId) {
		this.id = id;
		this.name = name;
		this.metaCategoryId = metaCategoryId;
	}
=======
>>>>>>> branch 'master' of ssh://git@github.com/Afcepf-GroupeM/ProjetCesium.git
	
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



	public MetaCategory getMetaCategory() {
		return metaCategory;
	}



	public void setMetaCategory(MetaCategory metaCategory) {
		this.metaCategory = metaCategory;
	}



	public List<Item> getItem() {
		return item;
	}



	public void setItem(List<Item> item) {
		this.item = item;
	}
	
	
	
	

}
