package fr.afcepf.groupem.business.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.groupem.business.api.ILivraisonBus;
import fr.afcepf.groupem.business.api.IStatutBus;
import fr.afcepf.groupem.dao.api.IAdresseDao;
import fr.afcepf.groupem.dao.api.ILivraisonDao;
import fr.afcepf.groupem.entities.Adresse;
import fr.afcepf.groupem.entities.Livraison;

@Component
@Transactional
public class LivraisonBus implements ILivraisonBus {
	
	@Autowired
	private ILivraisonDao livraisonDao;
	
	@Autowired
	private IAdresseDao adresseDao;
	
	@Autowired
	IStatutBus statutBus;

	@Override
	public Livraison getLivraisonByTrackingCode(String TrackingCode) {
		return livraisonDao.getLivraisonByTrackingCode(TrackingCode);
	}

	@Override
	public Livraison getLivraisonByCountry(String Country) {
		return livraisonDao.getLivraisonByCountry(Country);
	}

	@Override
	public Livraison getLivraisonByDepartement(String departement) {
		return livraisonDao.getLivraisonByDepartement(departement);
	}

	@Override
	public Livraison getLivraisonByName(String name) {
		return livraisonDao.getLivraisonByName(name);
	}

	@Override
	public Livraison createLivraison(Livraison livraison) {
		boolean checkAdresse = false;
		Adresse adresse = livraison.getAdresse();
		List<Adresse> adressesBase = adresseDao.getAll();
		
		for (Adresse adresseBase : adressesBase){
			checkAdresse = adresseBase.equals(adresse);
			
			if (checkAdresse){
				adresse = adresseBase;
				break;
			}
		}
		
		if (checkAdresse){
			livraison.setAdresse(adresse);
		}
		else{
			livraison.setAdresse(adresseDao.createAdresse(livraison.getAdresse()));
		}
		
		Livraison liv = livraisonDao.createLivraison(livraison);
		liv.setStatut(statutBus.createNewStatut(livraison));
		liv = livraisonDao.updateLivraison(liv);
		
		return liv;
	}

}
