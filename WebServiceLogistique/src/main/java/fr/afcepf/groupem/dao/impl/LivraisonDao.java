package fr.afcepf.groupem.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.groupem.dao.api.ILivraisonDao;
import fr.afcepf.groupem.entities.Livraison;

@Component
@Transactional
public class LivraisonDao implements ILivraisonDao {

	@Override
	public Livraison getLivraisonByTrackingCode(String TrackingCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Livraison getLivraisonByCountry(String Country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Livraison getLivraisonByDepartement(String departement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Livraison getLivraisonByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Livraison> getAllLivraisons() {
		// TODO Auto-generated method stub
		return null;
	}

}
