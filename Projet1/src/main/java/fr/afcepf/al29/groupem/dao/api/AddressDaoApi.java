package fr.afcepf.al29.groupem.dao.api;

import fr.afcepf.al29.groupem.entities.Address;

public interface AddressDaoApi {
	
	// CRUD (Create - Read - Update - Delete)
	Address createAddress(Address address);
	Address getAddressByUserId(int userId);
	Address updateAddress(Address address);
	boolean disableAddress(Address address);
	
	

}
