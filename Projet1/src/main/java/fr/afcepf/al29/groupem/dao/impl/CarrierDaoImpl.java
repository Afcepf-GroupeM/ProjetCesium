package fr.afcepf.al29.groupem.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.dao.api.CarrierDaoApi;
import fr.afcepf.al29.groupem.entities.Carrier;

@Transactional
@Component
public class CarrierDaoImpl implements CarrierDaoApi{

	@PersistenceContext(unitName="Projet1")
	private EntityManager entityManager;
	
	@Override
	public Carrier createCarrier(Carrier carrier) {
		entityManager.persist(carrier);
		return carrier;
	}

	@Override
	public Carrier getCarrierById(int carrierId) {
		return entityManager.find(Carrier.class, carrierId);
	}

	@Override
	public Carrier updateCarrier(Carrier carrier) {
		entityManager.merge(carrier);
		return carrier;
	}

	@Override
	public boolean deleteCarrier(int carrierId) {
		entityManager.remove(getCarrierById(carrierId));
		return (getCarrierById(carrierId) == null);
	}

}
