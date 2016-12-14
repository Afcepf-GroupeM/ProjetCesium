package fr.afcepf.al29.groupem.dao.api;

import fr.afcepf.al29.groupem.entities.Carrier;

public interface CarrierDaoApi {

	Carrier createCarrier(Carrier carrier);
	Carrier getCarrierById(int carrierId);
	Carrier updateCarrier(Carrier carrier);
	boolean deleteCarrier(int carrierId);
}
