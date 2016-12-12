package fr.afcepf.al29.groupem.entities;

public class CartLine {
	
	private int id;
	private float unitPrice;
	private int quantity;
	private int cartId;
	private int itemId;
	
	public CartLine() {
	}
	
	public CartLine(int id, float unitPrice, int quantity, int cartId, int itemId) {
		this.id = id;
		this.quantity = quantity;
		this.cartId = cartId;
		this.itemId = itemId;
		this.unitPrice = unitPrice;
	}

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
	
	

}
