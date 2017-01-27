package fr.afcepf.al29.groupem.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
@Entity
@Table(name="operation")
public class Operation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	@Column
    private String label;


    private BigDecimal amount;


    private Date dateOp;
    
    @ManyToOne
    @JoinColumn(name="accountid")
    private Account account;
    
    
    
    public Operation() {
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getDateOp() {
		return dateOp;
	}

	public void setDateOp(Date dateOp) {
		this.dateOp = dateOp;
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Operation [id=" + id + ", label=" + label + ", amount=" + amount + ", dateOp=" + dateOp + "]";
	}
	
	
	
}