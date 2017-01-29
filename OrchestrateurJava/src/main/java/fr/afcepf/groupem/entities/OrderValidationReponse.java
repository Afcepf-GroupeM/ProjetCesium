package fr.afcepf.groupem.entities;

import java.util.Date;

public class OrderValidationReponse {
	
	private String message;
	private int returnCode;
	
	private String trackingCode;
	private Date dateLivraison;
	
	private int transactionId;
	
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getErrorCode() {
		return returnCode;
	}

	public void setErrorCode(int errorCode) {
		this.returnCode = errorCode;
	}

	public String getTrackingCode() {
		return trackingCode;
	}

	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}

	public Date getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Date dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	} 
	
	
	
	
	

}
