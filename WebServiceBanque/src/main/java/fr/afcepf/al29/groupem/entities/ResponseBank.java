package fr.afcepf.al29.groupem.entities;

public class ResponseBank {
	private String status;
	private Integer code;
	public ResponseBank() {
		super();
	}
	
	public ResponseBank(String status, Integer code) {
		super();
		this.status = status;
		this.code = code;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
	

}
