package fr.afcepf.groupem.business.api;

import fr.afcepf.groupem.entities.Transporteur;

public interface ITranspoteurBus {
	
	Transporteur chooseTransporteurByDelaiMax(int delai);

}
