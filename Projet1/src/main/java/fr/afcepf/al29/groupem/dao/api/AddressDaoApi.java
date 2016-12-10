package fr.afcepf.al29.groupem.dao.api;

import fr.afcepf.al29.groupem.entities.Address;

public interface AddressDaoApi {
	
	// CRUD (Create - Read - Update - Delete)
	boolean createAddress(Address address);
	Address getAddressByUserId(int userId);
	Address updateAddressById(int addressId);
	boolean disableAddressById(int addressId);
	
	

}
