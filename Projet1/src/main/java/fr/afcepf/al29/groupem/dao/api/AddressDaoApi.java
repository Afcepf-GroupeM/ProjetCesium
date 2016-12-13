package fr.afcepf.al29.groupem.dao.api;

import java.util.List;

import fr.afcepf.al29.groupem.entities.Address;

public interface AddressDaoApi {
	
	// CRUD (Create - Read - Update - Delete)
	Address createAddress(Address address);
	List<Address> getAddressesByUserId(int userId);
	Address getAddressById(int addressId);
	Address updateAddress(Address address);
	Address disableAddress(Address address);
	
	

}
