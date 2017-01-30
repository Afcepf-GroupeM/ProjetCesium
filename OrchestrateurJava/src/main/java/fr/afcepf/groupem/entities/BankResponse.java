package fr.afcepf.groupem.entities;

public class BankResponse {
	
	private String status;
	private int referenceNumber;
	
	
	private String message;
	private int returnCode;
	
	
	public String getStatut() {
		return status;
	}
	public void setStatut(String statut) {
		this.status = statut;
	}
	public int getTransactionId() {
		return referenceNumber;
	}
	public void setTransactionId(int transactionId) {
		this.referenceNumber = transactionId;
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
		return "BankResponse [statut=" + status + ", "
							+ "transactionId=" + referenceNumber + ", "
							+ "returnCode=" + returnCode +","
							+ " message=" + message + "]";
	}
	
	
	
	

}
