package fr.afcepf.groupem.dao.api;

import java.util.List;

import fr.afcepf.groupem.entities.Livraison;

public interface ILivraisonDao {
	
	Livraison getLivraisonByTrackingCode(String TrackingCode);
	Livraison getLivraisonByCountry(String Country);
	Livraison getLivraisonByDepartement(String departement);
	Livraison getLivraisonByName(String name);
	List<Livraison> getAllLivraisons();
	Livraison createLivraison(Livraison livraison);
	

}
