package fr.afcepf.groupem.entities;

public class BankResponse {
	
	private String statut;
	private int transactionId;
	
	
	private String message;
	private int returnCode;
	
	
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	@Override
	public String toString() {
		return "BankResponse [statut=" + statut + ", "
							+ "transactionId=" + transactionId + ", "
							+ "returnCode=" + returnCode +","
							+ " message=" + message + "]";
	}
	
	
	
	

}
