package fr.afcepf.groupem.entities;

import java.math.BigDecimal;
import java.util.Date;

public class BankRequest {
	
//  Client
	private String lastName;
//	Credit Card
	private String numberCard;
	private int monthExpiredCard;
	private int yearExpiredCard;
	private String cryptogram;
	
//	Order
	private BigDecimal amount;
	
//	Company
	private String nameCompany = "Mix HighTech";

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNumberCard() {
		return numberCard;
	}

	public void setNumberCard(String numberCard) {
		this.numberCard = numberCard;
	}

	public int getMonthExpiredCard() {
		return monthExpiredCard;
	}

	public void setMonthExpiredCard(int monthExpiredCard) {
		this.monthExpiredCard = monthExpiredCard;
	}

	public int getYearExpiredCard() {
		return yearExpiredCard;
	}

	public void setYearExpiredCard(int yearExpiredCard) {
		this.yearExpiredCard = yearExpiredCard;
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

	public String getNameCompany() {
		return nameCompany;
	}

	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}

	
	
	
	
	
	
	
	
	

}
