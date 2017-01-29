package fr.afcepf.groupem.entities;

import java.math.BigDecimal;
import java.util.Date;

public class BankRequest {
	
//  Client
	private String lastname;
//	Credit Card
	private String cardNumber;
	private int monthExpiration;
	private int yearExpiration;
	private String cryptogram;
	
//	Order
	private BigDecimal amount;
	
//	Company
	private String companyName = "Mix HighTech";

	
	
	
	
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public String getCryptogram() {
		return cryptogram;
	}

	public void setCryptogram(String cryptogram) {
		this.cryptogram = cryptogram;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getMonthExpiration() {
		return monthExpiration;
	}

	public void setMonthExpiration(int monthExpiration) {
		this.monthExpiration = monthExpiration;
	}

	public int getYearExpiration() {
		return yearExpiration;
	}

	public void setYearExpiration(int yearExpiration) {
		this.yearExpiration = yearExpiration;
	}
	
	
	
	
	

}
