package fr.afcepf.al29.groupem.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name="userorder")
@Entity
public class Order {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="creationdate")
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	
	private float amount;
	
	@Column(name="typepayment")
	@Enumerated(EnumType.STRING)
	private TypePayment typePayment;
	
	
	@Column(name="trackingnumber")
	private String trackingNumber;
	
	@Column(name="orderstate")
	@Enumerated(EnumType.STRING)
	private OrderState state;
	
	
	
	@ManyToOne
	@JoinColumn(name="id_carrier")
	private Carrier carrier;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="order")
	private List<OrderLine> orderLines;
	
	@OneToOne()
	@JoinColumn(name="billingadressid")
	private Address adresseFacturation;
	
	@OneToOne()
	@JoinColumn(name="shippingadressid")
	private Address adresseLivraison;
	
	@ManyToOne()
	@JoinColumn(name="userid")
	private User user;
	
	@OneToOne()
	@JoinColumn(name="id_coupon")
	private Coupon coupon;
	
	
	
	public Order() {
	}

	
	
	
	public List<OrderLine> getOrderLines() {
		return orderLines;
	}




	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}




	public Address getAdresseFacturation() {
		return adresseFacturation;
	}




	public void setAdresseFacturation(Address adresseFacturation) {
		this.adresseFacturation = adresseFacturation;
	}




	public Address getAdresseLivraison() {
		return adresseLivraison;
	}




	public void setAdresseLivraison(Address adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public Coupon getCoupon() {
		return coupon;
	}




	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
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




	public Order(int id, Date creationDate, float amount, TypePayment typePayment, String trackingNumber,
			OrderState state, Carrier carrier, List<OrderLine> orderLines, Address adresseFacturation,
			Address adresseLivraison, User user, Coupon coupon) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.amount = amount;
		this.typePayment = typePayment;
		this.trackingNumber = trackingNumber;
		this.state = state;
		this.carrier = carrier;
		this.orderLines = orderLines;
		this.adresseFacturation = adresseFacturation;
		this.adresseLivraison = adresseLivraison;
		this.user = user;
		this.coupon = coupon;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresseFacturation == null) ? 0 : adresseFacturation.hashCode());
		result = prime * result + ((adresseLivraison == null) ? 0 : adresseLivraison.hashCode());
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + ((carrier == null) ? 0 : carrier.hashCode());
		result = prime * result + ((coupon == null) ? 0 : coupon.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((orderLines == null) ? 0 : orderLines.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((trackingNumber == null) ? 0 : trackingNumber.hashCode());
		result = prime * result + ((typePayment == null) ? 0 : typePayment.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Order other = (Order) obj;
		if (adresseFacturation == null) {
			if (other.adresseFacturation != null)
				return false;
		} else if (!adresseFacturation.equals(other.adresseFacturation))
			return false;
		if (adresseLivraison == null) {
			if (other.adresseLivraison != null)
				return false;
		} else if (!adresseLivraison.equals(other.adresseLivraison))
			return false;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (carrier == null) {
			if (other.carrier != null)
				return false;
		} else if (!carrier.equals(other.carrier))
			return false;
		if (coupon == null) {
			if (other.coupon != null)
				return false;
		} else if (!coupon.equals(other.coupon))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (id != other.id)
			return false;
		if (orderLines == null) {
			if (other.orderLines != null)
				return false;
		} else if (!orderLines.equals(other.orderLines))
			return false;
		if (state != other.state)
			return false;
		if (trackingNumber == null) {
			if (other.trackingNumber != null)
				return false;
		} else if (!trackingNumber.equals(other.trackingNumber))
			return false;
		if (typePayment != other.typePayment)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}




	@Override
	public String toString() {
		return "Order [id=" + id + ", creationDate=" + creationDate + ", amount=" + amount + ", typePayment="
				+ typePayment + ", trackingNumber=" + trackingNumber + ", state=" + state + ", carrier=" + carrier
				+ ", orderLines=" + orderLines + ", adresseFacturation=" + adresseFacturation + ", adresseLivraison="
				+ adresseLivraison + ", user=" + user + ", coupon=" + coupon + "]";
	}
	
	

}
