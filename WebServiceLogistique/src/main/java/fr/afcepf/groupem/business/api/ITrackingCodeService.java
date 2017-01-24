package fr.afcepf.groupem.business.api;

import fr.afcepf.groupem.entities.Transporteur;

public interface ITrackingCodeService {
	
	Transporteur getTransporteurByTrackingCode(String trackingCode);
	String generateTrackingCode(Transporteur transporteur, String country, String zipcode);
	
	

}
