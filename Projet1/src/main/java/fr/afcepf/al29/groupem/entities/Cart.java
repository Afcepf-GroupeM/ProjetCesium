package fr.afcepf.al29.groupem.entities;

import java.util.Date;

public class Cart {
	
	private int id;
	private Date creationDate;
	private int userId;
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}

	public Cart(int id, Date creationDate, int userId) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	

}
