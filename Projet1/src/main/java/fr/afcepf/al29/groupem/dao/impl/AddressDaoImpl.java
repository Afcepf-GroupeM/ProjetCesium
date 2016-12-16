package fr.afcepf.al29.groupem.dao.impl;

import java.util.List;

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
	public List<Address> getAddressesByUserId(int userId) {
		return entityManager.createQuery("SELECT adr FROM Address adr INNER JOIN adr.user usr WHERE usr.id = :userId AND adr.isValid = 1", Address.class).setParameter("userId", userId).getResultList();
	}
	
	@Override
	public Address getAddressById(int addressId) {
		return entityManager.find(Address.class, addressId);
	}

	@Override
	public Address updateAddress(Address address) {
		entityManager.merge(address);
		return address;
	}

	@Override
	public Address disableAddress(Address address) {
		address.setBilling(false);
		address.setValid(false);
		updateAddress(address);
		return (address);
	}
}
