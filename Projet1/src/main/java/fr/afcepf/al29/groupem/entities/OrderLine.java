package fr.afcepf.al29.groupem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class OrderLine {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private float unitPrice;
	
	private int quantity;
	
	@OneToOne()
	@JoinColumn(name="id_item")
	private Item item;
	
	
	@ManyToOne
	@JoinColumn(name="id_order")
	private Order order;
	
	
	
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

	
	
	

}
