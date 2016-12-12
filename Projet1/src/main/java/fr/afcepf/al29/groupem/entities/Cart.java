package fr.afcepf.al29.groupem.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Table(name="cart")
@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="creationdate")
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	
	
	
	@OneToOne()
	@JoinColumn(name="userid")
	private User user;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="cart")
	private List<CartLine> cartLines;
	
	


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



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public List<CartLine> getCartLines() {
		return cartLines;
	}



	public void setCartLines(List<CartLine> cartLines) {
		this.cartLines = cartLines;
	}



	public Cart(int id, Date creationDate, User user, List<CartLine> cartLines) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.user = user;
		this.cartLines = cartLines;
	}
	
	public Cart() {
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartLines == null) ? 0 : cartLines.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + id;
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
		Cart other = (Cart) obj;
		if (cartLines == null) {
			if (other.cartLines != null)
				return false;
		} else if (!cartLines.equals(other.cartLines))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (id != other.id)
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
		return "Cart [id=" + id + ", creationDate=" + creationDate + ", user=" + user + ", cartLines=" + cartLines
				+ "]";
	}
	
	

}
