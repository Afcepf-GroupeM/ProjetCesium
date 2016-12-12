package fr.afcepf.al29.groupem.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private float rating;
	
	private String comment;
	
	private Date creationDate;
	
	@ManyToOne
	@JoinColumn(name="id_item")
	private Item items;
	
<<<<<<< HEAD
	public Review(int id, float rating, String comment, Date creationDate, int userId, int itemId) {
		this.id = id;
		this.rating = rating;
		this.comment = comment;
		this.creationDate = creationDate;
		this.userId = userId;
		this.itemId = itemId;
	}
=======
	
	
	
>>>>>>> branch 'master' of ssh://git@github.com/Afcepf-GroupeM/ProjetCesium.git

	public Review() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Item getItems() {
		return items;
	}

	public void setItems(Item items) {
		this.items = items;
	}

	
	
	
	

}
