package fr.afcepf.al29.groupem.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="category")
@Entity
public class Category {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;
	
	
	
	@ManyToOne
	@JoinColumn(name="metacategoryid")
	private MetaCategory metaCategory;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="category")
	private List<Item> items;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="categories")
	private List<Coupon> coupons;
	
	
	
	
	
	

	

	
	public Category(int id, String name, MetaCategory metaCategory, List<Item> items, List<Coupon> coupons) {
		super();
		this.id = id;
		this.name = name;
		this.metaCategory = metaCategory;
		this.items = items;
		this.coupons = coupons;
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



	public MetaCategory getMetaCategory() {
		return metaCategory;
	}



	public void setMetaCategory(MetaCategory metaCategory) {
		this.metaCategory = metaCategory;
	}



	public List<Item> getItems() {
		return items;
	}



	public void setItems(List<Item> items) {
		this.items = items;
	}



	public List<Coupon> getCoupons() {
		return coupons;
	}



	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	
	
	
	


	


	public Category() {
		
	}
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coupons == null) ? 0 : coupons.hashCode());
		result = prime * result + id;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((metaCategory == null) ? 0 : metaCategory.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (coupons == null) {
			if (other.coupons != null)
				return false;
		} else if (!coupons.equals(other.coupons))
			return false;
		if (id != other.id)
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (metaCategory == null) {
			if (other.metaCategory != null)
				return false;
		} else if (!metaCategory.equals(other.metaCategory))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", metaCategory=" + metaCategory + ", items=" + items
				+ ", coupons=" + coupons + "]";
	}



	
	
	
	
	

}
