package fr.afcepf.al29.groupem.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Coupon {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String code;
	
	private Date startDate;
	
	private Date endDate;
	
	private float rebate;
	
	private String description;
	
	private String imagePath;
<<<<<<< HEAD
	private int categoryId;
	
	public Coupon() {
		
	}
	
	public Coupon(int id, String code, Date startDate, Date endDate, float rebate, String description, String imagePath, int categoryId) {
		this.id = id;
		this.code = code;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rebate = rebate;
		this.description = description;
		this.imagePath = imagePath;
		this.categoryId = categoryId;
	}

=======
	
	
	@ManyToOne
	@JoinColumn(name="id_category")
	private Category categories;
	
	
>>>>>>> branch 'master' of ssh://git@github.com/Afcepf-GroupeM/ProjetCesium.git
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public float getRebate() {
		return rebate;
	}
	public void setRebate(float rebate) {
		this.rebate = rebate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
	

}
