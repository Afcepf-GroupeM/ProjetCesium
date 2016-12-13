package fr.afcepf.al29.groupem.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al29.groupem.business.api.AddressBusApi;
import fr.afcepf.al29.groupem.dao.api.AddressDaoApi;
import fr.afcepf.al29.groupem.entities.Address;


@Transactional
@Component
public class AddressBusImpl implements AddressBusApi {

	@Autowired
	private AddressDaoApi addressDao;
	
	
	@Override
	public Address createAddress(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> getAddressesByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address getAddressById(int addressId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address updateAddress(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address disableAddress(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

}
