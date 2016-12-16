package fr.afcepf.al29.groupem.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al29.groupem.business.api.AddressBusApi;
import fr.afcepf.al29.groupem.dao.api.AddressDaoApi;
import fr.afcepf.al29.groupem.entities.Address;
import fr.afcepf.al29.groupem.entities.ComplementAddress;
import fr.afcepf.al29.groupem.entities.RoadType;
import fr.afcepf.al29.groupem.entities.User;


@Transactional
@Component
public class AddressBusImpl implements AddressBusApi {

	@Autowired
	private AddressDaoApi addressDao;
	
	
	@Override
	public Address createAddress(String name, int number, ComplementAddress complement, RoadType roadType, String roadName, String city, String zipcode, String country, boolean isBilling, boolean isValid, User user) {
		Address address = new Address();
		address.setName(name);
		address.setNumber(number);
		address.setComplement(complement);
		address.setRoadType(roadType);
		address.setRoadName(roadName);
		address.setCity(city);
		address.setZipcode(zipcode);
		address.setCountry(country);
		address.setBilling(isBilling);
		address.setValid(isValid);
		address.setUser(user);
		
		Address addressCreated = addressDao.createAddress(address);
		return addressCreated;
	}

	@Override
	public List<Address> getAddressesByUserId(int userId) {
		return addressDao.getAddressesByUserId(userId);
	}

	@Override
	public Address getAddressById(int addressId) {
		return addressDao.getAddressById(addressId);
	}

	@Override
	public Address updateAddress(int id, String name, int number, ComplementAddress complement, RoadType roadType, String roadName, String city, String zipcode, String country, boolean isBilling, boolean isValid, User user) {
		Address address = new Address();
		address.setId(id);
		address.setName(name);
		address.setNumber(number);
		address.setComplement(complement);
		address.setRoadType(roadType);
		address.setRoadName(roadName);
		address.setCity(city);
		address.setZipcode(zipcode);
		address.setCountry(country);
		address.setBilling(isBilling);
		address.setValid(isValid);
		address.setUser(user);
		
		Address addressUpdated = addressDao.updateAddress(address);
		return addressUpdated;
	}

	@Override
	public Address disableAddress(int addressId) {
		Address addressToDisable = getAddressById(addressId);
		Address addressDisabled = addressDao.disableAddress(addressToDisable);
		return addressDisabled;
	}
}
