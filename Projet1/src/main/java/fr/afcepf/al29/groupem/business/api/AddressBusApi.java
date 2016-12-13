package fr.afcepf.al29.groupem.business.api;

import java.util.List;

import fr.afcepf.al29.groupem.entities.Address;

public interface AddressBusApi {

	Address createAddress(Address address);
	List<Address> getAddressesByUserId(int userId);
	Address getAddressById(int addressId);
	Address updateAddress(Address address);
	Address disableAddress(Address address);
	
}
