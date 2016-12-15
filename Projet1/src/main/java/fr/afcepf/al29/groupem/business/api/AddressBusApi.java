package fr.afcepf.al29.groupem.business.api;

import java.util.List;

import fr.afcepf.al29.groupem.entities.Address;
import fr.afcepf.al29.groupem.entities.ComplementAddress;
import fr.afcepf.al29.groupem.entities.RoadType;
import fr.afcepf.al29.groupem.entities.User;

public interface AddressBusApi {

	Address createAddress(String name, int number, ComplementAddress complement, RoadType roadType, String roadName, String city, String zipcode, String country, boolean isBilling, boolean isValid, User user);
	List<Address> getAddressesByUserId(int userId);
	Address getAddressById(int addressId);
	Address updateAddress(int id, String name, int number, ComplementAddress complement, RoadType roadType, String roadName, String city, String zipcode, String country, boolean isBilling, boolean isValid, User user);
	Address disableAddress(Address address);
	
}
