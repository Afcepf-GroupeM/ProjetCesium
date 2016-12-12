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

	
	
	
	
	public Item getItem() {
		return item;
	}





	public void setItem(Item item) {
		this.item = item;
	}





	public Order getOrder() {
		return order;
	}





	public void setOrder(Order order) {
		this.order = order;
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





	public OrderLine(int id, float unitPrice, int quantity, Item item, Order order) {
		super();
		this.id = id;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.item = item;
		this.order = order;
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
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
		OrderLine other = (OrderLine) obj;
		if (id != other.id)
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (quantity != other.quantity)
			return false;
		if (Float.floatToIntBits(unitPrice) != Float.floatToIntBits(other.unitPrice))
			return false;
		return true;
	}





	@Override
	public String toString() {
		return "OrderLine [id=" + id + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", item=" + item
				+ ", order=" + order + "]";
	}

	
	
	

}
