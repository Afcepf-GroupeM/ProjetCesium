package fr.afcepf.al29.groupem.entities;

import java.util.Date;
import java.util.List;

public class Cart {
	
	private int id;
	private Date creationDate;
	private int userId;
	private List<CartLine> cartLine;
	
	public Cart() {
	}

	public Cart(int id, Date creationDate, int userId,List<CartLine> cartLine ) {
		this.id = id;
		this.creationDate = creationDate;
		this.userId = userId;
		this.cartLine = cartLine;
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

	public List<CartLine> getCartLine() {
		return cartLine;
	}

	public void setCartLine(List<CartLine> cartLine) {
		this.cartLine = cartLine;
	}
	
	

}
