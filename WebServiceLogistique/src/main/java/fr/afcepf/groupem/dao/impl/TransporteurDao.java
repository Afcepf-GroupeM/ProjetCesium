package fr.afcepf.groupem.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.groupem.dao.api.ITransporteurDao;
import fr.afcepf.groupem.entities.Transporteur;

@Component
@Transactional
public class TransporteurDao implements ITransporteurDao {
	
	
	@PersistenceContext(unitName="WebServiceLogistique")
	private EntityManager entityManager;

	@Override
	public Transporteur getTransporteurByTrackingCode(String trackingCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transporteur> getAllTransporteurs() {
		List<Transporteur> listeTr = entityManager.createQuery("SELECT transp FROM Transporteur transp",Transporteur.class).getResultList();
		
		return listeTr;
	}

}
