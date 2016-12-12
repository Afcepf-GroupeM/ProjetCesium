package fr.afcepf.al29.groupem.entities;

import java.util.Date;

public class Order {
	
	private enum TypePayment {CarteBleue,MasterCard,Visa,AmericanExpress};
	private enum OrderState {EnPreparation,CommandePrete,AcheminementChezLeTransporteur,EnAttenteDePaiement,Expediee,Livree};
	
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
	
	public Order() {
	}

	public Order(int id, Date creationDate, float amount, TypePayment typePayment, String trackingNumber, int carrierId, int userId, int shippingAddressId, int billingAddressId, int couponId, OrderState state) {
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
	
	

}
