package fr.afcepf.al29.groupem.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Order {
	
	private enum TypePayment {CarteBleue,MasterCard,Visa,AmericanExpress};
	private enum OrderState {EnPreparation,EnAttenteDePaiement,Expediee,Livree};
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private Date creationDate;
	
	private float amount;
	
	private TypePayment typePayment;
	
	private String trackingNumber;
	
	private int carrierId;
	
	private int userId;
	
	private int shippingAddressId;
	
	private int billingAddressId;
	
	private int couponId;
	
	private OrderState state;
	
	
	
	@ManyToOne
	@JoinColumn(name="id_carrier")
	private Carrier carrier;
	
	@OneToMany(mappedBy="order")
	private List<OrderLine> orderLines;
	
	
	
	public Order() {
	}

	public Order(int id, Date creationDate, float amount, TypePayment typePayment, String trackingNumber, int carrierId,
			int userId, int shippingAddressId, int billingAddressId, int couponId, OrderState state) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.amount = amount;
		this.typePayment = typePayment;
		this.trackingNumber = trackingNumber;
		this.carrierId = carrierId;
		this.userId = userId;
		this.shippingAddressId = shippingAddressId;
		this.billingAddressId = billingAddressId;
		this.couponId = couponId;
		this.state = state;
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

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public TypePayment getTypePayment() {
		return typePayment;
	}

	public void setTypePayment(TypePayment typePayment) {
		this.typePayment = typePayment;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public int getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(int carrierId) {
		this.carrierId = carrierId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getShippingAddressId() {
		return shippingAddressId;
	}

	public void setShippingAddressId(int shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}

	public int getBillingAddressId() {
		return billingAddressId;
	}

	public void setBillingAddressId(int billingAddressId) {
		this.billingAddressId = billingAddressId;
	}

	public int getCouponId() {
		return couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
	
	

}
