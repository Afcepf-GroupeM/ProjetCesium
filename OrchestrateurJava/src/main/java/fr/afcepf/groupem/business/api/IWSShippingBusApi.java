package fr.afcepf.groupem.business.api;

import fr.afcepf.groupem.entities.ShippingRequest;
import fr.afcepf.groupem.entities.ShippingResponse;

public interface IWSShippingBusApi {
	
	ShippingResponse requestShipping(ShippingRequest shippingRequest);

}
