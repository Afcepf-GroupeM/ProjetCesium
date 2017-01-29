package fr.afcepf.al29.groupem.dto;

import java.math.BigDecimal;
import java.util.Date;

public class RequestData {
	private String nameCompany;
	private String numberCard;
	private Integer yearExpiredCard;
	private Integer monthExpiredCard;
	private String cryptogram;
	private String lastName;
	private BigDecimal amount;
	
	public RequestData() {
		super();
	}	
	
	public RequestData(String nameCompany, String numberCard, Integer yearExpiredCard, Integer monthExpiredCard,
			String cryptogram, String lastName, BigDecimal amount) {
		super();
		this.nameCompany = nameCompany;
		this.numberCard = numberCard;
		this.yearExpiredCard = yearExpiredCard;
		this.monthExpiredCard = monthExpiredCard;
		this.cryptogram = cryptogram;
		this.lastName = lastName;
		this.amount = amount;
	}

	public String getNameCompany() {
		return nameCompany;
	}
	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}
	public String getNumberCard() {
		return numberCard;
	}
	public void setNumberCard(String numberCard) {
		this.numberCard = numberCard;
	}	
	public Integer getYearExpiredCard() {
		return yearExpiredCard;
	}
	public void setYearExpiredCard(Integer yearExpiredCard) {
		this.yearExpiredCard = yearExpiredCard;
	}
	public Integer getMonthExpiredCard() {
		return monthExpiredCard;
	}
	public void setMonthExpiredCard(Integer monthExpiredCard) {
		this.monthExpiredCard = monthExpiredCard;
	}
	public String getCryptogram() {
		return cryptogram;
	}
	public void setCryptogram(String cryptogram) {
		this.cryptogram = cryptogram;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "RequestData [nameCompany=" + nameCompany + ", numberCard=" + numberCard + ", yearExpiredCard="
				+ yearExpiredCard + ", monthExpiredCard=" + monthExpiredCard + ", cryptogram=" + cryptogram
				+ ", lastName=" + lastName + ", amount=" + amount + "]";
	}		
	
}
