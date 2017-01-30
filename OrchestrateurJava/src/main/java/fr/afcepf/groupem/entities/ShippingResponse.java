package fr.afcepf.groupem.entities;

import java.util.Date;

public class ShippingResponse {
	
	private String trackingCode;
	private Date datePriseEnCharge;
	private Date dateLivraison;
	
	private String transporteurName;
	private String url;
	
	private int returnCode;
	private String message;
	
	
	
	public String getTrackingCode() {
		return trackingCode;
	}
	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}
	public Date getDatePriseEnCharge() {
		return datePriseEnCharge;
	}
	public void setDatePriseEnCharge(Date datePriseEnCharge) {
		this.datePriseEnCharge = datePriseEnCharge;
	}
	public Date getDateLivraison() {
		return dateLivraison;
	}
	public void setDateLivraison(Date dateLivraison) {
		this.dateLivraison = dateLivraison;
	}
	public int getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ShippingResponse [trackingCode=" + trackingCode + ", datePriseEnCharge=" + datePriseEnCharge
				+ ", dateLivraison=" + dateLivraison + ", returnCode=" + returnCode + ", message=" + message + "]";
	}
	public String getTransporteurName() {
		return transporteurName;
	}
	public void setTransporteurName(String transporteurName) {
		this.transporteurName = transporteurName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	
	

}
