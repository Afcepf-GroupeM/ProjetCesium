package fr.afcepf.al29.groupem.entities;

import java.util.Date;

public class Review {
	
	private int id;
	private float rating;
	private String comment;
	private Date creationDate;
	private int userId;
	private int itemId;
	
	public Review(int id, float rating, String comment, Date creationDate, int userId, int itemId) {
		this.id = id;
		this.rating = rating;
		this.comment = comment;
		this.creationDate = creationDate;
		this.userId = userId;
		this.itemId = itemId;
	}

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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	
	

}
