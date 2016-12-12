package fr.afcepf.al29.groupem.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private Date creationDate;
	
	
	
	@OneToOne()
	@JoinColumn(name="id_user")
	private User user;
	
	@OneToMany(mappedBy="cart")
	private List<CartLine> cartLine;
	
	public Cart() {
	}

<<<<<<< HEAD
	public Cart(int id, Date creationDate, int userId,List<CartLine> cartLine ) {
		this.id = id;
		this.creationDate = creationDate;
		this.userId = userId;
		this.cartLine = cartLine;
	}
=======
	
>>>>>>> branch 'master' of ssh://git@github.com/Afcepf-GroupeM/ProjetCesium.git

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

	
	

	public List<CartLine> getCartLine() {
		return cartLine;
	}

	public void setCartLine(List<CartLine> cartLine) {
		this.cartLine = cartLine;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}
	
	

}
