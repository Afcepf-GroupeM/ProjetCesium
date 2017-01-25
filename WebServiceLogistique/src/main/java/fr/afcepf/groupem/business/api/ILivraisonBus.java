package fr.afcepf.groupem.business.api;

import fr.afcepf.groupem.entities.Livraison;

public interface ILivraisonBus {
	
	Livraison getLivraisonByTrackingCode(String TrackingCode);
	Livraison getLivraisonByCountry(String Country);
	Livraison getLivraisonByDepartement(String departement);
	Livraison getLivraisonByName(String name);
	Livraison createLivraison(Livraison livraison);
	

}
