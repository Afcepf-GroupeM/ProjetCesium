package fr.afcepf.al29.groupem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Table(name="cartline")
@Entity
public class CartLine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="unitprice")
	private float unitPrice;
	
	private int quantity;
	
	
	
	@ManyToOne
	@JoinColumn(name="cartid")
	private Cart cart;
	
	@OneToOne()
	@JoinColumn(name="itemid")
	private Item item;
	
	
	
	
	

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



	public Item getItem() {
		return item;
	}



	public void setItem(Item item) {
		this.item = item;
	}



	public CartLine(int id, float unitPrice, int quantity, Cart cart, Item item) {
		super();
		this.id = id;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.cart = cart;
		this.item = item;
	}
	
	public CartLine() {
	
	}
	
	public CartLine(int quantity, Item item) {
		super();
		this.quantity = quantity;
		this.item = item;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cart == null) ? 0 : cart.hashCode());
		result = prime * result + id;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + quantity;
		result = prime * result + Float.floatToIntBits(unitPrice);
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
		CartLine other = (CartLine) obj;
		if (cart == null) {
			if (other.cart != null)
				return false;
		} else if (!cart.equals(other.cart))
			return false;
		if (id != other.id)
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (quantity != other.quantity)
			return false;
		if (Float.floatToIntBits(unitPrice) != Float.floatToIntBits(other.unitPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CartLine [id=" + id + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", cart=" + cart
				+ ", item=" + item + "]";
	}

	
	
	//méthode qui permet de retourner le prix total d'une ligne panier c'est à dire le prix unitaire de l'article multiplié par la quantité
	
	public Float getSubTotal() {
		return item.getPrice()*quantity;
	}

	
	
	
	
}
