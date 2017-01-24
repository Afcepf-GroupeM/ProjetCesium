package fr.afcepf.groupem.business.api;

import fr.afcepf.groupem.entities.Transporteur;

public interface ITrackingCodeService {
	
	String generateTrackingCode(Transporteur transporteur);
	Transporteur getTransporteurByTrackingCode(String trackingCode);
	
	

}
