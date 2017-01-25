package fr.afcepf.groupem.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.groupem.dao.api.IAdresseDao;
import fr.afcepf.groupem.entities.Adresse;

@Component
@Transactional
public class AdresseDao implements IAdresseDao {
	
	
	@PersistenceContext(unitName="WebServiceLogistique")
	private EntityManager entityManager;

	@Override
	public Adresse getAdresseByLivraisonId(int idLivraison) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Adresse createAdresse(Adresse adresse) {
		entityManager.persist(adresse); 
		return adresse;
	}
	
	@Override
	public Adresse updateAdresse(Adresse adresse){
		entityManager.merge(adresse);
		return adresse;
	}

}
