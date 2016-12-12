package fr.afcepf.al29.groupem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CartLine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private float unitPrice;
	
	private int quantity;
	
	
	
	@ManyToOne
	@JoinColumn(name="id_cart")
	private Cart cart;
	
	@OneToOne()
	@JoinColumn(name="id_item")
	private Item item;
	
	
	
	
	public CartLine() {
	}
	
<<<<<<< HEAD
	public CartLine(int id, float unitPrice, int quantity, int cartId, int itemId) {
		this.id = id;
		this.quantity = quantity;
		this.cartId = cartId;
		this.itemId = itemId;
		this.unitPrice = unitPrice;
	}
=======
	
>>>>>>> branch 'master' of ssh://git@github.com/Afcepf-GroupeM/ProjetCesium.git

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	

}
