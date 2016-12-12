package fr.afcepf.al29.groupem.entities;

public class OrderLine {
	
	private int id;
	private float unitPrice;
	private int quantity;
	private int orderId;
	private int itemId;
	
	public OrderLine() {
	}

	public OrderLine(int id, float unitPrice, int quantity, int orderId, int itemId) {
		this.id = id;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.orderId = orderId;
		this.itemId = itemId;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	

}
