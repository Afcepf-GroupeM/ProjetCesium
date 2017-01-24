package fr.afcepf.groupem.dao.api;

import fr.afcepf.groupem.entities.Transporteur;

public interface ITransporteurDao {
	
	Transporteur getTransporteurByTrackingCode(String trackingCode);

}
