package fr.afcepf.groupem.business.api;

import fr.afcepf.groupem.entities.Transporteur;

public interface ITransporteurBus {
	
	Transporteur chooseTransporteurByDelaiMax(int delai);

}
