package fr.afcepf.al29.groupem.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al29.groupem.dao.api.AddressDaoApi;
import fr.afcepf.al29.groupem.entities.Address;

@Transactional
@Component
public class AddressDaoImpl implements AddressDaoApi {
	
	@PersistenceContext(unitName="Projet1")
	private EntityManager entityManager;
	
	@Override
	public Address createAddress(Address address) {
		entityManager.persist(address);
		return address;
	}

	@Override
	public Address getAddressByUserId(int userId) {
		Address address = entityManager.find(Address.class, userId);
		return address;
	}

	@Override
	public Address updateAddress(Address address) {
		entityManager.merge(address);
		return address;
	}

	@Override
	public boolean disableAddress(Address address) {
		address.setValide(false);
		updateAddress(address);
		return (getAddressByUserId(address.getUser().getId()).isValide());
	}

}
