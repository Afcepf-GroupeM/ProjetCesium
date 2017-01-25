package fr.afcepf.groupem.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.groupem.business.api.ILivraisonBus;
import fr.afcepf.groupem.business.api.IStatutBus;
import fr.afcepf.groupem.dao.api.ILivraisonDao;
import fr.afcepf.groupem.entities.Livraison;

@Component
@Transactional
public class LivraisonBus implements ILivraisonBus {
	
	@Autowired
	private ILivraisonDao livraisonDao;
	
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
		
		livraison.setStatut(statutBus.createNewStatut());
		return livraisonDao.createLivraison(livraison);
	}

}
