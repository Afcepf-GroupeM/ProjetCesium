package fr.afcepf.al29.groupem.dto;

public class ResponseBank {
	private String status;
	private Integer referenceNumber;
	
	public ResponseBank() {
		super();
	}	
	
	public ResponseBank(String status, Integer referenceNumber) {
		super();
		this.status = status;
		this.referenceNumber = referenceNumber;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(Integer referenceNumber) {
		this.referenceNumber = referenceNumber;
	}	

}
