package fr.afcepf.groupem.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.groupem.dao.api.ILivraisonDao;
import fr.afcepf.groupem.entities.Livraison;

@Component
@Transactional
public class LivraisonDao implements ILivraisonDao {
	
	@PersistenceContext(unitName="WebServiceLogistique")
	private EntityManager entityManager;

	@Override
	public Livraison getLivraisonByTrackingCode(String TrackingCode) {
		return entityManager.createQuery("SELECT l FROM Livraison l WHERE l.trackingCode = :trackingCode", Livraison.class).setParameter("trackingCode", TrackingCode).getSingleResult();
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
	
	@Override
	public Livraison createLivraison(Livraison livraison){
		entityManager.persist(livraison);
		return livraison;
	}

	@Override
	public Livraison updateLivraison(Livraison livraison) {
		return entityManager.merge(livraison);
	}

}
