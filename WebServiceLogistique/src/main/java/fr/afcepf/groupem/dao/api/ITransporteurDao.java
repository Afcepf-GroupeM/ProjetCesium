package fr.afcepf.groupem.dao.api;

import java.util.List;

import fr.afcepf.groupem.entities.Transporteur;

public interface ITransporteurDao {
	
	Transporteur getTransporteurByTrackingCode(String trackingCode);
	List<Transporteur> getAllTransporteurs();

}
